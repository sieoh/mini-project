package util;

import java.sql.DriverManager;		// 1. DB 관리
import java.sql.Connection;			// 2. DB 연결
import java.sql.PreparedStatement;	// 3. DB 쿼리문 사용
import java.sql.ResultSet;			// 4. DB 뭐리문 수행

public class DBManager {
	// 필드
	// 생성자
	// 메서드: DB관리(1.DB 연결 / 2.DB 닫기)

	public static Connection getCoonnection() {
		Connection conn=null;

		try {
	//		(1 단계) JDBC 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");			// 예외 발생 가능1
			
	//		(2 단계) 데이터 베이스 연결 객체 생성
			// 데이터베이스 연결 정보
			String url 	= "jdbc:oracle:thin:@localhost:1521:orcl";
			String uid 	= "account_book";
			String pass = "1234";
			conn = DriverManager.getConnection(url,uid,pass);	// 예외 발생 가능2
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: DB 연결");
		}
		return conn;
	}

//	2. DB 닫기(select)
	public static void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(rs!=null) {
				rs.close();
			}
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: DB 닫기(select)");
		}
	}
//	2. DB 닫기(insert,update, delete)
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
		} catch(Exception e) {
			System.out.println("예외 발생시 처라할 코드: DB 닫기(insert,update, delete)");
		}
	}
}
