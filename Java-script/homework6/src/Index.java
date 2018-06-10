import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */

public class Index {
    
    /**
     * It pares an input text file (using the same order as they appear in a file). 
     * and build an index tree using a natural alphabetical order.
     * @param fileName
     * @return BST<Word>
     */
    public BST<Word> buildIndex(String fileName) {
        if (fileName == null) {
            return null;
        }
        return buildIndex(fileName, null);
    } 

    /**
     * build an index tree using a specific ordering among words provided by a specific comparator.
     * @param fileName string
     * @param comparator Comparator<Word>
     * @return BST<Word>
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        if (fileName == null) {
            return null;
        }
        BST<Word> bstWords;
        if (comparator == null) {
            bstWords = new BST<Word>();
        } else {
            bstWords = new BST<Word>(comparator);
        }
        Scanner scanner = null;
        int numOfLine = 0;
        try {
            scanner = new Scanner(new File(fileName), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numOfLine++;            
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                   if (word == null || word.equals("")) {
                       continue;
                   }
                   if (word.matches("[a-zA-Z]+")) {
                       if (comparator instanceof IgnoreCase) {
                           word = word.toLowerCase();
                       }
                       Word searchWord = bstWords.search(new Word(word));
                       if (searchWord == null) {
                           Word inputWord = new Word(word);
                           inputWord.addToIndex(numOfLine);
                           bstWords.insert(inputWord);
                       } else {
                           searchWord.addToIndex(numOfLine);
                           searchWord.setFrequency(searchWord.getFrequency()+1);
                       }
                   }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return  bstWords;
    } 


    /**
     * allows to rebuild an index tree using a different ordering specified by a comparator.
     * @param list ArrayList<Word>
     * @param comparator Comparator<Word>
     * @return   BST<Word>
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        if (list == null) {
            return null;
        }
        BST<Word> bstWords;
        if (comparator == null) {
            bstWords = new BST<Word>();
        } else {
            bstWords = new BST<Word>(comparator);
        }
        for (Word inputWord : list) {
            bstWords.insert(inputWord);
        }

        return bstWords;

    } 

    /**
     * Sort words by alphabetically.
     * @param tree  BST<Word>
     * @return ArrayList<Word>
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        /*
         * Even though there should be no ties with regard to words in BST,
         * in the spirit of using what you wrote, 
         * use AlphaFreq comparator in this method.
         */
        if (tree == null) {
            return null;
        }
        ArrayList<Word> words = new ArrayList<Word>();
        for (Word word : tree) {
            words.add(word);
        }
        words.sort(new AlphaFreq());
        
        return words;
    }


    /**
     * sort by frequency.
     * @param tree BST<Word>
     * @return ArrayList<Word> 
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        if (tree == null) {
            return null;
        }
        ArrayList<Word> words = new ArrayList<Word>();
        for (Word word : tree) {
            words.add(word);
        }
        words.sort(new Frequency());

        return words;
    }

    /**
     * get highest frequency.
     * @param tree BST<Word> 
     * @return ArrayList<Word>
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        if (tree == null) {
            return null;
        }
        ArrayList<Word> wordsbyFre = sortByFrequency(tree);
        ArrayList<Word> highestFreq = new ArrayList<Word>();
        int hFre = wordsbyFre.get(0).getFrequency();
        for (Word word : wordsbyFre) {
            if (word.getFrequency() == hFre) {
                highestFreq.add(word);
            } else {
                break;
            }
        }
        return highestFreq;
    }

}
