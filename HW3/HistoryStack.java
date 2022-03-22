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
    The History class extends Stack<Equation> and contains a constructor,  a push() method
    that returns an equation, a pop() method to remove the top of the stack, an undo() and
    redo() method, a method to return the size of the stack, a method to return an equation based
    on the inputted position, and a toString() method to organize the hisory stack of equations
 */
public class HistoryStack extends Stack<Equation>{

    private Stack<Equation> tempStack = new Stack<>();

    //Constructor for a HistoryStack
    public HistoryStack(){
        super();
    }



    //This method will push a new equation to the top of the history stack
    public Equation push(Equation newEquation){
        return super.push(newEquation);
    }




    //this method will pop the equation at the top of the stack.
    public Equation pop(){
        return super.pop();
    }


    //This will undo an equation from the history stack.
    public void undo(){
        if(super.peek()==null){
            System.out.println("There is nothing to undo");
        }
        else{
            tempStack.push(super.pop());
        }
    }


    //this method will redo the previous undone equation and throw an exception if
    //no previous
    public void redo() throws EquationDoesntExistException{
        if(tempStack.peek()==null){
            throw new EquationDoesntExistException("There are no existing undone equations to redo");
        }
        else{
            super.push(tempStack.pop());
        }
    }


    //returns the size of the history stack
    public int size(){
        return super.size();
    }




    //will traverse through the history stack and show the equation at the entered position
    public Equation getEquation(int position) throws PositionOutOfRangeException{
        if(position>0 && position<=super.size()) {
            Stack<Equation> tempStack = new Stack<>();
            Equation s;
            Equation peek;
            while (super.size() != position) {
                s = super.pop();
                tempStack.push(s);
            }
            peek = super.peek();
            while (tempStack != null) {
                super.push(tempStack.pop());
            }
            return peek;
        }
        else{
            throw new PositionOutOfRangeException("Error: position out of range");
        }
    }


    //toString() method that will format an organized list/stack of equations
    public String toString(){
        Stack<Equation> tempStack = new Stack<>();
        String historyStack = "#   Equation                           Pre-Fix                            Post-Fix                           Answer               Binary  Hexadecimal\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
        while(!tempStack.empty()){
            Equation e=tempStack.pop();
            historyStack += e.toString()+"\n";
        }

        return historyStack;
    }






}