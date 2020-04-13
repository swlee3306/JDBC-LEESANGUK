import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddressBook {
	
	public static void main(String[] args) {
		String jdbc_driver = "org.mariadb.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC";
		
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url, "root", "Ykk159357");
			Statement st = con.createStatement();
			
			//여러번 돌릴때를 대비해 테이블 삭제를 먼저 실행
			String sql = "drop table addressbook;";
			st.execute(sql);
			
			//1) Java 프로젝트에서 Statement를 이용하여 addressbook 이라는 table 만들기
			
			StringBuilder sb = new StringBuilder();
	        sql = sb.append("create table if not exists addressbook(")
	                    .append("id int,")
	                    .append("name varchar(45),")
	                    .append("email varchar(60),")
	                    .append("address varchar(60)")
	                    .append(");").toString();
	 
	        st.execute(sql);
	        
			
	        //2) 위에서 생성한 addressbook table에 PreparedStatement를 이용하여 5개의 열을 채우기 (본인 임의로 데이터 생성)
	        
			sql= "insert into addressbook(id, name, email, address) values(?,?,?,?)";
			int result = 0;
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, 1);
	        ps.setString(2, "Lee");
	        ps.setString(3, "lee");
	        ps.setString(4, "Seoul");
	        
	        result = ps.executeUpdate();
	        
	        ps.setInt(1, 2);
	        ps.setString(2, "Kim");
	        ps.setString(3, "kim");
	        ps.setString(4, "Suwon");
	        
	        result = ps.executeUpdate();
	        
	        ps.setInt(1, 3);
	        ps.setString(2, "Ho");
	        ps.setString(3, "ho");
	        ps.setString(4, "Osan");
	        
	        result = ps.executeUpdate();
	        
	        ps.setInt(1, 4);
	        ps.setString(2, "Su");
	        ps.setString(3, "su");
	        ps.setString(4, "Incheon");
	        
	        result = ps.executeUpdate();
	        
	        ps.setInt(1, 5);
	        ps.setString(2, "Park");
	        ps.setString(3, "park");
	        ps.setString(4, "Busan");
	        
	        result = ps.executeUpdate();
	        
	        //2-1)addressbook의 모든 데이터를 Statement를 이용해서 조회하여 eclipse의 console에서 볼 수 있도록 반복문 및 System.out.printf 구현
	        
	        sql = "SELECT * FROM addressbook;";
			ResultSet rs = st.executeQuery(sql);
	        
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				System.out.println("id : "+id+", name : "+name+", email : "+email+", address : "+address);
			}
			
			//3) 2)번 코드 이후 PreparedStatement 이용하여 5개의 열의 email의 도메인을 @naver.com으로 UPDATE 수행
			sql= "update addressbook set email=? where id=?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1,"lee@naver.com");
			ps.setInt(2,1);
			
			result = ps.executeUpdate();
			
			ps.setString(1,"kim@naver.com");
			ps.setInt(2,2);
			
			result = ps.executeUpdate();
			
			ps.setString(1,"ho@naver.com");
			ps.setInt(2,3);
			
			result = ps.executeUpdate();
			
			ps.setString(1,"su@naver.com");
			ps.setInt(2,4);
			
			result = ps.executeUpdate();
			
			ps.setString(1,"park@naver.com");
			ps.setInt(2,5);
			
			result = ps.executeUpdate();
			
			
			//3-1) addressbook의 모든 데이터를 Statement를 이용해서 조회하여 eclipse의 console에서 볼 수 있도록 반복문 및 System.out.printf 구현
			
			System.out.println("=========================================================");
			
			sql = "SELECT * FROM addressbook;";
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				System.out.println("id : "+id+", name : "+name+", email : "+email+", address : "+address);
			}
			
			//4) 3)번 코드 이후 Statement를 이용하여 하위 2개의 행을 지우는 코드 구현
			
			sql = "SELECT * FROM addressbook;";
			rs = st.executeQuery(sql);
			
			int i = 0;
			
			while(true){	
				
				if(i == 2){
					break;
				}
				
				rs.last();
				
				String id = rs.getString("id");
				
				String sql2 = "DELETE FROM addressbook WHERE id ="+id+";";
				
				st.execute(sql2);
				
				String sql3 = "SELECT * FROM addressbook;";
				
				rs = st.executeQuery(sql3);
				
				i++;
					
			}
				
			System.out.println("=======================================================");
			
			//4-1) addressbook의 모든 데이터를 Statement를 이용해서 조회하여 eclipse의 console에서 볼 수 있도록 반복문 및 System.out.printf 구현
			
			sql = "SELECT * FROM addressbook;";
			rs = st.executeQuery(sql);
			
			while(rs.next()) {

				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				System.out.println("id : "+id+", name : "+name+", email : "+email+", address : "+address);
			}
			
			
	        ps.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}
