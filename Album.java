
/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

import java.util.ArrayList;

public class Album extends PhotographContainer {

    /**
     * Album class inherits the abstract PhotographContainer class. Class constructor: initiate the name of album and a list
     * of photos
     * 
     * @param name
     */
    public Album(String name) {
        this.name = name;
        this.photos = new ArrayList<Photograph>();

    }

}
