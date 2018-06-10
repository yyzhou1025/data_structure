/**
 * This code is for homework1.
 * @author Yuanyuan Zhou
 * Andrew ID: yuanyuaz
 *
 */
public class MyArray {

    /**
     * instance variable.
     * The size of the array
     */
    private int size = 0;

    /**
     * instance variable.
     * The capacity of the array
     */
    private int capacity = 0;

    /**
     * data structure.
     * String[] array
     */
    private String[] words = new String[capacity];

    /**
     * Constructor.
     * @param initialCapacity int type
     */
    public MyArray(int initialCapacity) {
        this.capacity = initialCapacity;
    }

    /**
     * add method, add new word "text" into the array.
     * @param text String type
     * O(n)
     */
    public void add(String text) {
        if (null == text) {
            return;
        } else {
            if (text.matches("[a-zA-Z]+")) {
                ensureCapacity(size + 1);
                words[size] = text;
                size = size + 1;
            }
        }

    }

    /**
     * Increases the capacity of the array, if necessary.
     * To ensure that it can hold at least the number if words
     * @param minCapacity the desired minimum capacity
     * O(n)
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = words.length;
        if (minCapacity > oldCapacity) {
            String[] oldWords = words;
            if (oldCapacity == 0) {
                capacity = 1;
            } else {
                capacity = 2 * oldCapacity;
            }
            if (capacity < minCapacity) {
                capacity = minCapacity;
            }
            words = new String[capacity];
            System.arraycopy(oldWords, 0, words, 0, size);
        }
    }

    /**
     * To search whether there is the key in the array.
     * @param key the search key
     * @return boolean type
     * O(n)
     */
    public boolean search(String key) {
        int i = 0;
        while (i < size) {
            if (words[i].equals(key)) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }
    /**
     * To get the size of the array.
     * @return int size
     * O(1)
     */
    public int size() {
        return size;
    }

    /**
     * To get the capacity of the array.
     * @return int capacity
     * O(1)
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * print all the words in one line and put a space between words.
     * O(n)
     */
    public void display() {
        for (int i = 0; i < size - 1; i++) {
                System.out.print(words[i] + " ");
        }
        System.out.print(words[size - 1]);
        System.out.println("");
    }

    /**
     * remove duplicates in the array.
     * O(n^3)
     */
    public void removeDups() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (words[i].equals(words[j])) {
                    for (int z = j + 1; z < size; z++) {
                        words[z - 1] = words[z];
                    }
                    size = size - 1;
                    j = j - 1;
                    }
            }

        }
    }

}
