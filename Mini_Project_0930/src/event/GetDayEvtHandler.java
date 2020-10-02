package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Dao;
import resource.R;

public class GetDayEvtHandler implements ActionListener, R{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		// 액션이 들어온 버튼과 날짜를 나타내는 버튼배열에 들어있는 버튼을 찾아낸다.
		for(int i=0; i<numberBtnArray.size(); i++) {
			if(numberBtnArray.get(i).equals(btn)) {
				// 찾아낸 인덱스를 Dao에서 처리할 수 있도록 변수에 값을 넣어준다.
				Dao.selectedDay = i;
			}
		}
		
	}

}
