
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #4
CSE 214
Recitation R10, TA: Daniel Calabria
 */

/*
The customer class will contain the details of each customer when they
enter the restaurant and dine in. The customer class will contain
the static variable that will contain the total amount of customers,
the customers food, price of food, order number, the time that the
customer arrived, and the time to serve.
 */
public class Customer {


    private static int totalCustomers = 1; //total amount of customers
    private String[] meals = {"cheeseburger","steak","grilled cheese","chicken tenders","chicken wings"};
    private int customerNumber; //order number for each customer
    private String food; //name of food for each customer
    private int priceOfFood; //variable to represent the price of the food
    private int timeArrived; // the time arrived for each customer
    private int timeToServe; //the time to serve each customer dining in the restaurant



    /*
    Constructor that helps create a new customer for a restaurant and initially increases
    the total amount of customers instantly as soon as a customer is created
     */
    public Customer(){
        customerNumber = totalCustomers;
        totalCustomers++;
    }


    //Retrieves the order number for each customer
    public int getCustomerNumber() {
        return customerNumber;
    }


    //Retrieves the food of a customer
    public String getFood() {
        return food;
    }


    /*
    Randomly generates a meal for a customer
     */
    public void setFood() {
        int randomNum=(int)(Math.random()*5);
        food=meals[randomNum];
        setPriceOfFood(randomNum);
    }


    //Retrieves the price of the food for each customer
    public int getPriceOfFood() {
        return priceOfFood;
    }


    /*
    Sets a price for the food depending on which meal is created
     */
    public void setPriceOfFood(int priceOfFood) {
        if(priceOfFood==0){
            this.priceOfFood=15;
        }
        else if(priceOfFood==1){
            this.priceOfFood=25;
        }
        else if(priceOfFood==2){
            this.priceOfFood=10;
        }
        else if(priceOfFood==3){
            this.priceOfFood=10;
        }
        else if(priceOfFood==4){
            this.priceOfFood=20;
        }
    }

    //Retrieves the time arrived for each customer
    public int getTimeArrived() {
        return timeArrived;
    }


    //Retrieves the total amount of customers
    public static int getTotalCustomers() {
        return totalCustomers;
    }

    //Sets a time arrived for each customer
    public void setTimeArrived(int timeArrived) {
        this.timeArrived = timeArrived;
    }


    //Retrieves the time to serve for each customer
    public int getTimeToServe() {
        return timeToServe;
    }


    //Initializes a time to serve for each customer depending on the amount of chefs and the meal they're receiving
    public void setTimeToServe(int chefs) throws Exception {
        if(chefs<=0){
            throw new Exception("No chefs found in the restaurant");
        }
        if(chefs>=5) chefs=5;

        if(food.equals("cheeseburger")){
            timeToServe= ((25 + 15) - chefs * 5)+15;

        }
        else if(food.equals("steak")){
            timeToServe=((30+15) - chefs * 5)+15;
        }
        else if(food.equals("grilled cheese")){
            timeToServe=((15+15) - chefs * 5)+15;
        }
        else if(food.equals("chicken tenders")){
            timeToServe=((25+15) - chefs * 5)+15;
        }
        else if(food.equals("chicken wings")){
            timeToServe=((30+15) - chefs * 5)+15;
        }
    }


    //Countdown method that helps deduct time after each iteration in the simulation
    public void countdown(){
        timeToServe -= 5;
    }


    //toString() method that prints out each customers information
    public String toString(){
        return " #" + customerNumber + ", " + getFood() + ", " + getTimeToServe() + " min.";
    }




}
