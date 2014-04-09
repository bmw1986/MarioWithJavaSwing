package thePackage;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class MarioGame extends JFrame {

	private Background background;
	
	public MarioGame() throws IOException {
		super("Mario Game");
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		background = new Background();
		add(background);
	}
}