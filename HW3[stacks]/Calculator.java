
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #3
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.util.*;


/*
The calculator class creates a history stack and contains the menu options for the
user to manipulate a calculator with equations depending on what the user selects.
 */
public class Calculator{
    public static void main(String [] args){
        HistoryStack historyStack = new HistoryStack(); //creates a new history stack
        HistoryStack undo = new HistoryStack(); //creates a history stack for the undone equations
        HistoryStack redo = new HistoryStack(); //creates a history stack for the redone equations
        Scanner scanner = new Scanner(System.in); //allows user to input for the menu

        while(true) { // loop to keep the menu reiterating after each successful selection

            System.out.println("Welcome to the calculator program: \n" +
                    "\n" +
                    "[A] Add new equation\n" +
                    "[F] Change equation from history\n" +
                    "[B] Print previous equation\n" +
                    "[P] Print full history\n" +
                    "[U] Undo\n" +
                    "[R] Redo\n" +
                    "[C] Clear history\n" +
                    "[Q] Quit\n\n" +

                    "Select an option: ");

            System.out.println();
            System.out.println();

            String input = scanner.nextLine();


            switch (input.toUpperCase()) {

                case "A":

                    System.out.println("Please enter an equation (in-fix notation): ");
                    String equation = scanner.nextLine();
                    Equation e = new Equation(equation);
                    historyStack.push(e);
                    if(e.isBalanced())
                        System.out.println("The equation is balanced and the answer is " + e.solve(equation));

                    break;



                case "F":

                    System.out.println("Which equation would you like to change? ");

                    System.out.println("Equation at position: ");

                    System.out.println("What would you like to do with the equation (Replace / remove / add)? ");

                    break;



                case "B": redo.toString();
                    break;



                case "P": System.out.print(historyStack.toString());
                    break;



                case "U":
                        historyStack.undo();
                        System.out.println(" previous equation has now been undone ");
                    break;



                case "R":
                    //historyStack.redo();
                    System.out.println("Redoing equation ");
                    break;



                case "C":   System.out.println("Resetting calculator");
                    break;



                case "Q":
                    System.out.println("Program terminating normally . . .");
                    System.exit(0);
                    break;


                default:
                    System.out.println("Enter a valid input: ");
            }
        }


        }
    }



