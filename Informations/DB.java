// imports
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Record;
import java.util.List;
import java.util.ArrayList;

/**
 * Class which contains the DB methods.
 */
public final class DB {
    /**
    * Private constructor.
    **/
    private DB() {
    }

    /**
    * Method to create an example of the database.
    *
    */
    public static void createDB() {
        // Create the constraints of the graph
        DBAccess.createUniqueConstraints();

        // Create the caracteristics in the graph
        DBAccess.createCaracteristics();

        // Create the sample of drinks
        Drink coca = new Drink("Coca regular", 200, 0.0, true);
        Drink iceTea = new Drink("Ice tea green", 220, 0.0, false);
        Drink duvel = new Drink("Duvel", 420, 8.5, true);

        // Create the sample of food
        Food salad = new Food("Quinoa salad", 800, true, true, true);
        Food wafel = new Food("Brussels Wafel", 400, false, false, true);

        // Create the menus
        Menu menu1 = new Menu();
        menu1.addDrink(coca);
        menu1.addDrink(iceTea);
        Menu menu2 = new Menu();
        menu2.addDrink(iceTea);
        menu2.addDrink(duvel);
        menu2.addFood(wafel);
        Menu menu3 = new Menu();
        menu3.addDrink(coca);
        menu3.addDrink(iceTea);
        menu3.addDrink(duvel);
        menu3.addFood(salad);
        menu3.addFood(wafel);

        // Create the caracteristics
        Caracteristics carac1 = new Caracteristics();
        carac1.set(true, false, false, false, false, false, false, false);
        Caracteristics carac2 = new Caracteristics();
        carac2.set(true, true, true, true, true, true, false, true);
        Caracteristics carac3 = new Caracteristics();
        carac3.set(false, true, true, true, true, true, true, true);

        // Create the preferences
        Preferences pref1 = new Preferences();
        pref1.addCaracteristics(carac1);
        pref1.addCaracteristics(carac2);
        pref1.setRadius(5000);
        Preferences pref2 = new Preferences();
        pref2.addCaracteristics(carac2);
        pref2.addCaracteristics(carac3);
        pref2.setTrip(true);

        //Create the positions
        Position posp1 = new Position(4.3528, 50.8468);
        Position posp2 = new Position(4.3542, 50.8468);
        Position posp3 = new Position(4.3528, 50.8405);
        Position posp4 = new Position(4.328, 50.468);
        Position posp5 = new Position(4.28, 50.8);
        Position posp6 = new Position(4.8, 49.9);
        Position posp7 = new Position(4.29, 50.8468);
        Position posp8 = new Position(4.27, 50.82);
        Position posp9 = new Position(4.362, 50.683);
        Position posp10 = new Position(4.38, 50.88);

        Position posu1 = new Position(4.3527, 50.8465);
        Position posu2 = new Position(4.3526, 50.8467);

        // Create the addresses
        Address addp1 = new Address("Chaussée de Wavre", "1789", posp1);
        Address addp2 = new Address("Chaussée de Louvain", "178B", posp2);
        Address addp3 = new Address("Rue des acacias", "26", posp3);
        Address addp4 = new Address("Chaussée de Wavre", "34", posp4);
        Address addp5 = new Address("Place Tian'Men", "14", posp5);
        Address addp6 = new Address("Clos des sapins", "45", posp6);
        Address addp7 = new Address("Avenue Thermidor", "17", posp7);
        Address addp8 = new Address("Grand place de Bruxelles", "178", posp8);
        Address addp9 = new Address("Avenue Rabelais", "19", posp9);
        Address addp10 = new Address("Chaussée de Waterloo", "265", posp10);

        // Create some places in the graph
        ArrayList<Place> places = new ArrayList<Place>();
        Place p1 = new Place(1, "La soeur du patron", addp1, menu1, carac1);
        Place p2 = new Place(2, "Café de Paris", addp2, menu2, carac2);
        Place p3 = new Place(3, "Ballodrome", addp3, menu3, carac3);
        Place p4 = new Place(4, "Brasse-temps", addp4, menu2, carac2);
        Place p5 = new Place(5, "Beer Bar", addp5, menu1, carac1);
        Place p6 = new Place(6, "Bucket food", addp6, menu2, carac2);
        Place p7 = new Place(7, "My tannour", addp7, menu3, carac3);
        Place p8 = new Place(8, "Tutti pizza", addp8, menu2, carac2);
        Place p9 = new Place(9, "El greco", addp9, menu1, carac1);
        Place p10 = new Place(10, "Chez Fernand", addp10, menu3, carac3);
        places.add(p1);
        places.add(p2);
        places.add(p3);
        places.add(p4);
        places.add(p5);
        places.add(p6);
        places.add(p7);
        places.add(p8);
        places.add(p9);
        places.add(p10);

        JSONAccess.writePlacesJSON("./../data/places.json", places);

        for (Place place : places) {
            DBAccess.matchPlace(place);
            DBAccess.createP2CRelationship(place, place.getCaracteristics());
            ArrayList<Place> dbPlaces = DBAccess.findPlaces();
            for (Place dbPlace : dbPlaces) {
                if (place.getId() != dbPlace.getId()) {
                    DBAccess.createP2PRelationship(place, dbPlace);
                }
            }
        }

        // Create some users in the graph
        ArrayList<User> users = new ArrayList<User>();
        User u1 = new User("user1", posu1, pref1);
        User u2 = new User("user2", posu2, pref2);
        users.add(u1);
        users.add(u2);

        JSONAccess.writeUsersJSON("./../data/users.json", users);

        for (User user : users) {
            DBAccess.matchUser(user);
            DBAccess.createU2CRelationship(user, user.getPreferences().getCaracteristics().get(0));
            for (Place place : places) {
                DBAccess.createU2PRelationship(user, place);
            }
        }
    }

