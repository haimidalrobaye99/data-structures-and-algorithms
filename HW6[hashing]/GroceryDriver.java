
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #6
CSE 214
Recitation R10, TA: Daniel Calabria
 */


import java.io.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





/*
The GroceryDriver class runs the program containing the main method and allowing the user to enter and
input the txt files. The class contains a menu of options for the user to manipulate a hashtable with extracted
information from txt files.
 */
public class GroceryDriver{

    static HashedGrocery table; // new HashedGrocery being created for the user to manipulate using the information being extracted from the txt files

    public static void main(String []args) throws NullPointerException,ClassNotFoundException,InputMismatchException,IOException,ParseException {
        try {
            File file = new File("Grocery.obj"); //new file

            Scanner scanner = new Scanner(System.in); //opens up system for user to input information
            if (file.exists()) { //if file already exists
                FileInputStream f = new FileInputStream("Grocery.obj");
                ObjectInputStream in = new ObjectInputStream(f);
                table = (HashedGrocery) in.readObject();
                System.out.println("Loading previous grocery list . . .");

            } else {
                System.out.println("Grocery.obj does not exist. Creating new HashedGrocery object...");
                try {
                    table = new HashedGrocery();
                } catch (Exception e) {
                    System.out.println("error");
                }

            }

            while (true) { //runs continuous loop until user quits the program
                System.out.println("Menu: ");
                System.out.println("(L) Load item catalog    \n" +
                        "(A) Add items              \n" +
                        "(B) Process Sales      \n" +
                        "(C) Display all items\n" +
                        "(N) Move to next business day  \n" +
                        "(Q) Quit ");

                System.out.println("Enter option: "); //
                String input = scanner.nextLine(); // allows user to make an option from the menu given aboce


                //certain action is taken depending on user input
                switch (input.toUpperCase()) {


                    case "L":
                        //load item catalog
                        System.out.println("Enter file to load: ");
                        String name = scanner.nextLine();
                        table.addItemCatalog(name);
                        break;


                    case "A":
                        //add items

                        System.out.println("Enter item code: ");
                        String itemCode = scanner.nextLine();
                        System.out.println("Enter item name: ");
                        String itemName = scanner.nextLine();
                        System.out.println("Enter quantity in store: ");
                        int qtyInStore = scanner.nextInt();
                        System.out.println("Enter Average sales per day: ");
                        int averageSalesPerDay = scanner.nextInt();
                        System.out.println("Enter price: ");
                        double price = scanner.nextDouble();

                        table.addItem(itemCode, itemName, qtyInStore, averageSalesPerDay, price);

                        System.out.println(itemCode + ": " + itemName + "added to inventory");

                        break;

                    case "B":
                        //Process sales
                        System.out.println("enter file name: ");
                        String f = scanner.nextLine();
                        table.processSales(f);

                        break;

                    case "C":
                        //display all items

                        table.toString();

                        break;


                    case "N":
                        //move to next business day

                        table.nextBusinessDay();
                        break;


                    case "Q":
                        System.out.println(" ");

                        System.out.println("Program terminating normally. . .");
                        System.exit(0);
                        break;


                    default:
                        System.out.println("Invalid input, try again");
                        break;
                }
            }
        }
        catch(FileNotFoundException f){
            System.out.println("file does not exist");
        }
        catch(InputMismatchException i){
            System.out.println("Invalid input, try again");
        }
        catch(NullPointerException n){
            System.out.println("does not exist");
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
