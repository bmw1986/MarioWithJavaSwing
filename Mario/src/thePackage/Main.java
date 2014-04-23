package thePackage;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class Main extends JFrame {

	public Main() {
		setFocusable(true);
	}
	
	public static void main(String[] args) throws IOException {
		
		JFrame frame = new JFrame();
		GamePlay newGame = new GamePlay();
		frame.setVisible(true);
		frame.add(newGame);
		frame.setVisible(true);
		frame.setSize(510, 450);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		frame.setFocusable(true);
	}
}
