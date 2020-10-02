package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import resource.R;

public class IntroView extends IntroFrame implements R{
	
	Container c = null;
	
	JPanel backgroundPane = new JPanel();
	JPanel loginPane = new JPanel();
	
	JPanel pane1 = new JPanel();
	JPanel pane2 = new JPanel();
	
	JPanel pane3_1 = new JPanel();
	JPanel pane3_2 = new JPanel();
	
	JTextField idField = new JTextField("ID", 10);
	JPasswordField pwField = new JPasswordField("PW", 10);
	
	JLabel titleLbl = new JLabel();
	JButton loginBtn = new JButton("Login");
	
	public IntroView() {
	
		c = getContentPane();
		c.setLayout(null);
		
		c.add(backgroundPane);
		backgroundPane.setBounds(0, 0, 800, 600);	
		backgroundPane.setBackground(backgroundColor);
		
		c.add(loginPane);
		loginPane.setBounds(275, 200, 260, 110);	// 로그인 패널 위치, 크기
		loginPane.setBackground(lightGray);
		
		loginPane.add(pane1);
		loginPane.add(pane2);
		pane1.setBackground(lightGray);
		pane2.setBackground(lightGray);
		
		pane1.add(titleLbl);					// 로그인패널에 타이틀
		titleLbl.setText(title);
		titleLbl.setFont(introFont);
		
		pane2.setLayout(new BorderLayout());
		pane2.add(BorderLayout.WEST, pane3_1);
		pane2.add(BorderLayout.EAST, pane3_2);
		pane3_1.setBackground(lightGray);
		pane3_2.setBackground(lightGray);
		
		pane3_1.setLayout(new GridLayout(2, 1));
		pane3_1.add(idField);
		pane3_1.add(pwField);
		
		pane3_2.add(loginBtn);
		
		idField.addMouseListener(new MouseAdapter() {	// id입력창 클릭하면 빈칸으로
			public void mouseClicked(MouseEvent e) {
				idField.setText("");
			}
		});
		
		pwField.addMouseListener(new MouseAdapter() {	// pw입력창 클릭하면 빈칸으로
			public void mouseClicked(MouseEvent e) {
				pwField.setText("");
			}
		});
		
		loginBtn.addActionListener(new ActionListener() {	// 버튼 클릭 이벤트. ID,PW 검사
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputID = idField.getText();
				String inputPW = pwField.getText();
				
				if(inputID.equals("admin") && inputPW.equals("admin1234")) {
					JOptionPane.showMessageDialog(null, "Login 성공!!");
					new MainView();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "ID 또는 Password가 틀렸습니다.");
				}
				
			}
		});
		
	}

}



