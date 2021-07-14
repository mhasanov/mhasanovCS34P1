import student.TestCase;

/**
 * @author mhasanov
 *
 */
public class BigNumArithmeticTest extends TestCase {

  LinkedList<Integer> LL;
  
  public void setUp(){
    LL = new LinkedList<Integer>();
  }
  
  public void testPushAndPop(){
        LL.push(1);
        LL.push(2);
        LL.push(3);
        assertEquals((int)LL.pop(), 3);
        assertEquals((int)LL.pop(), 2);
        assertEquals((int)LL.pop(), 1);
        assertNull(LL.pop());
  }
  
  public void testToString() {
      LL.push(1);
      LL.push(2);
      LL.push(3);
      assertEquals(LL.toString(), "321");  
      }

  public void testGet() {
      LL.push(1);
      LL.push(2);
      LL.push(3);
      assertEquals((int)LL.get(0).getData(), 1);
      assertEquals((int)LL.get(2).getData(), 3);
  }
  
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

  
    

