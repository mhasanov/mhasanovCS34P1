import student.TestCase;

/**
 * Test case class for BigNumArithmetic.java
 *
 * @author mhasanov, leohan95
 *
 */
public class BigNumArithmeticTest extends TestCase {

  LinkedList<Integer> LL;

  /**
   * SetUp
   */
  public void setUp(){
    LL = new LinkedList<Integer>();
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
        assertEquals(LL.pop(), null);
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
      assertEquals((int)LL.get(0).data, 1);
      assertEquals((int)LL.get(2).data, 3);
      assertEquals((int)LL.get(5).data, null);
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
}
