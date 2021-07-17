import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author mhasanov, leohan95
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
            if (line.trim().isEmpty()) {
                continue;
            }
            
            //Print the line

            line = line.trim();
            
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                System.out.print(words[i] + " ");
            }
            System.out.print("= ");
            String result = expressionCalculator(line);
            System.out.println(result);
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
        String[] words = line.split("\\s+");
        

        //For each part of expression
        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            
            //If a number
            if (word.matches("[0-9]+")) {
                char[] splitZero = stripZero(word.toCharArray());

                int strLen = splitZero.length;
                LinkedList<Integer> myLL = new LinkedList<Integer>();
                for(int j = strLen-1 ; j >= 0; j--) {
                    myLL.push(Integer.parseInt(String.valueOf(splitZero[j])));

                }
                myStack.push(myLL);

            }
            
            else if (word.equals("+") || word.equals("*") || word.equals("^")) {
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
        if (myStack.getSize() == 0 && answer != null) {
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


        if (word.equals("+")) {
            myStack.push(addition(myStack.pop(), myStack.pop()));
        }
        else if (word.equals("*")) {


            myStack.push(multiplication(myStack.pop(), myStack.pop()));
        }
        else if (word.equals("^")) {
            myStack.push(exponentiation(myStack.pop(), myStack.pop()));
        }
    }


    
    
    public static LinkedList<Integer> addition(LinkedList<Integer> LL1, LinkedList<Integer> LL2) {

        int upperBound = Math.max(LL1.getSize(), LL2.getSize());
        
        LinkedList<Integer>  LL3 = new LinkedList<Integer>();
        
        int result = 0;
        int cary = 0;
        int a, b;
        for (int i = 0; i < upperBound; i++) {
            
            if (upperBound < i) {
                break;
            }
            if (LL1.getSize() <= i) {
                a = 0;
            }
            else {
                a = LL1.get(i).getData();
            }
            if (LL2.getSize() <= i) {
                b = 0;
            }
            else {
                b = LL2.get(i).getData();
            }
            
            result = (a + b + cary) % 10;
            cary = (a + b + cary) / 10;
            
            LL3.push(result);
            
        }
        if (cary == 1) {
            LL3.push(cary);
        }
        return LL3;
    }
    
    /**
     * Processes the exponentiation using the exponentiation by squaring algorithm.
     * x^n will be calculated differently depending on whether n is even or odd.
     *
     * @param LL2 the x variable
     *        LL1 the n variable
     * @return  LL3 returning LinkedList output variable to x^n
     */
    public static LinkedList<Integer> exponentiation(LinkedList<Integer> LL2, LinkedList<Integer> LL1) {
        LinkedList<Integer>  LL3 = new LinkedList<Integer>();
        LinkedList<Integer>  LL4 = new LinkedList<Integer>();
        LinkedList<Integer>  temp = new LinkedList<Integer>();
        boolean num = false; //TRUE for odd, FALSE for even

        int bSize = LL2.getSize();
        int n = 0;

        if(bSize == 1 && LL2.get(bSize-1).getData() == 1) { //if n is 1, return x
            return LL1;
        }

        if(bSize == 1 && LL2.get(bSize-1).getData() == 0) { //if n is 0, return 1
            LL3.push(1);
            return LL3;
        }

        if(LL2.get(bSize-1).getData() % 2 == 1) { //if n is odd
            num = true;
        }
        if(num) { //if n is odd

            for(int i=0; i < bSize; i++) { //LL3 = n-1
                if(i == 0) {
                    LL3.push(LL2.get(i).getData() - 1);
                }
                else {
                    LL3.push(LL2.get(i).getData());
                }

            }
            for(int j=0; j < bSize; j++) { //(n-1)/2
                LL4.push((int)Math.floor(LL3.get(j).getData()/2));
            }

            for(int k=0; k < LL4.getSize(); k++){
                n += LL4.get(k).getData() * Math.pow(10, k);

            }


            LL3 = new LinkedList<Integer>(); //Reset LL3 to reuse for x^2
            LL3 = BigNumArithmetic.multiplication(LL1, LL1); //LL3 = x^2
            temp = LL3;

            for(int x=0; x < n-1; x++) {
                LL3 = BigNumArithmetic.multiplication(LL3, temp);

            }

            LL3 = BigNumArithmetic.multiplication(LL3, LL1);//x(x^2)
        }
        else {

            for(int j=0; j < bSize; j++) { //(n-1)/2
                LL4.push((int)Math.floor(LL2.get(j).getData()/2));
            }

            for(int k=0; k < LL4.getSize(); k++){
                n += LL4.get(k).getData() * Math.pow(10, k);
            }

            LL3 = new LinkedList<Integer>(); //Reset LL3 to reuse for x^2
            LL3 = BigNumArithmetic.multiplication(LL1, LL1); //LL3 = x^2
            temp = LL3;

            for(int x=0; x < n-1; x++) {
                LL3 = BigNumArithmetic.multiplication(LL3, temp);
            }
        }
       return LL3;
    }


    public static LinkedList<Integer> multiplication(LinkedList<Integer> LL1, LinkedList<Integer> LL2) {
{

        LinkedList<Integer> LL3 = new LinkedList<Integer>();
        LinkedList<Integer> LL4;
        LL3.push(0);

        int aSize = LL1.getSize();
        int bSize = LL2.getSize();
        int a, b;
        int carry = 0;
        int pushed;

        if ((aSize == 1 && LL2.get(0).getData() == 0)
            || (bSize == 1 && LL2.get(0).getData() == 0))
        {
            return LL3;
        }

        for (int i = 0; i <= aSize - 1; i++)
        {
            LL4 = new LinkedList<Integer>();
            for (int z = 0; z < i; z++)
            {
                if (i > 0)
                {
                    LL4.push(0);
                }
            }

            for (int j = 0; j <= bSize - 1; j++)
            {
                b = LL1.get(i).getData();
                a = LL2.get(j).getData();

                pushed = (a * b + carry) % 10;
                carry = (a * b + carry) / 10;

                LL4.push(pushed);

                if (j == bSize - 1)
                {
                    if (carry != 0)
                    {
                        LL4.push(carry);
                    }
                    carry = 0;
                }

            }
            LL3 = BigNumArithmetic.addition(LL3, LL4);
        }
        return LL3;
    }



    
    
}
