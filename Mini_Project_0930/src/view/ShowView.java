package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ShowController;
import event.FunctionEvtHandler;
import event.GetDayEvtHandler;
import event.GetTextEvtHandler;
import model.Dao;
import model.Dto;

public class ShowView extends ShowFrame {

	Container c = null;
	
	JPanel northPane = new JPanel();
	JPanel centerPane = new JPanel(new GridLayout(5, 7, 10, 10));
	
	JLabel titleLbl = new JLabel("2020. 10 Reservation Status");
	
	static ArrayList<JPanel> paneArray = new ArrayList<>();	// 버튼,라벨을 담는 패널 35개
	static {
		for(int i=0; i<35; i++) {
			JPanel pane = new JPanel();
			pane.setLayout(new GridLayout(6, 1));
			pane.setBackground(lightGray);
			pane.setPreferredSize(new Dimension(120, 100));
			if(!(i<4)) {
				pane.setBorder(border);
			}
			paneArray.add(pane);
		}
	}
	
	static {	// 1일부터 31일까지 사용할 버튼 btnList에 초기화 
		for(int i=0; i<31; i++) {
			JButton btn = new JButton();
			btn.setText(Integer.toString(i+1));		// 버튼에 날짜 넣어주고
			btn.setPreferredSize(new Dimension(120, 10));	// 버튼 크기 지정
			btn.setBackground(backgroundColor);			// 버튼 배경색 지정
			btn.setForeground(Color.WHITE);			// 버튼 글씨색 지정
			btn.setFont(koreanFont);				// 버튼 폰트 지정
			btn.addActionListener(new GetDayEvtHandler());
			numberBtnArray.add(btn);						// 버튼 배열에 삽입
		}
	}
	
	public ShowView() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(lightGray);
		
		c.add(BorderLayout.NORTH, northPane);
		c.add(BorderLayout.CENTER, centerPane);
		
		ShowController showController = new ShowController();
		showController.service();
		
		display();
		
		addEvt();
		
		
	}
	
	// 기능 버튼에 이벤트를 붙여주는 메소드
	public void addEvt() {
		FunctionEvtHandler l = new FunctionEvtHandler(this);
		for(int i=0; i<funcBtnArray.length; i++) {
			funcBtnArray[i].addActionListener(l);
		}
		
	}

	public void display() {
		// 상단 패널 세팅
		
		northPane.setBackground(barColor);
		northPane.setLayout(new BorderLayout());
		northPane.add(titleLbl);
		titleLbl.setFont(koreanFont17);
		titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
		titleLbl.setForeground(Color.white);
		
		// 중앙 버튼 세팅
		
		centerPane.setBackground(lightGray);
		
		// 센터패널에 팬 35개 붙이기
		for(int i=0; i<35; i++) {
			centerPane.add(paneArray.get(i));
		}
		
		// 4개 팬에 기능 버튼 만들고 붙이기
		for(int i=0; i<4; i++) {
			paneArray.get(i).setLayout(new BorderLayout());
			funcBtnArray[i].setBackground(lightGray);
			funcBtnArray[i].setBorder(null);
			funcBtnArray[i].setText(function[i]);
			funcBtnArray[i].setFont(koreanFont);
			paneArray.get(i).add(BorderLayout.CENTER, funcBtnArray[i]);
			
			
		}
		
		// 4개 팬에 입력창 만들고 붙이기
		for(int i=0; i<4; i++) {
			JPanel pane = new JPanel();
			JLabel lbl = new JLabel(component[i]);
			JTextField textField = new JTextField(10);
			
			lbl.setFont(koreanFont);
			lbl.setBackground(backgroundColor);
			textField.setFont(koreanFont);
			
			pane.add(lbl);
			pane.add(textField);
			pane.setBackground(lightGray);
			
			paneArray.get(i).add(BorderLayout.SOUTH, pane);
			// 리스트에 추가하기
			textFieldList.add(textField);
			
		}

		// 35개 팬에 1~31 버튼 붙이기
		for(int i=0; i<31; i++) {
			paneArray.get(i+4).add(numberBtnArray.get(i));
		}
		
		// 35개 팬에 1~31 텍스트 버튼 불러내서 붙이기
		for(int i=0; i<31; i++) {
			for(int j=0; j<5; j++) {
				JButton btn = (JButton)monthBtnList.get(i).get(j);
				btn.setBackground(lightGray);
				btn.setBorder(null); 
				btn.setFont(koreanFont12);
				paneArray.get(i+4).add(btn);
			}
		}
		
		// 텍스트버튼에 이벤트 연결하기
		GetTextEvtHandler l = new GetTextEvtHandler(this);
		for(int i=0; i<31; i++) {
			for(int j=0; j<5; j++) {
				JButton btn = (JButton)monthBtnList.get(i).get(j);
				btn.addActionListener(l);
			}
		}
	}
	
	public static void updateModify(int selectedDay, int selectedDaySeq) {
		// 수정된 내용의 dto에 저장된 필드값을 해당 예약버튼에 setText() 해준다.
		Dto dto = (Dto) Dao.monthList.get(selectedDay).get(selectedDaySeq);
		JButton targetBtn = (JButton)monthBtnList.get(selectedDay).get(selectedDaySeq);
		
		String text = "[" + dto.gettableNumber() + "], " + dto.getName()
		+ ", " + dto.getPhone() + ", " + dto.getMemo();
		
		targetBtn.setText(text);
		
	}
	
	public static void updateAppend(int selectedDay, int cnt) {
		for(int i=0; i<DAYLIST_SIZE; i++) {
			// 예약 내용이 새로 추가된 dto와 해당 내용을 출력해 줄 버튼을 찾아낸뒤, 
			Dto dto = (Dto) Dao.monthList.get(selectedDay).get(i);
			JButton btn = (JButton)monthBtnList.get(selectedDay).get(i);
			
			// selectedDaySeq까지(예약이 있는 만큼까지) 양식에 맞게 예약 내용을 버튼에 setText()해주고, 
			if(i<=cnt) {
				String text = "[" + dto.gettableNumber() + "], " + dto.getName()
				+ ", " + dto.getPhone() + ", " + dto.getMemo();
				btn.setText(text);
			// 예약이 없는곳은 ""로 setText() 해준다.
			} else {
				btn.setText("");
			}
		}
	}
	
	public static void updateDelete(int selectedDay, int selectedDaySeq) {
		// 해당 날짜의 모든 dto 데이터를 버튼에 setText() 해준다.
		for(int i=0; i<Dao.dayList.size(); i++) {
			Dto dto = (Dto) Dao.monthList.get(selectedDay).get(i);
			JButton btn = (JButton)monthBtnList.get(selectedDay).get(i);
			
			// 예약이 없는곳은 ""로 setText()해준다.
			if(dto.getName().equals("")) {
				btn.setText("");
			} else {
				String text = "[" + dto.gettableNumber() + "], " + dto.getName()
				+ ", " + dto.getPhone() + ", " + dto.getMemo();
				
				btn.setText(text);
			}
		}
	}
	
}
