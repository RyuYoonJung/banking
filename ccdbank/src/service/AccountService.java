package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import app.BankingApp;
import domain.Account;

public class AccountService {
	ArrayList<Account> accounts = new ArrayList<>(); // 전체 회원의 계좌 리스트
//	Account account = null; // 현재 로그인한 회원 계좌정보 
//	Customer customer = null; // 현재 로그인한 회원정보(기본값 :: 비로그인)
	CustomerService customerService = CustomerService.getInstance(); // 회원으로 계좌 조회 
	
	private static AccountService bankingService = new AccountService();
	private AccountService() {
		// 계좌정보 샘플 데이터
		accounts.add(new Account("1111", true, 50000, "id1"));
		accounts.add(new Account("1111", false, 50000, "id1")); 
		accounts.add(new Account("2222", true, 150000, "id2"));
		accounts.add(new Account("2222", false, 150000, "id2"));
		accounts.add(new Account("3333", true, 250000, "id3"));
		accounts.add(new Account("3333", false, 250000, "id3"));
		accounts.add(new Account("4444", true, 30000, "id4"));
		accounts.add(new Account("4444", false, 30000, "id4"));
		accounts.add(new Account("5555", true, 40000, "id5"));
		accounts.add(new Account("5555", false, 40000, "id5"));
	}
	public static AccountService getInstance() {
		return bankingService;
	}
	
	// 특정 아이디의 주거래 계좌만 조회
	
	// 계좌개설
	public void createAccount() {
		Account account = new Account();
		
		String accountPW = BankingApp.nextLine("신규계좌 비밀번호 입력 > ");
		
		if(findAccountsById().size() != 0) {
			System.out.println("주거래 계좌로 하시겠습니까? > ");
			int selectOpt = BankingApp.nextInt("1. yes 2. no");
			if (selectOpt == 1) {
				account.setMainAccount(true);
				for(Account a : findAccountsById()) {
					a.setMainAccount(false);
				}
			}
		}
		else {
			account.setMainAccount(true);
		}
		
		account.setAccountPW(accountPW);
		account.setAccountID(customerService.getCustomer().getCustomerID());
		
		accounts.add(account);
		displayOwnAcounts();
	}
	
	
	// 전체 계좌들 출력
	public void displayAllAcounts() {
		printAccounts(accounts);
	}
	
	// 현재로그인한 사용자의 소유 계좌들 출력
	public void displayOwnAcounts(){
		printAccounts(findAccountsById());
	}
	
	// 주계좌 출력
	public void displayMainAccount() {
		List<Account> list = new ArrayList<Account>();
		list.add(getMainAccount());
		printAccounts(list);
		printAccounts(Arrays.asList(getMainAccount()));
	}
	
	public void printAccounts(List<Account> accounts) {
		System.out.println("아이디\t  주거래계좌  \t계좌번호   \t현재잔액");
		System.out.println("==================================================================");
		for(Account arr : accounts) {
			System.out.println(arr);
		}
		System.out.println();
	}
	

	
	// 계좌비밀번호 수정
	public void modAccPW() {
		getMainAccount().setAccountPW(BankingApp.nextLine("변경할 비밀번호 입력"));
	}

	// 입금
//	public void transaction() {
//		Account account = new Account();
//		int putMoney= 0;
//		int result = 0;
//		
//		if(account.isMainAccount()) {
//			System.out.println("주거래 계좌가 아님");
//			return;
//		}
//	
//		
//					
//		if(selectNum == 1) {
//			System.out.println("금액 입력 >");
//			String inputMoney = scanner.nextLine();
//			putMoney = Integer.parseInt(inputMoney);
//			
//			result += findAccountByAccNum(accNum).getBalance() + putMoney;
//		}
//		
//		if(findAccountByAccNum(accNum).getBalance() <= 0) {
//			System.out.println("출금 가능한 잔액 없음");
//		}else {
//			result += findAccountByAccNum(accNum).getBalance() - putMoney;
//			findAccountByAccNum(accNum).setBalance(result);
//		}
//		System.out.println("현재 잔액 :: " + findAccountByAccNum(accNum).getBalance());
//			
//	}
	
	public void deposit() {
		getMainAccount().setBalance(getMainAccount().getBalance()+BankingApp.nextInt("입금할 금액"));
	}
	public int withdraw(String msg) {
		int money = BankingApp.nextInt(msg);
		if(getMainAccount().getBalance()-money < 0) {
			System.out.println("잔고 부족");
			return 0;
		}
		getMainAccount().setBalance(getMainAccount().getBalance()-money);
		return money;
	}
	public void transaction() {
		Account account = findAccountByAccNum(BankingApp.nextLine("이체할 계좌 입력"));
		if(account == null) {
			System.out.println("계좌번호를 찾을수 없습니다");
			return;
		}
		
		account.setBalance(account.getBalance()+withdraw("이체할 금액"));
		
	}
	
	// 주계좌 
	public Account getMainAccount() {
		Account account = null;
		List<Account> list = findAccountsById();
		for(Account a: list) {
			if(a.isMainAccount()) {
				account = a;
			}
		}
		return account;
	}

	// 현재잔액
	public void getCurbalance() {
		System.out.println("현재잔액");
	}
	
	// 회원:계좌 (1:多)
	public List<Account> findAccountsById() {
		List<Account> accountsList = new ArrayList<Account>();
		
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountID().equals(customerService.getCustomer().getCustomerID())) {
				accountsList.add(accounts.get(i));
			}
		}
		return accountsList; 
	}
	
	// 계좌번호로 조회
	public Account findAccountByAccNum(String accountNum) {
		Account account = null;
		
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNum().equals(accountNum)) {
				account = accounts.get(i);
			}
		}
		return account;
	}
	// 주계좌 변경
	public void modMainAccount() {
		displayOwnAcounts();
		Account account = findAccountByAccNum(BankingApp.nextLine("주계좌로 변경할 계좌번호를 입력"));
		if(account == null) {
			System.out.println("계좌번호를 찾을수 없습니다");
			return;
		}
		
		for(Account a : findAccountsById()) {
			a.setMainAccount(false);
		}
		account.setMainAccount(true);
	}
}
