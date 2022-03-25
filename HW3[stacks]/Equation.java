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
    The Equation class contains instance variables equation,prefix,postfix,answer,binary,hex,balanced,counter,
    and an equation stack to manipulate the equation being analyzed. Additionally, the methods decToBin() and
    decToHex() will help convert the final answer to different bases, while the toString() will print out
    the information in a neat format and isBalanced() will determine if the user entered a balanced equation
 */
public class Equation {

    private String equation; //will hold the equation in the stack
    private String prefix; //will hold the prefix version of the equation
    private String postfix; //will hold the postfix version of the equation
    private double answer; //will hold the final answer of the infix equation
    private String binary; //will hold the binary version of the final answer
    private String hex; //will hold the hexadecimal version of the final answer
    private boolean balanced; //places a statement of whether an equation is balanced or not
    private int counter; //counter for the number of the equation
    private Stack<EquationStack> stack; //stack to manipulate the individual equations


    /*
    Constructor for a new equation with specified parameters.
     */
    public Equation(String equation) {
        this.equation = equation;
    }

    /*
    Default constructor for a new equation. No parameters
     */
    public Equation() {

    }

    public int solve(String equation){
//        EquationStack operators = new EquationStack();
//        EquationStack operands = new EquationStack();
//        for(int i = 0; i<equation.length();i++){
//            String temp = "";
//            while(Character.isDigit(equation.charAt(i))){
//                temp += equation.substring(i,i+1);
//                i++;
//                operands.push(temp);
//            }
//            while(equation.charAt(i)=='+' || equation.charAt(i)=='-' ||equation.charAt(i)=='*' ||equation.charAt(i)=='/' ||
//                    equation.charAt(i)=='%' ||equation.charAt(i)=='^'){
//                //operators.push(charAt(i));
//            }
//            if (equation.charAt(i) == '('){
//                operators.push("(");
//            }
//            else if(equation.charAt(i)==')'){
//
//            }
//
//            operands.push(temp);
//        }
        return 0;
    }

