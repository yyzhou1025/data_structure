/**
 * The code is for binary search.
 * @author YuanyuanZhou
 *
 */
public class BinarySearch {
	/**
	 * This class shouldn't be instantiated.
	 */
    private BinarySearch() { }
    
    /**
     * Return index of the specified key in the specified Array.
     * @param data the array of numbers, must be stored in ascending order
     * @param key the search key
     * @return index of key in the array if present, -1 if not present 
     */
    public static int BinarySearch(int[] data, int key) {
		int lowerBound = 0;
		int upperBound = data.length -1;
		int mid;
		
		while(true) {
			if (lowerBound > upperBound) {
				return -1;
			}
			
			mid = (lowerBound + upperBound)/2;
			if (data[mid] == key) {
				return mid;
			}
			if (data[mid] < key) {
				lowerBound = mid +1;
			} else {
				upperBound = mid -1;
			}
		}
    }

    
    public static void main(String args[]) {
    	    int[] data = {2, 3, 4, 6, 7, 8};
    	    //System.out.println("please input the key: ");
    	    int key = Integer.parseInt(args[0]);
    	    int result = BinarySearch.BinarySearch(data, key);
    	    if (result == -1) {
    	    	    System.out.println("element not present!");
    	    } else {
    	    	    System.out.println("element found at index " + result);
    	    }
    	    
    }
}


