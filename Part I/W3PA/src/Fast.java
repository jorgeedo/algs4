import java.util.Arrays;


public class Fast {

	private static int N;
	private static Point[] points;
	
	private static void printArraySlopes(Point[] a, int basePointIndex) {
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[basePointIndex].slopeTo(a[i]) + " ");
		StdOut.println("");
	}
	
	private static void printArray(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println("");
	}
	
	private static void readInput(String filename) {
		String auxStr;
		int auxX, auxY;
		
		In fileIn = new In(filename);
		
		
		auxStr = fileIn.readLine().trim();
		
		try {
			N = Integer.parseInt(auxStr.trim());
		}
		catch (Exception e) {
			StdOut.println(e.getMessage() + " Invalid size parameter.");
			return;
		}
		
		points = new Point[N];
		
		int i = 0;
		while (i < N && (auxStr = fileIn.readLine()) != null) {
			
			auxStr = auxStr.trim();
			
			try {
				auxX = Integer.parseInt(auxStr.split("  *")[0]);
				auxY = Integer.parseInt(auxStr.split("  *")[1]);
				
				points[i] = new Point(auxX, auxY);
				
				points[i].draw();

				i++;
				
			}
			catch (Exception e) {
			}
			
			
		}
	}

	public static void main(String[] args) {
		double candidateSlope;
		int count;
		Point[][] foundLines = new Point[0][];
		int nFoundLines = 0;
		Point[] linePoints = new Point[N];
		int minSegmentLength = 4;
		
		if (args.length != 1)
			throw new IllegalArgumentException();
		
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);	
		
		readInput(args[0]);
		
		for (int i = 0; i < N; i++) {
			Arrays.sort(points, i + 1, N, points[i].SLOPE_ORDER);

			int j = i + 1;
			while (j < N) {
				count = 2;
				candidateSlope = points[i].slopeTo(points[j++]);
				
				while (j < N && points[i].slopeTo(points[j]) == candidateSlope) {
					count++;
					j++;
				}
				
				if (count >= minSegmentLength) {
					
					linePoints[0] = points[i];
					for (int k = 1; k < count; k++)
						linePoints[k] = points[j - count + k];						
					
					Arrays.sort(linePoints, 0, count);
					
					StdOut.print(linePoints[0]);
					for (int k = 1; k < count; k++)
						StdOut.print(" -> " + linePoints[k]);
					StdOut.println("");
					
					// Line found!
					nFoundLines++;
					linePoints[0].drawTo(linePoints[count - 1]);
					
					Point[][] auxV = new Point[nFoundLines][];
					auxV[nFoundLines - 1] = Arrays.copyOfRange(linePoints, 0, count-1);
					for (int l = 0; l < nFoundLines - 1; l++) {
						auxV[l] = foundLines[l];
						auxV = new Point[nFoundLines][];
					}
					foundLines = auxV;
						
				}
				
			}
		}
		
	}
	
}
