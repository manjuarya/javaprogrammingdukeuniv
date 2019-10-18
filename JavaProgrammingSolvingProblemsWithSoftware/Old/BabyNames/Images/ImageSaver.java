
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ImageSaver {
    public void SaveImage() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String ImgName = image.getFileName();
            String NewName = "Copy-" + ImgName;
            image.setFileName(NewName);
            image.save();
            image.draw();
            
        }
    }
}
