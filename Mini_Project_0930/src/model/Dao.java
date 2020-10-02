package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import resource.R;
import view.ShowView;

public class Dao implements R {
	
	public static int selectedDay = 0;
	public static int selectedDaySeq = 0;
	
	public static LinkedList<Dto> dayList;

	public static ArrayList<LinkedList<Dto>> monthList = new ArrayList<>();
	
	static FileReader reader;
	static FileWriter writer;
	
	static int no = 1;	// json배열 안에 날짜별로 저장된 json객체의 넘버링을 위한 변수 no
	
	public static void loadDataList() {
		File dataFile = new File(pathname);
		
		try {
			if(!dataFile.exists()) {
				if(dataFile.createNewFile()) {
					JSONArray dayArray = new JSONArray();
					for(int i=0; i<MAX_MONTH; i++) {
						JSONArray objArray = new JSONArray();
						for(int j=0; j<DAYLIST_SIZE; j++) {
							JSONObject obj = new JSONObject();
							obj.put("name", "이름");
							obj.put("phone", "번호");
							obj.put("tableNumber", "테이블번호");
							obj.put("memo", ".");
							objArray.put(obj);
						}
						JSONObject day = new JSONObject();
						day.put("day" + no++, objArray);
						dayArray.put(day);
					}
					JSONObject month = new JSONObject();
					month.put("month", dayArray);
					writer = new FileWriter(dataFile);
					writer.write(month.toString(2));
				}
				
			}
			
			reader = new FileReader(dataFile);
			// reader를 이용해서 JSONTokener객체 만들기
			JSONTokener tokener = new JSONTokener(reader);
			// tokener를 이용해서 파일 안에 있는 데이터 JSONObject로 만들기
			JSONObject root = new JSONObject(tokener);
			// root에 있는 JSONArray를 뽑아온다.
			JSONArray monthArray = root.getJSONArray("month");
			
			no = 1;	
			
			for(int i=0; i<MAX_MONTH; i++) {
				JSONObject day = monthArray.getJSONObject(i);
				JSONArray dayArray = day.getJSONArray("day" + no++);	//하루 5개 예약 저장하는 JSON배열
				dayList = new LinkedList<>();	// 하루 5개 예약 저장하는 벡터
				for(int j=0; j<DAYLIST_SIZE; j++) {
					JSONObject obj = dayArray.getJSONObject(j);	// 예약 저장 JSON객체
					Dto dto = new Dto();						// Dto객체
					dto.setName(obj.getString("name"));
					dto.setPhone(obj.getString("phone"));
					dto.settableNumber(obj.getString("tableNumber"));
					dto.setMemo(obj.getString("memo"));
					dayList.add(dto);						// 벡터에 dto 삽입
				}
				monthList.add(dayList);						// 한달 배열에 벡터 삽입
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			no = 1;
			try {
				if(writer != null) writer.close();
				if(reader != null) reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void append(Dto dto) {
		// textfield에서 넘어온 값이 하나라도 누락된 경우 
		// 메시지를 출력하고 해당 메소드를 빠져나간다. 
		if(dto.getPhone().equals("") || dto.getName().equals("") || 
		   dto.gettableNumber().equals("") || dto.getMemo().equals("")) {
			JOptionPane.showMessageDialog(null, "모든 정보를 입력하세요.");
			return;
		}
		
		int cnt = 0;
		// GetDayEvtHandler에서 선택한 인덱스를 이용하여 추가할 날짜의 벡터를 찾아낸다.
		LinkedList<Dto> v = monthList.get(selectedDay);
		
		for(int i=0; i<v.size(); i++) {
			// 벡터에 예약이 몇개 되어있나 cnt를 통해 알아낸다.
			// 예약이 안되어있는곳은 ""로 채워져 있기때문에, ""로 채워지지 않은곳을 찾아낸다.
			if(!v.get(i).getName().equals("")) {
				cnt++;
			}
		}
		
		// 예약 수가 DAYLIST_SIZE(하루에 받을수 있는 최대 예약수)이면 예약을 못받는 메시지를 출력하고 종료.
		if(cnt == DAYLIST_SIZE) {
			JOptionPane.showMessageDialog(null, "더 이상 예약이 불가능합니다.");
			return;
		} else {
			// 예약이 가능하면 파라미터로 받아온 dto를 벡터에 추가하는데,
			// 벡터의 앞에서부터 저장해야하기때문에 cnt 인덱스에 추가해주고 
			// 벡터의 크기가 DAYLIST_SIZE+1이 되기때문에 마지막 벡터를 지운다. 
			v.add(cnt, dto);
			v.remove(DAYLIST_SIZE-1);
			
			// 해당 날짜의 리스트(view)를 새로 업데이트 한다.
			ShowView.updateAppend(selectedDay, cnt);
			JOptionPane.showMessageDialog(null, "10월 " + (selectedDay+1) + "일 [" + dto.getName() 
												+ "]님 예약 완료!");
			// 바뀐 예약 내역을 json파일에 저장한다.
			saveDataList();
		}
		
	}
	
	public static void search(Dto dto) {
		// textfield에서 전화번호값이 넘어온 없는경우 검색할 내용이 넘어오지 않은 경우이기때문에, 
		// 메시지를 출력하고 해당 메소드를 빠져나간다. 
		if(dto.getPhone().equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 전화번호를 입력하세요.");
			return;
		}
		for(int i=0; i<monthList.size(); i++) {
			for(int j=0; j<dayList.size(); j++) {
			// equals로 리스트에 저장되어있는 dto객체를 찾아낸 후에, 팝업창으로 예약내역을 출력한다.
				if(monthList.get(i).get(j).equals(dto)) {
					Dto findDto = (Dto)monthList.get(i).get(j);
					String text = "[" + findDto.gettableNumber() + "], " + findDto.getName()
					+ ", " + findDto.getPhone() + ", " + findDto.getMemo();
					
					JOptionPane.showMessageDialog(null, "10월 " + (i+1) + "일 \n" + text);
					return;
				}
			}
		}
		// 맞는 dto객체를 찾지 못했으면 그에 맞는 메세지를 출력해서 처리해준다. 
		JOptionPane.showMessageDialog(null, "해당 정보의 예약 내역이 없습니다.");
	}
	
	public static void modify(Dto dto) {
		// textfield에서 넘어온 값이 없는경우 수정할 내용이 넘어오지 않은 경우이기때문에, 
		// 메시지를 출력하고 해당 메소드를 빠져나간다. 
		if(dto.getName().equals("")) {
			JOptionPane.showMessageDialog(null, "수정할 내용을 입력하세요.");
			return;
		}
		
		// GetTextEvtHandler에서 선택한 인덱스를 이용하여 추가할 날짜의 dto 객체를 찾아낸다.
		Dto targetDto = (Dto)monthList.get(selectedDay).get(selectedDaySeq);
		
		// 찾아낸 dto객체 필드를 수정할 내용으로 바꿔준다.
		targetDto.setName(dto.getName());
		targetDto.setPhone(dto.getPhone());
		targetDto.settableNumber(dto.gettableNumber());
		targetDto.setMemo(dto.getMemo());
		
		ShowView.updateModify(selectedDay, selectedDaySeq);
		JOptionPane.showMessageDialog(null, "10월 " + (selectedDay+1) + "일 [" + targetDto.getName() 
											+ "]님 수정 완료!");
		// 바뀐 예약 내역을 json파일에 저장한다.
		saveDataList();
		
	}
	
	public static void delete(Dto dto) {
		if(dto.getName().equals("")) {
			JOptionPane.showMessageDialog(null, "삭제 할 예약내역이 없습니다.");
			return;
		}
		// 선택한 날짜의 객체를 삭제한다.
		monthList.get(selectedDay).remove(selectedDaySeq);
		
		// 빈 리스트를 채워줄 ""로 초기화된 dto객체를 하나 생성하고,
		Dto tempDto = new Dto();
		
		// 리스트에 넣어준다.
		monthList.get(selectedDay).add(tempDto);
		
		ShowView.updateDelete(selectedDay, selectedDaySeq);
		JOptionPane.showMessageDialog(null, "10월 " + (selectedDay+1) + "일 [" + dto.getName() 
											+ "]님 삭제 완료!");
		// 바뀐 예약 내역을 json파일에 저장한다.
		saveDataList();
	}
	
	private static void saveDataList() {
		
		no = 1;
		File dataFile = new File(pathname);
		try {
			writer = new FileWriter(dataFile);
			// 기존 json 파일 안에 있던 데이터를 지우고
			dataFile.delete();
			
			// json 파일 안의 구조는 작은 단위순서부터
			// obj -> dayArray -> day -> monthArray -> month 순서로 저장한다.
			JSONArray dayArray = new JSONArray();
			
			for(int i=0; i<monthList.size(); i++) {
				JSONArray objArray = new JSONArray();
				for(int j=0; j<dayList.size(); j++) {
					Dto dto = monthList.get(i).get(j);
					JSONObject obj = new JSONObject();
					obj.put("name", dto.getName());
					obj.put("phone", dto.getPhone());
					obj.put("tableNumber", dto.gettableNumber());
					obj.put("memo", dto.getMemo());
					objArray.put(obj);
				}
				JSONObject day = new JSONObject();
				day.put("day" + no++, objArray);
				dayArray.put(day);
			}
			JSONObject month = new JSONObject();
			month.put("month", dayArray);
			
			try {
				writer.write(month.toString(2));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
