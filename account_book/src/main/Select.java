package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Scanner;

import dto.InquiryVo;
import util.DBManager;

public class Select {

	// 기록 조회
	public void select0() {
		Scanner sc = new Scanner(System.in);

		boolean srun=true;

		while(srun) {
			System.out.println();
			System.out.println("                   <<조회 화면>>                     ");
			System.out.println("=====================================================");
			System.out.println("| 1. 모든기록 조회 | 2. 선택기록 조회 | 3. 메인화면 |");
			System.out.println("=====================================================");
			System.out.print("사용할 메뉴의 번호를 입력 >> ");
			String select = sc.nextLine();
			System.out.println();

			switch(select) {
				case "1":
					select01();
					break;
				case "모든기록":
					select01();
					break;
				case "모든기록 조회":
					select01();
					break;
				case "2":
					select1();
					break;
				case "선택기록":
					select1();
					break;
				case "선택기록 조회":
					select1();
					break;
				case "3":
					System.out.println("메인화면으로 이동합니다");
					srun=false;
					break;
				case "메인화면":
					System.out.println("메인화면으로 이동합니다");
					srun=false;
					break;
				default:
					BucketList.re1();
					break;
			}
		}
	}

	// 수입 / 지출 선택 화면
	public void select1() {
        	Scanner sc = new Scanner(System.in);

        	System.out.println("             <<조회를 시작합니다>>              ");
        	System.out.println("================================================");
        	System.out.println("        [수입] 또는 [지출]을 입력하세요         ");
        	System.out.println("================================================");
        	System.out.print("수입 또는 지출 입력 >> ");
        	String inserti = sc.nextLine();

        	if(inserti.equals("수입")) {
        		select2();
        	} else if(inserti.equals("지출")) {
        		select3();
        	} else {
        		ree();
        	}
	}

	// 선택 수입 조회 화면
	public static void select2() {
		Scanner sc = new Scanner(System.in);
		boolean srun2=true;

		while(srun2) {
			System.out.println();
			System.out.println("                 <<수입 조회 화면>>                 ");
			System.out.println("====================================================");
			System.out.println("| 1. 날짜로 조회\t\t| 2. 카테고리(목록)로 조회 |");
			System.out.println("| 3. 상세내용으로 조회\t\t| 4. 조회화면\t\t   |");
			System.out.println("====================================================");
			System.out.print("사용할 메뉴의 번호를 입력 >> ");
			String select1 = sc.nextLine();
			System.out.println();

			if (select1.equals("1")) {
				selectInD();
			} else if (select1.equals("날짜")) {
				selectInD();
			} else if (select1.equals("날짜로 조회")) {
				selectInD();
			} else if(select1.equals("2")) {	
				selectInC();
			} else if(select1.equals("카테고리")) {	
				selectInC();
			} else if(select1.equals("카테고리로 조회")) {	
				selectInC();
			} else if(select1.equals("3")) {	
				selectInM();
			} else if(select1.equals("상세내용")) {	
				selectInM();
			} else if(select1.equals("상세내용으로 조회")) {	
				selectInM();
			} else if(select1.equals("4")) {
				System.out.println("조회화면으로 이동합니다");
				srun2=false;
			} else if(select1.equals("조회화면")) {
				System.out.println("조회화면으로 이동합니다");
				srun2=false;
			} else {
				BucketList.re1();
			}
		}
	}

