import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
//import org.json.*;
import org.apache.commons.io.FileUtils;

/**
* Class used to save objects in a JSON files and to extract objects from it.
*/
public final class JSONAccess {
    /**
    * Private constructor.
    **/
    private JSONAccess() {
    }

    /**
    * Instance of utility Gson.
    */
    private static Gson gson = new Gson();

    /**
    * This method is used to convert an object into a JSON string.
    *
    * @param obj This is the object to convert into JSON.
    * @param t This is the type of the object.
    * @return String This is the string containing the JSON formatted object.
    */
    public static String serialize(final Object obj, final Type t) {
        return JSONAccess.gson.toJson(obj, t);
    }

    /**
    * This method is used to parse an object from a JSON string.
    *
    * @param json This is the JSON string to be parsed.
    * @param t This is the type of the object.
    * @return Object This is the parsed object.
    */
    public static Object deserialize(final String json, final Type t) {
        return JSONAccess.gson.fromJson(json, t);
    }

    /**
    * Read a JSON file and return an object.
    *
    * @param path The path of the file to be read.
    * @param t This is the type of the object.
    * @return Object The object from the JSON file.
    */
    public static Object readJSON(final String path, final Type t) {
        try {
        String str = FileUtils.readFileToString(new File(path), "utf-8");
            return JSONAccess.deserialize(str, t);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
    * Read a list of Places from a JSON file.
    *
    * @param path The path of the fileto be read.
    * @return ArrayList<Place> List of places from the JSON file.
    */
    public static ArrayList<Place> readPlacesJSON(final String path) {
        Type t = new TypeToken<ArrayList<Place>>() { }.getType();
        try (FileReader file = new FileReader(path)) {
            return JSONAccess.gson.fromJson(file, t);
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Place>();
        }
    }

    /**
    * Read a list of Users from a JSON file.
    *
    * @param path The path of the fileto be read.
    * @return ArrayList<User> List of users from the JSON file.
    */
    public static ArrayList<User> readUsersJSON(final String path) {
        Type t = new TypeToken<ArrayList<User>>() { }.getType();
        try (FileReader file = new FileReader(path)) {
            return JSONAccess.gson.fromJson(file, t);
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<User>();
        }
    }

    /**
    * Write Places to a JSON file - can be used to write a List.
    *
    * @param path Path of the file to be written.
    * @param obj This is the object to convert into JSON.
    */
    public static void writePlacesJSON(final String path,
    final ArrayList<Place> obj) {
    Type t = new TypeToken<ArrayList<Place>>() { }.getType();
        //try-with-resources
        try (FileWriter file = new FileWriter(path)) {
            JSONAccess.gson.toJson(obj, t, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
    * Write Users to a JSON file - can be used to write a List.
    *
    * @param path Path of the file to be written.
    * @param obj This is the object to convert into JSON.
    */
    public static void writeUsersJSON(final String path,
    final ArrayList<User> obj) {
        Type t = new TypeToken<ArrayList<User>>() { }.getType();
        //try-with-resources
        try (FileWriter file = new FileWriter(path)) {
            JSONAccess.gson.toJson(obj, t, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
