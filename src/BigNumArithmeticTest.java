import student.TestCase;
import java.io.FileNotFoundException;

/**
 * Test case class for BigNumArithmetic.java
 *
 * @author mhasanov
 * @author leohan95
 * @version 2021.07.18
 */
public class BigNumArithmeticTest
    extends TestCase
{

    private LinkedList<Integer> linkL;
    private LinkedList<Integer> linkL2;

    /**
     * SetUp
     */
    public void setUp()
    {
        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
    }


    /**
     * Tests against SampleInput file
     *
     * @throws FileNotFoundException
     */
    public void testMain()
        throws FileNotFoundException
    {
        String[] s = new String[1];
        s[0] = "src/SampleInput.txt";
        BigNumArithmetic.main(s);
        assertNotNull(s);
    }

    /**
     * Testing push() and pop()
     */
    public void testPushAndPop()
    {
        assertNull(linkL.pop());
        linkL.push(1);
        linkL.push(2);
        linkL.push(3);
        assertEquals((int)linkL.pop(), 3);
        assertEquals((int)linkL.pop(), 2);
        assertEquals((int)linkL.pop(), 1);
        assertNull(linkL.pop());
    }


    /**
     * Testing toString()
     */
    public void testToString()
    {
        linkL.push(1);
        linkL.push(2);
        linkL.push(3);
        assertEquals(linkL.toString(), "321");
    }


    /**
     * Testing get()
     */
    public void testGet()
    {
        linkL.push(1);
        linkL.push(2);
        linkL.push(3);
        assertEquals((int)linkL.get(0).getData(), 1);
        assertEquals((int)linkL.get(2).getData(), 3);
    }


    /**
     * testing getSize()
     */
    public void testGetSize()
    {
        assertEquals(linkL.getSize(), 0);
        linkL.push(1);
        linkL.push(2);
        linkL.push(3);
        assertEquals(linkL.getSize(), 3);
    }


    /**
     * Test method that tests the peek() in LinkedList Compares return value and
     * expected value
     */
    public void testPeek()
    {
        linkL.push(3);
        assertEquals((int)linkL.peek(), 3);
        linkL = new LinkedList<Integer>();
    }


    /**
     * Tests Addition method
     */
    public void testAddition()
    {

        linkL.push(1);
        linkL2.push(2);
        LinkedList<Integer> linkL3 = BigNumArithmetic.addition(linkL, linkL2);
        assertEquals(linkL3.toString(), "3");
        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();
        linkL.push(1);
        linkL.push(2);
        linkL.push(7);
        linkL2.push(1);
        linkL2.push(2);
        linkL2.push(3);

        linkL3.push(2);
        linkL3.push(4);
        linkL3.push(0);
        linkL3.push(1);
        LinkedList<Integer> linkL4 = BigNumArithmetic.addition(linkL, linkL2);
        assertEquals(linkL3.toString(), linkL4.toString());

        linkL2.push(4);
        linkL2.push(5);
        linkL4 = BigNumArithmetic.addition(linkL, linkL2);
        linkL3 = new LinkedList<Integer>();
        linkL3.push(2);
        linkL3.push(4);
        linkL3.push(0);
        linkL3.push(5);
        linkL3.push(5);
        assertEquals(linkL3.toString(), linkL4.toString());
    }


    /**
     * Tests Exponentiation method
     */
    public void testExponentiation()
    {
        linkL.push(2);
        linkL2.push(5);
        LinkedList<Integer> linkL3 =
            BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "32");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(1);
        linkL2.push(1);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "1");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(5);
        linkL2.push(0);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "1");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(20);
        linkL2.push(1);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "20");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(2034);
        linkL2.push(0);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "1");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(3);
        linkL2.push(17);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "129140163");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(3);
        linkL2.push(16);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "43046721");

        linkL = new LinkedList<Integer>();
        linkL2 = new LinkedList<Integer>();
        linkL3 = new LinkedList<Integer>();

        linkL.push(6);
        linkL2.push(20);
        linkL3 = BigNumArithmetic.exponentiation(linkL2, linkL);
        assertEquals(linkL3.toString(), "3656158440062976");

    }


    /**
     * Tests Multiplication method
     */
    public void testMultiplication()
    {
        {
            linkL.push(2);
            linkL2.push(0);
            LinkedList<Integer> linkL3 =
                BigNumArithmetic.multiplication(linkL, linkL2);
            assertEquals(linkL3.toString(), "0");

            linkL = new LinkedList<Integer>();
            linkL2 = new LinkedList<Integer>();
            linkL3 = new LinkedList<Integer>();

            linkL.push(2);
            linkL2.push(2);
            linkL3 = BigNumArithmetic.multiplication(linkL, linkL2);
            assertEquals(linkL3.toString(), "4");

            linkL = new LinkedList<Integer>();
            linkL2 = new LinkedList<Integer>();
            linkL3 = new LinkedList<Integer>();

            linkL.push(999999);
            linkL2.push(0);
            linkL3 = BigNumArithmetic.multiplication(linkL, linkL2);
            assertEquals(linkL3.toString(), "0");

            linkL = new LinkedList<Integer>();
            linkL2 = new LinkedList<Integer>();
            linkL3 = new LinkedList<Integer>();

            linkL = new LinkedList<Integer>();
            linkL2 = new LinkedList<Integer>();
            linkL3 = new LinkedList<Integer>();
            linkL.push(1);
            linkL.push(2);

            linkL2.push(3);
            linkL2.push(4);

            linkL3.push(3);
            linkL3.push(0);
            linkL3.push(9);
            LinkedList<Integer> linkL4 =
                BigNumArithmetic.multiplication(linkL, linkL2);
            assertEquals(linkL3.toString(), linkL4.toString());



        }
    }
}