    /**
    * Method to get the bars where it is possible to eat (easy query).
    *
    * @return an array list of places.
    */
    public static ArrayList<Place> barsToEat() {
        ArrayList<Place> places = new ArrayList<Place>();
        Driver driver = DBAccess.connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run("MATCH (p:Place)-[r:FOLLOWS]-(c:Caracteristic {name: 'food'}) WHERE r.status='true' RETURN p.id AS id");
            List<Record> ids = rs.list();
            if (!ids.isEmpty()) {
                ArrayList<Place> dbPlaces = JSONAccess.readPlacesJSON("./../data/places.json");
                for (Record i : ids) {
                    // To find the places in the JSON with record.get("id")
                    Place place = Place.findPlace(dbPlaces, i.get("id").asInt());
                    places.add(place);
                }
            } else {
                System.out.println("There is no place where it is possible to eat in the database !");
            }
        } catch (Exception e) {
            System.out.println("An error occured during the places where it is possible to eat searching !");
        }
        driver.close();
        // To print for the test
        System.out.println(places);
        return places;
    }

    /**
    * Method to get the nearest place of a user(medium query).
    *
    * @param user object user.
    * @return the nearest place.
    */
    public static Place nearestBar(final User user) {
        Driver driver = DBAccess.connect();
        Place place = new Place(0, "Le bouche trou", new Address("Test", "Test", new Position(0.0, 20.0)), new Menu(), new Caracteristics());
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r:AWAY]-(p:Place) RETURN min(r.distance) AS distance", user.getPseudo()));
            List<Record> distances = rs.list();
            if (!distances.isEmpty()) {
                for (Record d : distances) {
                    double distance = d.get("distance").asDouble();
                    rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r:AWAY]-(p:Place) WHERE r.distance = %s RETURN p.id AS id", user.getPseudo(), distance));
                    List<Record> ids = rs.list();
                    ArrayList<Place> places = JSONAccess.readPlacesJSON("./../data/places.json");
                    for (Record i : ids) {
                        // To find the places in the JSON with record.get("id")
                        place = Place.findPlace(places, i.get("id").asInt());
                    }
                }
            } else {
                System.out.println("Impossible to find the nearest bar.\nNo relationship AWAY in this graph.");
            }
        } catch (Exception e) {
            System.out.println("An error occured during the search of the nearest bar !");
            System.out.println(e.getMessage());
        }
        driver.close();
        // To print for the test
        System.out.println(place.getName());
        return place;
    }

    /**
    * Method to get an array list of x bars in a radius y of the user (complex).
    *
    * @param user object user.
    * @param x size of list.
    * @param y radius around the user.
    * @return an array list of places.
    */
    public static ArrayList<Place> nearbyBars(final User user, final int x, final int y) {
        ArrayList<Place> places = new ArrayList<Place>();
        Driver driver = DBAccess.connect();
        try (Session session = driver.session()) {
            StatementResult rs = session.run(String.format("MATCH (u:User {pseudo: '%s'})-[r:AWAY]-(p:Place) WHERE r.distance <= %d RETURN p.id AS id, r.distance AS distance ORDER BY r.distance LIMIT %d", user.getPseudo(), y, x));
            List<Record> ids = rs.list();
            if (!ids.isEmpty()) {
                ArrayList<Place> dbPlaces = JSONAccess.readPlacesJSON("./../data/places.json");
                for (Record i : ids) {
                    // To find the places in the JSON with record.get("id")
                    Place place = Place.findPlace(dbPlaces, i.get("id").asInt());
                    places.add(place);
                }
            } else {
                System.out.println("There is no place where it is possible to eat in the database !");
            }
        } catch (Exception e) {
            System.out.println("An error occured during the places where it is possible to eat searching !");
        }
        driver.close();
        // To print for the test
        System.out.println(places);
        return places;
    }
}
