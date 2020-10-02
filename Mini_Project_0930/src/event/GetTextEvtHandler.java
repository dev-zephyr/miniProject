package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Dao;
import model.Dto;
import resource.R;
import view.ShowView;

public class GetTextEvtHandler implements ActionListener, R {

	ShowView target;
	
	public GetTextEvtHandler(ShowView target) {
		this.target = target;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		// 클릭된 버튼이 저장된 배열의 인덱스를 찾는다.
		for(int i=0; i<31; i++) {
			for(int j=0; j<5; j++) {
				if(btn.equals(target.monthBtnList.get(i).get(j))) {
					Dao.selectedDay = i;
					Dao.selectedDaySeq = j;
					break;
				}
			}
		}
		
		// 그 인덱스를 통해 값이 저장되어있는 dto를 뽑아낸다.
		try {	// 뽑아내서 텍스트필드에 하나씩 넣는다.
			Dto dto = (Dto) Dao.monthList.get(Dao.selectedDay).get(Dao.selectedDaySeq);
			textFieldList.get(0).setText(dto.getName());
			textFieldList.get(1).setText(dto.getPhone());
			textFieldList.get(2).setText(dto.gettableNumber());
			textFieldList.get(3).setText(dto.getMemo());
		} catch(Exception e1) {
			
		}
		
	}

}
