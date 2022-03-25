/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming Assignment Homework #2
CSE 214
Recitation R10, TA: Daniel Calabria

 */




/*
This Train class provides the features of each train node that will be used
within the Track class. The train class contains all of the getters and setters of the
parameters, in addition to a toString() and equals() method.The parameters that are needed
 to create the train class are called trainNumber, destination, arrivalTime, and transferTime.
 */

public class Train {

//Variables
    private Train next; //train variable that will signal the next node/train in the list
    private Train prev; //train variable that will signal the previous node/train in the list
    private int trainNumber; // integer that will represent the number of the train
    private String destination; //variable containing the destination of the train
    private int arrivalTime; //integer representing the arrival time of the train
    private int transferTime; //integer containing the transfer time of the train.






    /*
    Constructor for a new train that will contain four parameters/features of the train to input,
    which are the trainNumber, destination, arrivalTime, and transferTime of the new train being created
     */
    public Train(int trainNumber,String destination,int arrivalTime,int transferTime){

        this.trainNumber = trainNumber;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.transferTime = transferTime;

    }




    /*
    Default train constructor with no parameters
     */
    public Train(){

    }





    /*
    returns the train number for the train object
     */
    public int getTrainNumber() {
        return trainNumber;
    }





    /*
    Assigns a train number to the train object.
     */
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }







    /*
    Retrieves the train destination for a certain train
     */
    public String getDestination() {
        return destination;
    }






    /*
     Assigns/sets a destination for a certain train
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }






    /*
         Retrieves the arrival time for a certain train
    */
    public int getArrivalTime() {
        return arrivalTime;
    }






    /*
         Assigns/sets an arrival time for a certain train
    */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }








    /*
         Retrieves the transfer time for a certain train
    */
    public int getTransferTime() {
        return transferTime;
    }







    /*
         Assigns/sets a transfer time for a certain train
    */
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }






    /*
         Assigns/sets a train to be the next after a certain train
    */
    public void setNext(Train nextTrain){
        this.next = nextTrain;
    }





    /*
         Assigns/sets a train to be the previous before a certain train
    */
    public void setPrev(Train prevTrain){
        this.prev=prevTrain;
    }






    /*
             Retrieves the next train after a certain train
    */
    public Train getNext(){
        return next;
    }






    /*
         Retrieves the previous train before a certain train
    */
    public Train getPrev(){
        return prev;
    }







    /*
         Compares two trains
    */
    public boolean equals(Object o){
        Train train = (Train) o;
        if(this.getTrainNumber()==train.getTrainNumber()
                && this.getDestination().equals(train.getDestination())
                && this.getArrivalTime()==train.getArrivalTime()
                && this.getTrainNumber()==train.getTrainNumber()){
            return true;
        }
        return false;
    }






    /*
         toString() method that produces the train's content
         and details when it is in a certain track
    */
    public String toString(){
        return   " \t\t\t" + getTrainNumber() + "\t\t\t  "
                + getDestination() + "\t\t\t\t    " +
                (getArrivalTime()) + "\t\t\t\t" +
                (((getArrivalTime()+getTransferTime())/60))+""+(((getArrivalTime()+getTransferTime())%60));
    }

}

