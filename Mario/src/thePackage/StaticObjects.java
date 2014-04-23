package thePackage;

public class StaticObjects {

	private int[] location = {0,0,0,0};
	private static int numberOfObjects = 0;
	
	public StaticObjects(int X_Start, int Y_Start, int X_End, int Y_End) {
		
		numberOfObjects++;
		
		location[0] = X_Start;
		location[1] = Y_Start;
		location[2] = X_End;
		location[3] = Y_End;
	
	}
	
	public static int getNumberOfObjects() {		
		return numberOfObjects;
	}
	
	public static void resetNumObj() {
		numberOfObjects = 0;
	}
	
	public int[] getLocation() {
		return location;
	}
}
