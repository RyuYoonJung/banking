package service;

import java.util.ArrayList;
import java.util.Scanner;

import domain.Account;
import domain.Customer;

public class CustomerService {
	static Scanner scanner = new Scanner(System.in);
	ArrayList<Customer> customers = new ArrayList<>(); // 가입회원 리스트(회원가입 되었는지 확인) 
	Customer customer = null; // 현재 로그인한 회원정보(기본값 :: 비로그인)
	Account account = null; // 현재 로그인한 회원 계좌정보 
	
	private static CustomerService customerService = new CustomerService();
	private CustomerService() {
		// 회원정보 샘플 데이터
		customers.add(new Customer("id1", "1234", "홍길동1", "010-1234-4567", "940912-1111111", "prestige"));
		customers.add(new Customer("id2", "1234", "홍길동2", "010-1234-4567", "940912-2222222", "prestige"));
		customers.add(new Customer("id3", "1234", "홍길동3", "010-1234-4567", "940912-3333333", "royal"));
		customers.add(new Customer("id4", "1234", "홍길동4", "010-1234-4567", "940912-4444444", "family"));
		customers.add(new Customer("id5", "1234", "홍길동5", "010-1234-4567", "940912-1000000", "family"));
	}
	public static CustomerService getInstance() {
		return customerService;
	}


	public Customer getCustomer() {
		return customer;
	}
	// 회원가입
	public void join() {
		Customer customer = null;
		System.out.println("아이디 입력 > ");
		String inputID = scanner.nextLine();
		
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getCustomerID().equals(inputID)) {
				System.out.println("중복된 아이디");
				System.out.println();
				return;
			}
		}
		
		System.out.println("비밀번호 입력 > ");
		String inputPW = scanner.nextLine();

		System.out.println("이름 입력 > ");
		String inputName = scanner.nextLine();

		System.out.println("핸드폰번호 입력 > ");
		String inputPhoneNum = scanner.nextLine();

		System.out.println("주민등록번호 입력 > ");
		String inputIdNum = scanner.nextLine();
		
		customers.add(new Customer(inputID, inputPW, inputName, inputPhoneNum, inputIdNum, customer.getGrade()));
		customerInfo(null);
		
		System.out.println("회원가입 완료.");
		System.out.println();
	}

	// 로그인
	public void login() {
		System.out.println("아이디 > ");
		String inputID = scanner.nextLine();
		System.out.println("비번 > ");
		String inputPW = scanner.nextLine();
		customer = findCustomerById(inputID,inputPW);

		if(customer == null) {
			System.out.println("없는 회원");
			System.out.println();
			return;
		}
		System.out.println("로그인 성공");
		customerInfo(customer);
	}

	// 전체조회
	public void customerInfo(Customer customer) {
		System.out.println("아이디     \t회원명     \t전화번호       \t주민번호     \t회원등급");
		System.out.println("===========================================================================");
		
		if(customer != null) {
			System.out.println(customer);
			return;
		}
		
		for(Customer c : customers) {
			System.out.println(c);
		}
	}
	

	// 내정보수정
	public void modify() {
		System.out.println("새 비밀번호 > ");
		String modPW = scanner.nextLine();
		System.out.println("새 핸드폰번호 > ");
		String modPN = scanner.nextLine();
		
		customer.setCustomerPW(modPW);
		customer.setPhoneNum(modPN);
		
		System.out.println("수정완료");
		System.out.println();
	}

	// 회원탈퇴
	public void remove() {
		customers.remove(customer);
		
		System.out.println("탈퇴 완료 회원 ");
		System.out.print("> ");
		System.out.println();
		System.out.println(customer);
		logout();
	}

	// 로그아웃
	public void logout() {
		customer = null;
		System.out.println("로그아웃 완료");
		System.out.println();
	}

	public boolean isLogin() {
		if(customer == null) {
			System.out.println("로그인 정보 없음");
		}
		return customer != null;
	}
	
	
	// 입력ID값과 일치하는 회원 (1명) 조회
	public Customer findCustomerById(String customerID) {
		Customer customer = null;
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getCustomerID().equals(customerID)) {
				customer = customers.get(i);
			}
		}
		return customer;
	}

	// 로그인 회원 정보 반환
	public Customer findCustomerById(String customerID, String customerPW) {
		Customer customer = findCustomerById(customerID);
		return customer != null && customer.getCustomerPW().equals(customerPW) ? customer : null;
	}
}
