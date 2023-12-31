package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dto.BucketlistVo;
import util.DBManager;

public class BucketList {
	
	// 버킷리스트 
	public void BucketList1() {
		Scanner sc = new Scanner(System.in);
		
		boolean brun=true;
		
		while(brun) {
			System.out.println();
			System.out.println("                     <<버킷리스트 화면>>                       ");
			System.out.println("===============================================================");
			System.out.println("| 1. 버킷리스트 조회 | 2. 버킷리스트 등록 | 3.버킷리스트 성공 |");
			System.out.println("| 4. 버킷리스트 삭제 | 5. 메인화면 |");
			System.out.println("===============================================================");
			System.out.print("번호를 입력 >> ");
			int bucket = sc.nextInt();
			System.out.println();
			
			if (bucket==1) {
				selectBucketlist();
				
			} else if(bucket==2) {
				insertBucketlist();
				
				
			} else if(bucket==3) {
				updateBucketlist();
				
			} else if(bucket==4) {
				deleteBucketlist();
				
			} else if(bucket==5) {
				System.out.println("메인화면으로 이동합니다");
				brun=false;
				
			} else {
				re();
				
			}
		}
		
	}
	
	
	
	// 버킷리스트 조회
	public static BucketlistVo selectBucketlist() {
		String sql = "SELECT bl_number,bl_name,target_amount,"
				+ " bl_success FROM bucketlist ORDER BY bl_number";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		BucketlistVo selectb1 = new BucketlistVo();
		
		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리
			
			System.out.println("\n버킷리스트를 조회합니다\n");
			System.out.println("===========================================================");
			System.out.println("버킷리스트 활용 가능 금액: \t"+Select.bamount()+" 원");
			System.out.println("===========================================================");
			System.out.println("[번호]\t[버킷리스트이름]\t[목표금액]\t[성공여부]");
			System.out.println("===========================================================");
			
			while(rs.next()) {
				System.out.println(rs.getInt("bl_number")+"\t"+rs.getString("bl_name")+
						"\t\t"+Select.changeFormat(rs.getInt("target_amount"))+"원\t"+rs.getBoolean("bl_success"));
			}
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: 쿼리문 조회(버킷리스트)");
		}
		
