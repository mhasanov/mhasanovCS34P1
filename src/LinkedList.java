  
public class LinkedList<T> {
    
    private Node<T> tail;
    private int size = 0;
    
    /**
     * Adds a new node to LL.
     * 
     * @param value Value to be added
     */
    public void push(T value) {
        tail = new Node<T>(value, tail);
        size++;
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
        T value = tail.data;
        tail = tail.next;
        size--;
        return value;
    }
    
    /**
     * Return the List as a string of elements contained seperated by spaces.
     */
    public String toString() {
        String ans = "";
        for (int i=size-1; i > 0; i--) {
             ans += get(i).data.toString();
        }
    }
    
    
    
    /**
     * Returns the Node at the index 
     * Returns null if out of bounds 
     *
     * @param i
     * @return
     */
    public Node<T> get(int index) {
        Node<T> temp = tail;
        
        if(index > size) {
            temp = null;
        }
        else {
            for (int i = size - 1; i > index; i--) {
                temp = temp.next;
            }
        }
        
        return temp;
    }
    
    
    public int getSize() {
        return this.size;
    }
  
    public T peek() {
      return tail.data;
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
