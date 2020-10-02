package view;

import java.awt.Toolkit;

import javax.swing.JFrame;

import resource.R;

public class ShowFrame extends JFrame implements R{

	public ShowFrame() {
		setTitle(title);
		setSize(SHOWFRAME_WIDTH, SHOWFRAME_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		double sw = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double sh = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		int x = (int)(sw/2 - SHOWFRAME_WIDTH/2);
		int y = (int)(sh/2- SHOWFRAME_HEIGHT/2 - 15);
		
		this.setLocation(x, y);
		
		setVisible(true);
	}
}
