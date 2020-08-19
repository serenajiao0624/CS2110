
/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

    /**
     * Set up compare method for sorting photographs based on ratings in a descending order.
     * 
     * @param p1 First photograph for comparing
     * @param p2 Second photograph for comparing
     */
    public int compare(Photograph p1, Photograph p2) {
        if (p1.getRating() == p2.getRating())   // if the ratings are the same
            return p1.getCaption().compareToIgnoreCase(p2.getCaption());   // compare the captions
        else if (p1.getRating() <= p2.getRating())  // Sort the photos in a descending order
            return 1;
        else
            return -1;

    }

}
