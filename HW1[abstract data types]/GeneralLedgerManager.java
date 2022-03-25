/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #1
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.util.*;
import java.lang.*;

public class GeneralLedgerManager {

    public static void main(String []args) throws CloneNotSupportedException {

        GeneralLedger generalLedger = new GeneralLedger(); //this is a general ledger being created

        GeneralLedger backup = new GeneralLedger(); //this is a backup general ledger being created


        //loop to continuously run the menu after each successful selection
        while(true) {
            System.out.println(
                      "(A) - Add Transaction " +
                    "\n(G) - Get Transaction " +
                    "\n(R) - Remove Transaction " +
                    "\n(P) - Prints Transaction " +
                    "\n(F) - Filter by Transaction Date " +
                    "\n(L) - Look for Transaction" +
                    "\n(S) - Size " +
                    "\n(B) - Backup " +
                    "\n(PB) - Print Transactions in Backup " +
                    "\n(RB) - Revert to Backup " +
                    "\n(CB) - Compare Backup with Current " +
                    "\n(PF) - Print Financial Information " +
                    "\n(Q)  - Quit " +
                    "\n\nEnter a selection: ");

                    Scanner scanner = new Scanner(System.in); //lets user input selection
                    String input=scanner.nextLine();



                    //switch statement for each scenario that a user selects a certain option
                    switch(input.toUpperCase()){

                        case "A": System.out.println("Enter date: ");
                                  String newTransaction = scanner.nextLine();
                                  System.out.println("Enter Amount ($): ");
                                  Double newAmount = scanner.nextDouble();
                                  System.out.println("Enter Description: ");
                                  scanner.nextLine();
                                  String newDescription = scanner.nextLine();

                                  generalLedger.addTransaction(new Transaction(newTransaction,newAmount,newDescription));
                                  System.out.println("Transaction has been added");
                            break;


                        case "G":
                            System.out.println("Enter position: ");
                            int position = scanner.nextInt();
                            System.out.println(generalLedger.getTransaction(position).toString());
                            break;


                        case "R":
                            System.out.println("Enter position: ");
                            int pos = scanner.nextInt() - 1;
                            generalLedger.removeTransaction(pos-1);
                            System.out.println("Transaction " + generalLedger.getLedger()[pos-1].getDate() + " " +
                                    generalLedger.getLedger()[pos-1].getAmount() + " " +
                                    generalLedger.getLedger()[pos-1].getDescription() + " has been removed from the general ledger");
                            break;

                        case "P":
                            generalLedger.toString();
                            break;

                        case "F":
                            System.out.println("Enter Date: ");
                            String date= scanner.nextLine();
                            generalLedger.filter(generalLedger,date);
                            break;

                        case "L":
                            System.out.println("Enter Date: ");
                            String a = scanner.nextLine();
                            System.out.println("Enter Amount ($): ");
                            int i = scanner.nextInt();
                            System.out.println("Enter description: ");
                            scanner.nextLine();
                            String d = scanner.nextLine();
                            generalLedger.exists(a,i,d);
                            break;

                        case "S":
                            System.out.println("There are " + generalLedger.size() +
                                    " transactions currently in the general ledger.");
                            break;


                        case "B":
                            try {
                                backup = (GeneralLedger) generalLedger.clone();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Created a backup of the current general ledger");
                            break;


                        case "PB":
                            backup.toString();
                            break;


                        case "RB":
                            generalLedger = backup;
                            break;


                        case "CB":
                            if(generalLedger.equals(backup)){
                                System.out.println("The current general ledger IS the same as the backup copy");
                            }
                            else{
                                System.out.println("The current general ledger is NOT the same as the backup copy");
                            }
                            break;


                        case "PF":
                            System.out.println("Financial Data for Jack's Account");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("Assets: $" + generalLedger.getTotalDebitAmount());
                            System.out.println("Liabilities: $" + Math.abs(generalLedger.getTotalCreditAmount()));
                            System.out.println("Net Worth: $" + (generalLedger.getTotalDebitAmount() -
                                    Math.abs(generalLedger.getTotalCreditAmount())));
                            break;


                        case "Q":
                                System.out.println("Program terminating successfully . . . ");
                                System.exit(0);
                                break;




                    }

        }
    }

}
