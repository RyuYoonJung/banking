package domain;

import java.util.Arrays;

public class Account {
	// 계좌번호 PK
	private String accountNum = "1002-238-587094";
	// 계좌비밀번호
	private String accountPW;
	// 주거래통장 여부
	private boolean mainAccount;
	
	// 현재잔액 (=출금가능금액)
	private int balance;
	
	// 회원ID FK
	private String accountID;
	
	public Account() {}

 	public Account(String accountPW, boolean mainAccount, int balance, String accountID) {
		this.accountPW = accountPW;
		this.mainAccount = mainAccount;
		this.balance = balance;
		this.accountID = accountID;
	}
 	
 	{
 		String[] arr = new String[6];
 		Arrays.setAll(arr, (a)->(int)(Math.random() * 10)+"");
 		accountNum = "1002-238-" + String.join("", arr);
 	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountPW() {
		return accountPW;
	}

	public void setAccountPW(String accountPW) {
		this.accountPW = accountPW;
	}

	public boolean isMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(boolean mainAccount) {
		this.mainAccount = mainAccount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	@Override
	public String toString() {
		return accountID + "\t \t" + mainAccount + "\t" + accountNum + "\t  " + balance;
	}
}
