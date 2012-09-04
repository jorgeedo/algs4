
public class Brute {
	
	private static int N;
	private static Point[] points;
	
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
		while ( i < N && (auxStr = StdIn.readLine()) != null) {
			
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
		int groupSize = 4;
		double auxSlope;
		Point[] linePoints = new Point[groupSize];		
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);		
		
		readInput();
		
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
							
							System.out.println(linePoints[0] + " -> " + 
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
