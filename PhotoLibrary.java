
/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

import java.util.ArrayList;
import java.util.HashSet;

/**
 * PhotoLibrary class stores all the photos under each user. It helps the user to organize the photos based on ratings,
 * year, date, etc. And it allows users to create new albums, add photos to an album, remove photos etc.
 */
public class PhotoLibrary extends PhotographContainer {

    /**
     * FIELDS Define the final id, and a hashset of albums
     */
    private final int id;
    private HashSet<Album> albums = new HashSet<>();

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param name An updated name
     * @param id   An updated user ID
     */
    public PhotoLibrary(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // GETTERS AND SETTERS
    /**
     * @return The user ID
     */
    public int getId() {
        return id;
    }

    /**
     * @param name Set the name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * @return A hashset of albums
     */
    public HashSet<Album> getAlbums() {
        return albums;
    }
    // END OF GETTERS AND SETTERS

    /**
     * Method for creating a new album under a user's photolibrary
     * 
     * @param albumName
     * @return a boolean regarding if an album has been successfully created
     */
    public boolean createAlbum(String albumName) {
        if (!albums.contains(getAlbumByName(albumName))) { // if the album with an entered name does not exist
            Album newAlbum = new Album(albumName);  // Create a new Album object
            albums.add(newAlbum);   // add the new object with the albumName to the albums list
            return true;   // return true if added successfully
        }
        return false;
    }

    /**
     * Method for removing an album from a list of albums
     * 
     * @param albumName
     * @return a boolean regarding if an album has been successfully removed
     */
    public boolean removeAlbum(String albumName) {
        for (Album a : albums) {
            if (a.getName().equals(albumName)) {   // If one album has the same name as the album that the user wants to delete
                albums.remove(a);  // remove the album from albums
                return true;
            }
        }
        return false;
    }

    /**
     * Method for adding a photo to a given album
     * 
     * @param p
     * @param albumName
     * @return a boolean regarding if a photo has been added to the given album
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (getAlbumByName(albumName) == null)    // if the albumName doesn't exist, return null
            return false;
        if (photos.contains(p))  // if the photo exists in the user's photolibrary
            return getAlbumByName(albumName).addPhoto(p);  // add the photo to the album with the given name
        return false;
    }

    /**
     * Method for removing a photo from a given album.
     * 
     * @param p
     * @param albumName
     * @return a boolean regarding if a photo has been removed from the given album
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        for (Album a : albums) {
            if (a.getName().equals(albumName) && a.getPhotos().contains(p)) {
                return a.removePhoto(p);  // if the photo exists in the given album and the album with the given name exists
            }
        }
        return false;
    }

    /**
     * Method for getting an album via a given name
     * 
     * @param albumName
     * @return the album object that matches the given album name
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : albums) {
            if (a.getName().equals(albumName)) {  // if the album name is equal to the given String albumName
                return a; // return the album
            }
        }
        return null;  // if the album does not exist, return null
    }

    /**
     * Method for deleting a photo from albums AND the photo library
     * 
     * @param p A photo to be removed
     * @return a boolean regarding if a photo has been removed from the given album and user's library
     */
    @Override
    public boolean removePhoto(Photograph p) {
        if (photos.contains(p)) { // if the picture exists in the photo library
            photos.remove(p); // remove the picture
            for (Album a : albums) {
                if (a.getPhotos().contains(p)) {  // if it also exists in an album
                    a.getPhotos().remove(p);   // remove it from the album
                }
            }
            return true;
        } else
            return false;
    }

    /**
     * Method for checking if two photo libraries have the same user ID
     * 
     * @param o An object o
     * @return A boolean regarding if the user IDs are the same
     */
    public boolean equals(Object o) {
        if (o instanceof PhotoLibrary) {  // Check if o is of type PhotoLibrary
            return (this.id == ((PhotoLibrary) o).getId());
        }
        return false;
    }

    /**
     * toString method returns all name, user id, all the photos, and a list of album names.
     * 
     * @return name, ID, and a list of album names
     */
    public String toString() {
        ArrayList<String> albumName = new ArrayList<String>(); // create an empty list for storing album names
        for (Album a : albums) {
            albumName.add(a.getName());
        }
        return "Name: " + name + "\n" + "ID: " + id + "\n" + "Photos: " + photos + "\n" + "List of album names: " + albumName;
    }

    /**
     * Method for finding a list of shared photos between two photo libraries.
     * 
     * @param a Photolibrary a
     * @param b Photolibrary b
     * @return An arraylist containing the shared photos
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> commonPics = new ArrayList<>();   // defining a new arraylist for storing common photos
        for (Photograph pic : a.photos) {   // looping through Person a's photo list
            for (Photograph pic2 : b.photos) {  // looping through Person b's phone list
                if (pic.equals(pic2)) {    // evaluating the two variables with the equals() method defined above
                    commonPics.add(pic);
                }
            }
        }   // if true, add the shared photo to the new commonPics<>
        return commonPics;
    }

    /**
     * Find the smaller number of photos among the two people's photograph lists and return the ratio. We first determine if
     * any of the arrayLists is empty and return 0.0 if it's empty. Then we find the smaller value. The ratio is found by
     * dividing the number of common photos by the smaller value
     * 
     * @param a Photolibrary a
     * @param b Photolibrary b
     * @return A double indicating the ratio of similarity
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        if (a.numPhotographs() == 0 || b.numPhotographs() == 0) // if any person's arraylist is empty
        {
            return 0.0;     // return 0.0
        }
        double smaller = a.numPhotographs() / 1.00;   // define a new double variable for finding the smaller number; make it a
                                                      // double
        if (b.numPhotographs() < smaller) // if the size of person b's arraylist is smaller than a's
        {
            smaller = b.numPhotographs();   // make it the new 'smaller'
        }
        double numSimilarity = commonPhotos(a, b).size() / smaller;  // find the size of the commonPhotos and divide it by smaller
        return numSimilarity;   // return the ratio
    }
}
