package app;

import java.util.Scanner;

import service.AccountService;
import service.CustomerService;

public class BankingApp {
	static Scanner scanner = new Scanner(System.in);
	public static String nextLine(String msg) {
		System.out.println(msg);
		System.out.print(">");
		return scanner.nextLine();
	}
	
	public static int nextInt(String msg) {
		return Integer.parseInt(nextLine(msg));
	}
	
	public static void main(String[] args) {
		CustomerService customerService = CustomerService.getInstance();
		AccountService accountService = AccountService.getInstance();

		while (true) {
			System.out.println("[CCD BANK]");
			if (!customerService.isLogin()) {
//				System.out.println("1. 회원가입 2. 로그인");
//				System.out.print("> ");
//				String input = scanner.nextLine();
				switch (nextInt("1. 회원가입 2. 로그인")) {
				case 1:
					// 회원가입
					customerService.join();
					break;
				case 2:
					// 로그인
					customerService.login();
					break;
				}
			} else {
				while (customerService.isLogin()) {
//					System.out.println("[CCD BANK]");
//					System.out.println("1. 계좌개설 2.주계좌변경 3. 입금 4. 출금 5. 이체 6. 마이페이지");
//					System.out.print("> ");
//					String input = scanner.nextLine();
//					int menu = Integer.parseInt(input);
					switch (nextInt("1. 계좌개설 2.주계좌변경 3. 입금 4. 출금 5. 이체 6. 마이페이지")) {
					case 1:
						// 계좌개설
						accountService.createAccount();
						break;
					case 2:
						// 주계좌변경
						accountService.modMainAccount();
						break;
					case 3:
						// 입금
						accountService.deposit();
						break;
					case 4:
						// 출금
						accountService.withdraw("출금할 금액");
						break;
					case 5:
						// 이체
						accountService.transaction();
						break;
					case 6:
//						System.out.println("1. 내정보조회 2. 정보수정 3. 회원탈퇴 4. 로그아웃");
//						System.out.print("> ");
//						input = scanner.nextLine();
//						menu = Integer.parseInt(input);
						switch (nextInt("1. 내정보조회 2. 정보수정 3. 회원탈퇴 4. 로그아웃")) {
						case 1:
							// 내정보조회
							customerService.customerInfo(customerService.getCustomer());
							// 계좌정보조회
							accountService.displayOwnAcounts();
							break;
						case 2:
							// 정보수정
							customerService.modify();
							break;
						case 3:
							// 회원탈퇴
							customerService.remove();
							break;
						case 4:
							// 로그아웃
							customerService.logout();
							break;
						}
					}
				}
			}
		}
	}
}
