package domain;

public class Customer {
	// 회원ID PK
	private String customerID;
	// 회원PW 
	private String customerPW;

	// 회원이름
	private String name;
	// 회원연락처
	private String phoneNum;
	// 주민등록번호
	private String identificationNum;
	// 회원등급 
	private static String grade = "family"; // 등급단계 family > royal > prestige

	public Customer() {}
	
	public Customer(String customerID, String customerPW, String name, String phoneNum, String identificationNum,
			String grade) {
		this.customerID = customerID;
		this.customerPW = customerPW;
		this.name = name;
		this.phoneNum = phoneNum;
		this.identificationNum = identificationNum;
		this.grade = grade;
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerPW() {
		return customerPW;
	}
	public void setCustomerPW(String customerPW) {
		this.customerPW = customerPW;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getIdentificationNum() {
		return identificationNum;
	}
	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}
	public static String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
//		accountID + "\t" + bankName + "\t \t" + accountType + "\t" + accountNum + "\t  " + balance;
		return customerID + "\t" + name + "\t\t" + phoneNum + "\t\t" + identificationNum + "\t\t" + grade ;
	}
}
