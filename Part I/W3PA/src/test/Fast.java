import java.util.Arrays;


public class Fast {

	private static int N;
	private static Point[] points;
	
	private static void printArraySlopes(Point[] a, int basePointIndex) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[basePointIndex].slopeTo(a[i]) + " ");
		System.out.println("");
	}
	
	private static void printArray(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println("");
	}
	
	private static void readInput() {
		String auxStr;
		int auxX, auxY;
		
		auxStr = StdIn.readLine();
		try {
			N = Integer.parseInt(auxStr);
		}
		catch (Exception e) {
			System.out.println("Invalid size parameter.");
			return;
		}
		
		points = new Point[N];
		
		int i = 0;
		while (i < N && (auxStr = StdIn.readLine()) != null) {
			
			auxStr = auxStr.trim();
			
			try {
				auxX = Integer.parseInt(auxStr.split("  *")[0]);
				auxY = Integer.parseInt(auxStr.split("  *")[1]);
				
				points[i] = new Point(auxX, auxY);
				
			}
			catch (Exception e) {
				System.out.println("Invalid point. Point: " + i);
				return;
			}
			
			points[i].draw();
			
			i++;
		}
	}

	public static void main(String[] args) {
		double candidateSlope, auxSlope;
		int count;
		Point[] linePoints = new Point[10];
		int minSegmentLength = 4;
		
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);		
		
		readInput();
		
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
					
					System.out.print(linePoints[0]);
					for (int k = 1; k < count; k++)
						System.out.print(" -> " + linePoints[k]);
					System.out.println("");
					
					linePoints[0].drawTo(linePoints[count - 1]);
				}
				
			}
		}
		
	}
	
}
