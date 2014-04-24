package thePackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.SliderUI;

@SuppressWarnings("serial")
public class GamePlay extends JPanel implements KeyListener, ActionListener { 
	
	private static int x =0, velX = 2, count = 0;
	private Timer timer, timer2;
	private int flagHeight = 90, X_Position = 0, Y_Position = 360, yInit=0;
	private int accel=10, accelMax=0;
	private int marioX, marioY, lb, rb, ub, db;
	public static Graphics g;
	private boolean dieNow = false, notStopped=true, themeRunning=false, showFlag = false, stopMoving = false, beginGame=false, moveUpward = false, moveForward = false, moveBackward = false, iWannaGoFast = false;
	private JButton startButton, forward, backward, jump;
	private ImageIcon marioImage, flag;
	@SuppressWarnings("unused")
	private ObjectsToHit bricksAndPipes;
	private boolean inTheAir = false;
	private ImageIcon backgroundImage;
	private String backgroundLocation = "C:/backgroundSimple.jpg";
	private String marioLocation = "C:/mario2.png";
	private String flagLocation = "C:/flag.jpg";
	private Clip clip1, clip2, clip3, clip4;
	Controller nes = new Controller();

	public GamePlay() throws IOException {		
		super();	
		addKeyListener( this );		
		add(nes);
		
		timer = new Timer(12, this);
		timer2 = new Timer(12, this);

		flag = new ImageIcon(flagLocation);
		backgroundImage = new ImageIcon(backgroundLocation);
		marioImage = new ImageIcon(marioLocation);
		bricksAndPipes = new ObjectsToHit();

	    startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(startButton);
				X_Position = 450;	
				Y_Position = 360;
				refresh(); 
			}});

		add(startButton);

		setPreferredSize(new Dimension(510, 450));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {

		
		g.drawImage(backgroundImage.getImage(), -X_Position, 0, null);
		g.drawImage(marioImage.getImage(), 230, Y_Position, 40, 40, null);
		if (showFlag) g.drawImage(flag.getImage(), 224, flagHeight, null);
		System.out.println("X: " + X_Position);
		System.out.println("Y: " + Y_Position);
	}

	public void moveForward() {
		if (Y_Position > 360) Y_Position = 360;
		if (stopMoving == false) X_Position = X_Position + 10;
		refresh();
	}

	public void moveForwardFast() {
		if (Y_Position > 360) Y_Position = 360;
		if (stopMoving == false) X_Position = X_Position + 16;
		refresh();
	}

	public void moveBackward() { 
		if (Y_Position > 360) Y_Position = 360;
		if (stopMoving == false) X_Position = X_Position - 10;		
		refresh();
	}

	public void cheat(){
		X_Position = 2150;
		refresh();
	}

	public void moveBackwardFast() {
		if (Y_Position > 360) Y_Position = 360;
		if (stopMoving == false) X_Position = X_Position - 16;		
		refresh();
	}

	public void refresh() {
		revalidate();
		repaint();
	}
	
	public void themeSound(){	   
	    try {
	    	// Open an audio input stream.
	    	File soundFile1 = new File("C:/marioTheme.wav");
	    	AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(soundFile1);
	        // Get a sound clip resource.
	        clip1 = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        clip1.open(audioIn1);	        
	        clip1.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch (UnsupportedAudioFileException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void stopThemeSound(){	   
		clip1.stop();
	}	
	
	public void jumpSound(){	   
	    try {
	    	// Open an audio input stream.
	    	File soundFile2 = new File("C:/marioJump.wav");
	    	AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
	        // Get a sound clip resource.
	        clip2 = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        clip2.open(audioIn2);
	        clip2.start();
	    } catch (UnsupportedAudioFileException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void diedSound(){	
	    try {
	    	// Open an audio input stream.
	    	File soundFile3 = new File("C:/marioDeath.wav");
	    	AudioInputStream audioIn3 = AudioSystem.getAudioInputStream(soundFile3);
	        // Get a sound clip resource.
	        clip3 = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        clip3.open(audioIn3);
        	clip3.start();
	    } catch (UnsupportedAudioFileException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void winSound(){	
	    try {
	    	// Open an audio input stream.
	    	File soundFile4 = new File("C:/marioWin.wav");
	    	AudioInputStream audioIn4 = AudioSystem.getAudioInputStream(soundFile4);
	        // Get a sound clip resource.
	        clip4 = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        clip4.open(audioIn4);
        	clip4.start();
	    } catch (UnsupportedAudioFileException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void endGame() {
		notStopped=true;
		refresh();
		dieNow = false;
		stopMoving = false;
		try {Thread.sleep(2 * 1000);} catch (InterruptedException e1) {	e1.printStackTrace(); }
		X_Position = 0;
		Y_Position = 360;
		beginGame = false;
	}

	public void jump() {		//boolean result = ObjectsToHit.checkHere(X_Position, Y_Position);		

		moveUpward = true;
		timer.start();
		
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
	}
	
	public void dieAnimation() {
		
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		nes.setInput(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));

		// Pressed Start (Y)
		if(nes.getInput() == 'Y' && !beginGame){		
			beginGame=true;
			remove(startButton);
			themeSound();
			X_Position = 450;	
			Y_Position = 360;
			flagHeight = 90;
			inTheAir = false;
			count = 0;
			showFlag = false;
			stopMoving = false;
			dieNow = false;
			notStopped=true;
			moveUpward = false;
			moveForward = false;
			moveBackward = false;
			iWannaGoFast = false;
			refresh(); 
		}

		// Pressed R
		if(nes.getInput() == 'R' && beginGame){

			if (stopMoving == false) {
				if (iWannaGoFast == true) {
					boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position-4);
					boolean goDown = ObjectsToHit.checkHere_X(X_Position);	
					if (inTheAir) { if (goDown) { Y_Position = 360; inTheAir = false; moveForwardFast(); }}
					if (justDoIt) moveForwardFast();
				} else {
					boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position-4);
					boolean goDown = ObjectsToHit.checkHere_X(X_Position);	
					if (inTheAir) { if (goDown) { Y_Position = 360; inTheAir = false; moveForward(); }}
					if (justDoIt) moveForward();
				}
				if ((X_Position >= 1410 && X_Position <= 1440) && Y_Position >= 350) {
					Y_Position = 380;
					if(notStopped==true){
						notStopped=false;
						stopThemeSound();
						diedSound();
						stopMoving = true;
						dieAnimation();
						dieNow = true;
					}
					
				}
				if ((X_Position >= 1950 && X_Position <= 2020) && Y_Position >= 350) {
					Y_Position = 380;
					if(notStopped==true){
						notStopped=false;
						stopThemeSound();
						diedSound();
						stopMoving = true;
						dieAnimation();
						dieNow = true;
					}
				}
				if (X_Position > 2320) {
					showFlag = true;
					stopMoving = true;
					refresh();
					timer2.start();
					
				}
			}
		}

		// Pressed L
		if(nes.getInput() == 'L' && beginGame){

			if (iWannaGoFast == true) {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position-4);
				boolean goDown = ObjectsToHit.checkHere_X(X_Position);
				if (inTheAir) { if (goDown) { Y_Position = 360; inTheAir = false; moveBackwardFast(); }}
				if (justDoIt) moveBackwardFast();
			} else {
				boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position-4);
				boolean goDown = ObjectsToHit.checkHere_X(X_Position);
				if (inTheAir) { if (goDown) { Y_Position = 360; inTheAir = false; moveBackward(); }}
				if (justDoIt) moveBackward();
			}
			if ((X_Position >= 1410 && X_Position <= 1440) && Y_Position >= 350) {
				Y_Position = 380;
//				try {Thread.sleep(2 * 1000);} catch (InterruptedException e1) {	e1.printStackTrace(); }
				if(notStopped==true){
					notStopped=false;
					stopThemeSound();
					diedSound();
					stopMoving = true;
					dieAnimation();
					dieNow = true;
				}
			}
			if ((X_Position >= 1950 && X_Position <= 2020) && Y_Position >= 350) {
				Y_Position = 380;
				if(notStopped==true){
					notStopped=false;
					stopThemeSound();
					diedSound();
					stopMoving = true;
					dieAnimation();
					dieNow = true;
				}
			}
		}

		// Pressed Jump (A)
		if(nes.getInput() == 'A' && beginGame){
			boolean justDoIt = ObjectsToHit.checkHere(X_Position, 250);
			jumpSound();
			if (justDoIt == true) jump();
		}	

		// Pressed Fast (B)
		if(nes.getInput() == 'B' && beginGame)
			iWannaGoFast = true;

		// Pressed Select (x)
		if(nes.getInput() == 'X')
			cheat();
	}

//	public void startTimer2() {
//		timer2.start();
//	}
//	
	@Override
	public void keyReleased(KeyEvent e) {

		if (nes.getInput() == 'B' && beginGame) iWannaGoFast = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void actionPerformed(ActionEvent event) {

		
		if (dieNow) {
			
			if (count >= 0 && count < 20) {
				Y_Position = Y_Position - 5;
				count ++;
				refresh();
			} else if (count >= 20 && count < 25) {
				count ++;
				refresh();
			} else if (count >= 25 && count < 45) {
				Y_Position = Y_Position + 10; 
				count++;
				refresh();
			} else if (count == 45) {
				Y_Position = 400;
				refresh();
				count = 0;
				timer.stop();
				endGame();
				dieNow = false;
			}
			
		} else {
		
		if (stopMoving == false) {
		
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
						if (result == false) { 
							timer.stop(); 
							moveUpward = false; 
							count = 0;
							Y_Position -= 5;
							inTheAir = true;
						}
						refresh();
						count ++;
					} else if (count == 45) {
						count = 0;
						timer.stop();
						moveUpward = false;
					}
	
				} else { 
					timer.stop();
					Y_Position -= 6;
				}
			}
			if ((X_Position >= 1410 && X_Position <= 1440) && Y_Position >= 350) {
				timer.stop();
				Y_Position = 380;
				if(notStopped==true){
					notStopped=false;
					stopThemeSound();
					diedSound();
					stopMoving = true;
					refresh();
					dieAnimation();
					dieNow = true;
				}
			}
			if ((X_Position >= 1950 && X_Position <= 2020) && Y_Position >= 350) {
				timer.stop();
				Y_Position = 380;
				if(notStopped==true){
					notStopped=false;
					stopThemeSound();
					diedSound();
					stopMoving = true;
					refresh();
					dieAnimation();
					dieNow = true;
				}
			}
			if (X_Position > 2320) {
				showFlag = true;
				stopMoving = true;
				X_Position = 2320;
				Y_Position= 360;
				count = 0;
				refresh();
				timer2.start();
			}
		} else {
		
			if (count >= 0 && count < 47) {
				count ++;
				flagHeight += 5;
				refresh();
			} else {
				timer2.stop();
				stopThemeSound();
				winSound();
				endGame();
				showFlag = false;
				
			}
		}
	}
	}
}



//forward = new JButton(" -->");	
//forward.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		boolean justDoIt = ObjectsToHit.checkHere(X_Position+12, Y_Position);
//		if (justDoIt) moveForward();
//	}});
//
//backward = new JButton("<-- ");	
//backward.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		boolean justDoIt = ObjectsToHit.checkHere(X_Position-12, Y_Position);
//		if (justDoIt) moveBackward();
//	}});
//
//jump = new JButton("Jump Up");
//jump.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		boolean justDoIt = ObjectsToHit.checkHere(X_Position, 250);
//		if (justDoIt) jump();
//	}});
