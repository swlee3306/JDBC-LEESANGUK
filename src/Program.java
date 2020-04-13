import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) {
		String jdbc_driver = "org.mariadb.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC";
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url, "root", "Ykk159357");
			Statement st = con.createStatement();
			String sql = "SELECT * FROM databasetest.member";
			ResultSet rs = st.executeQuery(sql);
			
//          for 문을 이용한 작성문 
			
/*			String[] id = new String[6];
			String[] userName = new String[6];
			String[] birth = new String[6];
			String[] dept = new String[6];
			String[] email = new String[6];
			
			
			for(int i = 0 ; i<6; i++) {
				rs.next();
				
				id[i] = rs.getString("id");
				userName[i] = rs.getString("username");
				birth[i] = rs.getString("brith");
				dept[i] = rs.getString("dept");
				email[i] = rs.getString("email");
				
				System.out.println("id : "+id[i]+", userName: "+userName[i]+", birth: "+birth[i]+", dept: "+dept[i]+", email: "+email[i]);
				
			}*/
			
			
			
// 			while문을 이용한 방법			
			
			while(rs.next()) {
				String id2 = rs.getString("id");
				String userName2 = rs.getString("username");
				String birth2 = rs.getString("brith");
				String dept2 = rs.getString("dept");
				String email2 = rs.getString("email");
				
				System.out.println("id : "+id2+", userName: "+userName2+", birth: "+birth2+", dept: "+dept2+", email: "+email2);
			}
			
//			String sql2 = "INSERT INTO member value('7','sang','1995-08-04','Computer', 'lee')";
//			int rs2 = st.executeUpdate(sql2);
			
			
			rs.close();
			st.close();
			con.close();			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
