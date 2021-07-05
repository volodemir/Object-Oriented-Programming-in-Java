
/**
 * Write a description of class Batch_Grayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Batch_Grayscale {
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixel's red to average
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }
    public void SaveToGrayscale (){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
}
}
    public ImageResource makeInversion (ImageResource inImage) {
            ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            for (Pixel pixel: outImage.pixels()) {
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
                int redInvrs = 255 - inPixel.getRed();
                int greenInvrs = 255 - inPixel.getGreen();
                int blueInvrs = 255 - inPixel.getBlue();
                pixel.setRed(redInvrs);
                pixel.setGreen(greenInvrs);
                pixel.setBlue(blueInvrs);
        }
        return outImage;
}
    public void selectAndConvert (){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource inverted = makeInversion(image);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            inverted.setFileName(newName);
            inverted.draw();
            inverted.save();
        }
}
}
