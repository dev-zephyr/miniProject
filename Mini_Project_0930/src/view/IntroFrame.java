package view;

import java.awt.Toolkit;

import javax.swing.JFrame;

import resource.R;

public class IntroFrame extends JFrame implements R{

	public IntroFrame() {
		setTitle(title);
		setSize(800, 600);
		
		double sw = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double sh = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		int x = (int)(sw/2 - 820/2);
		int y = (int)(sh/2- 650/2);
		
		this.setLocation(x, y);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
