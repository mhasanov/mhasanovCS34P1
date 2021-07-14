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
        assertEquals(LL.pop(), 3);
        assertEquals(LL.pop(), 2);
        assertEquals(LL.pop(), 1);
        assertEquals(LL.pop(), null);
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
      assertEquals(LL.get(0).data, 1);
      assertEquals(LL.get(2).data, 3);
      assertEquals(LL.get(5).data, null);
  }
  
  public void testGetSize() {
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
        assertEquals(lstack.peek(), 3);
        LL = new LinkedList<Integer>();
        assertEquals(lstack.peek(), null);
    }
}

  
    

