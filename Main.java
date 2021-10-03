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
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile); //con�̶�� ��ü�� db���� ����!
			
			//������ ��ȸ
			System.out.println("\n***** ������ ��ȸ *****");
			Statement stat1 = con.createStatement(); //��ɾ ������ ��ü ����
			String sql1 = "Select * from g_artists"; //������ �ϳ� ������
			ResultSet rs1 = stat1.executeQuery(sql1);
			
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + ". " + name);
			}
			stat1.close();
			
			//������ �߰�
			System.out.println("\n***** �� ������ �߰� *****");
			Statement stat2 = con.createStatement();
			//insert case 1
			String sql2 = "INSERT into g_artists (name, a_type, a_year, debut, regdate)"
						+ "values ('��ź�ҳ��', '����', '2010���','2013��',datetime('now', 'localtime'))";
			//insert case 2
			PreparedStatement pstmt = con.prepareStatement("INSERT into g_artists (name, a_type, a_year, debut, regdate)"
														+ "values (?,?,?,?,datetime('now', 'localtime'))");
			pstmt.setString(1, "������");
			pstmt.setString(2, "����");
			pstmt.setString(3, "2000���, 2010���, 2020���");
			pstmt.setString(4, "2006��");
			
			//int cnt = stat2.executeUpdate(sql2);
			int cnt2 = pstmt.executeUpdate();
			
			if(cnt2 > 0)		System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
			else						System.out.println("[Error] ������ �߰� ����!");
			stat2.close();
			
			
			
			//������ ����
			System.out.println("\n***** ������ ���� *****");
			Statement stat3 = con.createStatement();
			String sql3 = "UPDATE g_artists set a_year = '2000, 2010, 2020���'"
						+ "where id=2;";
			
			//PreparedStatement pstmt1 = con.prepareStatement(sql3);
			
			int count = stat3.executeUpdate(sql3);
			if(count > 0)	System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else			System.out.println("[Error] ������ ���� ����!");
			
			stat3.close();
			
			
			
			//������ ����
			System.out.println("\n***** ������ ���� *****");
			Statement stat4 = con.createStatement();
			String sql4 = "DELETE from g_artists where regdate like 'datetime%'";
			
			int check = stat4.executeUpdate(sql4);
			if(check > 0)	System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else			System.out.println("[Error] ������ ���� ����!");
			
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