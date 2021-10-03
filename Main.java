import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!");
		
		Connection con = null;
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile); //con이라는 객체에 db파일 연결!
			
			//데이터 조회
			System.out.println("\n***** 데이터 조회 *****");
			Statement stat1 = con.createStatement(); //명령어를 저장할 객체 생성
			String sql1 = "Select * from g_artists"; //쿼리문 하나 저장함
			ResultSet rs1 = stat1.executeQuery(sql1);
			
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + ". " + name);
			}
			stat1.close();
			
			//데이터 추가
			System.out.println("\n***** 새 데이터 추가 *****");
			Statement stat2 = con.createStatement();
			//insert case 1
			String sql2 = "INSERT into g_artists (name, a_type, a_year, debut, regdate)"
						+ "values ('방탄소년단', '남성', '2010년대','2013년',datetime('now', 'localtime'))";
			//insert case 2
			PreparedStatement pstmt = con.prepareStatement("INSERT into g_artists (name, a_type, a_year, debut, regdate)"
														+ "values (?,?,?,?,datetime('now', 'localtime'))");
			pstmt.setString(1, "전은주");
			pstmt.setString(2, "여성");
			pstmt.setString(3, "2000년대, 2010년대, 2020년대");
			pstmt.setString(4, "2006년");
			
			//int cnt = stat2.executeUpdate(sql2);
			int cnt2 = pstmt.executeUpdate();
			
			if(cnt2 > 0)		System.out.println("새로운 데이터가 추가되었습니다!");
			else						System.out.println("[Error] 데이터 추가 오휴!");
			stat2.close();
			
			
			
			//데이터 수정
			System.out.println("\n***** 데이터 수정 *****");
			Statement stat3 = con.createStatement();
			String sql3 = "UPDATE g_artists set a_year = '2000, 2010, 2020년대'"
						+ "where id=2;";
			
			//PreparedStatement pstmt1 = con.prepareStatement(sql3);
			
			int count = stat3.executeUpdate(sql3);
			if(count > 0)	System.out.println("데이터가 수정되었습니다!");
			else			System.out.println("[Error] 데이터 수정 오류!");
			
			stat3.close();
			
			
			
			//데이터 삭제
			System.out.println("\n***** 데이터 삭제 *****");
			Statement stat4 = con.createStatement();
			String sql4 = "DELETE from g_artists where regdate like 'datetime%'";
			
			int check = stat4.executeUpdate(sql4);
			if(check > 0)	System.out.println("데이터가 삭제되었습니다!");
			else			System.out.println("[Error] 데이터 삭제 오류!");
			
			stat4.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}