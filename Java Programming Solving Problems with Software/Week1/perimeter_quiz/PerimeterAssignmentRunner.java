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
        int num_points = 0;
        
        for(Point p : s.getPoints()) {
            ++num_points;
        }
        
        return num_points;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largest_side = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            largest_side = Math.max(largest_side, currDist);
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        
        return largest_side;
    }

    public double getLargestX(Shape s) {
        double largest_x = 0.0;
        
        for (Point currPt : s.getPoints()) {
            largest_x = Math.max(largest_x, currPt.getX());
        }
        
        return largest_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        double max_perimeter = -1.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            max_perimeter = Math.max(max_perimeter, length);
        }
        return max_perimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double max_perimeter = -1.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(max_perimeter < length) {
                max_perimeter = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testGetAverageLength() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double avg_length = getAverageLength(s);
        System.out.println("avg length = " + avg_length );
    }
    
    public void testGetLargestSide() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largest_side = getLargestSide(s);
        System.out.println("largest side = " + largest_side);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest_perim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largest_perim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largest_perim_file = getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = " + largest_perim_file);
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
        // pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        // pr.testGetAverageLength();
        // pr.testGetLargestSide();
    }
}
