import java.util.Comparator;
/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */

public class Frequency implements Comparator<Word> {
    @Override
    public int compare(Word wA, Word wB) {
        return wB.getFrequency() - wA.getFrequency();
    }


}
