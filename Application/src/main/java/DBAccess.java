// imports

import org.neo4j.driver.v1.*;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is used to connect the database neo4j w/ the application Barathon.
 */
public final class DBAccess {

    /**
     * Private constructor.
     */
    private DBAccess() {
    }

    /**
     * The factor to change the degrees in kilometers.
     */
    private static final Double FACTOR = 111.03;

    /**
     * ip address.
     */
    private static String ip = "81.244.81.95:7687";
    /**
     * Login.
     */
    private static String login = "neo4j";
    /**
     * Password.
     */
    private static String password = "mesa4!";

    /**
     * Method to connect to the database.
     *
     * @return Driver object.
     */
    public static Driver connect() {
        return GraphDatabase.driver(String.format("bolt://%s", ip), AuthTokens.basic(login, password));
    }

    /**
     * Method to create the unique constraints in the database.
     */
    public static void createUniqueConstraints() {
    /*
    This function creates the unique constraints on the following nodes:
    - Place: id;
    - User: pseudo;
    - Caracteristic: name.
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("CREATE CONSTRAINT ON (place:Place) ASSERT place.id IS UNIQUE");
            System.out.println("Unique id constraint creation: OK");
        } catch (Exception e) {
            System.out.println("An error occured during the unique id constraint creation!");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("CREATE CONSTRAINT ON (user:User) ASSERT user.pseudo IS UNIQUE");
            System.out.println("Unique pseudo constraint creation: OK");
        } catch (Exception e) {
            System.out.println("An error occurred during the unique pseudo constraint creation!");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("CREATE CONSTRAINT ON (carac:Caracteristic) ASSERT carac.name IS UNIQUE");
            System.out.println("Unique name constraint creation: OK");
        } catch (Exception e) {
            System.out.println("An error occured during the unique name constraint creation!");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to create a place in the database.
     *
     * @param place a place to create.
     */
    public static void createPlace(final Place place) {
    /*
    This function takes a parameter of type Place and creates a node of this type in the graph.
    The parameters are:
    - id (unique);
    - name;
    - position (longitude, latitude).
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("CREATE (:Place {id: %d, name: '%s', longitude: %s, latitude: %s})", place.getId(), place.getName(), place.getAddress().getPosition().getLongitude(), place.getAddress().getPosition().getLatitude()));
            System.out.println(String.format("Place %d creation: OK", place.getId()));
        } catch (Exception e) {
            System.out.println("An error occurred during the place creation !");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to match a place in the database.
     *
     * @param place a place to match.
     */
    public static void matchPlace(final Place place) {
    /*
    This function takes a parameter of type Place and searches a node of this type in the graph.
    If no node is found, it will be created.
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d}) RETURN p.id AS id", place.getId()));
            if (rs.list().isEmpty()) {
                createPlace(place);
            } else {
                System.out.println(String.format("Place %d matched: OK", place.getId()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the place matching !");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to find the places in the database.
     *
     * @return a list of places.
     */
    public static ArrayList<Place> findPlaces() {
    /*
    This function searches all places in the graph.
    */

        ArrayList<Place> places = new ArrayList<Place>();
        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("MATCH (p:Place) RETURN p.id AS id");
            if (!rs.list().isEmpty()) {
                // To find the places in the JSON
                places = JSONAccess.readPlacesJSON("./../data/places.json");
            } else {
                System.out.println("There is no place in the database !");
            }
        } catch (Exception e) {
            System.out.println("An error occured during the places searching !");
            System.out.print(e);
        }
        driver.close();
        return places;
    }

    /**
     * Method to create an user in the database.
     *
     * @param user an user to create.
     */
    public static void createUser(final User user) {
    /*
    This function takes a parameter of type User and creates a node of this type in the graph.
    The parameters are:
    - pseudo (unique);
    - position (longitude, latitude).
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("CREATE (:User {pseudo: '%s', longitude: %s, latitude: %s})", user.getPseudo(), user.getPosition().getLongitude(), user.getPosition().getLatitude()));
            System.out.println(String.format("User %s creation: OK", user.getPseudo()));
            DBAccess.matchUser(user);
            DBAccess.createU2CRelationship(user, user.getPreferences().getCaracteristics().get(0));
            ArrayList<Place> places = findPlaces();
            for (Place place: places) {
                DBAccess.createU2PRelationship(user, place);
            }
        } catch (Exception e) {
            System.out.println("An error occured during the user creation !");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to match an user in the database.
     *
     * @param user an user to match.
     */
    public static void matchUser(final User user) {
    /*
    This function takes a parameter of type User and searches a node of this type in the graph.
    If no node is found, it will be created.
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}) RETURN u.pseudo AS pseudo", user.getPseudo()));
            if (rs.list().isEmpty()) {
                createUser(user);
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}) SET u.longitude = %s, u.latitude = %s", user.getPseudo(), user.getPosition().getLongitude(), user.getPosition().getLatitude()));
                System.out.println(String.format("User's position of %s set: OK", user.getPseudo()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the user matching !");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to create the caracteristics in the database.
     */
    public static void createCaracteristics() {
    /*
    This function creates one node for each caracteristics (cheap, music, famousPlace, food, vegetarian, halal, vegan, alcohol).
    */

        List<String> fields = new ArrayList<String>();
        fields.add("cheap");
        fields.add("music");
        fields.add("famousPlace");
        fields.add("food");
        fields.add("vegetarian");
        fields.add("halal");
        fields.add("vegan");
        fields.add("alcohol");

        for (String field : fields) {
            Driver driver = connect();
            try (Session session = driver.session()) {
                StatementResult rs = session.run(String.format("MATCH (c:Caracteristic {name: '%s'}) Return c.name AS name", field));
                if (rs.list().isEmpty()) {
                    rs = session.run(String.format("CREATE (:Caracteristic {name: '%s'})", field));
                    System.out.println(String.format("Caracteristic %s created: OK", field));
                }
            } catch (Exception e) {
                System.out.println("An error occurred during the caracteristics creation !");
                System.out.println(e);
            }
            driver.close();
        }
    }

    /**
     * Method to create relationship between a place and the caracteristics.
     *
     * @param place a place to link.
     * @param carac caracteristics to link.
     */
    public static void createP2CRelationship(final Place place, final Caracteristics carac) {
    /*
    This function creates a relationship between a place and the caracteristics.
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "cheap"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status = '%b'", place.getId(), "cheap", carac.getCheap()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status = '%b'", place.getId(), "cheap", carac.getCheap()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-cheap relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "music"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "music", carac.getMusic()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "music", carac.getMusic()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-music relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "famousPlace"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "famousPlace", carac.getFamousPlace()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "famousPlace", carac.getFamousPlace()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-famousPlace relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "food"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "food", carac.getFood()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "food", carac.getFood()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-food relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "vegetarian"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "vegetarian", carac.getVegetarian()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "vegetarian", carac.getVegetarian()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-vegetarian relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "halal"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "halal", carac.getHalal()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "halal", carac.getHalal()));
            }
        } catch (Exception e) {
            System.out.println("An error occured during the Place-halal relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "vegan"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "vegan", carac.getVegan()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "vegan", carac.getVegan()));
            }
        } catch (Exception e) {
            System.out.println("An error occured during the Place-vegan relationship creation !");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", place.getId(), "alcohol"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p:Place {id: %d}), (c:Caracteristic {name: '%s'}) CREATE (p) -[r:FOLLOWS]-> (c) SET r.status='%b'", place.getId(), "alcohol", carac.getAlcohol()));
            } else {
                rs = session.run(String.format("MATCH (p:Place {id: %d})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", place.getId(), "alcohol", carac.getAlcohol()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-alcohol relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to create relationship between an user and the caracteristics.
     *
     * @param user  an user to link.
     * @param carac caracteristics to link.
     */
    public static void createU2CRelationship(final User user, final Caracteristics carac) {
    /*
    This function creates a relationship between a place and the caracteristics.
    */

        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "cheap"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "cheap", carac.getCheap()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "cheap", carac.getCheap()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-cheap relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "music"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "music", carac.getMusic()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "music", carac.getMusic()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-music relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "famousPlace"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "famousPlace", carac.getFamousPlace()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "famousPlace", carac.getFamousPlace()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-famousPlace relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "food"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "food", carac.getFood()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "food", carac.getFood()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-food relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "vegetarian"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "vegetarian", carac.getVegetarian()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "vegetarian", carac.getVegetarian()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-vegetarian relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "halal"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "halal", carac.getHalal()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "halal", carac.getHalal()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-halal relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "vegan"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "vegan", carac.getVegan()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "vegan", carac.getVegan()));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-vegan relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();

        driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) RETURN r", user.getPseudo(), "alcohol"));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (c:Caracteristic {name: '%s'}) CREATE (u) -[r:WANTS]-> (c) SET r.status='%b'", user.getPseudo(), "alcohol", carac.getAlcohol()));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(c:Caracteristic {name: '%s'}) SET r.status='%b'", user.getPseudo(), "alcohol", carac.getAlcohol()));
            }
        } catch (Exception e) {
            System.out.println("An error occured during the User-alcohol relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to create relationship between two places.
     *
     * @param place1 a place to link.
     * @param place2 a place to link.
     */
    public static void createP2PRelationship(final Place place1, final Place place2) {
    /*
    This function creates a relationship AWAY between two places. This relationship contains one property, the distance.
    */

        double distance = FACTOR * Math.sqrt(Math.pow(place1.getAddress().getPosition().getLongitude() - place2.getAddress().getPosition().getLongitude(), 2) + Math.pow(place1.getAddress().getPosition().getLatitude() - place2.getAddress().getPosition().getLatitude(), 2));
        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (p1:Place {id: %d})-[r]-(p2:Place {id: %d}) RETURN r", place1.getId(), place2.getId()));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (p1:Place {id: %d}), (p2:Place {id: %d}) CREATE (p1) -[r:AWAY]-> (p2) SET r.distance=%s", place1.getId(), place2.getId(), distance));
            } else {
                rs = session.run(String.format("MATCH (p1:Place {id: %d})-[r]-(p2:Place {id: %d}) SET r.distance=%s", place1.getId(), place2.getId(), distance));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Place-Place relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to create relationship between an user and a place.
     *
     * @param user  an user to link.
     * @param place a place to link.
     */
    public static void createU2PRelationship(final User user, final Place place) {
    /*
    This function creates a relationship AWAY between two places. This relationship contains one property, the distance.
    */

        double distance = FACTOR * Math.sqrt(Math.pow(user.getPosition().getLongitude() - place.getAddress().getPosition().getLongitude(), 2) + Math.pow(user.getPosition().getLatitude() - place.getAddress().getPosition().getLatitude(), 2));
        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(p:Place {id: %d}) RETURN r", user.getPseudo(), place.getId()));
            if (rs.list().isEmpty()) {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'}), (p:Place {id: %d}) CREATE (u) -[r:AWAY]-> (p) SET r.distance=%s", user.getPseudo(), place.getId(), distance));
            } else {
                rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r]-(p:Place {id: %d}) SET r.distance=%s", user.getPseudo(), place.getId(), distance));
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the User-Place relationship creation ! \n");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to drop the database.
     */
    public static void dropDatabase() {
        Driver driver = connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("MATCH (n) DETACH DELETE n");
            System.out.println("Database dropped: OK \n");
        } catch (Exception e) {
            System.out.println("An error occurred during the database erasing ! \n");
            System.out.print(e);
        }
        driver.close();
    }

    /**
     * Method to get an array list of x bars in a radius y of the user (complex).
     *
     * @param user object user.
     * @param x    size of list.
     * @return an array list of places.
     */
    public static ArrayList<Place> getNearbyPlaces(final User user, final int x) {
        int y = user.getPreferences().getRadius();
        ArrayList<Place> places = new ArrayList<Place>();
        Driver driver = DBAccess.connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r:AWAY]-(p:Place) WHERE r.distance <= %d RETURN p.id AS id, r.distance AS distance ORDER BY r.distance LIMIT %d", user.getPseudo(), y, x));
            List<Record> ids = rs.list();
            if (!ids.isEmpty()) {
                ArrayList<Place> dbPlaces = JSONAccess.readPlacesJSON("../data/places.json");
                for (Record i : ids) {
                    // To find the places in the JSON with record.get("id")
                    Place place = Place.findPlace(dbPlaces, i.get("id").asInt());
                    places.add(place);
                }
            } else {
                System.out.println("There is no place where it is possible to eat in the database!\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the places where it is possible to eat searching !");
        }
        driver.close();
        // To print for the test
        System.out.println(places + "\n");
        return places;
    }
}
