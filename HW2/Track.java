/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #2
CSE 214
Recitation R10, TA: Daniel Calabria

 */



/*
    The Track class implements a Doubly linked list of trains and will allow the user to
    add and remove trains within the track depending on the parameters of each train. The class includes
    the getters and setters of each parameter, in addition to the methods that allow a user to add a train,
    remove a train, and print a certain train depending on which direction the user allows the cursor to move towards.
    The parameters of the track class are the utilizationRate of the track, and the trackNumber to represent the
    order of the tracks
 */
public class Track {

    private Train head; //variable to represent the head train of the list
    private Train tail; //variable to represent the  of the train list
    private Train cursor; //variable to represent the cursor of the train list
    private Track next; //variable to signal the next track in the list
    private Track prev; //variable to signal the previous track in the list
    private int numberOfTrains = 0; //variable to represent the number of trains in a track
    private double utilizationRate; //represents the utilization rate of each track
    private int trackNumber; //integer that will represent the number of the track
    private int totalTime; //integer that will represent the total time on each track




    /*
    Constructor for a new track that will contain one parameter/feature of the track to input,
    which is simply the track number. When a track is created, the head, tail, and cursor are all
    initiated to null since no action to change that has been done yet.
     */
    public Track(int trackNumber){
        this.trackNumber = trackNumber;
        head = null;
        cursor = null;
        tail = null;
    }



    //default track constructor with no parameters
    public Track(){

    }




    /*
         Retrieves the head train of the track and throws a NullPointerException if needed
    */
    public Train getHead() throws NullPointerException{
        //if(head == null){
            //System.out.println("error. track is empty and has no trains");
            ;
        //}
        return head;
    }




    /*
         Assigns/sets a certain train as the new head of the list
    */
    public void setHead(Train head) {
        this.head = head;
    }




    /*
             Retrieves the tail train of the track
    */
    public Train getTail() {
        return tail;
    }



    /*
         Assigns/sets a certain train as the new tail of the list
    */
    public void setTail(Train tail) {
        this.tail = tail;
    }



    /*
         Retrieves the train in the list that the cursor is pointed towards
    */
    public Train getCursor() {
        return cursor;
    }



    /*
         Assigns/sets the cursor to a new train in the list
    */
    public void setCursor(Train newCursor) {
        cursor.setNext(newCursor);
    }



    /*
             Retrieves the next track in the list after a certain track
    */
    public Track getNext() {
        return next;
    }



    /*
         Assigns/sets a track as the next after a certain track
    */
    public void setNext(Track next) {
        this.next = next;
    }



    /*
         Retrieves the previous track in the list before a certain track
    */
    public Track getPrev() {
        return prev;
    }



    /*
         Assigns/sets a track as the previous before a certain track
    */
    public void setPrev(Track prev) {
        this.prev = prev;
    }



    /*
         Retrieves the number of trains within a certain track
    */
    public int getNumberOfTrains() {
        return numberOfTrains;
    }


    /*
             Retrieves the utilization rate of a track
    */
    public double getUtilizationRate() {

        return utilizationRate;
    }




    /*
         Assigns/sets the utilization rate for a track
    */
    public void setUtilizationRate(double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }



    /*
         Retrieves the number of a track
    */
    public int getTrackNumber() {
        return trackNumber;
    }



