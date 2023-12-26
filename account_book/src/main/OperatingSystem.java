package main;

import java.util.Scanner;

// 실행 클래스
public class OperatingSystem {

	public static void main(String[] args) {
		// 메인메뉴 화면
		Scanner sc = new Scanner(System.in);		// 입력
		BucketList b1 = new BucketList();
		Select s1 = new Select();
		InPut i1 = new InPut();

		boolean run=true;

		System.out.println("== 가계부 프로그램 ==\n");

		while(run) {

			System.out.println();
			System.out.println("=======================<<메뉴>>========================");
			System.out.println("\t현재금액: \t\t[ "+Select.changeFormat(Select.amount())+" 원 ]");
			System.out.println("=======================================================");
			System.out.println("| 1. 입력 | 2. 조회 | 3. 버킷리스트 | 4. 프로그램 종료 |");
			System.out.println("=======================================================");
			System.out.print("사용할 메뉴의 번호를 입력 >> ");
			String menu = sc.nextLine();
			System.out.println();

			switch(menu){
				case "1":		// 입력 파트
					i1.insert();

					break;
				case "입력":		// 입력 파트
					i1.insert();
					break;
				case "2":		// 조회 파트
					s1.select0();

					break;
				case "조회":		// 조회 파트
					s1.select0();
					break;
				case "3":		// 버킷리스트 파트
					b1.BucketList1();

					break;
				case "버킷리스트":		// 버킷리스트 파트
					b1.BucketList1();

					break;
				case "4":		// 프로그램 종료 파트
					b1.end();
					String yesOrNO = sc.nextLine();
					System.out.println();
					if (yesOrNO.equals("1")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO.equals("종료")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO.equals("종료한다")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO.equals("2")) {
						System.out.println("가계부로 돌아갑니다");
					} else if (yesOrNO.equals("돌아간다")) {
						System.out.println("가계부로 돌아갑니다");
					} else {
						BucketList.re1();
					}
					break;
				case "종료":		// 프로그램 종료 파트
					b1.end();
					String yesOrNO1 = sc.nextLine();
					System.out.println();
					if (yesOrNO1.equals("1")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO1.equals("종료")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO1.equals("종료한다")) {
						System.out.println("프로그램을 종료합니다");
						run=false;
					} else if (yesOrNO1.equals("2")) {
						System.out.println("가계부로 돌아갑니다");
					} else if (yesOrNO1.equals("돌아간다")) {
						System.out.println("가계부로 돌아갑니다");
					} else {
						BucketList.re1();
					}
					break;
				default:
					BucketList.re1();
					break;
			}

		}

		sc.close();
	}
}