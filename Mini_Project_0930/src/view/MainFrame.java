package view;

import java.awt.Toolkit;

import javax.swing.JFrame;

import resource.R;

public class MainFrame extends JFrame implements R{
	
	public MainFrame() {
		setTitle(title);
		setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		double sw = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double sh = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		int x = (int)(sw/2 - MAINFRAME_WIDTH/2);
		int y = (int)(sh/2- MAINFRAME_HEIGHT/2);
		
		this.setLocation(x, y);
		
		setVisible(true);
	}
}
