
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #4
CSE 214
Recitation R10, TA: Daniel Calabria
 */


/*
The restaurant class will contain the methods for the queue and will
use a linked list to implement the methods. This class helps enqueue
and dequeue customers into the restaurants
 */
public class Restaurant {

    private int i =1; //simple counter for restaurants
    private Node head; // the head of the queue, the first element
    private Node tail; //the tail of the queue, the last element
    private int size = 0; //size of the restaurant, initiated to zero


    //constructor for a new restaurant in the diner
    public Restaurant(){
        head = null;
        tail = null;
    }


    /*
    enqueue method allows customers to be enqueued into the restaurant and
    adds them to the end of the queue. If they are the first customer, simply insert
    into the queue
     */
    public void enqueue(Customer c){
        Node node = new Node(c);
        if(head==null){
            head = node;
            tail = head;
            size++;
        }
        else {
            tail.setLink(node);
            tail=node;
            size++;
        }

    }


    /*
    Dequeue method that will remove the customer from the queue after their wait time is complete
     */
    public Customer dequeue(){
        Node data;
        if(head==null){
            throw new NullPointerException("Queue is Empty");
        }
        if(this.size()==1){
            data=head;
            head=null;
            tail=null;
            size--;
            return data.getCustomer();
        }
        else{
            data=head;
            head=head.getLink();
            size--;
            return data.getCustomer();
        }

    }


    /*
    Remove method that helps remove a customer from the queue if their time is up
    and they are in the middle of the queue
     */
    public Node remove(Node cursor){
        Node deletedCustomer = cursor;
        Node ptr = head;
        while(ptr!=null){
            if(ptr.getLink()==cursor){
                ptr.setLink(cursor.getLink());
                break;
            }
            ptr=ptr.getLink();
        }

        return deletedCustomer;
    }



    //peeks into the first element of the queue, does not remove. Throws an
    //exception if the queue is empty
    public Customer peek(){
        if(!this.isEmpty()){
            return head.getCustomer();
        }
        else{
            throw new NullPointerException("This queue is empty");
        }
    }

    //retrieves first element of the queue
    public Node getHead(){
        return head;
    }


    //returns size of queue
    public int size(){
        return size;
    }


    //Determines whether or not the queue is empty
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }



    /*
    toString method that helps print out the content of each restaurant
     */
    public String toString() {
        String list = "{ ";
        if (head != null) {
            Node c = head;
            while (c != null) {
                list = list + " [" + c.toString() + "], ";
                c = c.getLink();
            }
            list += "}";
        }
    return list;
    }

}
