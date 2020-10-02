package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainView extends MainFrame {
	
	Container c = null;
	
	JPanel westPane = new JPanel();
	JPanel southPane = new JPanel();
	
	JPanel btnPane = new JPanel();
	JPanel lblPane = new JPanel();
	
	JButton btnExit = new JButton();
	JLabel southLbl = new JLabel();
	
	JPanel imgPane1 = new JPanel();
	JLabel textLbl1 = new JLabel("Go");
	
	// 아이콘쪽 기본 밑작업
	{
		imgPane1.setBackground(backgroundColor);
		btnShow.setBackground(backgroundColor);
		btnShow.setBorder(new EmptyBorder(0, 0, 0, 0));
		textLbl1.setBackground(backgroundColor);
		textLbl1.setForeground(Color.WHITE);
		textLbl1.setFont(koreanFont);
		textLbl1.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public MainView() {
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(backgroundColor);
		
		c.add(BorderLayout.WEST, westPane);
		c.add(BorderLayout.SOUTH, southPane);
		
		westPane.setBackground(backgroundColor);
		southPane.setBackground(lightGray);
		
		// 좌측 아이콘 작업
		westPane.setLayout(new GridLayout(6, 1));
		
		westPane.add(imgPane1);
		
		imgPane1.setLayout(new GridLayout(2, 1));
		imgPane1.add(btnShow);
		imgPane1.add(textLbl1);
		
		btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowView().setVisible(true);;
			}
		});
		
		
		
		// 하단 작업
		southPane.setLayout(new BorderLayout());
		southPane.add(BorderLayout.WEST, btnPane);
		southPane.add(BorderLayout.EAST, lblPane);
		
		btnPane.add(btnExit);
		btnPane.setBorder(border);
		btnPane.setBackground(lightGray);
		
		lblPane.add(southLbl);
		lblPane.setBorder(border);
		lblPane.setBackground(lightGray);
		
		btnExit.setText("시작");
		btnExit.setFont(koreanFont);
		btnExit.setBackground(lightGray);
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowView().setVisible(true);;
			}
		});
		
		southLbl.setText("made by zephyr");
		southLbl.setFont(introFont);
		
		
	}
}
