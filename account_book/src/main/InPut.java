package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import dto.InquiryVo;
import util.DBManager;

public class InPut {
	
	// 입력
	public void insert() {
		Scanner sc = new Scanner(System.in);
		
		boolean irun=true;
		
		while(irun) {
			System.out.println();
			System.out.println("           <<입력 화면>>           ");
			System.out.println("===================================");
			System.out.println("|    1. 입력    |   2. 메인화면   |");
			System.out.println("===================================");
			System.out.print("사용할 번호를 입력 >> ");
			int insert = sc.nextInt();
			System.out.println();
			
			if (insert==1) {
				insert1();
				
			} else if(insert==2) {
				System.out.println("메인화면으로 이동합니다");
				irun=false;
				
			} else {
				BucketList.re();
			}
			
		}
	}
	
	// 수입 지출 선택 화면
	public void insert1() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("             <<내역을 입력합니다>>              ");
        System.out.println("================================================");
        System.out.println("        [수입] 또는 [지출]을 입력하세요         ");
        System.out.println("================================================");
        System.out.print(" 수입 또는 지출 입력 >> ");
        String inserti = sc.nextLine();
        
        if(inserti.equals("수입")) {
        	insert2();
        	
        } else if(inserti.equals("지출")) {
        	inser3();
        	
        } else {
        	ree();
        }
        
        
        
	}
	
	
	
	
	// 수입 입력
	public void insert2() {
		InquiryVo insert01 = new InquiryVo();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n");
		System.out.println("===================================");
        System.out.println(" 수입 카테고리(형태)를 입력하세요 ");
        System.out.println("===================================");
        System.out.print("카테고리를 입력 >> ");
        insert01.setin_list(sc.nextLine());
        
        
        System.out.println("\n");
        System.out.println("===================================");
        System.out.println(" 수입 날짜를 입력하세요 ");
        System.out.println("===================================");
        System.out.print("날짜를 입력 >> ");
        insert01.setIn_date(sc.nextLine());
        
        System.out.println("\n");
        System.out.println("===================================");
        System.out.println(" 수입 금액을 입력하세요 ");
        System.out.println("===================================");
        System.out.print("금액을 입력 >> ");
        insert01.setIn_amount(sc.nextInt());
        
        System.out.println("\n");
        System.out.println("===================================");
        System.out.println(" 상세 내용을 입력하세요 ");
        System.out.println("===================================");
        System.out.print("상세내용을 입력 >> ");
        sc.nextLine();
        try {
        	insert01.setIn_memo(sc.nextLine());
        } catch(Exception e) {
        	rer();
        }
        
        System.out.println("\n");
        System.out.println("===============================================");
        System.out.println(" 카테고리 : " + insert01.getin_list());
        System.out.println("수입 날짜 : " + insert01.getIn_date());
        System.out.println("수입 금액 : " + insert01.getIn_amount());
        System.out.println("상세 내용 : " + insert01.getIn_memo());
        System.out.println("===============================================");
        System.out.println("입력하시겠습니까?\n1.확인 | 2.취소");
        System.out.println("===============================================");
        System.out.print("입력 >> ");
        int yesOrNO = sc.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        if(yesOrNO==1) {
           try {
        	  String sql = "INSERT INTO income VALUES(in_seq.NEXTVAL, ?, ?, ?, ?)";
              
              conn = DBManager.getCoonnection();
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, insert01.getIn_date());
              pstmt.setString(2, insert01.getin_list());
              pstmt.setInt(3, insert01.getIn_amount());
              pstmt.setString(4, insert01.getIn_memo());
              
              pstmt.executeUpdate();
              System.out.println("=======================================");
              System.out.println("입력되었습니다.");
              System.out.println("=======================================");
              
           } catch (Exception e) {
              System.out.println("예외 발생시 처라할 코드: 쿼리문 삽입(수입)");
           }
        } else {
           System.out.println("=======================================");
           System.out.println("입력을 취소하였습니다.");
           System.out.println("=======================================");
        }
        DBManager.close(conn, pstmt);
	}
	
	// 지출 입력
	public void inser3() {
		InquiryVo insert02 = new InquiryVo();
        Scanner sc = new Scanner(System.in);
		
		System.out.println("===================================");
        System.out.println(" 지출 카테고리(형태)를 입력하세요 ");
        System.out.println("===================================");
        System.out.print("카테고리를 입력 >> ");
        insert02.setout_list(sc.nextLine());
        
        System.out.println("===================================");
        System.out.println(" 지출 날짜를 입력하세요 ");
        System.out.println("===================================");
        System.out.print("날짜를 입력 >> ");
        insert02.setOut_date(sc.nextLine());
        
        System.out.println("===================================");
        System.out.println(" 지출 금액을 입력하세요 ");
        System.out.println("===================================");
        System.out.print("금액을 입력 >> ");
        insert02.setOut_amount(sc.nextInt());
        
        System.out.println("===================================");
        System.out.println(" 상세 내용을 입력하세요 ");
        System.out.println("===================================");
        System.out.print("상세내용을 입력 >> ");
        sc.nextLine();
        insert02.setOut_memo(sc.nextLine());
        
        System.out.println("\n===============================================");
        System.out.println(" 카테고리 : " + insert02.getout_list());
        System.out.println("수입 날짜 : " + insert02.getOut_date());
        System.out.println("수입 금액 : " + insert02.getOut_amount());
        System.out.println("상세 내용 : " + insert02.getOut_memo());
        System.out.println("===============================================");
        System.out.println("입력하시겠습니까?\n1.확인 | 2.취소");
        System.out.println("===============================================");
        System.out.print("입력 >> ");
        int yesOrNO = sc.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        if(yesOrNO==1) {
           try {
        	  String sql = "INSERT INTO expenditure VALUES(out_seq.NEXTVAL, ?, ?, ?, ?)";
              
              conn = DBManager.getCoonnection();
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, insert02.getOut_date());
              pstmt.setString(2, insert02.getout_list());
              pstmt.setInt(3, insert02.getOut_amount());
              pstmt.setString(4, insert02.getOut_memo());
              
              pstmt.executeUpdate();
              System.out.println("=======================================");
              System.out.println("입력되었습니다.");
              System.out.println("=======================================");
              
           } catch (Exception e) {
              System.out.println("예외 발생시 처라할 코드: 쿼리문 삽입(지출)");
           }
        } else {
           System.out.println("=======================================");
           System.out.println("입력을 취소하였습니다.");
           System.out.println("=======================================");
        }
        DBManager.close(conn, pstmt);
	}
	
	// 잘못입력 멘트
	public void rer() {
		System.out.println("잘못입력하셨습니다 다시 입력해주세요");
	}
	
	// 다시입력 멘트
	public void ree() {
		System.out.println("==================================================");
		System.out.println("\n없는 단어 입니다. 제시된 단어를 다시 입력해주세요.\n");
		System.out.println("==================================================");
	}
	
}