    /*
         Assigns/sets a track to a number
    */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }



    /*
         Adds a train to the track list. If cursor is null, then the cursor, head and tail are all equal to the new train.
         If the cursor is on the tail of the list, the new train will become the tail and have the cursor on it.
         If none of these are true, then a train will be added to the list in the order of the arrival/departure time and have the cursor on it.
    */
    public void addTrain(Train newTrain)throws InvalidTimeException{

        if(cursor == null){
            tail = cursor = head = newTrain;
            newTrain.setPrev(null);
            newTrain.setNext(null);
            this.numberOfTrains++;
            totalTime += newTrain.getTransferTime();
            setUtilizationRate((totalTime/1440.00)*100.00);
        }

        else if(cursor == tail){
            tail.setNext(newTrain);
            newTrain.setPrev(tail);
            setCursor(newTrain);
            setTail(newTrain);
            this.numberOfTrains++;
            totalTime += newTrain.getTransferTime();
            setUtilizationRate((totalTime/1440.00)*100.00);
        }

        else{
            Train t;
            t = cursor.getNext();
            if(newTrain.getArrivalTime()<(t.getArrivalTime()+t.getTransferTime()) && (newTrain.getArrivalTime()>t.getArrivalTime())){
                throw new InvalidTimeException("Error with timing");

            }
            if(((newTrain.getArrivalTime()+newTrain.getTransferTime())<(t.getArrivalTime()+t.getTransferTime())&&((newTrain.getArrivalTime()+newTrain.getTransferTime())>t.getArrivalTime()))){
                throw new InvalidTimeException("Error with timing");

            }
            if((newTrain.getArrivalTime()<t.getArrivalTime())&&((newTrain.getArrivalTime()+newTrain.getTransferTime())>(t.getArrivalTime()+t.getTransferTime()))){
                throw new InvalidTimeException("Error with timing");
            }
            newTrain.setPrev(cursor);
            cursor.setNext(newTrain);
            t.setPrev(newTrain);
            newTrain.setNext(t);
            cursor = newTrain;
            numberOfTrains++;
            totalTime += newTrain.getTransferTime();
            setUtilizationRate((totalTime/1440.00)*100.00);
        }

    }





    /*
         Prints out the information of the selected train that the cursor is on.
    */
    public void printSelectedTrain(){
        System.out.println(cursor.toString());
    }





    /*
         Removes the selected train that the cursor is on. If cursor is null, nothing is removed.
         If the cursor is on the tail, the last train is removed and the cursor is pointed towards the
         previous train. If none of these are true, the selected train is removed and the cursor is pointed towards
         the next train.
    */
    public Train removeSelectedTrain(){
        Train deletedTrain = cursor;
        if(cursor == null){
           System.out.println("Error. The selected space is empty. Therefore," +
                   "nothing has been removed.");
        }

        else if(cursor == tail){
            cursor = tail = tail.getPrev();
            tail.setNext(null);
            numberOfTrains--;
            totalTime -= deletedTrain.getTransferTime();
            setUtilizationRate((totalTime/1440.00)*100.00);

        }

        else{
            Train next = cursor.getNext();
            Train prev = cursor.getPrev();
            prev.setNext(next);
            next.setPrev(prev);
            cursor = next;
            numberOfTrains--;
            totalTime -= deletedTrain.getTransferTime();
            setUtilizationRate((totalTime/1440.00)*100.00);
        }
        System.out.println("Train No. " + deletedTrain + " to " + deletedTrain.getDestination() +
        " has been removed from Track " + trackNumber);
        return deletedTrain;
    }





    /*
         Moves the cursor to the next train in the list. If cursor is null or on the tail, nothing will change
         and everything will remain the same.
    */
    public boolean selectNextTrain(){
        if(cursor==tail){
            //System.out.println("There is no next train. The selected train will remain the same.");
            return false;
        }
        else if(cursor==null){
            System.out.println("There is no selected train. Nothing has changed and will remain the same.");
            return false;
        }
        else{
            cursor = cursor.getNext();
            System.out.println("Cursor has been moved to next train");
            return true;
        }
    }




    /*
        Moves the cursor to the previous train in the list. If cursor is null or on the head, nothing will change
         and everything will remain the same.
    */
    public boolean selectPrevTrain(){
        if(cursor==head){
            System.out.println("There is no previous train. The selected train will remain the same.");
            return false;
        }
        else if(cursor==null){
            System.out.println("There is no selected train. The selected train will remain the same.");
            return false;
        }
        else{
            cursor = cursor.getPrev();
            System.out.println("Cursor has been moved to previous train");
            return true;
        }
    }





    /*
        toString() method that produces the track's content and details of all of the trains in each track.
        There will be a small asterisk(*) located to the side of the selected train in each track.
    */
    public String toString(){
        System.out.println("Track " + trackNumber + " (" + getUtilizationRate() + "% Utilization Rate):");
        System.out.println("\tSelected  Train Number     Train Destination         Arrival Time     Departure Time" +
                "\n-------------------------------------------------------------------------------------\n");
        String list = "";
        if(head != null){
            Train t = head;
            while(t!=null){
                if(t==cursor){
                    list = "\t" + list + "*" + t.toString() + "\n";
                    t = t.getNext();
                }
                else {
                    list += t.toString() + "\n";
                    t = t.getNext();
                }
            }

        }


        return list;
    }



    public boolean trackNumberExist(int newTrainNumber){
        boolean trainExist = false;
        for(Train ptr = head; ptr != null; ptr = ptr = ptr.getNext()){
            if(ptr.getTrainNumber() == newTrainNumber) trainExist = true;
        }
        return trainExist;
    }




}
