package controller;

import java.util.ArrayList;

import javax.swing.JButton;

import model.Dao;
import model.Dto;

public class ShowController implements Controller {

	ArrayList<JButton> dayBtnList;
	
	@Override
	public void service() {
		
		// 파일에 있는 리스트를 불러온다.
		Dao.loadDataList();

		// 불러온 리스트(예약 현황)을 화면에 뿌려준다.
		// 최종적으로 dto의 필드들을 JButton에 setText()해준다.
		
		for(int i=0; i<MAX_MONTH; i++) {
			dayBtnList = new ArrayList<>();
			for(int j=0; j<DAYLIST_SIZE; j++) {
				// 저장된 dto를 순차적으로 가져온다.
				Dto dto = (Dto) Dao.monthList.get(i).get(j);
				// 내용을 setText()할 버튼을 생성한다.
				JButton btn = new JButton();
				
				// dto안에 내용이 없으면 빈칸으로 setText()
				if(dto.getName().equals("")) {
					btn.setText("");
				} else {
					// 내용이 있으면 dto의 내용을 각각 setText()한다.
					String text = "[" + dto.gettableNumber() + "], " + dto.getName()
								+ ", " + dto.getPhone() + ", " + dto.getMemo();
					btn.setText(text);
				}
				
				// 생성한 버튼을 ShowView에서도 쓸수있게 배열에 넣어준다.
				dayBtnList.add(btn);
			}
			
			// dayMonthList 배열에도 넣어준다.
			monthBtnList.add(dayBtnList);
		}
		
		
	}

}
