/**
 * LinkedList class that defines the data structure. Used to organize the
 * characters into LinkedList format. Also used as Stack format.
 *
 * @author mhasanov
 * @author leohan95
 * @version 2021.7. 18.
 * @param <T>
 *            Generic type used for the Node<T> field
 */
public class LinkedList<T>
{

    private Node<T> tail;
    private int     size;

    // ----------------------------------------------------------
    /**
     * Constructor for this class. Creates a new LinkedList object.
     */
    public LinkedList()
    {
        tail = null;
        size = 0;
    }


    /**
     * Adds a new node to LL. The new node will be updated as the tail. The size
     * is incremented to keep track of the number of the nodes of the Linked
     * List.
     * @param value Value to be pushed in linked list.
     *
     */
    public void push(T value)
    {
        Node<T> temp = tail;
        tail = new Node<T>(value, temp);
        size++;
    }


    /**
     * Returns the data of the tail node, and deletes the tail. The size is
     * decremented to keep track of the number of the nodes of the Linked List.
     * @return T The value that will be returned.
     */
    public T pop()
    {
        Node<T> temp = tail;
        if (tail == null)
        {
            return null;
        }
        tail = tail.getNext();

        T value = temp.getData();

        size--;
        return value;
    }


    /**
     * Return the Linked List as a string of elements contained separated by
     * spaces.
     *
     * @return returns LinkedList in a order of coherent order.
     */
    public String toString()
    {
        String ans = "";
        for (int i = size - 1; i >= 0; i--)
        {
            ans += get(i).data.toString();
        }
        return ans;
    }


    /**
     * Returns the Node at the index Returns null if out of bounds
     * @param index he index intended to search

     * @return Node<T> The corresponding node with given index
     */
    public Node<T> get(int index)
    {
        Node<T> temp = tail;

        for (int i = size - 1; i > index; i--)
        {
            temp = temp.next;
        }

        return temp;
    }


    /**
     * Returns the number of nodes in the Linked List
     *
     * @return int The size variable of the Linked List
     */
    public int getSize()
    {
        return this.size;
    }


    /**
     * Returns the data of the tail node
     *
     * @return T The data of the tail node
     */
    public T peek()
    {
        return tail.data;
    }

    /**
     * Node class that defines the methods and fields that are used to create
     * the LinkedList
     *
     * @author mhasanov
     * @author leohan95
     * @version 2021. 7. 15.
     * @param <T> @param generic type of data that used in the node
     */
    public class Node<T>
    {
        private T       data;
        private Node<T> next;

        /**
         * Constructor to create a new node with the data and the next node
         *
         * @param a The data stored in the node
         * @param next The node that is pointed to
         */
        Node(T a, Node<T> next)
        {
            data = a;
            this.next = next;
        }


        /**
         * getter method that returns the data value of this node's data.
         *
         * @return T Returns the corresponding data of the node.
         */
        public T getData()
        {
            return data;
        }

        /**
         * The setter method that sets the value of this node's data
         *
         * @return Node<T> Returns the next node.
         */
        public Node<T> getNext()
        {
            return this.next;
        }
    }

}
