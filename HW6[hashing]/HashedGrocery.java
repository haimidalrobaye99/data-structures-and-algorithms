
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #6
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.io.*;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/*
The HashedGrocery class contains the methods to manipulate the
hashtable and the business day variable that will be incremented after
each full day. Methods consist of processing a sale in a text file,
adding an item, extracting from the file and adding into the catalog
 */
public class HashedGrocery implements Serializable {


    private int businessDay = 1;    //business day for the inventory, initially set to 1
    private Hashtable<String, Item> hashTable;   //hashtable being created for the items and sales in each text file to be inserted into the table


    /*
        constructor for the hashtable that is initialized
        to a type of string and item for each element inserted into
        the table
     */
    public HashedGrocery() {

        hashTable = new Hashtable<String, Item>();

    }


    /*
        adds a new item into the hashtable using specified arguments for each item.
        if the hashtable doesn't contain the item, create the item and add it,
        or else throw an exception
     */
    public void addItem(String itemCode, String name, int qtyInStore, int averageSalesPerDay, double price) throws Exception {
        if (!hashTable.contains(itemCode)) {
            Item item = new Item(itemCode, name, qtyInStore, averageSalesPerDay, price);
            hashTable.put(itemCode, item);
        } else {
            throw new Exception("Error, unable to add because this item already exists");
        }
    }


    /*
        adds a new item into the catalog that is printed out using
        the HashedGrocery class's toString() method
     */
    public void addItemCatalog(String filename) throws ParseException, IOException {
        File file = new File(filename); //creates new file
        if (!hashTable.contains(file)) { //if the table contains the file
            String table[][] = textfileToJSON(filename);
            for (int i = 0; i < table.length; i++) {
                if (hashTable.contains(table[i][0])) {
                    Item temp = new Item();
                    for(int j = 0; j < 5; j++){
                        System.out.println(hashTable.put(table[i][j], temp));
                    }
                    System.out.println(hashTable.get(temp.getItemCode()) + ": " + hashTable.get(temp.getName()) + " has been placed in inventory");
                    //FIX THIS< STILL INCOMPLETE
                }
            }
        }
    }




    //retrieves the business day
    public int getBusinessDay() {
        return businessDay;
    }



    //sets the business day to a certain integer
    public void setBusinessDay(int businessDay) {
        this.businessDay = businessDay;
    }





    //returns and retrieves the hashTable
    public Hashtable<String, Item> getHashTable() {
        return hashTable;
    }





    //setter method for the hashTable, most likely won't be used
    public void setHashTable(Hashtable<String, Item> hashTable) {
        this.hashTable = hashTable;
    }



    /*
                updates the quantity of each item by a certain amount, that difference
                value will be set to the argument "adjustByQty"
             */
    public void updateItem(Item item, int adjustByQty){
        if(hashTable.contains(item)){
            hashTable.get(item.getItemCode()).setQtyInStore(hashTable.get(item.getItemCode()).getQtyInStore()+adjustByQty);
        }
        else{
            System.out.println("item does not exist");
        }
    }



    //retrieves an item by it's item code, also known as the key for each element in the hashtable
    public Item getItemByKey(String key){
        Set<String> s = hashTable.keySet();
        for(String k : s){
           if(k.equals(key)) return hashTable.get(k);
        }
        return null;
    }





