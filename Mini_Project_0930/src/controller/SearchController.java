package controller;

import model.Dao;
import model.Dto;

public class SearchController implements Controller {

	
	@Override
	public void service() {
		// 텍스트 필드에 기입된 추가할 내용을 받아온다. 
		// 검색은 핸드폰번호로만 하기때문에 phone만 받아온다.
		String phone = textFieldList.get(1).getText();
		
		// 검색할때 비교할 Dto 객체를 만들고 phone데이터, 나머지는 ""를 넣어준다.
		Dto dto = new Dto();
		dto.setName("");
		dto.setPhone(phone);
		dto.settableNumber("");
		dto.setMemo("");
		
		// Dao로 보내 리스트에 추가한다.
		Dao.search(dto);
	
	}

}
