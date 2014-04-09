package thePackage;

import java.awt.*;

public class Mario {

	private static boolean jumping = false;
	//private static boolean running = false;
	//private static int position_Y = 0;
	private static Image mariosState;

	public Mario() {
		//TODO
	}

	public boolean Jump() {
		return jumping;
	}

	public boolean runState(boolean running) {
		return running;
	}

	public Image startRunning(Image standingStill) {
		mariosState = standingStill;
		return mariosState;
	}

	public Image stopRunning(Image notStandingStill) {
		mariosState = notStandingStill;
		return notStandingStill;
	}
}