		DBManager.close(conn, pstmt, rs);	// DB 닫기
		return selectb1;
	}
		
	// 버킷리스트 등록
	public static BucketlistVo insertBucketlist() {
		BucketlistVo insertB = new BucketlistVo();
         Scanner sc = new Scanner(System.in);
         
         System.out.println("\n버킷리스트를 등록합니다\n");
         System.out.println("==============================");
         System.out.println("버킷리스트의 이름을 입력하세요");
         System.out.println("==============================");
         System.out.print("입력 >> ");
         insertB.setBl_name(sc.nextLine());
         
         System.out.println("===================================");
         System.out.println("버킷리스트의 목표 금액을 입력하세요");
         System.out.println("===================================");
         System.out.print("입력 >> ");
         insertB.setTarget_amount(sc.nextInt());
         
         System.out.println("\n===============================================");
         System.out.println("버킷리스트 이름 : " + insertB.getBl_name());
         System.out.println("버킷리스트 목표금액 : " + insertB.getTarget_amount());
         System.out.println("===============================================");
         System.out.println("등록하시겠습니까?\n1.확인 | 2.취소");
         System.out.println("===============================================");
         System.out.print("입력 >> ");
         int yesOrNO = sc.nextInt();

         Connection conn = null;
         PreparedStatement pstmt = null;

         if(yesOrNO==1) {
            try {
               String sql = "INSERT INTO bucketlist VALUES(b_seq.NEXTVAL,?, ?, 0)";
               
               conn = DBManager.getCoonnection();
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, insertB.getBl_name());
               pstmt.setInt(2, insertB.getTarget_amount());
               
               pstmt.executeUpdate();
               System.out.println("=======================================");
               System.out.println("등록되었습니다.");
               System.out.println("=======================================");
               
            } catch (Exception e) {
               System.out.println("예외 발생시 처라할 코드: 쿼리문 삽입(버킷리스트)");
            }
         } else {
            System.out.println("=======================================");
            System.out.println("등록을 취소하였습니다.");
            System.out.println("=======================================");
         }
         DBManager.close(conn, pstmt);
         return insertB;
      }
	
	
	// 버킷리스트 성공 등록
	public static BucketlistVo updateBucketlist() {
		BucketlistVo updateB = new BucketlistVo();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n버킷리스트를 성공여부를 등록합니다");
		System.out.println("===========================================================");
		System.out.println("[번호]\t[버킷리스트이름]\t[목표금액]\t[성공여부]");
		selectAllBucket();
		System.out.println();
		System.out.print("성공 등록할 번호를 입력 >> ");
		updateB.setBl_number(sc.nextInt());
		
	    System.out.println("===============================================");
        System.out.println("성공 등록할 버킷리스트 번호: " + updateB.getBl_number());
        System.out.println("===============================================");
        System.out.println("성공여부를 등록하시겠습니까?\n1.확인 | 2.취소");
        System.out.println("===============================================");
        System.out.print("입력 >> ");
        int yesOrNO = sc.nextInt();
		
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		if(yesOrNO==1) {
			try {
				String sql = "UPDATE bucketlist SET bl_success = 1 WHERE bl_number=?";
				
				conn = DBManager.getCoonnection();		// DB 연결
				pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
				pstmt.setInt(1, updateB.getBl_number());
				
				pstmt.executeUpdate();					// insert, update, delete 쿼리문 결과 처리
				System.out.println("=======================================");
				System.out.println("등록되었습니다.");
				System.out.println("=======================================");
				
			} catch(Exception e) {
				System.out.println("예외 발생시 처라할 코드: 쿼리문 수정(버킷리스트)");
			}
		} else {
            System.out.println("=======================================");
            System.out.println("등록을 취소하였습니다.");
            System.out.println("=======================================");
         }
		DBManager.close(conn, pstmt);	// DB 닫기
		return updateB;
	}
	
	
	// 버킷리스트 삭제
	public static BucketlistVo deleteBucketlist() {
		BucketlistVo deleteB = new BucketlistVo();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n버킷리스트를 삭제합니다");
		System.out.println("===========================================================");
		System.out.println("[번호]\t[버킷리스트이름]\t[목표금액]\t[성공여부]");
		selectAllBucket();
		System.out.println();
		System.out.print("성공 등록할 번호를 입력 >> ");
		deleteB.setBl_number(sc.nextInt());
		
	    System.out.println("===============================================");
        System.out.println("삭제할 버킷리스트 번호: " + deleteB.getBl_number());
        System.out.println("===============================================");
        System.out.println("버킷리스트를 삭제하시겠습니까?\n1.확인 | 2.취소");
        System.out.println("===============================================");
        System.out.print("입력 >> ");
        int yesOrNO = sc.nextInt();
		
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		if(yesOrNO==1) {
			try {
				String sql = "DELETE FROM bucketlist WHERE bl_number=?";
				
				conn = DBManager.getCoonnection();		// DB 연결
				pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
				pstmt.setInt(1, deleteB.getBl_number());
				
				pstmt.executeUpdate();					// insert, update, delete 쿼리문 결과 처리
				System.out.println("=======================================");
				System.out.println("삭제되었습니다.");
				System.out.println("=======================================");
				
			} catch(Exception e) {
				System.out.println("예외 발생시 처라할 코드: 쿼리문 삭제(버킷리스트)");
			}
		} else {
            System.out.println("=======================================");
            System.out.println("삭제를 취소하였습니다.");
            System.out.println("=======================================");
         }
		DBManager.close(conn, pstmt);	// DB 닫기
		return deleteB;
	}
	
	// 다시입력 멘트
	public static void re() {
		System.out.println("==================================================");
		System.out.println("\n없는 번호 입니다. 제시된 번호를 다시 입력해주세요.\n");
		System.out.println("==================================================");
	}
	
	// 프로그램 종료 멘트
	public void end() {
		System.out.println("\n==========================");
		System.out.println("프로그램을 종료시겠습니까?");
		System.out.println("1 가계부를 종료한다");
		System.out.println("2.가계부로 돌아간다");
		System.out.println("==========================");
		System.out.print("번호를 입력 >> ");
	}
	
	
	
	
	
	
	// DB 버킷리스트 조회(select) 함수
	//		selectBucket(1);
	public static void selectBucket(int code) {
		String sql = "SELECT * FROM bucketlist where bl_number=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			pstmt.setInt(1, code);					// 1: 물음표 순서, code: 물음표 값
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리
		
		while(rs.next()) {
			int bl_number = rs.getInt("bl_number");
			String bl_name = rs.getString("bl_name");
			int target_amount = rs.getInt("target_amount");
			Boolean bl_success = rs.getBoolean("bl_success");
			
			System.out.print(bl_number+"\t");
			System.out.print(bl_name+"\t");
			System.out.print(target_amount+"\t");
			System.out.print(bl_success+"\n");
		}
		
		
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: 쿼리문 조회(버킷리스트)");
		}
		
		DBManager.close(conn, pstmt, rs);	// DB 닫기
	}
	
	// DB 버킷리스트 모든 기록 조회(select) 함수
	//		selectAllBucket();
	public static void selectAllBucket() {
		String sql = "SELECT * FROM bucketlist ORDER BY bl_number";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = DBManager.getCoonnection();		// DB 연결
			pstmt = conn.prepareStatement(sql);		// 쿼리문 실행
			rs = pstmt.executeQuery();				// 쿼리문 결과 처리
		
		
		while(rs.next()) {
			int bl_number = rs.getInt("bl_number");
			String bl_name = rs.getString("bl_name");
			int target_amount = rs.getInt("target_amount");
			Boolean bl_success = rs.getBoolean("bl_success");
			
			System.out.print(bl_number+"\t");
			System.out.print(bl_name+"\t\t");
			System.out.print(target_amount+"\t\t");
			System.out.print(bl_success+"\n");
		}
		
		
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: 쿼리문 조회");
		}
		
		DBManager.close(conn, pstmt, rs);	// DB 닫기
	}
			
	
	
	
	
}
