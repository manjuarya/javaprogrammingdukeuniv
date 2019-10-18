
/**
 * Write a description of ImageToInvertedImg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ImageToInvertedImg {
    public ImageResource getInvtImage(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inImagePixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int Redpxl = inImagePixel.getRed();
            int Grnpxl = inImagePixel.getGreen();
            int Blpxl = inImagePixel.getBlue();
            pixel.setRed(255-Redpxl);
            pixel.setGreen(255-Grnpxl);
            pixel.setBlue(255-Blpxl);
        }
        return outImage;
    }
    
    
    public void testInvtImage() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource result = getInvtImage(image);
            result.draw();
            String imgFileName = image.getFileName();
            String newInvtFileName = "InvertedOf-" + imgFileName;
            result.setFileName(newInvtFileName);
            result.save();
            
        }
    }

}
