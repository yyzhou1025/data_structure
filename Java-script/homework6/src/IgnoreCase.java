import java.util.Comparator;
/**
 * 08722 Data Structures for Application Programmers.
 * @author  Yuanyuan Zhou
 *  Andrew ID: yuanyuaz
 */

public class IgnoreCase implements Comparator<Word> {
    @Override
    public int compare(Word wA, Word wB) {
        return wA.getWord().toLowerCase().compareTo(wB.getWord().toLowerCase());
    }

}
