
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #1
CSE 214
Recitation R10, TA: Daniel Calabria

 */

import java.lang.*;

public class GeneralLedger implements Cloneable{

    public static final int MAX_TRANSACTIONS = 50; //maximum amount of transactions allowed in the general ledger.
    private Transaction[] ledger; //The array of transactions for the general ledger.
    private int size = 0; //size of general ledger being initiated to 0 until a transaction is inserted into the general ledger.
    private double totalDebitAmount; // variable that adds up all of the debit transactions in the general ledger.
    private double totalCreditAmount; // variable that adds up all of the credit transactions in the general ledger.




    /*
        Constructor for a general ledger that sets the array size to MAX_TRANSACTIONS,
        which is 50. No more than 50 transactions allowed in the general ledger.
     */
    public GeneralLedger(){
        this.ledger=new Transaction[MAX_TRANSACTIONS];
    }



    /*
        setter for the ledger array
     */
    public void setLedger(Transaction[] ledger) {
        this.ledger = ledger;
    }




    /*
        getter for the ledger array. Retrieves the transactions in the array.
     */
    public Transaction[] getLedger() {
        return ledger;
    }




    /*
        getter for the amount of transactions in the array. Returns
        the amount of transactions in the general ledger.
     */
    public int getSize() {
        return size;
    }




    /*
        setter for the ledger array size.
     */
    public void setSize(int size) {
        this.size = size;
    }





    /*
        getter for the total amount of all debit transactions in the general ledger.
     */
    public double getTotalDebitAmount() {
        return totalDebitAmount;
    }





    /*
        setter for the total amount of all debit transactions in the general ledger.
     */
    public void setTotalDebitAmount(double totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }




    /*
            getter for the total amount of all credit transactions in the general ledger.
         */
    public double getTotalCreditAmount() {
        return totalCreditAmount;
    }



    /*
            setter for the total amount of all credit transactions in the general ledger.
         */
    public void setTotalCreditAmount(double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }





    /*
        adds a transaction into the general ledger
     */
    public void addTransaction(Transaction newTransaction){
        if(size == MAX_TRANSACTIONS) return;
        ledger[size] = newTransaction;
        if(newTransaction.getAmount() < 0) setTotalCreditAmount(getTotalCreditAmount()+newTransaction.getAmount());
        else setTotalDebitAmount(getTotalDebitAmount()+newTransaction.getAmount());
        size++;

    }





    /*
    Removes a transaction from the general ledger
     */
    public void removeTransaction(int position){
        if(size == 0) return;
        if(ledger[position-1].getAmount() < 0) setTotalCreditAmount(getTotalCreditAmount()-ledger[position-1].getAmount());
        else {setTotalDebitAmount(getTotalDebitAmount()-ledger[position-1].getAmount());}
        for(int i = position-1; i < size; i++) ledger[i] = ledger[i + 1];
    }








    /*
        Returns a transaction in the general ledger. If the user enters "3".
        it will return the third transacrtion in the general ledger.
     */
    public Transaction getTransaction(int position){

        return ledger[position-1];

    }





    /*
    filters the general ledger by the date that the user inputs. This will return
    all of the transactions made on the same day.
     */
    public void filter(GeneralLedger generalLedger, String date){
        for(Transaction a : ledger){
            if(a.getDate().equals(date)){
                System.out.println(a.toString());
            }
        }

    }





    //returns size
    public int size(){
        return size;
    }





    /*
    Clones the entire general ledger. This will be used mainly to store
    and save a backup of the general ledger.
     */
    public Object clone() throws CloneNotSupportedException{
            GeneralLedger gl;
            gl = (GeneralLedger) super.clone();
        return gl;
    }








    /*
        Determines whether a transaction with certain details exists within the general ledger.
     */
    public boolean exists(String date, int amount, String description)
    throws IllegalArgumentException{
        for(Transaction t : this.ledger){
            if(t.getDate().equals(date) && t.getAmount() == amount
            && t.getDescription().equals(description)){
                System.out.println(t.toString());
            }
        }

        return false;
    }






    /*
    Prints out all of the transactions in the general ledger
     */
    public void printAllTransactions(){
        for(int i = 0; i<size; i++){
            System.out.println((i + 1) + "\t\t" + this.ledger[i]);
        }
    }





    /*
    toString method that returns the entire general ledger with the transactions
     */
    public String toString(){
        System.out.println("No.    Date          Debit    Credit   Description              ");
        System.out.println("---------------------------------------------------------------------------------------------------\n");
        printAllTransactions();
        return "\n\n";
    }



}