
public class LinkedList<T> implements ListInterface<T> {
private Node<T> Head;
    private int size = 0;
    
    /**
     * Adds a new node in the LL.
     * 
     * If the LL's firstNode is null, then the first pushed node becomes
     * the firstNode.
     * 
     * If the LL's firstNode is NOT null, then the pushed node goes all the way
     * to the back of the list and added to the end.
     * 
     * The size is incremented once when this method is run successfully.
     * @param value
     */
    public void push(T value) {
        Head = new Node<T>(value, Head);
        
        
    }
    
    
    
    /** 
     * Returns and deletes the last node.
     * 
     * Searches for the last and the second to the last node,
     * naming temp and temp2 respectively.
     * 
     * returns temp for being the last node,
     * and temp2 becomes the new last node.
     * 
     */
    public T pop() {
        T value = Head.data;
        Head = Head.next
        return value;
    }
    
    /**
     * 
     * @param prev_node
     * @param new_data
     */
    public String stringPut() {
        
    }
    
    /**
     * 
     * @param i
     * @return
     */
    public Node<T> get(int i) {
        Node<T> temp = new Node<T>();
        temp = firstNode;
        
        if(i > size) {
            System.out.println("Check the getter input\n");
        }
        else {
            for (int c = 1; c < i; c++) {
                temp = temp.next;
            }
            System.out.println("\nThe data of this node:");
            System.out.println(temp.data);
            System.out.println("\nThe index of this node:");
            System.out.println(i);
            
            return temp;
        }
    }
    
    public int getSize() {
        return this.size;
    }
    

public class Node<T> {
        private T data;
        private Node<T> next;
        
        Node() {
            this.data = null; 
            this.next = null;  
        }
        
        // Constructor to create a new node with the data and 
        Node(T a, Node<T> next) {
            data = a; 
            this.next = null;  
        }
        
        // Constructor to create a new node without the data
        Node(Node<T> next) {
            this.next = next;  
            data = null;
        }
        
        //getter method that returns the data value of this node's data.
        private T getData(Node<T> node) {
            return node.data;
        }
        
        //setter method that sets the  value of this node's data
        private void setData(T data) {
            this.data = data;
        }
        
        // getter method that returns the next node of this node
        private Node<T> getNext() {
            return this.next;
        }
        
        // setter method that defines the pointer that points to the next node.
        private void setNext(Node<T> next) {
            this.next = next;
        }
        
    }

}
