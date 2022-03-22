

/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #6
CSE 214
Recitation R10, TA: Daniel Calabria
 */



/*
The Item class contains the details for each item, which consist of the itemCode(represents the
key for the hashtable), the name, the quantity of items in the store, the average sales per day,
the amount of orders per item, the price of each item, and the arrival day of the item
 */
public class Item {

    private String itemCode; //This must be unique, as it will serve as the key in your hash table.
    private String name; // the name of the item in the store
    private int qtyInStore; // the quantity of items in stores
    private int averageSalesPerDay; // the average sales made in a day
    private int onOrder; //How many have already been ordered for restocking.
    private double price; // price of each item
    private int arrivalDay; /*
    This variable will keep track of the business day at which your order
    for this item will arrive. Every order takes 2 whole days to ship and will
    arrive on the beginning of the third day, so you should assign a value to this
    variable depending on that and the business day you are currently on.
    For example, if you are on business day 1, then you should set this variable to 4.
     If there is nothing currently on order, then the value of this variable should be 0.
      See sample I/O for examples.
    */



    /*
       default constructor for an item, used mainly for temporary items to
       loop through a hashtable
     */
    public Item(){
        itemCode = null;
        name="";
        qtyInStore=0;
        averageSalesPerDay=0;
        price=0.0;
    }



    /*
        a constructor for an item with specified arguments, used mainly to add a new item
        into the catalog
     */
    public Item(String itemCode,String name,int qtyInStore,int averageSalesPerDay,double price){
        this.itemCode=itemCode;
        this.name=name;
        this.qtyInStore=qtyInStore;
        this.averageSalesPerDay=averageSalesPerDay;
        this.price=price;
    }



    /*
        returns the code for an item
     */
    public String getItemCode() {
        return itemCode;
    }



    /*
        sets an item code to a new value
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }



    /*
        returns the name for an item
     */
    public String getName() {
        return name;
    }


    /*
           sets the name of an item for a
     */
    public void setName(String name) {
        this.name = name;
    }



    /*

     */
    public int getQtyInStore() {
        return qtyInStore;
    }


    /*

     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }



    /*

     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }




/*

 */
    public void setAverageSalesPerDay(int averageSalesPerDay) {
        this.averageSalesPerDay = averageSalesPerDay;
    }





    /*

     */
    public int getOnOrder() {
        return onOrder;
    }




    /*

     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }



    /*

     */
    public double getPrice() {
        return price;
    }



    /*

     */
    public void setPrice(double price) {
        this.price = price;
    }



    /*

     */
    public int getArrivalDay() {
        return arrivalDay;
    }




    /*

     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }




    /*
        the toString() method returns all the variables and details
        in each
     */
    public String toString(){

        return itemCode + "   " + name + "\t" + qtyInStore + "\t\t"
                + averageSalesPerDay + "   " + price + "\t\t\t" +
                onOrder + "\t\t\t\t" + arrivalDay;

    }

}
