package utilities;

import java.sql.PreparedStatement;
import java.util.Calendar;

import com.mysql.jdbc.Connection;

import beans.AdminBean;

/**
 * @author sandhya.motepalli
 *
 */
public class CreateAdmin {
	public static void main(String[] args) throws Exception {
		Calendar calendar = Calendar.getInstance(); 
		java.sql.Timestamp creationdatetime = new java.sql.Timestamp(calendar.getTime().getTime());
		Connection conn=(Connection) DatabaseUtility.getDatabaseConnection();
		AdminBean lb=new AdminBean();
		lb.setUsername("sandyurce@gmail.com");
		lb.setPassword("123456");
		lb.setUsertype("admin");
		lb.setCreationdatetime(creationdatetime);
		
		boolean res= insertUser(lb,conn);
		if(res)
		{
		System.out.println("Admin Created.");
		}
		else
		{
			System.out.println("Admin Not Created.");
		}
	}
	
	public static boolean insertUser(AdminBean lb,Connection conn ) throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		java.sql.Timestamp creationdatetime = new java.sql.Timestamp(calendar.getTime().getTime());
		String username=lb.getUsername();
		String usertype=lb.getUsertype();
		String password=lb.getPassword();
		try
		{
			String SQL = "INSERT  INTO userlogin (username,password,userrole,creationdatetime) values (?, ?, ?, ?)";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, PasswordAuthentication.hashpw(password,PasswordAuthentication.gensalt()));
			stmt.setString(3, usertype);
			stmt.setTimestamp(4, creationdatetime);
	        stmt.executeUpdate();
	        conn.commit();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			return false;
		}
	}
}