package thePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Background extends JPanel{

	private int position = 0;
	private JPanel myPanel;
	private JButton startButton;
	private ImageIcon backgroundImage;
	private String theImageLocation = "/Users/Freddy/Desktop/background.png";

	public Background() throws IOException{
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(510, 450));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setPreferredSize(new Dimension(510, 450));
		myPanel.setBackground(Color.BLACK);
		myPanel.setFocusable(true);
		
	    startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				position = 450;			
				revalidate();
				repaint();
			}
	    });
		
	    this.add(startButton);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		backgroundImage = new ImageIcon(theImageLocation);
		g2d.drawImage(backgroundImage.getImage(), -position, 0, this);
		
		System.out.println(position);
	}

	public Image setBackground(Image image) {
		return image;
	}

	public void moveBackground(int shift) {
		position = position + shift;
	}
}