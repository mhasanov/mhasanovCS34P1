import student.TestCase;

/**
 * Test case class for BigNumArithmetic.java
 *
 * @author mhasanov, leohan95
 *
 */
public class BigNumArithmeticTest extends TestCase {

  private LinkedList<Integer> LL;
  private LinkedList<Integer> LL2;


  /**
   * SetUp
   */
  public void setUp(){
    LL = new LinkedList<Integer>();
    LL2 = new LinkedList<Integer>();
  }


  /**
   * Testing push() and pop()
   */
  public void testPushAndPop(){
        LL.push(1);
        LL.push(2);
        LL.push(3);
        assertEquals((int)LL.pop(), 3);
        assertEquals((int)LL.pop(), 2);
        assertEquals((int)LL.pop(), 1);
        assertNull(LL.pop());
  }

  /**
   * Testing toString()
   */
  public void testToString() {
      LL.push(1);
      LL.push(2);
      LL.push(3);
      assertEquals(LL.toString(), "321");
      }

  /**
   * Testing get()
   */
  public void testGet() {
      LL.push(1);
      LL.push(2);
      LL.push(3);
      assertEquals((int)LL.get(0).getData(), 1);
      assertEquals((int)LL.get(2).getData(), 3);
  }

  /**
   * testing getSize()
   */
  public void testGetSize() {
      assertEquals(LL.getSize(), 0);
      LL.push(1);
      LL.push(2);
      LL.push(3);
      assertEquals(LL.getSize(), 3);
  }

  /**
     * Test method that tests the peek() in LinkedList
     * Compares return value and expected value
     */
  public void testPeek() {
      LL.push(3);
      assertEquals((int)LL.peek(), 3);
      LL = new LinkedList<Integer>();
  }
  
  /**
   * Tests Addition method
   */
  public void testAddition() {


      LL.push(1);
      LL2.push(2);
      LinkedList<Integer> LL3 = BigNumArithmetic.addition(LL, LL2);
      assertEquals(LL3.toString(), "3");    
      LL = new LinkedList<Integer>();
      LL2 = new LinkedList<Integer>();
      LL3 =new LinkedList<Integer>();
      LL.push(1);
      LL.push(2);
      LL.push(7);
      LL2.push(1);
      LL2.push(2);
      LL2.push(3);
      
      LL3.push(2);
      LL3.push(4);
      LL3.push(0);
      LL3.push(1);
      LinkedList<Integer> LL4 = BigNumArithmetic.addition(LL, LL2);
      assertEquals(LL3.toString(), LL4.toString());
        
      LL2.push(4);
      LL2.push(5);
      LL4 = BigNumArithmetic.addition(LL, LL2);
      LL3 = new LinkedList<Integer>();
      LL3.push(2);
      LL3.push(4);
      LL3.push(0);
      LL3.push(5);
      LL3.push(5);
      assertEquals(LL3.toString(), LL4.toString());        
    }

    /**
   * Tests Multiplication method
   */
  public void testMultiplication() {
      LL.push(2);
      LL2.push(2);
      LinkedList<Integer> LL3 = BigNumArithmetic.multiplication(LL, LL2);
      assertEquals(LL3.toString(), "4");
      
      
      LL = new LinkedList<Integer>();
      LL2 = new LinkedList<Integer>();
      LL3 =new LinkedList<Integer>();
      LL.push(1);
      LL.push(2);

      LL2.push(3);
      LL2.push(4);
      LL3.push(3);
      LL3.push(0);
      LL3.push(9);
      LinkedList<Integer> LL4 = BigNumArithmetic.multiplication(LL, LL2);
      assertEquals(LL3.toString(), LL4.toString());    
      

      LL2.push(4);
      LL3 =new LinkedList<Integer>();
      LL3.push(3);
      LL3.push(9);
      LL3.push(0);
      LL3.push(9);
      LL4 = BigNumArithmetic.multiplication(LL, LL2);

      System.out.println(LL.toString());

      System.out.println(LL2.toString());
      System.out.println(LL3.toString());
      System.out.println(LL4.toString());
      assertEquals(LL3.toString(), LL4.toString());    
  }


}
