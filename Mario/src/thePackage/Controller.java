package thePackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controller extends JPanel {
	public static char input = 'A';
	
	public char getInput(){
		System.out.println("GET: "+input);
		return input;
	}
	
	public void setInput(char character){
		System.out.println("SET: "+character);
		input=character;
	}
	
	public Controller() {
		
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(KeyEvent.getKeyText(e.getKeyCode()).equals("D")){
				setInput(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));				
				System.out.println("Start Pressed");
			}
			System.out.println("keyPressed3="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
//			System.out.println("keyReleased3="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