	// 선택 지출 조회 화면
	public static void select3() {
		Scanner sc = new Scanner(System.in);
		boolean srun2=true;

		while(srun2) {
			System.out.println();
			System.out.println("                 <<지출 조회 화면>>                 ");
			System.out.println("====================================================");
			System.out.println("| 1. 날짜로 조회\t\t| 2. 카테고리(목록)로 조회 |");
			System.out.println("| 3. 상세내용으로 조회\t\t| 4. 조회화면\t\t   |");
			System.out.println("====================================================");
			System.out.print("사용할 메뉴의 번호를 입력 >> ");
			String select2 = sc.nextLine();
			System.out.println();

			if (select2.equals("1")) {
				selectOutD();
			} else if(select2.equals("날짜")) {	
				selectOutD();
			} else if(select2.equals("날짜로 조회")) {	
				selectOutD();
			} else if(select2.equals("2")) {	
				selectOutC();
			} else if(select2.equals("카테고리")) {	
				selectOutC();
			} else if(select2.equals("카테고리로 조회")) {	
				selectOutC();
			} else if(select2.equals("3")) {	
				selectOutM();
			} else if(select2.equals("상세내용")) {	
				selectOutM();
			} else if(select2.equals("상세내용으로 조회")) {	
				selectOutM();
			} else if(select2.equals("4")) {
				System.out.println("조회화면으로 이동합니다");
				srun2=false;
			} else if(select2.equals("조회화면")) {
				System.out.println("조회화면으로 이동합니다");
				srun2=false;
			} else {
				BucketList.re1();
			}
		}
	}


	// 현재 잔액
	public static int amount() {

		int amount = 0;
		int i = 0;
		int e = 0;

		i = inamount();
		e = outamount();

		amount = i-e;

		return amount;
	}

	// 총 수입
	public static int inamount() {
		String sql = "SELECT sum(in_amount) as amount FROM income";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		int amounti = 0;

		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리

			while(rs.next()) {
				amounti = rs.getInt("amount");
			}

		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: 쿼리문 조회");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return amounti;
	}

