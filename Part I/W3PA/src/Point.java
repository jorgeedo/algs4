/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator(this);       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    
    private static final class SlopeComparator implements Comparator<Point> {
    	
    	Point p;
    	
    	public SlopeComparator(Point p) {
    		this.p = p;
    	}
    	
    	public int compare(Point v, Point w) {
    		
    		double slopeV = p.slopeTo(v);
    		double slopeW = p.slopeTo(w);
    		
    		if (slopeV < slopeW) return -1;
    		else if (slopeV > slopeW) return 1;
    		else return 0;
    		
    	}
    }
    
    private static void printArray(Comparable[] a){
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println("");
	}

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	double offsetX, offsetY;
    	
    	if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;    	
    	
    	offsetY = this.y - that.y;
    	offsetX = this.x - that.x;
    	
    	if (offsetY == 0) return 0.0;
    	else if (offsetX == 0) return Double.POSITIVE_INFINITY;
    	else
    		return offsetY / offsetX;    	
    	
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.y < that.y) return -1;
    	else if (this.y > that.y) return 1;
    	else if (this.x < that.x) return -1;    	
    	else if (this.x > that.x) return 1;
    	else return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point[] points = new Point[4];
    	
    	points[0] = new Point(0, 0);
    	points[1] = new Point(2, 1);
    	points[2] = new Point(0, 1);
    	points[3] = new Point(4, 5);
    	
    	StdOut.println(points[0] + ": " + points[0].slopeTo(points[0]));
    	StdOut.println(points[1] + ": " + points[0].slopeTo(points[1]));
    	StdOut.println(points[2] + ": " + points[0].slopeTo(points[2]));
    	StdOut.println(points[3] + ": " + points[0].slopeTo(points[3]));
    	
    	printArray(points);
    	Arrays.sort(points, points[0].SLOPE_ORDER);
    	printArray(points);
    	
    }
}
