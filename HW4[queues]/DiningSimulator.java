
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #4
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.util.*;


/*
the dining simulator class creates a simulation of customers and restaurants and
lets the user input the specifics of the diner that will implement a queue
 */
public class DiningSimulator {


    static Restaurant diner = new Restaurant(); //new diner being constructed of type Restaurant
    private Collection<Restaurant> restaurants; //will contain a collection of restaurants
    private static int chefs; //variable to represent the number of chefs for the eatery
    private static int duration; // the amount of times the simulation will run
    private static double arrivalProb; //variable to represent the probability of an arrival
    private static int maxCustomerSize; //variable to represent the maximum amount of customers containable
    private static int numRestaurants; //variable to represent the amount of restaurants within the simulation
    private static int customersLost; //total number of customers lost
    private static int totalServiceTime; //total combined service time for the dining simulation
    private static int customersServed; // total amount of customers served in the diner
    private static int profit; //total profit made from the customers that ate in the diner



    //main method to allow user input for the user and enter for the static variables
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); //opens up user input
        String answer; //variable named answer to let user decide if another simulation should be ran
        boolean proceed; //boolean variable to determine if the simulation should proceed with being ran again or not


        // do while loop to run the simulation at least once
        do{
            try {
                System.out.println("Starting simulator . . .\n\n\n");
                System.out.println("Enter the number of restaurants: ");
                numRestaurants = scanner.nextInt();
                System.out.println("Enter the maximum number of customers a restaurant can serve: ");
                maxCustomerSize = scanner.nextInt();
                System.out.println("Enter the arrival probability of a customer: ");
                arrivalProb = scanner.nextDouble();
                System.out.println("Enter the number of chefs: ");
                chefs = scanner.nextInt();
                System.out.println("Enter the number of simulation units: ");
                duration = scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Try again");
            }
            simulate(numRestaurants,maxCustomerSize,arrivalProb,chefs,duration);
            System.out.println();
            System.out.println("Simulation ending . . .");
            System.out.println("Total customer time: " + totalServiceTime + " minutes");
            System.out.println("Total customers served: " + customersServed);


            if(customersServed==0){     //if no customers were served, there is no average time lapse
                System.out.println("unable to obtain average because no customers were served");
            }

            else{
                System.out.println("Average customer time lapse: " + ((double)totalServiceTime/(double)customersServed) + " minutes per order");

            }


            System.out.println("Total profit: $" + profit);
            System.out.println("Customers that left: " + customersLost);
            System.out.println("Do you want to try another simulation? (y/n): ");

            //allows user to decide if they want to run another simulation or not
            answer = scanner.next();
            answer=answer.toUpperCase();

            if (answer.equals("Y")) {
                proceed=true;
            }else if (answer.equals("N")) {
                System.out.println("Program terminating normally ...");
                proceed=false;
                System.exit(0);     //closes down the simulation
            }

        }while(proceed=true); //continues to run the simulation for more than once if the user allows it

    }


        /*
            simulation method that will take all of the user inputted variables that include the number of restaurants,
            the maximum amount of customers per restaurant, the arrival probability for each customer, the amount of chefs,
            and the duration of the simulation
         */
        public static double simulate (int numRestaurants,int maxCustomerSize,double arrivalProb,int chefs,int duration) throws Exception {
            boolean passedFirstTime = false;
            Restaurant[] restaurants = new Restaurant[numRestaurants];

            /*
            for loop to create new restaurants
             */
            for (int i = 0; i < restaurants.length; i++) {
                restaurants[i] = new Restaurant();
            }

            /*
            for loop that will generate the customers in the queue and will print out each
            customer's dining information, and the amount of profit the diner receives
            after the customer finishes eating and leaves
             */
            for (int i = 1; i <= duration; i++) {
                System.out.println("Time: " + i);
                if (passedFirstTime){
                    for (int j = 0; j < numRestaurants; j++) {
                        Node ptr = restaurants[j].getHead();
                        if(ptr != null){
                            while(ptr != null){
                                ptr.getCustomer().countdown();
                                if(ptr.getCustomer().getTimeToServe()<=0 &&  restaurants[j].peek() == ptr.getCustomer()){
                                    System.out.println("Customer #" + ptr.getCustomer().getCustomerNumber() + " has enjoyed their food! $" + ptr.getCustomer().getPriceOfFood() + " profit");
                                    customersServed++;
                                    restaurants[j].dequeue();
                                    profit+=ptr.getCustomer().getPriceOfFood();
                                }
                                else if (ptr.getCustomer().getTimeToServe()<=0){
                                    System.out.println("Customer #" + ptr.getCustomer().getCustomerNumber() + " has enjoyed their food! $" + ptr.getCustomer().getPriceOfFood() + " profit");
                                    customersServed++;
                                    restaurants[j].remove(ptr);
                                    profit+=ptr.getCustomer().getPriceOfFood();
                                }
                                ptr=ptr.getLink();
                            }
                        }
                    }
                }



                /*
                For loop that prints out which customer has been seated, which customers entered the restaurant,
                and which customers left the restaurant because they could not be seated.
                 */
                for (int z = 0; z < 3; z++) {
                    if (arrivalProb > Math.random()) {
                        Customer customer = new Customer();
                        customer.setTimeArrived(i);
                        customer.setFood();
                        customer.setTimeToServe(chefs);
                        int restaurantNumber=(int)(Math.random()*numRestaurants);
                        System.out.println("Customer #" + customer.getCustomerNumber() + " has entered restaurant " + (restaurantNumber+1));

                        if (restaurants[restaurantNumber].size()<maxCustomerSize) {
                            System.out.println("Customer " + customer.getCustomerNumber() + " has been seated with order \"" + customer.getFood() + "\"");
                            restaurants[restaurantNumber].enqueue(customer);
                            totalServiceTime+=customer.getTimeToServe();
                        }
                        else {
                            System.out.println("Customer #" + customer.getCustomerNumber() +" cannot be seated. They have left the restaurant");
                            customersLost++;
                        }
                    }
                }
                    /*
                    For loop that prints out what each restaurant contains: customer, food, and wait time
                     */
                for (int j = 0; j < numRestaurants; j++) {
                    System.out.println("R" + (j+1) + restaurants[j].toString());
                }
                passedFirstTime = true;
            }

            return 0.0;
        }







        /*
        method that will generate a random integer value within a range that has a minimum value and a max value
         */
    public static int randInt(int minVal, int maxVal){
        int difference = maxVal - minVal;
        return (int)(Math.random()*difference)+minVal;
    }


}
