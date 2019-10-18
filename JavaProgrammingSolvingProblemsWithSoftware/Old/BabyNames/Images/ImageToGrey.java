
/**
 * Write a description of ImageToGrey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ImageToGrey {
    public ImageResource getGreyImage(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inImagePixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int avgpxl = (inImagePixel.getRed() + inImagePixel.getGreen() + inImagePixel.getBlue())/3;
            pixel.setRed(avgpxl);
            pixel.setGreen(avgpxl);
            pixel.setBlue(avgpxl);
        }
        return outImage;
    }
    
    
    public void testGreyImage() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource result = getGreyImage(image);
            result.draw();
            String imgFileName = image.getFileName();
            String newGreyFileName = "GreyOf-" + imgFileName;
            result.setFileName(newGreyFileName);
            result.save();
            
        }
    }

}
