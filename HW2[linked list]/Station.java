/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #2
CSE 214
Recitation R10, TA: Daniel Calabria
 */



import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/*
    The Station class implements a doubly linked list of tracks and allows the user to add and remove tracks
    within the station using the number of a track. The class includes the getters and setters for the head, cursor,
    and tail of the list, and the trackCounter parameter. Additionally, the class also contains the methods that allow the
    user to add a track, remove the selected track, select a new track, print the selected track, print all tracks, and
    a toString() method to produce a thorough and organized set of information for the created station.
 */
public class Station {


    private Track head; //variable to represent the head track of the list
    private Track tail;//variable to represent the tail track of the list
    private Track cursor;//variable to represent the cursor within the list
    private int trackCounter = 0;//represents the number for each track and increases everytime a new track is added to the station






    /*
        Constructor for a new station that will contain no parameters since no specific parameter
        is needed to create the needed station. When a station is created, the head, tail, and cursor are all
        initiated to null since no action to change that has been done yet.
     */
    public Station() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }




    /*
       Retrieves the head track of the station
     */
    public Track getHead() {
        return head;
    }



    /*
           Assigns a track as the head of the station list
     */
    public void setHead(Track head) {
        this.head = head;
    }



    /*
          Retrieves the last track of the station list
    */
    public Track getTail() {
        return tail;
    }



    /*
           Assigns a track as the tail of the station list
    */
    public void setTail(Track tail) {
        this.tail = tail;
    }



    /*
       Retrieves the selected track of the station that the cursor is pointing towards
    */
    public Track getCursor() {
        return cursor;
    }



    /*
           Assigns the cursor to a new selected track in the station
    */
    public void setCursor(Track cursor) {
        this.cursor = cursor;
    }



    /*
         Adds a track to the station list. If cursor is null, then the cursor, head and tail are all equal to the new track.
         If the cursor is on the tail of the list, the new track will become the tail and have the cursor on it.
         If none of these are true, then a track will be added to the list and have the cursor on it.
    */
    public void addTrack(Track newTrack) {
        if(cursor==null){
            tail=cursor=head=newTrack;
            trackCounter++;
        }

        else if(cursor==tail){
            tail.setNext(newTrack);
            newTrack.setPrev(tail);
            setCursor(newTrack);
            setTail(newTrack);
            trackCounter++;
        }

        else{
            Track t;
            t = cursor.getNext();
            newTrack.setPrev(cursor);
            cursor.setNext(newTrack);
            newTrack.setNext(t);
            t.setPrev(newTrack);
            cursor=newTrack;
            trackCounter++;
        }
    }







    /*
          Removes the selected track that the cursor is on. If cursor is null, nothing is removed.
         If the cursor is on the tail, the last track is removed and the cursor is pointed towards the
         previous track. If none of these are true, the selected track is removed and the cursor is pointed towards
         the next track.
    */
    public Track removeSelectedTrack() {

        Track deletedTrack = cursor;
        if(cursor==null){
            System.out.println("Error. The selected space is empty. Therefore, nothing has been removed.");
        }

        else if(cursor==tail){
            tail=tail.getPrev();
            cursor = tail = null;
            trackCounter--;
        }

        else{
            Track n = cursor.getNext();
            Track p = cursor.getPrev();
            p.setNext(n);
            n.setPrev(p);
            cursor = n;
            trackCounter--;
        }
            return deletedTrack;
    }



    /*
         Prints out the information of the selected track that the cursor is on.
    */
    public void printSelectedTrack() throws  NullPointerException{
        try {
            System.out.println(cursor.toString());
        }
        catch(NullPointerException n){
            System.out.println("Cursor is null");
        }
    }





    /*
         Prints out the information and details of all of the tracks within the station
    */
    public void printAllTracks() {
        String list ="";

        if(head != null){
            Track t = head;
            while(t!=null){
                list += t.toString() + "\n";
                t = t.getNext();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Selected Track: " + cursor.getTrackNumber());
            System.out.println();
            System.out.println();

        }
        else{
            list = "No tracks have been created";
            System.out.println(list);
        }
    }


    public void trackNumber(){
        System.out.println("Station (" + trackCounter + " tracks):");
        for(Track ptr = head; ptr!=null;ptr=ptr.getNext()){
            System.out.println("Track " + ptr.getTrackNumber() + ": " + ptr.getNumberOfTrains() +
                    " trains arriving (" + ptr.getUtilizationRate() + "% Utilization Rate)");
        }
    }


    /*
          Moves the cursor to a selected track in the station. trackToSelect will be an integer that
          the user inputs to move the cursor to.
    */
    public boolean selectTrack(int trackToSelect) {
        int counter = 0;
        boolean trackExist = false;
        if(head != null){
            for(Track ptr = head; ptr != null; ptr = ptr.getNext()){
                if( ptr.getTrackNumber() == trackToSelect){
                    trackExist = true;
                    cursor = ptr;
                    break;
                }
            }
        }


        return trackExist;
    }




    /*
          toString() method that produces the amount of tracks in the station,
          along with the amount of trains in each track and the utilization rate.
    */
    public String toString() {
        trackNumber();
        return "";
    }





    /*
          main method containing the menu options for the user to produce the station, tracks, and trains
    */
    public static void main(String[] args) throws InvalidTimeException,InputMismatchException {

        Station station = new Station(); //new station

        while (true) { //continuous loop allowing the program to run until the user manually decided to quit
            System.out.println();
            System.out.println("|-----------------------------------------------------------------------------|\n" +
                    "| Train Options                       | Track Options                         |\n" +
                    "|    A. Add new Train                 |    TA. Add Track                      |\n" +
                    "|    N. Select next Train             |    TR. Remove selected Track          |\n" +
                    "|    V. Select previous Train         |    TS. Switch Track                   |\n" +
                    "|    R. Remove selected Train         |   TPS. Print selected Track           |\n" +
                    "|    P. Print selected Train          |   TPA. Print all Tracks               |\n" +
                    "|-----------------------------------------------------------------------------|\n" +
                    "| Station Options                                                             |\n" +
                    "|   SI. Print Station Information                                             |\n" +
                    "|    Q. Quit                                                                  |\n" +
                    "|-----------------------------------------------------------------------------|\n\n\n" +
                    "Choose an operation: ");

            Scanner scanner = new Scanner(System.in); //allows user to input an option
            String input = scanner.nextLine();

            switch(input.toUpperCase()){      //switch statement for each scenario that a user selects a certain option

                case "A":
                    if(station.getCursor() == null){
                        System.out.println("ADD TRACK FIRST");
                        break;
                    }
                    else {
                        try {
                            System.out.println("Enter train number: ");
                            int trainNumber = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter train destination: ");
                            String destination = scanner.nextLine();
                            System.out.println("Enter train arrival time: ");
                            String arrivalTime = scanner.nextLine();
                            System.out.println("Enter train transfer time");
                            int transferTime = scanner.nextInt();
                            int i = Integer.parseInt(arrivalTime);
                            boolean flag = station.getCursor().trackNumberExist(trainNumber);
                            int hour = i / 100;
                            int minutes = i % 100;
                            if (!flag) {
                                    if ((hour >= 0 && hour < 24) && minutes >= 0 && minutes < 60) {
                                        Train train = new Train(trainNumber, destination, i, transferTime);
                                        station.getCursor().addTrain(train);
                                        System.out.println("Train " + trainNumber + " has been added to track " + station.getCursor().getTrackNumber());
                                    }
                                    else{
                                        System.out.println("Invalid time.");
                                    }

                                }
                            else {
                                System.out.println("invalid. train already exists");
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("invalid input. try again");
                        }
                    }



                case "N":   station.getCursor().selectNextTrain();
                    break;


                case "V":   station.getCursor().selectPrevTrain();
                    break;


                case "R":   station.getCursor().removeSelectedTrain();
                    break;


                case "P":   station.getCursor().printSelectedTrain();
                    break;


                case "TA":  System.out.println("Enter track number: ");
                            try{
                                int trackNumber = scanner.nextInt();
                                Track track = new Track(trackNumber);
                                station.addTrack(track);
                                System.out.println("Track "+trackNumber+" added to the station");
                            }
                            catch(InputMismatchException e){
                                System.out.println("Invalid input");
                            }
                    break;


                case "TR":  station.removeSelectedTrack();
                    break;


                case "TS":  System.out.println("Enter the track number: ");
                            try{
                                int number = scanner.nextInt();
                                if(!station.selectTrack(number)) {
                                    System.out.println("Track does not exist");
                                }
                                else {
                                    System.out.println("Cursor has been moved");
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("Invalid input");
                            }
                    break;


                case "TPS":
                    station.printSelectedTrack();
                    break;


                case "TPA": station.printAllTracks();
                    break;


                case "SI": station.toString();
                    break;


                case "Q":   System.out.println("Program terminating successfully . . .");
                            System.exit(0);
                    break;


                default: System.out.println("Enter a valid input");
                    break;
            }


        }


    }
}

