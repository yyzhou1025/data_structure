
/**
 * It must implement MyListInterface.
 * It does not allow any duplicate at any point.
 * It is a linked structure of nodes (Singly) and each node's data type is String.
 * It is always (or at any point) sorted in an ascending order.
 * @author: Yuanyuan Zhou
 * Andrew ID: yuanyuaz
 */

public class SortedLinkedList implements MyListInterface {
    /**
     * instance variable.
     */
    private Node head;
    /**
     * instance variable.
     * the length of the list
     */
    private int listCount;

    /**
     * constructor with no parameters.
     */
    public SortedLinkedList() {
        listCount = 0;
        head = null;
    }

    /**
     * another constructor that takes unsorted String array as a parameter and build a new SortedLinkedList.
     * @param unsorted String
     */
    public SortedLinkedList(String[] unsorted) {
        listCount = 0;
        if (unsorted == null) {
            head = null;
            listCount = 0;
        }
        sortedList(unsorted, 0);
    }

    /**
     * add values in unsorted array to sortedList.
     * @param unsorted String
     * @param size integer
     */
    private void sortedList(String[] unsorted, int size) {
        if (size < unsorted.length) {
            add(unsorted[size]);
            sortedList(unsorted, size + 1);
        }
            return;
    }

    @Override
    /**
     * @param value.
     */
    public void add(String value) {
        if (value == null) {
            return;
        }
        /*no duplicate word allowed*/
        if (contains(value)) {
            return;
        }
        /*call recursive method for add*/
        head = addMethod(value, head);
        listCount++;
    }


    /**
     * helper method for add(recursive method).
     * It is always (or at any point) sorted in an ascending order.
     * @param value String
     * @param cur Node
     * @return Node
     */
    private Node addMethod(String value, Node cur) {

        if (cur == null) {
            return new Node(value, cur);
        }
        //if add smaller value, insert before cur.data
        if (value.compareTo(cur.data) <= 0) {
            return new Node(value, cur);
        } else {    // if add bigger value, insert after cur.data
            cur.next = addMethod(value, cur.next);
            return cur;
        }
    }

    @Override
    /**
     * return the size of list.
     * @return size int
     */
    public int size() {
        return listCount;
    }


    @Override
    public void display() {
        if (head == null) {
            return;
        }
        System.out.print("[");
        displayMethod(head);
        System.out.println("]");

    }
    /**
     * recursive method for display.
     * base case : current node (head)
     * recursive case: next node (if it's the final value, don't print ",")
     * @param cur current node
     */
    private void displayMethod(Node cur) {
        if (cur == null) {
            return;
        } else {
            if (cur.next != null) {
                System.out.print(cur.data + ", ");
                displayMethod(cur.next);
            }
            if (cur.next == null) {
                System.out.print(cur.data);
            }
        }

    }


    @Override
    /**
     * return true if we can find key in the list, return false otherwise.
     * @param key String
     */
    public boolean contains(String key) {
        if (head == null) {
            return false;
        } else {
            return containMethod(key, head);
        }
    }
    /**
     * recursive method for contains.
     * base case: current node(head)
     * recursive case: nodes after head
     * @param cur Node
     * @param key String
     * @return true or false
     */
    private boolean containMethod(String key, Node cur) {
        if (cur == null) {
            return false;
        }
        if (key.equals(cur.data)) {
            return true;
        } else {
            return containMethod(key, cur.next);
        }

    }

    @Override
    /**
     * @return true if it's empty, false if it's not empty.
     */
    public boolean isEmpty() {
       return listCount == 0;
    }

    @Override
    /**
     * @return string  the remove one.
     */
    public String removeFirst() {
        if (head == null) {
            return null;
        } else {
            String removeone = new String();
            removeone = head.data;
            head = head.next;
            listCount--;
            return removeone;
        }


    }

    @Override
    /**
     * @return string the remove one.
     */
    public String removeAt(int index) {
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        //check if the index id valid.
        if (index < 0 || index > listCount) {
            return null;
        }
        return removeMethod(head, index);


    }
    /**
     * recursive method for remove according to index.
     * base case: index = 1;
     * recursive case: index > 1.
     * @param cur Node
     * @param index integer
     * @return string the remove one
     */
    private String removeMethod(Node cur, int index) {
        if (index == 1) {
            listCount = listCount - 1;
            String removeone = new String();
            removeone = cur.next.data;
            cur.next = cur.next.next;
            return removeone;
        }

        return removeMethod(cur.next, index - 1);
    }

    /**
     * static nested Node class.
     */
    private static class Node {

        /**
         * String variable.
         * The data in the linkedlist
         */
        private String data;

        /**
         * Node.
         */
        private Node next;

        /**
         * Constructor.
         * @param d String
         * @param n Node
         */
        public Node(String d, Node n) {
            data = d;
            next = n;
        }
    }

}
