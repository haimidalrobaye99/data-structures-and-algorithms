
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #5
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.util.*;


/*
The Adventure Designer class consists of the main method class that will allow
the user to manipulate the adventure tree, and a playGame() method that will
allow the user to play the game they've created and traverse through each node.
 */
public class AdventureDesigner {

    private static SceneTree tree; //static scene tree to be accessed by the main method and the playGame() method
    public static void main(String []args) throws FullSceneException, NoSuchNodeException { //main method

            Scanner scanner = new Scanner(System.in); //allows input for the user
            System.out.println("Creating a story . . . ");
            System.out.println(" ");
            System.out.println("Please enter a title: ");
            String title = scanner.nextLine(); //title for root
            System.out.println("Please enter a scene: ");
            String scene = scanner.nextLine(); // description for the root scene
            tree = new SceneTree(title, scene);  // new tree being created with a root with the information entered by the user
            System.out.println("Scene # " + SceneNode.getNumScenes() + " added.");
            while (true) { // loop to keep the menu producing after each input
                try{
                System.out.println("A) Add Scene - \n" +
                        "R) Remove Scene\n" +
                        "S) Show Current Scene\n" +
                        "P) Print Adventure Tree\n" +
                        "B) Go Back A Scene\n" +
                        "F) Go Forward A Scene\n" +
                        "G) Play Game\n" +
                        "N) Print Path To Cursor\n" +
                        "M) Move Scene\n" +
                        "Q) Quit - ");


                String input = scanner.nextLine(); // input for user to select from menu


                switch (input.toUpperCase()) { //switch statement that will take an action based on the user's selected input

                    case "A":

                        System.out.println("Please enter a title: ");
                        String t = scanner.nextLine();
                        System.out.println("Please enter a scene: ");
                        String s = scanner.nextLine();
                        tree.addNewNode(t, s);
                        System.out.println("Scene #" + SceneNode.getNumScenes() + " added.");
                        //add scene


                        break;


                    case "R":

                        System.out.println("Please enter an option: ");
                        String r = scanner.nextLine();
                        tree.removeScene(r);
                        //remove a scene

                        break;


                    case "S":

                        tree.getCursor().displayFullScene();

                        //show current scene


                        break;


                    case "P":
                        tree.toString();
                        //print adventure tree

                        break;


                    case "B":
                        tree.moveCursorBackwards();
                        System.out.println("Successfully moved back to " + tree.getCursor().getTitle());
                        //go back a scene


                        break;


                    case "F":
                        System.out.println("Which option do you wish to go to: ");
                        String o = scanner.nextLine();
                        tree.moveCursorForward(o);
                        System.out.println("Successfully moved to " + tree.getCursor().getTitle());
                        //go forward a scene

                        break;


                    case "G":

                        playGame();


                        break;


                    case "N":

                        tree.getPathFromRoot();

                        //Print Path To Cursor

                        break;

                    case "M":

                        System.out.println("Move current scene to: ");
                        int num = scanner.nextInt();
                        tree.moveScene(num);
                        System.out.println("Successfully moved scene");

                        //Move scene

                        break;


                    case "Q":

                        System.out.println("Program terminating normally");
                        System.exit(0);


                        break;


                    default:
                        //in case user selects a different option from the menu
                        System.out.println("Invalid input, try again");

                        break;

                }





            }
                //no nodes exist
                catch(NoSuchNodeException n){
                    System.out.println("Option does not exist");
                }
                catch(FullSceneException f){
                    System.out.println("Error, scene is full");
                }
                catch(InputMismatchException i){
                    System.out.println("Invalid input, try again");
                }
                catch(NullPointerException n){
                    System.out.println("does not exist");
                    n.printStackTrace();
                }

        }

    }

    //method to give the user the option to play the game that
    //they've just made, produces the description of the scene with it's options/children nodes
    public static void playGame(){
        try {
            Scanner scanner = new Scanner(System.in);
            tree.moveToRoot();
            System.out.println("Now beginning game. . .");
            do{
                tree.getCursor().displayScene();
                System.out.println("Select an option: ");
                String answer = scanner.nextLine();
                tree.moveCursorForward(answer);
            }while(!tree.getCursor().isEnding());
            System.out.println("The end");
            System.out.println("Returning to creation mode . . .");

        }catch(InputMismatchException | NoSuchNodeException i){
            System.out.println("Invalid input, try again");
        }



    }


}
