package thePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Background extends JPanel {

	private int position = 0;
	private JButton startButton;
	private Image backgroundImage;
	private String theImageLocation = "/Users/Freddy/Desktop/background.png";

	public Background() throws IOException{
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(510, 450));
		
		JPanel myPanel = new JPanel();
		myPanel.setPreferredSize(new Dimension(510, 450));
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
	    JButton startButton = new JButton("Start");
	    this.add(startButton);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				position = 450;			
				revalidate();
				repaint();
			}
	    });
		
		backgroundImage = new ImageIcon(theImageLocation).getImage();
		g.drawImage(backgroundImage, position, 0, this);
		
		System.out.println(position);
	}

	public Image setBackground(Image image) {
		return image;
	}

	public void moveBackground(int shift) {
		position = position + shift;
	}
}