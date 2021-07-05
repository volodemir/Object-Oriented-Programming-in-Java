import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point pt:s.getPoints()){
            numPoints += 1;
    }
    return numPoints;
}
    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        double numSides = (double) getNumPoints(s);
        double avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double lrgstSide = 0;
        Point lastPoint = s.getLastPoint();
        
        for (Point p:s.getPoints()){
            double dist = lastPoint.distance(p);
            if (dist>lrgstSide){
                lrgstSide = dist;
    }
}
return lrgstSide;
}

    public double getLargestX(Shape s) {
        // Put code here
        Point lastPoint = s.getLastPoint();
        int lastPointX = lastPoint.getX();
        double largestX = (double) lastPointX;
        
        for (Point p:s.getPoints()){
            int newX = p.getX();
            if (newX>largestX){
                largestX = (double) newX;
            }
        }
    return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        
        for (File f:dr.selectedFiles()){
            FileResource sh = new FileResource(f);
            Shape shape = new Shape(sh);
            double perim = getPerimeter(shape);
            if (perim>largestPerim){
                largestPerim = perim;
    }
}
return largestPerim;
}
    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        File largestFile = null;
        
        for (File f:dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim>largestPerim){
                largestPerim = perim;
                largestFile = f;
            }
        }
        return largestFile.getName();
        
        
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("Number of Points: " + numPoints);
        double avgLengt = getAverageLength(s);
        System.out.println("Average Length: " + avgLengt);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side: " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String filewithlargestperim = getFileWithLargestPerimeter();
        System.out.println("Name of file with largest perimeter is " + filewithlargestperim);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
