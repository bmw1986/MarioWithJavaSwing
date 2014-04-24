package thePackage;

public class ObjectsToHit {

	private static StaticObjects pipe_1, pipe_2;
	private static StaticObjects[] arrayOfObjects = {new StaticObjects(0, 0, 0, 0), new StaticObjects(0, 0, 0, 0), new StaticObjects(0, 0, 0, 0)};

	public ObjectsToHit() {

		StaticObjects.resetNumObj();

		pipe_1 = new StaticObjects(631,365,726,300);
		addToArray(pipe_1);

		pipe_2 = new StaticObjects(954, 365, 1048, 270);
		addToArray(pipe_2);
	}

	// Checks both X and Y
	public static boolean checkHere(int X_Position, int Y_Position) {

		boolean toReturn = false;

		for (int i=0; i<StaticObjects.getNumberOfObjects(); i++) {
			int[] coord = arrayOfObjects[i].getLocation();

			boolean Y_is_Fine = true;
			boolean X_is_Fine = true;
			toReturn = true;

			// Is X direction fine?
			if (coord[0] <= X_Position && coord[2] >= X_Position)
					X_is_Fine = false;

			// Is Y direction fine?
			if (coord[1] >= Y_Position && coord[3] <= Y_Position)
					Y_is_Fine = false;

			if (X_is_Fine == false && Y_is_Fine == false)
				toReturn = false;

			if (toReturn == false) break;
		}

		return toReturn;
	}
	
	public static boolean checkHere_X(int position) {
		
		boolean toReturn = true;
		
		for (int i=0; i<StaticObjects.getNumberOfObjects(); i++) {
			int[] coord = arrayOfObjects[i].getLocation();

			// Is X direction fine?
			if (coord[0] <= position && coord[2] >= position) {
				toReturn = false;
				break;
			}
		}
		return toReturn;
	}

	public static boolean checkHere_XY(int position, char direction) {

		int start = 0, finish = 2;
		boolean toReturn = false;

		if (direction == 'y') {
			start = 1; finish = 3;
			System.out.println("You've entered y");
		} else if (direction == 'x'){
			start = 0; finish = 2;
			System.out.println("You've entered x");
		}

		for (int i=0; i<StaticObjects.getNumberOfObjects(); i++) {
			int[] coord = arrayOfObjects[i].getLocation();

			boolean isFine = true;
			toReturn = true;

			// Is direction fine?
			if (coord[start] >= position && coord[finish] <= position)
					isFine = false;

			if (isFine == false)
				toReturn = false;

			if (toReturn == false) break;
		}

		return toReturn;
	}

	private void addToArray(StaticObjects toBeAdded) {	

		arrayOfObjects[StaticObjects.getNumberOfObjects() - 1] = toBeAdded;
	}
}