	// 총 지출
	public static int outamount() {
		String sql = "SELECT sum(out_amount) as amount FROM expenditure";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		int amounte = 0;

		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리

			while(rs.next()) {
				amounte = rs.getInt("amount");
			}

		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: 쿼리문 조회");
		}

		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return amounte;
	}

	// 버킷리스트 사용 가능 금액
	public static int bamount() {

		int bamount = 0;
		int a = amount();

		bamount = a/2;

		return bamount;
	}
	

	// 모든기록 조회 수입/지출 선택
	public void select01() {
        	Scanner sc = new Scanner(System.in);

        	System.out.println("         <<모든기록조회를 시작합니다>>          ");
        	System.out.println("================================================");
        	System.out.println("        [수입] 또는 [지출]을 입력하세요         ");
        	System.out.println("================================================");
        	System.out.print("수입 또는 지출 입력 >> ");
        	String inserti = sc.nextLine();

        	if(inserti.equals("수입")) {
        		selectAlli();

        	} else if(inserti.equals("지출")) {
        		selectAlle();

        	} else {
        		ree();
        	}
	}

	// 모든 수입 조회
	public static InquiryVo selectAlli() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		InquiryVo selecti1 = new InquiryVo();

		try {
			String sql = "SELECT to_char(in_date, 'YYYY/MM/DD') AS in_date, in_list,in_amount,"
					+ "in_memo FROM income ORDER BY in_date";

			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리

			System.out.println();
			System.out.println("===============================================================");
			System.out.println(" 수입 내용을 조회합니다");
			System.out.println("===============================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===============================================================");

			while(rs.next()) {
				System.out.println(rs.getString("in_date")+"\t"+rs.getString("in_list")
					+"\t\t"+changeFormat(rs.getInt("in_amount"))+"원\t"+rs.getString("in_memo"));
			}

			System.out.println("===============================================================");
			System.out.println(" 총 수입 금액: \t"+changeFormat(inamount())+" 원");
			System.out.println("===============================================================");
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(모든수입내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecti1;
	}

	// 모든 지출 조회
	public static InquiryVo selectAlle() {
		String sql = "SELECT to_char(out_date, 'YYYY/MM/DD') AS out_date,out_list,out_amount,"
				+ "out_memo FROM expenditure ORDER BY out_date";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		InquiryVo selecte1 = new InquiryVo();

		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();			// 쿼리문 결과 처리

			System.out.println();
			System.out.println("===============================================================");
			System.out.println(" 지출 내용을 조회합니다 ");
			System.out.println("===============================================================");
			System.out.println("[지출날짜]\t[카테고리]\t[지출금액]\t[상세내용]");
			System.out.println("===============================================================");

			while(rs.next()) {
				System.out.println(rs.getString("out_date")+"\t"+rs.getString("out_list")+
						"\t\t"+changeFormat(rs.getInt("out_amount"))+"원\t"+rs.getString("out_memo"));
			}
			System.out.println("===============================================================");
			System.out.println(" 총 지출 금액: \t"+changeFormat(outamount())+" 원");
			System.out.println("===============================================================");
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회 (모든지출내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecte1;
	}


	// 수입 날짜 조회
	public static InquiryVo selectInD() {
		InquiryVo selecti2 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

        	System.out.println("\n===================================");
        	System.out.println(" 찾고싶은 날짜를 입력하세요 ");
        	System.out.println("  찾고 싶은 년도 = ? ");
        	System.out.println("  찾고 싶은 월 = ?/? ");
        	System.out.println("  찾고 싶은 일 = ?/?/? ");
        	System.out.println("===================================");
        	System.out.print("날짜를 입력 >> ");
        	selecti2.setIn_date(sc.nextLine());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(in_date, 'YYYY-MM-DD') AS in_date,in_list,in_amount,"
					+ "in_memo FROM income WHERE in_date LIKE ? ORDER BY in_date";

			conn = DBManager.getCoonnection();					// DB 연결
			pstmt = conn.prepareStatement(sql);					// 쿼리문 실행
			pstmt.setString(1, "%"+selecti2.getIn_date()+"%");			// 날짜(년/월) 입력 값
			rs = pstmt.executeQuery();						// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 지출 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("in_date")+"\t"+rs.getString("in_list")
					+"\t\t"+changeFormat(rs.getInt("in_amount"))+"원\t"+rs.getString("in_memo"));
			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(수입 날짜 내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecti2;
	}

	// 수입 카테고리 조회
	public static InquiryVo selectInC() {
		InquiryVo selecti4 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n======================================");
        	System.out.println(" 찾고싶은 카테고리(형태)를 입력하세요 ");
        	System.out.println("======================================");
        	System.out.print("카테고리를 입력 >> ");
        	selecti4.setin_list(sc.nextLine());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(in_date, 'YYYY-MM-DD') AS in_date,in_list,in_amount,"
					+ "in_memo FROM income WHERE in_list=? ORDER BY in_date";

			conn = DBManager.getCoonnection();			// DB 연결
			pstmt = conn.prepareStatement(sql);			// 쿼리문 실행
			pstmt.setString(1, selecti4.getin_list());		// 카테고리 입력 값
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 수입 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("in_date")+"\t"+rs.getString("in_list")
					+"\t\t"+changeFormat(rs.getInt("in_amount"))+"원\t"+rs.getString("in_memo"));
			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(카테고리 수입내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecti4;
	}

	// 수입 상세내역으로 조회
	public static InquiryVo selectInM() {
		InquiryVo selecti5 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n======================================");
        	System.out.println(" 찾고싶은 상세내용을 입력하세요 ");
        	System.out.println("======================================");
        	System.out.print("상세내용을 입력 >> ");
        	selecti5.setIn_memo(sc.nextLine());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(in_date, 'YYYY-MM-DD') AS in_date,in_list,in_amount,"
					+ "in_memo FROM income WHERE in_memo LIKE ? ORDER BY in_date";

			conn = DBManager.getCoonnection();			// DB 연결
			pstmt = conn.prepareStatement(sql);			// 쿼리문 실행
			pstmt.setString(1, "%"+selecti5.getIn_memo()+"%");	// 카테고리 입력 값
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 수입 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("in_date")+"\t"+rs.getString("in_list")
					+"\t\t"+changeFormat(rs.getInt("in_amount"))+"원\t"+rs.getString("in_memo"));
			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(메모 수입 내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecti5;
	}


	// 지출 날짜 조회
	public static InquiryVo selectOutD() {
		InquiryVo selecte2 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n===================================");
        	System.out.println(" 찾고싶은 날짜를 입력하세요 ");
        	System.out.println("  찾고 싶은 년도 = ? ");
        	System.out.println("  찾고 싶은 월 = ?/? ");
        	System.out.println("  찾고 싶은 일 = ?/?/? ");
        	System.out.println("===================================");
        	System.out.print("날짜를 입력 >> ");
        	selecte2.setOut_date(sc.nextLine());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(out_date, 'YYYY-MM-DD') AS out_date,out_list,out_amount,"
					+ "out_memo FROM expenditure WHERE out_date LIKE ? ORDER BY out_date";

			conn = DBManager.getCoonnection();					// DB 연결
			pstmt = conn.prepareStatement(sql);					// 쿼리문 실행
			pstmt.setString(1, "%"+selecte2.getOut_date()+"%");			// 날짜 입력 값
			rs = pstmt.executeQuery();						// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 수입 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("out_date")+"\t"+rs.getString("out_list")+
						"\t\t"+changeFormat(rs.getInt("out_amount"))+"원\t"+rs.getString("out_memo"));
			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(지출 날짜 내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecte2;
	}

	// 지출 카테고리 조회
	public static InquiryVo selectOutC() {
		InquiryVo selecte4 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n======================================");
        	System.out.println(" 찾고싶은 카테고리(형태)를 입력하세요 ");
        	System.out.println("======================================");
        	System.out.print("카테고리를 입력 >> ");
        	selecte4.setout_list(sc.nextLine());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(out_date, 'YYYY-MM-DD') AS out_date,out_list,out_amount,"
					+ "out_memo FROM expenditure WHERE out_list=? ORDER BY out_date";

			conn = DBManager.getCoonnection();				// DB 연결
			pstmt = conn.prepareStatement(sql);				// 쿼리문 실행
			pstmt.setString(1, selecte4.getout_list());			// 카테고리 입력 값
			rs = pstmt.executeQuery();					// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 수입 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("out_date")+"\t"+rs.getString("out_list")+
						"\t\t"+changeFormat(rs.getInt("out_amount"))+"원\t"+rs.getString("out_memo"));

			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(지출 카테고리 내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecte4;
	}

	// 지출 상세내용으로 조회
	public static InquiryVo selectOutM() {
		InquiryVo selecte5 = new InquiryVo();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n======================================");
        	System.out.println(" 찾고싶은 상세내용을 입력하세요 ");
        	System.out.println("======================================");
        	System.out.print("상세내용을 입력 >> ");
        	selecte5.setOut_memo(sc.nextLine());


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			String sql = "SELECT to_char(out_date, 'YYYY-MM-DD') AS out_date,out_list,out_amount,"
					+ "out_memo FROM expenditure WHERE out_memo LIKE ? ORDER BY out_date";

			conn = DBManager.getCoonnection();				// DB 연결
			pstmt = conn.prepareStatement(sql);				// 쿼리문 실행
			pstmt.setString(1, "%"+selecte5.getOut_memo()+"%");		// 카테고리 입력 값
			rs = pstmt.executeQuery();					// 쿼리문 결과 처리

			System.out.println("\n===========================================================");
			System.out.println(" 입력하신 수입 내용을 조회합니다");
			System.out.println("===========================================================");
			System.out.println("[수입날짜]\t[카테고리]\t[수입금액]\t[상세내용]");
			System.out.println("===========================================================");

			while(rs.next()) {
				System.out.println(rs.getString("out_date")+"\t"+rs.getString("out_list")+
						"\t\t"+changeFormat(rs.getInt("out_amount"))+"원\t"+rs.getString("out_memo"));
			}
		} catch(Exception e) {
			System.out.println("\n예외 발생시 처라할 코드: 쿼리문 조회(지출 상세내용)");
		}
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selecte5;
	}


	// 다시 입력 멘트
	public static void ree() {
		System.out.println("==================================================");
		System.out.println("\t제시된 단어를 다시 입력해주세요.");
		System.out.println("==================================================");
	}

	// 금액 천단위 콤마
	public static String changeFormat(int amount) {
		DecimalFormat df = new DecimalFormat("###,###");
		String money = df.format(amount);
		return money;		
	}


}