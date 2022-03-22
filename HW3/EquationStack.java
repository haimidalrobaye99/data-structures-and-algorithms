/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #3
CSE 214
Recitation R10, TA: Daniel Calabria
 */

import java.util.*;


/*
    EquationStack extends Stack<String> and contains a constrcutor for an equation stack,
    a push method to insert operators and operands into the stack, a pop method to
    remove the top element in the stack, an isEmpty() method to check if the
    stack is empty, and a size method to count the amount of elements in the stack
 */
public class EquationStack extends Stack<String>{



    //Constructor for an equation stack
    public EquationStack(){
        super();
    }


    //push method to help enter a value into the stack
    public String push(String s){
        super.push(s);
        return s;
    }


    //removes and pops the value at the top of the stack
    public String pop(){
        return super.pop();
    }

    //boolean method that returns whether the stack is empty or not
    public boolean isEmpty(){
        return super.isEmpty();
    }

    //returns size of the equation stack
    public int size(){
        return super.size();
    }

}