    /*
        processes the sale of the business in a text file and will manipulate and
        calculate the orders and average sales per day
     */
    public void processSales(String filename) throws IOException, ParseException {
        File file = new File(filename);
        if(file.exists()) {
            String[][] table = textfileToJSON(filename);
            Item dummyItem;
            boolean orderAlreadyPlaced = false;
            for (int i = 0; i < table.length; i++) {
                if (hashTable.contains(table[i][0])) {
                    dummyItem = hashTable.get(table[i][0]);
                    int qtySold = Integer.parseInt(table[i][0]);
                    if (dummyItem.getQtyInStore() > qtySold) {
                        int qtyLeft = dummyItem.getQtyInStore() - qtySold;
                        if (qtyLeft < dummyItem.getAverageSalesPerDay()) {
                            if (orderAlreadyPlaced) {
                                //if order already place skip
                                continue;
                            }
                            int restocking = 2 * dummyItem.getAverageSalesPerDay();
                            int minimum_quantity = 3 * dummyItem.getAverageSalesPerDay();
                            if (qtyLeft < minimum_quantity) {
                                //order minimum_quantity
                                //PRINT place order of minimum_quantity
                            }
                        }
                        dummyItem.setQtyInStore(dummyItem.getQtyInStore() - qtySold);

                    } else {
                        //When processing a sale, if the quantity sold is greater than the quantity in the store, print a message and do not process the sale.
                        System.out.println(" quantity sold is greater than the quantity in the store");
                    }
                    //do manipulation
                }
            }
        }
        else{
            System.out.println("error, file does not exist");
        }
    }




    /*
       updates the current business day and increments it by 1
       if arrival date and order date are equal, then the order
       has arrived
     */
    public void nextBusinessDay(){
        //updates the business day to the next day, by 1;
        setBusinessDay(businessDay++);
        Set<String> k = hashTable.keySet();
        for(String i : k){
            if(hashTable.get(k).getOnOrder()==hashTable.get(k).getArrivalDay()){
                System.out.println("Order has arrived for: ");
                for(String s : k){
                    Item item = hashTable.get(k);

                }

                hashTable.get(k).getOnOrder();
                hashTable.get(k).setOnOrder(0);

            }
        }
    }





    /*
    the following method, textFileToJSON, will help convert the files into a readable format
    that can assist with letting the user run the program with text files being inputted
     */
    public String [][] textfileToJSON(String filename) throws IOException, ParseException {
        /*if(!filename.contains("sale") || !filename.contains("item")){
            System.out.println("Not  Valid ");
            return;
        }*/
        File file = new File(filename); //new file
        if (file.exists()) { //if the file inputted exists
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            JSONParser parser = new JSONParser();
            JSONArray objs = (JSONArray) parser.parse(isr); // objs is a JSONArray which contains all JSONObjects found in the InputStream
            int jsonsize = objs.size();
            int col = 0;
            String [][] table = null;
            if(filename.contains("sale")){
                col = 2;
                table =  new String [jsonsize][2];
                for(int i = 0; i < jsonsize; i++){
                        JSONObject obj = (JSONObject) objs.get(i);      // obj is the first JSONObject in the objs JSONArray
                        table[i][0] = (String) obj.get("itemCode");
                        table[i][1] = (String) obj.get("qtySold");
                }
            }else if(filename.contains("item")){
                col = 6;
                table =  new String [jsonsize][6];
                for(int i = 0; i < jsonsize; i++){
                    JSONObject obj = (JSONObject) objs.get(i);      // obj is the first JSONObject in the objs JSONArray
                    table[i][0] = (String) obj.get("itemCode");
                    table[i][1] = (String) obj.get("itemName");
                    table[i][2] = (String) obj.get("avgSales");
                    table[i][3] = (String) obj.get("qtyInStore");
                    table[i][4] = (String) obj.get("price");
                    table[i][5] = (String) obj.get("amtOnOrder");
                }
            }
            for(int i = 0; i < jsonsize; i++){
                for(int j = 0; j < col; j++){
                    System.out.print(table[i][j] +  " ");
                }
                System.out.println();
            }
            return table;
        }
        else{
            throw new FileNotFoundException("error, file not found"); // throws exception if file is not found
        }
    }




    /*
        toString() method that prints out the hashtable of all the items
        in the catalog and neatly formats the hashtable
     */
    public String toString(){
        System.out.println("Item Code\tName\t\t\t\t\tQty\tAvgSales\tPrice\tOnOrder\t\tArrOnBusDay");
        System.out.println("-----------------------------------------------------------------------------------------------");
        Set<String> s;
        s = hashTable.keySet();
        for(String k : s){
            Item item = hashTable.get(k);
            System.out.println(item.toString());
        }
        return "";
    }

}
