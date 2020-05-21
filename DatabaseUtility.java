package utilities;

import java.sql.DriverManager;

public class DatabaseUtility {
	public static java.sql.Connection getDatabaseConnection(){
	
	try{	
     java.sql.Connection conn = null;
      String url="jdbc:mysql://localhost:3306/technoreach";
     String Driver="com.mysql.jdbc.Driver";
	String username="root";
	String pass="123456";
     // Production
	/* String url="jdbc:mysql://localhost:3306/technore_technoreach";
     String Driver="com.mysql.jdbc.Driver";
	String username="technore_mysql";
	String pass="pa55w0rd!@#$%";*/
	//test in prod
	
	/*String url="jdbc:mysql://localhost:3306/technore_newtechnoreachit";
    String Driver="com.mysql.jdbc.Driver";
	String username="technore_techdb";
	String pass="Techn0reachit!@#$%";*/
	  Class.forName(Driver);
	  conn=DriverManager.getConnection(url,username,pass);
	  conn.setAutoCommit(false);
	 	   return conn;
	
	}catch(Exception e){
		
		e.printStackTrace();
		
	}
return null;
	
}
}
