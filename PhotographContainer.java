
/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

import java.util.ArrayList;

/**
 * PhotographContainer class implements contains common methods that are used by multiple child classes. It allows users
 * to add, remove, count, search photos and such.
 */

public abstract class PhotographContainer {

    /**
     * FIELDS Define name, an arraylist of photos
     */
    protected String name;
    protected ArrayList<Photograph> photos = new ArrayList<Photograph>();

    /**
     * An empty constructor for the abstract class
     */
    public PhotographContainer() {

    }

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param aName the updated name Hold the arraylist of photos
     */
    public PhotographContainer(String aName) {
        this.name = aName;
        this.photos = new ArrayList<Photograph>();
    }

    // GETTERS AND SETTERS

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return an updated name
     */
    public String getName() {
        return name;
    }

    /**
     * @return an arraylist of photos
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /**
     * Check if photograph p is in photos. If the photo is already in, return false If the photograph is null, return false
     * as well else, return true and add the photo in
     * 
     * @param p a photo that needs to be added
     * @return a boolean regarding if p is added
     */
    public boolean addPhoto(Photograph p) {
        if (!photos.contains(p) && p != null && Photograph.allowedPhotos(p)== true) {
            photos.add(p);   // if it doesn't contain p and is not null, add it to the arraylist
            return true;
        }
        return false;
    }

    /**
     * Check if p is in the photograph arraylist. If the photograph is in the arraylist, return true. Else, return false.
     * 
     * @param p A photo
     * @return A boolean regarding if p exists
     */
    public boolean hasPhoto(Photograph p) {
        if (photos.contains(p) && p != null) {
            return true;
        }
        return false;
    }

    /**
     * This method is used for deleting photographs. We first check if there's photograph p in a person's photograph
     * arraylist and if it's null. If there is, we remove the photo and return true after removing the photo. Else, return
     * false.
     * 
     * @param p A photo
     * @return A boolean regarding if p has been removed
     */
    public boolean removePhoto(Photograph p) {
        if (photos.contains(p) && p != null) {
            photos.remove(p);
            return true;
        }
        return false;
    }

    /**
     * This method return the size of the arraylist through method .size()
     * 
     * @return The number of photos in a given arraylist
     */
    public int numPhotographs() {
        return photos.size();
    }

    /**
     * Use an equals method to check if two albums are the same. We first check the type of Object o. Then we cast o to type
     * Album and compare the albumName of two objects .
     * 
     * @param o An object o
     * @return A boolean regarding if it is the same album
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Album) {  // Check if o is of type Album
            return (this.name.equals(((Album) o).getName()));
        }
        return false;
    }

    /**
     * toString() returns the album and the filename of the photos under it.
     * 
     * @return Album name, and the filename of photos
     */
    @Override
    public String toString() {
        ArrayList<String> photoFilename = new ArrayList<String>();  // Create an empty list for storing filenames
        for (Photograph pic : photos)
            photoFilename.add(pic.getFilename());   // add each filename under the album to the arrayList
        return "Name: " + name + "\n" + "Filenames of photos contained in this album: " + photoFilename;
    }
    

