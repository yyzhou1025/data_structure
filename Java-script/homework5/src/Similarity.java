import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 5
 * Andrew ID: yuanyuaz
 * @author Yuanyuan Zhou
 */
public class Similarity {
    /**
     * instance variable.
     * the number of lines
     */
    private int numLines;

    /**
     * instance variable.
     * the number of words
     */
    private BigInteger numWords;

    /**
     * instance variable.
     * the number of no duplicate words
     */
    private int numNoDups;

    /**
     * instance variable.
     * map
     */
    private Map<String, BigInteger> myMap;

    /**
     * Constructor with string.
     * @param string string to analyze
     */
    public Similarity(String string) {
        // initialize variables
        myMap = new HashMap<>();
        numWords = BigInteger.ZERO;
        numLines = 0;
        numNoDups = 0;
        // check if string is null.
        if (string == null) {
            myMap = new HashMap<>();
            numWords = BigInteger.ZERO;
            numLines = 0;
            numNoDups = 0;
            return;
        }
        //check if string is empty.
        if (string.length() == 0) {
            myMap = new HashMap<>();
            numWords = BigInteger.ZERO;
            numLines = 0;
            numNoDups = 0;
            return;
        }
        //add string to map.
        add(string);

    }



    /**
     *  helper method to add.
     * @param string add string to map
     */
    private void add(String string) {
        String[] words = string.split("\\W");
        for (String word : words) {
            if (word.matches("[A-Za-z]+")) {
                String inputword = word.toLowerCase();
                BigInteger freq = myMap.getOrDefault(inputword,
                        BigInteger.ZERO);
                freq = freq.add(BigInteger.ONE);
                myMap.put(inputword, freq);

                numWords = numWords.add(BigInteger.ONE);
            }
        }
        numNoDups = myMap.size();    //key value is identical

    }

    /**
     * Constructor with parameter.
     * @param file file to read
     */
    public Similarity(File file) {
        myMap = new HashMap<>();
        numWords = BigInteger.ZERO;
        numLines = 0;
        numNoDups = 0;
        // check if file is null.
        if (file == null) {
            myMap = new HashMap<>();
            numWords = BigInteger.ZERO;
            numLines = 0;
            numNoDups = 0;
            return;
        }

        // to split text using any non-word character like HW3Driver.java file.
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "latin1");
            while (scanner.hasNextLine()) {
                numLines++;
                String line = scanner.nextLine();
                add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * return number of lines.
     * @return numLines number of lines
     */
    public int numOfLines() {
        return numLines;

    }

    /**
     * return number of words.
     * @return numWords
     */
    public BigInteger numOfWords() {
        return numWords;

    }

    /**
     * return number of distinct words.
     * @return numNoDups
     */
    public int numOfWordsNoDups() {
        return numNoDups;

    }
    /**
     * calculate eculideanNorm.
     * @return euclideanNormHelper(myMap)
     */
    public double euclideanNorm() {
        return euclideanNormHelper(myMap);

    }

    /**
     * help method for euclideanNorm.
     * @param map map
     * @return euclideanSqrt
     */
    private double euclideanNormHelper(Map<String, BigInteger> map) {
        //local variable
        BigInteger euclidean = BigInteger.ZERO;
        if (map == null) {
            return 0.0;
        }

        if (map.isEmpty()) {
            return 0.0;
        }
        for (BigInteger frequency : map.values()) {
            euclidean = euclidean.add(frequency.multiply(frequency));
        }
        double euclideanSqrt = Math.sqrt(euclidean.doubleValue());

        return euclideanSqrt;

    }

    /**
     * calculate dot product.
     * @param map map
     * @return double result
     */
    public double dotProduct(Map<String, BigInteger> map) {

        if (map == null) {
            return 0.0;
        }

        if (map.isEmpty()) {
            return 0.0;
        }

        if (myMap == null) {
            return 0.0;
        }

        if (myMap.isEmpty()) {
            return 0.0;
        }
        BigInteger dotProductResult = BigInteger.ZERO;
        for (String word : myMap.keySet()) {
            if (map.containsKey(word)) {
                dotProductResult = dotProductResult.
                        add(map.get(word).multiply(myMap.get(word)));
            }
        }

        return dotProductResult.doubleValue();
    }

    /**
     * calculate distance.
     * @param map map
     * @return double finalResult
     */
    public double distance(Map<String, BigInteger> map) {

        if (map == null) {
            return Math.PI / 2;
        }

        if (map.isEmpty()) {
            return Math.PI / 2;
        }
        if (myMap.isEmpty()) {
            return Math.PI / 2;
        }
        // if two maps are same
        if (isSamefile(map)) {
            return 0;
        }
        if (map.size() == 0 && myMap.size() == 0) {
            return 0;
        }

        double finalResult;
        double fileA = euclideanNormHelper(map);
        double fileB = euclideanNormHelper(myMap);

        if (fileA == 0 || fileB == 0) {
            return Math.PI / 2;
        }
        finalResult = Math.acos((dotProduct(map)) / (fileA * fileB));
        return finalResult;
    }
    /**
     * help method for distance.
     * @param map map
     * @return false is two files are not same, otherwise return true
     */
    public boolean isSamefile(Map<String, BigInteger> map) {
        if (myMap.size() != map.size()) {
            return false;
        }

        for (String word : myMap.keySet()) {
            BigInteger frequency = myMap.get(word);
            if (!map.containsKey(word) || (!map.get(word).equals(frequency))) {
                return false;
            }
        }
        return true;
    }

    /**
     * return map.
     * @return map
     */
    public Map<String, BigInteger> getMap() {
        //create a new map to return.
        Map<String, BigInteger> newMap = new HashMap<>();
        for (String word : myMap.keySet()) {
            BigInteger frequency = myMap.get(word);
            newMap.put(word, frequency);
        }
        return newMap;
    }

 }
