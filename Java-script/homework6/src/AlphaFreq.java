import java.util.Comparator;
/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */

public class AlphaFreq implements Comparator<Word> {
    @Override
    public int compare(Word wA, Word wB) {
        int result = wA.getWord().compareTo(wB.getWord());
        if (result != 0) {
            return result;
        }
        return wA.getFrequency() - wB.getFrequency();
    }

}
