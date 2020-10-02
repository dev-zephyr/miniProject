package model;

public class Dto {
	private String name = "";
	private String phone = "";
	private String tableNumber = "";
	private String memo = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String gettableNumber() {
		return tableNumber;
	}
	public void settableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return name + ", " + phone + ", " + tableNumber + ", " + memo;
	}
	
	// 모든 검색은 phone을 비교로 하기때문에 equals를 phone 비교로 오버라이딩한다.
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Dto) {
			Dto dto = (Dto)obj;
			if(phone.equals(dto.phone)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