    /**
     * This method finds photos that are above a given rating
     * 
     * @param rating
     * @return an arraylist of photos that have a rating >= the given rating
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> ratedPhotos = new ArrayList<Photograph>();
        if (rating < 0 || rating > 5)   // if argument is of an illegal type
            return null;
        for (Photograph pic : photos) {   // loop through all photos in the photo library
            if ((pic.getRating() >= rating) && (rating >= 0 && rating <= 5))
                ratedPhotos.add(pic);  // add photos that have a rating higher than the specified rating
        }
        return ratedPhotos;  // return the arrayList

    }

    /**
     * Method for getting photos based on the year. The method takes the parameter of the year
     * 
     * @param year
     * @return an arraylist of photos that have a year >= the given year
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> datedPhotos = new ArrayList<Photograph>();
        for (Photograph pic : photos) {
            if (Photograph.allowedPhotos(pic) == false) // check if the argument matches the format
                return null;   // if not, return null
            else { // check the format of the date
                String date = pic.getDateTaken();
                String[] dateList = date.split("-");
                int yearOfPic = Integer.valueOf(dateList[0]);   // get the value of year
                
                if (year == yearOfPic)
                    datedPhotos.add(pic);
            }
        }
        return datedPhotos;
    }

    /**
     * Method for getting photos based on the year and the month. We want to check that the arguments are legal through a
     * format checking. The method takes the parameter of the year and the month
     * 
     * @param month
     * @param year
     * @return an arraylist of photos that have a year >= the given year and a month >= the given month
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> datedPhotos1 = new ArrayList<Photograph>();
        for (Photograph pic : photos) {
            if (Photograph.allowedPhotos(pic) == false)
                return null;
            else { // check the format of the date
                String date = pic.getDateTaken();
                String[] dateList = date.split("-");
                int yearOfPic = Integer.valueOf(dateList[0]);   // get the value of year
                int monthOfPic = Integer.valueOf(dateList[1]);  // get the value of month
                
                if (year == yearOfPic && month == monthOfPic)
                    datedPhotos1.add(pic);
            }
        }
        return datedPhotos1;
    }

    /**
     * Method for getting photos based on dates. We want to check that the arguments are legal through a format checking.
     * 
     * @param beginDate
     * @param endDate
     * @return an arraylist of photos that are between the two dates
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> datedPhotos2 = new ArrayList<Photograph>();
        if (!beginDate.matches("^\\d{4}-\\d{2}-\\d{2}$") || !endDate.matches("^\\d{4}-\\d{2}-\\d{2}$"))
            return null;   // if the format is wrong, return null
        String[] dateListBegin = beginDate.split("-");
        int yearOfBeginDate = Integer.valueOf(dateListBegin[0]);   // get the value of year
        int monthOfBeginDate = Integer.valueOf(dateListBegin[1]);  // get the value of month
        int dayOfBeginDate = Integer.valueOf(dateListBegin[2]);  // get the value of day
        String[] dateListEnd = endDate.split("-");
        int yearOfEndDate = Integer.valueOf(dateListEnd[0]);   // get the value of year
        int monthOfEndDate = Integer.valueOf(dateListEnd[1]);  // get the value of month
        int dayOfEndDate = Integer.valueOf(dateListEnd[2]);    // get the value of day
        // check the each is within reasonable ranges. If not, return null
        if (yearOfBeginDate < 1900 || yearOfBeginDate > 2019 || monthOfBeginDate < 1 || monthOfBeginDate > 12
                || dayOfBeginDate < 1 || dayOfBeginDate > 31 || yearOfEndDate < 1900 || yearOfEndDate > 2019 || monthOfEndDate < 1
                || monthOfEndDate > 12 || dayOfEndDate < 1 || dayOfEndDate > 31)
            return null;
        // Convert the dates to double for comparing
        double doubleBeginDate = yearOfBeginDate * 1.0 + monthOfBeginDate / 12.0 + dayOfBeginDate / 365.0;
        double doubleEndDate = yearOfEndDate * 1.0 + monthOfEndDate / 12.0 + dayOfEndDate / 365.0;
        if (doubleBeginDate > doubleEndDate)  // if the begin date is after the end date
            return null;  // return null

        for (Photograph pic : photos) {
            String date = pic.getDateTaken();
            String[] dateList = date.split("-");
            int yearOfPic = Integer.valueOf(dateList[0]);   // get the value of year
            int monthOfPic = Integer.valueOf(dateList[1]);  // get the value of month
            int dayOfPic = Integer.valueOf(dateList[2]);    // get the value of day
            double doubleDate = yearOfPic * 1.0 + monthOfPic / 12.0 + dayOfPic / 365.0;  // convert date to a double
            if (doubleDate >= doubleBeginDate && doubleDate <= doubleEndDate)  // if the date of a photo is within the given range
                datedPhotos2.add(pic);   // add that picture to the datedPhoto list
        }
        return datedPhotos2;
    }

}
