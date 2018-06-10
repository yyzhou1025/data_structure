import java.util.HashSet;
import java.util.Set;
/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */

public class Word implements Comparable<Word> {
    /**
     * instance variable.
     * String word
     */
    private String word;
    /**
     * instance variable.
     * line number of the word
     */
    private Set<Integer> index;
    /**
     * instance variable.
     * frequency of the word
     */
    private int frequency;

    /**
     * Constructor with string parameter.
     * @param inputWord string parameter
     */
    public Word(String inputWord) {
        //check if the imputWord is null
        if (null == inputWord) {
            return;
        } else {
            word = inputWord;
            index = new HashSet<Integer>();
            frequency = 1;           
        }
     
    }

    /**
     * The method is to indicates a new line number.
     * for the word that should be added to the index.
     * @param line
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }

    /**
     * The method returns the Set<Integer> data structure that.
     * contains all of the line numbers for the word.
     * @return
     */
    public Set<Integer> getIndex() {
        //create a new HashSet to protect index 
        Set<Integer> newIndex;
        newIndex =  new HashSet<Integer> (index);
        return newIndex;
    }

    /**
     * setter for word.
     * @param inputWord string
     */
    public void setWord(String inputWord) {
        //check if the imputWord is null
        if (null == inputWord) {
            return;
        } else {
            word = inputWord;
        }
    }

    /**
     * getter for word.
     * @return word string
     */
    public String getWord() {
        return word;
    }

    /**
     * setter for frequency of the word.
     * @param FreOfWord
     */
    public void setFrequency(int freOfWord) {
        frequency = freOfWord;
    }

    /**
     * getter for frequency of the word.
     * @return frequency string
     */
    public int getFrequency() {
        return frequency;
    }



    @Override
    public int compareTo(Word inputWord) {
        return word.compareTo(inputWord.getWord());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(word).append(" ").append(frequency).append(" ").append(index);
        return output.toString();
    }
}
