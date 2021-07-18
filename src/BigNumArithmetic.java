import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Main class of BigNumArithmetic that runs to open the text file, read each
 * line using the RPN by parts, handles mathematics operations and prints the
 * results.
 *
 * @author mhasanov
 * @author leohan95
 * @version 2021.07.18
 * @throws FileNotFoundException
 */
public class BigNumArithmetic
{

    private static LinkedList<LinkedList<Integer>> myStack;

    // ----------------------------------------------------------
    /**
     * Main method ran by console command, uses helper method
     *
     * @param args
     *            Array of args to be passed by console
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException
    {
        mainMethod(args);
    }


    /**
     * Helper method for main Checks number of arguments And passes filename
     * down to next method
     *
     * @param args
     *            Array of args to be passed by console
     * @throws FileNotFoundException
     */
    public static void mainMethod(String[] args)
        throws FileNotFoundException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException(
                "Exactly one argument needed: file-input");
        }
        String inputFilePath = args[0];
        printCalculations(inputFilePath);
    }


    /**
     * Handles reading file, and writing output. Calls helper for help with
     * calculating expression
     *
     * @param filepath
     *            Name of input sfile
     * @throws FileNotFoundException
     */
    public static void printCalculations(String filepath)
        throws FileNotFoundException {
        Scanner scanIn = new Scanner(new File(filepath));
        while (scanIn.hasNextLine()) {
            String line = scanIn.nextLine();

            // Line is blank ? skip
            if (line.trim().isEmpty()) {
                continue;
            }

            // Print the line

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
     * @return empty string if wrong expression, else string containing answer
     */
    private static String expressionCalculator(String line)
    {
        myStack = new LinkedList<LinkedList<Integer>>();
        // Break line in parts
        String[] words = line.split("\\s+");

        // For each part of expression
        for (int i = 0; i < words.length; i++)
        {

            String word = words[i];

            // If a number
            if (word.matches("[0-9]+"))
            {
                char[] splitZero = stripZero(word.toCharArray());

                int strLen = splitZero.length;
                LinkedList<Integer> mylinkL = new LinkedList<Integer>();
                for (int j = strLen - 1; j >= 0; j--)
                {
                    mylinkL
                        .push(Integer.parseInt(String.valueOf(splitZero[j])));

                }
                myStack.push(mylinkL);

            }

            else if (word.equals("+") || word.equals("*") || word.equals("^"))
            {

                // If operator run function, but only if stack has more than 1
                // otherwise we skip to next line w/continue;
                if (myStack.getSize() < 2)
                {
                    return "";
                }

                operator(word);

            }
        }

        // When done with line
        // Pop
        LinkedList<Integer> answer = myStack.pop();
        String s = answer.toString();

        // Check if stack is empty and answer exists
        if (myStack.getSize() == 0 && answer != null)
        {
            return s;
        }
        return "";
    }


    /**
     * Strips extra zeros from number, unless number is all zeros
     *
     * @param original
     *            char array
     * @return char array without zeros
     */
    private static char[] stripZero(char[] original)
    {
        boolean z = true;
        for (char ch : original)
        {
            if (ch != '0')
            {
                z = false;
                break;
            }
        }
        if (z)
        {
            char[] r = new char[1];
            r[0] = '0';
            return r;
        }

        int zeros = 0;

        while (zeros < original.length && original[zeros] == '0')
        {
            zeros++;
        }
        char[] r = new char[original.length - zeros];

        System.arraycopy(original, zeros, r, 0, r.length);

        return r;
    }


    /**
     * Determines which operation to run by reading the keywords.
     *
     * @param word
     *            recognized characters from the line of strings
     */
    private static void operator(String word)
    {

        if (word.equals("+"))
        {
            myStack.push(addition(myStack.pop(), myStack.pop()));
        }
        else if (word.equals("*"))
        {

            myStack.push(multiplication(myStack.pop(), myStack.pop()));
        }
        else if (word.equals("^"))
        {
            myStack.push(exponentiation(myStack.pop(), myStack.pop()));
        }
    }


    // ----------------------------------------------------------
    /**
     * A method that processes addition operation. Takes each digit from the
     * LinkedList and sums, adding the carry to the next digits.
     *
     * @param linkL1
     *            First operand to be added.
     * @param linkL2
     *            Second operand to be added.
     * @return linkL3 Returns the sum of both operands.
     */
    public static
        LinkedList<Integer>
        addition(LinkedList<Integer> linkL1, LinkedList<Integer> linkL2)
    {

        int upperBound = Math.max(linkL1.getSize(), linkL2.getSize());

        LinkedList<Integer> linkL3 = new LinkedList<Integer>();

        int result = 0;
        int cary = 0;
        int a;
        int b;
        for (int i = 0; i < upperBound; i++)
        {

            if (upperBound < i)
            {
                break;
            }
            if (linkL1.getSize() <= i)
            {
                a = 0;
            }
            else
            {
                a = linkL1.get(i).getData();
            }
            if (linkL2.getSize() <= i)
            {
                b = 0;
            }
            else
            {
                b = linkL2.get(i).getData();
            }

            result = (a + b + cary) % 10;
            cary = (a + b + cary) / 10;

            linkL3.push(result);

        }
        if (cary == 1)
        {
            linkL3.push(cary);
        }

        return linkL3;
    }


    /**
     * Processes the exponentiation using the exponentiation by squaring
     * algorithm. x^n will be calculated differently depending on whether n is
     * even or odd.
     *
     * @param linkL2 the x variable, base variable
     * @param linkL1 the n variable, exponent variable
     * @return linkL3 returning LinkedList output variable to x^n
     */
    public static
        LinkedList<Integer>
        exponentiation(LinkedList<Integer> linkL2, LinkedList<Integer> linkL1)
    {
        LinkedList<Integer> linkL3 = new LinkedList<Integer>();
        LinkedList<Integer> linkL4 = new LinkedList<Integer>();
        LinkedList<Integer> temp = new LinkedList<Integer>();
        boolean num = false; // TRUE for odd, FALSE for even

        int bSize = linkL2.getSize();
        int n = 0;

        if (bSize == 1 && linkL2.get(bSize - 1).getData() == 1)
        { // if n is 1,
          // return x
            return linkL1;
        }

        if (bSize == 1 && linkL2.get(bSize - 1).getData() == 0)
        { // if n is 0,
          // return 1
            linkL3.push(1);

            return linkL3;
        }

        if (linkL2.get(bSize - 1).getData() % 2 == 1)
        { // if n is odd
            num = true;
        }
        if (num)
        { // if n is odd

            for (int i = 0; i < bSize; i++)
            { // linkL3 = n-1
                if (i == 0)
                {
                    linkL3.push(linkL2.get(i).getData() - 1);
                }
                else
                {
                    linkL3.push(linkL2.get(i).getData());
                }

            }
            for (int j = 0; j < bSize; j++)
            { // (n-1)/2
                linkL4.push((int)Math.floor(linkL3.get(j).getData() / 2));
            }

            for (int k = 0; k < linkL4.getSize(); k++)
            {
                n += linkL4.get(k).getData() * Math.pow(10, k);

            }

            linkL3 = new LinkedList<Integer>(); // Reset linkL3 to reuse for x^2
            linkL3 = BigNumArithmetic.multiplication(
                linkL1, linkL1); // linkL3 = x*x = x^2
            temp = linkL3;

            for (int x = 0; x < n - 1; x++)
            {
                linkL3 = BigNumArithmetic.multiplication(linkL3, temp);

            }

            linkL3 = BigNumArithmetic.multiplication(
                linkL3, linkL1); // x(x^2)
        }
        else
        {

            for (int j = 0; j < bSize; j++)
            { // (n-1)/2
                linkL4.push((int)Math.floor(linkL2.get(j).getData() / 2));
            }

            for (int k = 0; k < linkL4.getSize(); k++)
            {
                n += linkL4.get(k).getData() * Math.pow(10, k);
            }

            linkL3 = new LinkedList<Integer>(); // Reset linkL3 to reuse for x^2
            linkL3 = BigNumArithmetic.multiplication(linkL1, linkL1); // linkL3
                                                                      // = x^2
            temp = linkL3;

            for (int x = 0; x < n - 1; x++)
            {
                linkL3 = BigNumArithmetic.multiplication(linkL3, temp);
            }
        }

        return linkL3;
    }


    // ----------------------------------------------------------
    /**
     * A method that processes multiplication. Each digit of a operand will be
     * multiplied by each of the other operand, and then will be added together.
     *
     * @param linkL1
     *            A operand to be multiplied
     * @param linkL2
     *            A operand to be multiplied
     * @return linkL3 A linked list that represent the mutiplication of linkL1
     *         and linkL2
     */
    public static
        LinkedList<Integer>
        multiplication(LinkedList<Integer> linkL1, LinkedList<Integer> linkL2)
    {

        LinkedList<Integer> linkL3 = new LinkedList<Integer>();
        LinkedList<Integer> linkL4;
        linkL3.push(0);

        int aSize = linkL1.getSize();
        int bSize = linkL2.getSize();
        int a;
        int b;
        int carry = 0;
        int pushed;

        if ((aSize == 1 && linkL1.get(0).getData() == 0)
            || (bSize == 1 && linkL2.get(0).getData() == 0))
        {
            return linkL3;
        }

        for (int i = 0; i <= aSize - 1; i++)
        {
            linkL4 = new LinkedList<Integer>();
            for (int z = 0; z < i; z++)
            {
                if (i > 0)
                {
                    linkL4.push(0);
                }
            }

            for (int j = 0; j <= bSize - 1; j++)
            {
                b = linkL1.get(i).getData();
                a = linkL2.get(j).getData();

                pushed = (a * b + carry) % 10;
                carry = (a * b + carry) / 10;

                linkL4.push(pushed);

                if (j == bSize - 1)
                {
                    if (carry != 0)
                    {
                        linkL4.push(carry);
                    }
                    carry = 0;
                }

            }
            linkL3 = BigNumArithmetic.addition(linkL3, linkL4);
        }

        return linkL3;
    }
}
