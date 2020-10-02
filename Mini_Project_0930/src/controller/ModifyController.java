package controller;

import model.Dao;
import model.Dto;

public class ModifyController implements Controller {

	@Override
	public void service() {
	// 텍스트 필드에 기입된 추가할 내용을 받아온다. 
		String name = textFieldList.get(0).getText();
		String phone = textFieldList.get(1).getText();
		String tableNo = textFieldList.get(2).getText();
		String memo = textFieldList.get(3).getText();
		
		// 수정할 Dto 객체를 만들고 데이터를 넣어준다.
		Dto dto = new Dto();
		dto.setName(name);
		dto.setPhone(phone);
		dto.settableNumber(tableNo);
		dto.setMemo(memo);
		
		// Dao로 보내 리스트에 추가한다.
		Dao.modify(dto);
	}

}
