import java.io.Console;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

/**
 *  Class to have an inteface between the application and the user.
 */
public final class App {

    /**
    * Private constructor.
    **/
    private App() {
    }
    /**
     *  Method main.
     *
     * @param args Args of main function
     */
    public static void main(final String[] args) {

        String intro = "\n======================="
                + "\n| Welcome to Barathon |"
                + "\n======================= \n";
        System.out.println(intro);
        CheapInterface cheapInterface = new CheapInterface();
        while(true){
            cheapInterface.askTask();
        }
    }
}
