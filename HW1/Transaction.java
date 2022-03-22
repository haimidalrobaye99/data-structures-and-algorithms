/*

Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #1
CSE 214
Recitation R10, TA: Daniel Calabria

*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Transaction implements Cloneable {

    private String date; // date of transaction
    private double amount; // amount within transaction
    private String description; // the description of the transaction



    /*
     Returns transaction description
     */
    public String getDescription() {
        return description;
    }


    /*
    Returns amount of the transaction
     */
    public double getAmount() {
        return amount;
    }


    /*
    Returns date of the transaction
     */
    public String getDate() {
        return date;
    }



    /*
    Constructor for a new transaction with three parameters/features of the
    transaction to input, which are the date, amount, and description of the
    transaction.
     */
    public Transaction(String date,double amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }




    /*
    default transaction constructor with no parameters.
     */
    public Transaction(){

    }



    /*
    Clones a transaction in the general ledger.
     */
    public Object clone() throws CloneNotSupportedException{
        Transaction t = (Transaction) super.clone();
        return t;
    }



    /*
    Compares two transactions
     */
    public boolean equals(Object object){
        Transaction t = (Transaction)object;
        if(this.getDescription().equals(t.getDescription())
            &&this.getAmount() == t.getAmount()
                &&this.getDate().equals(t.getDate()))
        {
            return true;
        }
        return false;
    }



    /*
    toString method that produces the transaction with it's details
    in the general ledger.
     */
    public String toString(){
        String str = "";
        if(amount > 0){
            str = getDate() + " \t" + getAmount() + "\t\t\t\t" + getDescription() ;
        }
        else{
            str = getDate() + "\t\t\t\t" + Math.abs(getAmount()) + "\t" + getDescription() ;
        }
        return str;
    }

}
