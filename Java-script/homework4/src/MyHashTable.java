/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: yuanyuaz
 * @author Yuanyuan Zhou
 */
public class MyHashTable implements MyHTInterface {
    /**
     * instance variable.
     * the size of the array
     */
    private int length;

    /**
     * the underlying data structure is array.
     */
    private DataItem[] hashArray;

    /**
     * instance variable.
     * number of collision
     */
    private int numberOfCollision = 0;

    /**
     * instance variable.
     * initial capacity
     */
    private int capacity;

    /**
     * load factor.
     * load factor = length/capacity
     */
    private double loadFactor;

    /**
     * mark the deleted item.
     */
    private static final DataItem DELETED = new DataItem(null, 0);


    /**
     * constructor.
     */
    public MyHashTable() {
        hashArray = new DataItem[10];
        length = 0;
        numberOfCollision = 0;
        capacity = 10;

    }
    /**
     * Constructor.
     * @param initialCapacity int type
     */
    public MyHashTable(int initialCapacity) {
        capacity = initialCapacity;
        if (initialCapacity <= 0) {
            throw new RuntimeException("Invalid initial capacity!");
        }
        hashArray = new DataItem[initialCapacity];
        length = 0;
        numberOfCollision = 0;
    }

    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    @Override
    public void insert(String value) {
        if (value == null || !value.matches("[a-zA-Z]+")) {
            return;
        }

        insertHelper(value, 1);
        loadFactor = ((double) length) / ((double) capacity);
        if (loadFactor > 0.5) {
            rehash();
        }
    }

    /**
     * help method for insert.
     * @param value string
     * @param frequency int
     */
    private void insertHelper(String value, int frequency) {
        int finalHashVal = hashValue(value);
        int hashVal = finalHashVal;
        if (hashVal == -1) {
            return;
        }
        boolean collisionFlag = false;

        //insert a same value
         while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
            if (hashArray[hashVal].getValue().equals(value)) {
                hashArray[hashVal].frequency = hashArray[hashVal].frequency + 1;
                return;
            }
            if (hashValue(hashArray[hashVal].getValue()) == finalHashVal
                    && hashArray[hashVal].getValue() != value) {
                collisionFlag = true;
            }
            hashVal++;
            hashVal = hashVal % capacity;
        }


        hashArray[hashVal] = new DataItem(value, frequency);
        length++;

        if (collisionFlag) {
            numberOfCollision++;
        }

    }


    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    @Override
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (hashArray[i] == null) {
                System.out.print("** ");
            } else if (hashArray[i] == DELETED) {
                System.out.print("#DEL# ");
            } else {
                System.out.print("[");
                System.out.print(hashArray[i].getValue());
                System.out.print(", ");
                System.out.print(hashArray[i].getFre());
                System.out.print("] ");
            }
        }
        System.out.println();
    }

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    @Override
    public boolean contains(String key) {
        if (key == null || (!key.matches("[a-zA-Z]+"))) {
            return false;
        }
        int hashVal = hashValue(key);
        if (hashVal == -1) {
            return false;
        }
        int k = 0;
        int tempValue = hashVal + k;
        while (hashArray[tempValue] != null && k <= capacity) {
            if (hashArray[tempValue].getValue() != null) {
                if (hashArray[tempValue].getValue().equals(key)) {
                    return true;
                }
            }
            k++;
            tempValue = (hashVal + k) % capacity;
        }
        return false;

    }


    /**
     * Returns the number of collisions in relation to insert and rehash.
     * When rehashing process happens, the number of collisions should be properly updated.
     *
     * The definition of collision is "two different keys map to the same hash value."
     * Be careful with the situation where you could overcount.
     * Try to think as if you are using separate chaining.
     * "How would you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    @Override
    public int numOfCollisions() {
        return numberOfCollision;
    }

    /**
     * Returns the hash value of a String.
     * Assume that String value is going to be a word with all lowercase letters.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    @Override
    public int hashValue(String value) {
        if (value == null || (!value.matches("[a-zA-Z]+"))) {
            return -1;

        }
        value = value.toLowerCase();
        return hashFunc(value);
    }

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    @Override
    public int showFrequency(String key) {
        if (key == null || (!key.matches("[a-zA-Z]+"))) {
            return 0;
        } else {
            int hashVal = hashValue(key);
            if (hashVal == -1) {
                return 0;
            }
            while (hashArray[hashVal] != null) {
                if (hashArray[hashVal].getValue() != null) {
                    if (hashArray[hashVal].getValue().equals(key)) {
                        return hashArray[hashVal].getFre();
                    }
                }
                hashVal = hashVal + 1;
                hashVal = hashVal % capacity;
            }
        }
        return 0;

    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    @Override
    public String remove(String key) {
        if (key == null || (!key.matches("[a-zA-Z]+"))) {
            return  null;
        }
        int hashVal = hashValue(key);
        if (!(contains(key))) {
            return null;
        } else {
            hashArray[hashVal] = DELETED;
            length--;
            return hashArray[hashVal].getValue();
        }

    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = 0;
        int n = 27;
        for (int i = 0; i < input.length(); i++) {
            int letterToNumber = (int) (input.charAt(i)) - (int) 'a' + 1;
            hashVal = ((hashVal * n) + letterToNumber) % capacity;
        }
        return hashVal;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        numberOfCollision = 0;
        length = 0;
        capacity = 2 * capacity;
        capacity = rehashHelper(capacity);
        DataItem[] curArray = hashArray;
        hashArray = new DataItem[capacity];

        //insert element one by one into new array
        for (int i = 0; i < curArray.length; i++) {
            if (curArray[i] != DELETED && curArray[i] != null) {
                insertHelper(curArray[i].getValue(), curArray[i].getFre());
            }
        }
        System.out.print("Rehashing ");
        System.out.print(length);
        System.out.print(" items, new size is ");
        System.out.print(capacity);
        System.out.println("");

    }
    /**
     * rehash help method.
     * @param size the current capacity
     * @return size prime number capacity
     */
    private int rehashHelper(int size) {
        boolean isPrime = false;
        if (size <= 0) {
            throw new RuntimeException("Invalid initial capacity!");
        }
        while (!isPrime) {
            if (size % 2 == 0) {
                isPrime = false;
                size++;
            } else {
                isPrime = true;
                for (int i = 3; i < size; i += 2) {
                    if (size % i == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime) {
                    break;
                } else {
                    size++;
                }
            }
        }
        return size;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * constructor.
         * @param key string
         * @param fre int
         */
        public DataItem(String key, int fre) {
            value = key;
            frequency = fre;
        }


        /**
         * return the value in DataItem.
         * @return key string value
         */
        public String getValue() {
            return value;
        }

        /**
         * return the frequency of the value in DataItem.
         * @return frequency int
         */
        public int getFre() {
            return frequency;
        }




    }

}
