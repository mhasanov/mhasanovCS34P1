import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author mhasanov
 *
 */

//On my honor:
// - I have not used source code obtained from another student,
//   or any other unauthorized source, either modified or
//   unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
//   with anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.


public class BigNumArithmetic {
    
    private static LinkedList<LinkedList<Integer>> myStack;

    /*
     * Main method ran by console comand, uses helper method
     */
    public static void main(String[] args) throws FileNotFoundException {
        mainMethod(args);
    }
    
    
    /**
     * Helper method for main
     * Checks number of arguments
     * And passes filename down to next method
     * 
     * @param args      Array of args to be passed by console
     * @throws FileNotFoundException
     */
    public static void mainMethod(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "Exactly one argument needed: file-input");
        }
        String inputFilePath = args[0];
        printCalculations(inputFilePath);
    }
    
    /**
     * Handles reading file, and writing output.
     * Calls helper for help with calculating expression
     * 
     * @param filepath  Name of input sfile
     * @throws FileNotFoundException
     */
    public static void printCalculations(String filepath) throws FileNotFoundException {
        Scanner scanIn = new Scanner(new File(filepath));
        while (scanIn.hasNextLine()) {
            String line = scanIn.nextLine();
            
            //Line is blank ? skip
            if (line == "") {
                continue;
            }
            
            //Print the line
            System.out.println("Input Line: " + line);
            System.out.print(" = ");
            String result = expressionCalculator(line);
            System.out.print(result);
            
        }
        scanIn.close();
            
    }
    
    /**
     * Reads the expression and passes each part to other functions
     * 
     * @param line
     * @return  empty string if wrong expression, else string containing answer
     */
    private static String expressionCalculator(String line) {
        myStack = new LinkedList<LinkedList<Integer>>();
        //Break line in parts
        String[] words = line.split(" ");
        
        //For each part of expression
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            //If a number
            if (word.matches("[0-9]+")) {
                char[] splitZero = stripZero(word.toCharArray());
                int strLen = splitZero.length;
                LinkedList<Integer> myLL = new LinkedList<Integer>();
                for(int j = 0; j < strLen; j++) {
                    myLL.push(Integer.parseInt(String.valueOf(splitZero[j])));

                }
                myStack.push(myLL);
            }
            else {
                //If operator run function, but only if stack has more than 1
                //otherwise we skip to next line w/continue;
                if (myStack.getSize() < 2) {
                    return "";
                }
                operator(word);
            }
        }
        
        //When done with line        
        //Pop
        LinkedList<Integer> answer = myStack.pop();
        String s = answer.toString();
        
        //Check if stack is empty and answer exists
        if (myStack.pop() == null && answer != null) {
            return s;
        }
        return "";
    }
    
    /**
     * Strips extra zeros from number, unless number is all zeros
     * 
     * @param original  char array
     * @return  char array without zeros
     */
    private static char[] stripZero(char[] original) {
        boolean z = true;
        for (char ch: original) {
            if (ch != '0') {
                z = false;
                break;
            }
        }
        if (z) {
            char[] r = new char[1];
            r[0] = '0';
            return r;
        }
        
        int zeros = 0;
        
        while (zeros < original.length && original[zeros] == '0') {
            zeros++;
        }
        char[] r = new char[original.length - zeros];
        
        System.arraycopy(original, zeros, r, 0, r.length);
        
        return r;
    }    
    
    /*
     * Determines which operation to run
     */
    private static void operator(String word) {
        if (word == "+") {
            addition(myStack.pop(), myStack.pop());
        }
        else if (word == "*") {
            multiplication();
        }
        else if (word == "^") {
            exponentiation();
        }
    }

    
    
    private static void addition(LinkedList<Integer> LL1, LinkedList<Integer> LL2) {

        int upperBound = Math.max(LL1.getSize(), LL2.getSize());
        
        LinkedList<Integer>  LL3 = new LinkedList<Integer>();
        int result = 0;
        int cary = 0;
        for (int i = 0; i < upperBound; i++) {
            
            Object a = LL1.get(i);
            Object b = LL2.get(i);
            
            if (a.equals(null) && b.equals(null)) {
                break;
            }
            else if (a.equals(null)) {
                a = 0;
            }
            else if (b.equals(null)) {
                b = 0;
            }
            
            result = ((int)a + (int)b + cary) % 10;
            cary = ((int)a + (int)b + cary) / 10;
            
            LL3.push(result);
        }
        if (cary == 1) {
            LL3.push(cary);
        }
        myStack.push(LL3);
    }
    
    private static void exponentiation() {
        
        
        
    }


    private static void multiplication() {
        // TODO Auto-generated method stub
        
    }



    
    
}
