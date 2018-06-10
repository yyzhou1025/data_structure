import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */
/**
 *
 * @param <T> type
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * instance variable.
     * root of the binary search tree
     */
    private Node<T> root;
    /**
     * instance variable.
     * comparator
     */
    private Comparator<T> comparator;

    /**
     * constructor for BST.
     */
    public BST() {
        this(null);
    }

    /**
     * constructor with comp as parameter.
     * @param comp Comparator<T>
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * constructor for comparator.
     * @return comparator
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * getter method for root.
     * @return root
     */
    public T getRoot() {
        if (null == root) {
            return null;
        }
        return root.data;
    }

    /**
     * getter method for height.
     * it must be recursively.
     * @return height 
     */
    public int getHeight() {
        if (null == root) {
            return 0;
        } else {
            return getHeightHelp(root);
        }
    }

    /**
     * help method for getHeight().
     * @param inputRoot Node<T>
     * @return height int
     */
    private int getHeightHelp(Node<T> inputRoot) {
        //if inputRoot == null, height = 0, base case  
        if (null == inputRoot) {
            return 0;
        }
        // BST only have the root, no children, height = 0, base case
        if (inputRoot.left == null && inputRoot.right == null) {
            return 0;
        }
        //recursive case
        return Math.max(getHeightHelp(inputRoot.right), 
                getHeightHelp(inputRoot.left)) + 1;      
    }

    /**
     * get the number of nodes in the BST.
     * @return numOfNodes
     */
    public int getNumberOfNodes() {
        return getNumberOfNodesHelp(root);
    }

    /**
     * help method for getNumberOfNodes().
     * @param inputRoot Node<T>
     * @return numOfNodes
     */
    private int getNumberOfNodesHelp(Node<T> inputRoot) {
        //root = 0, node = 0, base case
        if (inputRoot == null) {
            return 0;
        }
        //BST only have one root, node = 1, base case
        if (inputRoot.left == null && inputRoot.right == null) {
            return 1;
        }
        //recursive case
        return 1 + getNumberOfNodesHelp(inputRoot.right) + 
                getNumberOfNodesHelp(inputRoot.left);
    }



    @Override
    public T search(T toSearch) {
        //check toSearch is null or not
        if (null == toSearch) {
            return null;
        }
        //check root is null or not
        if (null == root) {
            return null;
        }

        return searchHelp(root, toSearch);
             
    }
    /**
     * help method for search().
     * @param inputRoot Node<T>
     * @param toSearch T
     * @return
     */
    private T searchHelp(Node<T> inputRoot, T toSearch) {
        //check if the toSearch is valid
        if (null == toSearch) {
            return null;
        }
        //not found, base case
        if (null == inputRoot) {
            return null;
        }
        //found it, base case
        if (comparator == null && toSearch.compareTo(inputRoot.data) == 0
                || comparator != null && comparator.compare( toSearch, inputRoot.data) == 0) {
            return inputRoot.data;
        }
        //recursive case
        if (comparator == null && toSearch.compareTo(inputRoot.data) > 0
                || comparator != null && comparator.compare(toSearch, inputRoot.data) > 0) {
            return searchHelp(inputRoot.right, toSearch);
        } else {
            return searchHelp(inputRoot.left, toSearch);
        }


    }



    @Override
    public void insert(T toInsert) {
        if (null == toInsert) {
            return;
        }
        if (null == root) {
            root = new Node<>(toInsert);
        }
        insertHelp(root, toInsert);
    }
    /**
     * help method for insert().
     * @param inputRoot Node<T>
     * @param toInsert T
     */
    private void insertHelp(Node<T> inputRoot, T toInsert) {
        //keep the old one, base case
        if (comparator == null && inputRoot.data.compareTo(toInsert) == 0
                || comparator != null && comparator.compare(inputRoot.data, toInsert) == 0) {
            return;
        }
        // recursive case
        if (comparator == null && inputRoot.data.compareTo(toInsert) > 0
                || comparator != null && comparator.compare(inputRoot.data, toInsert) > 0) {
            //base case
            if (inputRoot.left == null) {
                inputRoot.left = new Node<T>(toInsert);
            } else {//recursive case 
            insertHelp(inputRoot.left, toInsert);
            }
        } else if (comparator == null && inputRoot.data.compareTo(toInsert) < 0
                || comparator != null && comparator.compare(inputRoot.data, toInsert) < 0) {
            //base case
            if (inputRoot.right == null){
                inputRoot.right = new Node<T>(toInsert);
            } else {//recursive case
                insertHelp(inputRoot.right, toInsert);
            }
        }
 

    }


    @Override
    public Iterator<T> iterator() {
        return new iteratorHelp();
    }
    public class iteratorHelp implements Iterator<T> {
        private Stack<Node<T>> stack;
        /**
         * constructor.
         */
        public iteratorHelp() {
            stack = new Stack<Node<T>>();
            Node<T> temp = root;
            if (temp == null) {
                return;
            }
            
            while(temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
          
        }
        @Override
        public boolean hasNext() {
           if (root == null) {
               return false;
           }
           if (stack.isEmpty()) {
               return false;
           }
           return true;
        }

        @Override
        public T next() {
            if (root == null) {
                return null;
            }
            if (stack.isEmpty()) {
                return null;
            }

            Node<T> next = stack.pop();
            Node<T> temp = next.right;             
                while(temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            return next.data;
        }

    }


    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;
        /**
         * 
         * @param d
         */
        Node(T d) {
            this(d, null, null);
        }
        /**
         * 
         * @param d
         * @param l
         * @param r
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