    //sets balanced to a boolean value;
    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }




    //returns the hexadecimal value of the answer
    public String getHex() {
        return hex;
    }



    //setter method for the hex variable and will assist with assigning the hexadecimal value
    public void setHex(String hex) {
        this.hex = hex;
    }




    //returns the binary value of the answer (base 2)
    public String getBinary() {
        return binary;
    }




    //setter for the binary value
    public void setBinary(String binary) {
        this.binary = binary;
    }



    //returns the final answer of an equation
    public double getAnswer() {
        return answer;
    }




    //returns counter and increments the value
    public int getCounter() {
        return counter;
    }



    //setter for the counter method
    public void setCounter(int counter) {
        this.counter = counter;
    }



    //setter for the final answer of the equation
    public void setAnswer(double answer) {
        this.answer = answer;
    }


    //getter method to retrieve the postfix expression of the equation
    public String getPostfix() {
        this.postfix = postfix;
        String postfixExpression = "";
        EquationStack stack = new EquationStack();
        for (int i = 0; i < equation.length(); i++) {
            if (Character.isDigit(equation.charAt(i))) {
                postfixExpression += equation.charAt(i);
            } else if (equation.charAt(i) == '(') {
                stack.push("(");
            } else if (equation.charAt(i) == ')') {
                String top = stack.pop();
                while (top != "(") ;
                {
                    postfixExpression += top;
                    top = stack.pop();
                }
            } else if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/' ||
                    equation.charAt(i) == '^' || equation.charAt(i) == '%') {
                String top = stack.peek();
                String temp = Character.toString(equation.charAt(i));
                while (priority(top) >= priority(temp)) {
                    postfixExpression += stack.pop();
                    top = stack.peek();
                }
                stack.push(temp);
            }
        }
        return postfix;
    }


    //setter for the postfix
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }


    //converts the equation to a prefix expression
    public String getPrefix() {
        this.prefix = prefix;
        String postfixExpression = "";
        String reverseInfix = "";
        String prefix = "";
        for(int i = equation.length()-1; i>=0; i--){
            reverseInfix += equation.substring(i,i+1);
        }
        EquationStack stack = new EquationStack();
        for (int i = 0; i < reverseInfix.length(); i++) {
            if (Character.isDigit(reverseInfix.charAt(i))) {
                postfixExpression += reverseInfix.charAt(i);
            } else if (reverseInfix.charAt(i) == '(') {
                stack.push("(");
            } else if (reverseInfix.charAt(i) == ')') {
                String top = stack.pop();
                while (top != "(") ;
                {
                    postfixExpression += top;
                    top = stack.pop();
                }
            } else if (reverseInfix.charAt(i) == '+' || reverseInfix.charAt(i) == '-' || reverseInfix.charAt(i) == '*' || reverseInfix.charAt(i) == '/' ||
                    reverseInfix.charAt(i) == '^' || reverseInfix.charAt(i) == '%') {
                String top = stack.peek();
                String temp = Character.toString(reverseInfix.charAt(i));
                while (priority(top) >= priority(temp)) {
                    postfixExpression += stack.pop();
                    top = stack.peek();
                }
                stack.push(temp);
            }
        }
        for(int i = postfixExpression.length()-1;i>=0;i--){
            prefix += postfixExpression.substring(i,i+1);
        }
        return prefix;
    }


    //setter for the prefix conversion
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }



    //returns the string of the equation
    public String getEquation() {
        return equation;
    }





    //setter method for the equation
    public void setEquation(String equation){
            this.equation = equation;
        }



        //priority method that determines the order of priority for operators
    public int priority(String operator){
        switch(operator){
            case "^": return 3;

            case "*": return 2;

            case "/": return 2;

            case "%": return 2;

            case "+": return 1;

            case "-": return 1;
        }
        return -1;
    }







    /*
    Determines if an equation is balanced or not by determining if the number of left
    parentheses and right parentheses are equal to each other
     */
    public boolean isBalanced() {
        int counter1 = 0;
        int counter2 = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '(') {
                counter1++;
            } else if (equation.charAt(i) == ')') {
                counter2++;
            }
        }
        if(counter1==counter2) {
           System.out.println("The equation is balanced");
           return true;
        }
        else{
            System.out.println("The equation is not balanced");
            return false;
        }
    }







    /*Will convert the decimal answer that is inputted into the method into
    a binary version of it (base 2). Throws unbalancedException if theequation
    is unbalanced
     */
    public String decToBin(int number) throws UnbalancedException{
        String binstring = "";
        int currentnumber = number;
        if (balanced == true) {
            while(currentnumber!=0){
                int remainder = currentnumber %  2;
                String literal = Integer.toString(remainder);
                binstring  = literal + binstring;
                currentnumber /= 2;
            }
            while(binstring.length()%4!=0){
                binstring = "0" + binstring;
            }
        }
        else{
            throw new UnbalancedException("Cannot convert because the equation is unbalanced");
        }
        binary=binstring;
        return binary;
    }






    /*
    Will convert the decimal answer that is inputted into the method into
    a binary version of it (base 2)
     */
    public String decToHex(int number) throws UnbalancedException{
        String binary = decToBin(number);
        String s = "";
            int length = binary.length();
        if(balanced==true){
            if(length%4==0){
                int countByFour = 0;
                String[] parsedBinary = new String[length/4];
                for(int i = 0; i<parsedBinary.length;i++){
                    parsedBinary[i]=binary.substring(countByFour,countByFour+4);
                    System.out.println(i + " " + parsedBinary[i]);
                    s = s + fourBitToHex(parsedBinary[i]);
                }
            }
        }
        else{
            throw new UnbalancedException("Cannot convert because the equation is unbalanced");
        }
        hex=s;
        return s;
    }





    //this method converts every four bits into a hexidecimal. This method will mainly be a helper method
    //to assist with converting a decimal to a hexidecimal.
    public static String fourBitToHex(String bits) {
        String convertedChar = "";
        if (bits.equals("0000")) {
            convertedChar = "0";
        }
        else if (bits.equals("0001")) {convertedChar = "1"; } else if (bits.equals("0010")) { convertedChar = "2";}else if (bits.equals("0011")) {convertedChar = "3"; }
        else if (bits.equals("0100")) {convertedChar = "4"; }else if (bits.equals("0101")) {convertedChar = "5"; }else if (bits.equals("0110")) { convertedChar = "6";}
        else if (bits.equals("0111")) {convertedChar = "7"; }else if (bits.equals("1000")) {convertedChar = "8"; }else if (bits.equals("1001")) {convertedChar = "9"; }
        else if (bits.equals("1010")) {convertedChar = "A"; }else if (bits.equals("1011")) {convertedChar = "B"; }else if (bits.equals("1100")) {convertedChar = "C"; }
        else if (bits.equals("1101")) {convertedChar = "D"; }else if (bits.equals("1110")) {convertedChar = "E"; }else if (bits.equals("0001")) {convertedChar = "F"; }
        return convertedChar;
    }





    /*Creates a format and organizes an equation with it's converted expressions and
    answers in different bases
    */
    public String toString(){
        return getCounter() + "\t" + getEquation() + "\t\t\t" +
                getPrefix() + "\t\t\t" + getPostfix() + "\t\t\t"
                + getAnswer() + "\t\t\t" + getBinary() + "\t\t\t" +
                getHex();
    }



}
