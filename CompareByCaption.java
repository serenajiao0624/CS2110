
/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {

    /**
     * Set up compare method for sorting photographs based on captions in an alphabetical order.
     * 
     * @param p1 First photograph for comparing 
     * @param p2 Second photograph for comparing
     */
    @Override
    public int compare(Photograph p1, Photograph p2) {
        int result = p1.getCaption().compareToIgnoreCase(p2.getCaption());   // compareTo method, ignoring the cases of strings
        if (result == 0)   // if two photos have the same caption
            if (p1.getRating() == p2.getRating())
                return p1.getCaption().compareToIgnoreCase(p2.getCaption());
            else if (p1.getRating() <= p2.getRating()) // if the photos with the same captions have different ratings
                return 1;   // sort them based on ratings in a descending order
            else
                return -1;
        return result;

    }

}
