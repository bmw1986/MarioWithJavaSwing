package thePackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePlay extends JPanel implements KeyListener, ActionListener { 

	private static int x =0, velX = 2, count = 0;
	private Timer timer;
	private int X_Position = 0, Y_Position = 360, yInit=0;
	private int accel=10, accelMax=0;
	private int marioX, marioY, lb, rb, ub, db;
	public static Graphics g;
	private boolean beginGame=false, moveUpward = false, moveForward = false, moveBackward = false, iWannaGoFast = false;
	private JButton startButton, forward, backward, jump;
	private ImageIcon marioImage;
	@SuppressWarnings("unused")
	private ObjectsToHit bricksAndPipes;
	private ImageIcon backgroundImage;
	private String backgroundLocation = "/Users/Freddy/Desktop/background.png";
	private String marioLocation = "/Users/Freddy/Desktop/mario2.png";
	Controller nes = new Controller();

	public GamePlay() throws IOException {		
		super();	
		addKeyListener( this );		
		add(nes);

		timer = new Timer(12, this);

		backgroundImage = new ImageIcon(backgroundLocation);
		marioImage = new ImageIcon(marioLocation);
		bricksAndPipes = new ObjectsToHit();

	    startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(backward);
				add(jump);
				add(forward);
				remove(startButton);
				X_Position = 450;	
				Y_Position = 360;
				refresh(); 
			}});
		
		forward = new JButton(" -->");	
		forward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position);
				boolean justDoIt2 = ObjectsToHit.checkHere_Y(X_Position+16, Y_Position);
				if (justDoIt) moveForward();
				if (justDoIt2 == true) { moveForward(); Y_Position = 360; }
			}});
		
		backward = new JButton("<-- ");	
		backward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position);
				boolean justDoIt2 = ObjectsToHit.checkHere_Y(X_Position-16, Y_Position);
				if (justDoIt) moveBackward();
				if (justDoIt2 == true) { moveBackward(); Y_Position = 360; }
			}});
		
		jump = new JButton("Jump Up");
		jump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position, 250);
				//if (justDoIt == true){ moveUpward = true; Y_Position = 300; refresh();}
				if (justDoIt) jump();
//				refresh();
			}});

		add(startButton);

//		setVisible(true);
//		setSize(510, 450);		
//		setFocusable(true);

//		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setPreferredSize(new Dimension(510, 450));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {

		g.drawImage(backgroundImage.getImage(), -X_Position, 0, null);
		g.drawImage(marioImage.getImage(), 230, Y_Position, 40, 40, null);
		System.out.println("X: " + X_Position);
		System.out.println("Y: " + Y_Position);
	}

	public void moveForward() {
		X_Position = X_Position + 10;
		refresh();
	}

	public void moveForwardFast() {
		X_Position = X_Position + 10;
		refresh();
	}

	public void moveBackward() { 
		X_Position = X_Position - 10;		
		refresh();
	}

	public void cheat(){
		X_Position = 6000;
		refresh();
	}

	public void moveBackwardFast() {
		X_Position = X_Position - 10;		
		refresh();
	}

	public void refresh() {
		revalidate();
		repaint();
	}


	public void jump() {		//boolean result = ObjectsToHit.checkHere(X_Position, Y_Position);		
//		if (moveUpward == true) {
//			if(count==0){
//				yInit = Y_Position;
//				accelMax = accel;
//			}
//			count++;
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {}
//			if (count >= 0*accelMax && count < 1*accelMax) {
//				Y_Position = Y_Position - 1*accel - 1;
//				if(accel>0){
//					accel--;
//				}refresh(); 
//			}else if (count > 1*accelMax && count <= 2*accelMax) {
//				Y_Position = Y_Position + accel + 1;
//				if(accel<10){
//					accel++;
//				}refresh();
//			}else if (count == 2*accelMax+1) {
//				count = 0;
//				Y_Position = yInit;
//			}			
//		}

		moveUpward = true;
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		nes.setInput(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));

		// Pressed Start (Y)
		if(nes.getInput() == 'Y' && !beginGame){		
			beginGame=true;
			remove(startButton);
			X_Position = 450;	
			Y_Position = 360;
			refresh(); 
		}

		// Pressed R
		if(nes.getInput() == 'R' && beginGame){

			if (iWannaGoFast == true) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position);
				boolean justDoIt2 = ObjectsToHit.checkHere_Y(X_Position+12, Y_Position);
				if (justDoIt && justDoIt2) { moveForwardFast(); Y_Position = 360; }
				else if (justDoIt) moveForward(); 
			} else {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position);
				if (justDoIt == true) moveForward();
				boolean justDoIt2 = ObjectsToHit.checkHere_Y(X_Position, Y_Position);
				if (justDoIt2) { moveForward(); Y_Position = 360; }
			}
		}

		// Pressed L
		if(nes.getInput() == 'L' && beginGame){

			if (iWannaGoFast == true) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position);
				if (justDoIt == true) moveBackwardFast();
			} else {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position);
				if (justDoIt == true) moveBackward();
			}
		}

		// Pressed Jump (A)
		if(nes.getInput() == 'A' && beginGame){
			boolean justDoIt = ObjectsToHit.checkHere(X_Position, 250);
			//if (justDoIt == true){ moveUpward = true; Y_Position = 300; refresh();}
			if (justDoIt == true) jump();
		}	

		// Pressed Fast (B)
		if(nes.getInput() == 'B' && beginGame)
			iWannaGoFast = true;

		// Pressed Select (x)
		if(nes.getInput() == 'X')
			cheat();

	}

	@Override
	public void keyReleased(KeyEvent e) {
//		if(nes.getInput() == 'A' && beginGame){
//			boolean justDoIt = ObjectsToHit.checkHere(X_Position, 250);
//			if (justDoIt == true){ moveUpward = false; Y_Position = 360; refresh();}
//		}

		if (nes.getInput() == 'B' && beginGame) iWannaGoFast = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void actionPerformed(ActionEvent event) {

		boolean justDoIt = ObjectsToHit.checkHere(X_Position, Y_Position);
		if (justDoIt) {

			if (moveUpward == true) {
				if (count >= 0 && count < 20) {
					Y_Position = Y_Position - 5;
					count ++;
					refresh();
				} else if (count >= 20 && count < 25) {
					count ++;
					refresh();
				} else if (count >= 25 && count < 45) {
					Y_Position = Y_Position + 5;
					boolean result = ObjectsToHit.checkHere(X_Position, Y_Position);
					if (result == false) timer.stop();
					refresh();
					count ++;
				} else if (count == 45) {
					count = 0;
					timer.stop();
					Y_Position = 360;
					moveUpward = false;
				}
			}
		} else if (justDoIt == false){ 
			timer.stop();
			Y_Position -= 4;
		}
	}
}
