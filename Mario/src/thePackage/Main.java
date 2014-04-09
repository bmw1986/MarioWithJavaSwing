package thePackage;

import java.io.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		MarioGame frame = new MarioGame();
		
		frame.setVisible(true);
		frame.setSize(510, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		frame.setFocusable(true);
	}
}