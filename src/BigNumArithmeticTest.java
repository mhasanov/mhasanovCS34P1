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
  
}
