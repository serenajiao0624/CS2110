import java.util.Collections;

public class PhotoViewer {
    
    private PhotographContainer imageAlbum;
    private PhotoLibrary imageLibrary;
    
    public static void main(String[] args) {
        PhotoViewer myViewer = new PhotoViewer();
        // relative path for Macs/Linux:
        String imageDirectory = "images/";
        
        Photograph p1 = new Photograph("Alessandra Ambrosio's Amazing show.", imageDirectory + "angel1", "2015-06-30", 5);
        Photograph p2 = new Photograph("The wings are actually very healvy.", imageDirectory + "angel2", "2016-05-01", 4);
        Photograph p3 = new Photograph("Prinsloo at the beach.", imageDirectory + "angel3", "2010-01-12", 3);
        Photograph p4 = new Photograph("Cool look.", imageDirectory + "angel4", "2018-12-09", 4);
        Photograph p5 = new Photograph("Meet the new angel!", imageDirectory + "angel5", "2019-02-08", 5);
        
        myViewer.imageLibrary = new PhotoLibrary("VS Angel Photo Library", 1);
        myViewer.imageLibrary.addPhoto(p1);
        myViewer.imageLibrary.addPhoto(p2);
        myViewer.imageLibrary.addPhoto(p3);
        myViewer.imageLibrary.addPhoto(p4);
        myViewer.imageLibrary.addPhoto(p5);
        
        // four more photographs added like the line above
        Collections.sort(myViewer.imageAlbum.photos);
        javax.swing.SwingUtilities.invokeLater(() ->  myViewer.createAndShowGUI() );
    }

    private Object createAndShowGUI() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
