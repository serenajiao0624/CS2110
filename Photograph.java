import java.io.File;

/**
 * Homework 4
 * 
 * @author Serena Jiao, yj5qe Sources: Notes, Big Java book
 */

/**
 * Photograph class implements the Comparable interface and constructs photos based on the given caption, filename,
 * dateTaken and rating. It enables users to sort photos based on captions.
 */

public class Photograph implements Comparable<Photograph> {

    /**
     * FIELDS Define the caption, filename, dateTaken and rating of each photo
     */
    private String caption;
    private String filename;
    private String dateTaken;
    private int rating;
    private File imageFile;

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param a The updated caption
     * @param b The updated filename
     */
    public Photograph(String a, String b) {
        this.caption = a;
        this.filename = b;
    }

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param filename  The updated filename
     * @param caption   The updated caption
     * @param dateTaken The updated date of when the photo was taken
     * @param rating    The updated rating of the photo
     * @param imageFine The actual file of the image 
     */
    public Photograph( String caption, String filename, String dateTaken, int rating) {
        this.filename = filename;
        this.caption = caption;
        this.dateTaken = dateTaken;
        this.rating = rating;
        imageFile = new File(filename);

    }

    // GETTERS AND SETTERS

    /**
     * @return the caption of the photo
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param a the updated caption of the photo input to the program
     */
    public void setCaption(String a) {
        this.caption = a;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param b the updated filename of the photo input to the program
     */
    public void setFilename(String b) {
        this.filename = b;
    }

    /**
     * @param dateTaken the updated date of the photo input to the program
     */
    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;

    }

    /**
     * @return the date of when the photo was taken
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * @param rating the updated rating of the photo Set rating only when it's in [0,5]
     */
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5)  // check if the rating is a legal argument
            this.rating = rating;
    }

    /**
     * @return the rating of the photo
     */
    public int getRating() {
        return rating;
    }
    
    /**
     * @param rating the updated rating of the photo Set rating only when it's in [0,5]
     */
    public void setImageFile(String filename) {
       imageFile = new File(filename);
    }

    /**
     * @return the rating of the photo
     */
    public File getImageFile() {
        return imageFile;
    }

    @Override
    /**
     * Writing an equals method that compares whether or not the caption and filename are the same
     * 
     * @param o an object
     * @return true or false on if the two photos have the same caption and filename
     */
    public boolean equals(Object o) {
        if (o instanceof Photograph)  // check if o is of Photograph type
            return ((this.caption.equals(((Photograph) o).getCaption()))
                    && (this.filename.equals(((Photograph) o).getFilename())));
        return false;
    }
    
    public static boolean allowedPhotos(Photograph p) {
        //p = new Photograph("burrito", "salsa", "2020-19-20", 6);
        
        if (p.getDateTaken().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            String[] dateListThis = p.getDateTaken().split("-");
          //  System.out.println("lsit date: " + Arrays.toString(dateListThis));
            int yearOfThisDate = Integer.valueOf(dateListThis[0]);   // get the value of year
            int monthOfThisDate = Integer.valueOf(dateListThis[1]); 
            //System.out.println("month of date " + monthOfThisDate);// get the value of month
            int dayOfThisDate = Integer.valueOf(dateListThis[2]);
            if (yearOfThisDate >= 1900 && yearOfThisDate <= 2019 && monthOfThisDate >= 1 && monthOfThisDate <= 12 && dayOfThisDate >= 1 && dayOfThisDate <= 31)
                return true;
    }
        return false;
    }
    
    /**
     * A method that sorts photos based on their dates, from the earliest to the oldest
     * 
     * @param p a photograph that needs to be compared to the current one
     * @return an integer for sorting
     */
    public int compareTo(Photograph p) {
        int result = this.dateTaken.compareTo(p.dateTaken);
        if (result == 0) {  // if the dates are the same
            if (this.getRating() == p.getRating())
                return this.getCaption().compareToIgnoreCase(p.getCaption()); // compare captions
            else if (this.getRating() <= p.getRating())
                return 1;
            else
                return -1;
        }
        return result;
    }

    /**
     * Use a toString method to combine the fields and print out the photo object
     * 
     * @return caption, filename, dateTaken, and rating
     */
    @Override
    public String toString() {
        return "Caption: " + caption + "\n" + "Filename: " + filename + "\n" + "Datetaken: " + dateTaken + "\n" + "Rating: "
                + rating;
    }
}
