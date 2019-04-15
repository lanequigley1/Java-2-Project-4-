package common;
import java.util.Comparator;

import mediaTypes.Media;
/**
 * Provides implementation of the Comparator and allows comparison of titles and years based on the users
 * desired sort type
 *
 */
public class MediaComparator implements Comparator<Media>{
	/**Stores a value which decides how the list will be sorted**/
	private int sortCriterion;
	/**The int value if the user decides to sort by title**/
	final static int SORT_BY_TITLE = 0;
	/**Sort value if the user decides to sort by year**/
	public final static int SORT_BY_YEAR = 1;
	
	/**
	 * Constructs an object that allows you to compare between two media objects
	 * @param sortCriterion The value which decides how the user will sort
	 */
	public MediaComparator(int sortCriterion){
		this.sortCriterion = sortCriterion;
	}
	
	/**
	 * Overrides compare and allows for sorting comparisons
	 */
	@Override
	public int compare(Media o1, Media o2) {
		int result;
		int modifiedYear1 = o1.getStartYear();
		int modifiedYear2 = o2.getStartYear();
		if(modifiedYear1 == -1){
			modifiedYear1 = Integer.MAX_VALUE;
		}
		if(modifiedYear2 == -1){
			modifiedYear2 = Integer.MAX_VALUE;
		}
		if(sortCriterion == SORT_BY_TITLE){
			result = o1.getTitle().compareTo(o2.getTitle());
			if(result == 0){
				result = modifiedYear1 - modifiedYear2;
			}
		}else{
			result = modifiedYear1 - modifiedYear2;
			if(result == 0){
				result = o1.getTitle().compareTo(o2.getTitle());
			}
		}
		return result;
	}
}
