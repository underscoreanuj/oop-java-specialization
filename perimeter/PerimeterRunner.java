    import edu.duke.*;

public class PerimeterRunner {
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
    
    public int getNumPoints(Shape s) {
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

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int num_points = getNumPoints(s);
        System.out.println("num_points = " + num_points);
        double avg_length = getAverageLength(s);
        System.out.println("avg_length= " + avg_length);
        double largest_side = getLargestSide(s);
        System.out.println("largest_side= " + largest_side);
        double largest_x = getLargestX(s);
        System.out.println("largest_side= " + largest_x);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
