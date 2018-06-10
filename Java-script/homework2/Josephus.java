import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 2 Solve Josephus problem
 * with different data structures
 * and different algorithms and compare running times
 *
 * Andrew ID: yuanyuaz
 * @author   Yuanyuan Zhou
 * I prefer to use the third method (use the LinkedList as "List"), because it's faster than the other two.
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     * @throws Exception
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here

        /**
         * size should be bigger than 0.
         */
        if (size <= 0) {
            throw new RuntimeException("size should be bigger than 0");
        }

        /**
         * rotation should be greater than 0.
         */
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be bigger than 0");
        }

        if (size == 1) {
            return 1;
        }
        //create a Queue
        ArrayDeque<Integer> theList = new ArrayDeque<Integer>(size);

        //add people to the Queue
        for (int i = 1; i <= size; i++) {
            theList.addLast(i);
        }

        //System.out.println(theList.size());

        //delete special people until only one person in the Queue
        while (theList.size() > 1) {
            int remove = 0;
            if (rotation % theList.size() != 0) {
                remove = rotation % theList.size();
            }
            if (rotation % theList.size() == 0) {
                remove = theList.size();
            }
            //System.out.println(remove);

            for (int i = 0; i < remove - 1; i++) {
                int data = theList.removeFirst();
                theList.add(data);

            }

            theList.removeFirst();
        }

        //return the only item using peek
        return theList.peek();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        /**
         * size should be bigger than 0.
         */
        if (size <= 0) {
            throw new RuntimeException("size should be bigger than 0");
        }

        /**
         * rotation should be greater than 0.
         */
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be bigger than 0");
        }

        if (size == 1) {
            return 1;
        }
        //create a Linkedlist
        LinkedList<Integer> theList = new LinkedList<Integer>();

        //add people to the Linkedlist
        for (int i = 1; i <= size; i++) {
            theList.add(i);
        }
        while (theList.size() > 1) {
            int remove = 0;
            if (rotation % theList.size() != 0) {
                remove = rotation % theList.size();
            }
            if (rotation % theList.size() == 0) {
                remove = theList.size();
            }
            //System.out.println(remove);

            for (int i = 0; i < remove - 1; i++) {
                int data = theList.removeFirst();
                theList.add(data);
            }

            theList.removeFirst();
        }

        //return the only item using peek
        return theList.peek();

    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        /**
         * size should be bigger than 0.
         */
        if (size <= 0) {
            throw new RuntimeException("size should be bigger than 0");
        }

        /**
         * rotation should be greater than 0.
         */
        if (rotation <= 0) {
            throw new RuntimeException("rotation should be bigger than 0");
        }

        //create a linkelist
        LinkedList<Integer> theList = new LinkedList<Integer>();

        //add people to the linkedlist
        for (int i = 1; i <= size; i++) {
            theList.add(i);
        }

        //remove
        int index = 0;
        while (theList.size() > 1) {
            int remove = 0;
            if (rotation % theList.size() != 0) {
                remove = rotation % theList.size();
            }
            if (rotation % theList.size() == 0) {
                remove = theList.size();
            }
            //System.out.println(remove);
            index = (remove + index - 1) % theList.size();

            theList.remove(index);
        }

        return theList.get(0);
    }

}
