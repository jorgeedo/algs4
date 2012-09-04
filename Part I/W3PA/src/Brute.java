import java.util.Arrays;


public class Brute {
	
	private static int N;
	private static Point[] points;
	
	private static void readInput(String filename) {
		String auxStr;
		int auxX, auxY;
		In fileIn = new In(filename);
		
		auxStr = fileIn.readLine();
		try {
			N = Integer.parseInt(auxStr.trim());
		}
		catch (Exception e) {
			StdOut.println("Invalid size parameter.");
			return;
		}
		
		points = new Point[N];
		
		int i = 0;
		while ( i < N && (auxStr = fileIn.readLine()) != null) {
			
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
		int groupSize = 4;
		double auxSlope;
		Point[] linePoints = new Point[groupSize];	
		
		if (args.length != 1)
			throw new IllegalArgumentException();
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);		
		
		readInput(args[0]);
		
		for (int i = 0; i<N-groupSize+1; i++){
			for (int j = i+1; j < N-groupSize+2; j++) {
				for (int k = j+1; k < N-groupSize+3; k++) {
					for (int l = k+1; l < N; l++) {
						
						auxSlope = points[i].slopeTo(points[j]);						
						
						if (points[i].slopeTo(points[k]) == auxSlope && 
								points[i].slopeTo(points[l]) == auxSlope) {
							
							linePoints[0] = points[i];
							linePoints[1] = points[j];
							linePoints[2] = points[k];
							linePoints[3] = points[l];
							Arrays.sort(linePoints);
							
							StdOut.println(linePoints[0] + " -> " + 
							linePoints[1] + " -> " +
							linePoints[2] + " -> " +
							linePoints[3]);
							
							linePoints[0].drawTo(linePoints[3]);
							
						}
								
					}
				}
			}
		}
		
		
		
	}
	
}
