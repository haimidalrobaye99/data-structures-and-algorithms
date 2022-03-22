
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #4
CSE 214
Recitation R10, TA: Daniel Calabria
 */



/*
The node class will help form customers into a node that contains
the data being used in the implementation of a linked list as a
queue
 */
public class Node {

    private Customer customer; //customer in each node
    private Node link; //link to obtain each node(customer)

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public Node(Customer customer) {
        this.customer = customer;
    }

    public String toString() {
        return customer.toString();
    }
}

