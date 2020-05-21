package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import beans.AdminBean;
import beans.UserBean;
import utilities.DatabaseUtility;
import utilities.PasswordAuthentication;


public class AdminDao {

	public AdminBean getAdminDetails(String username) throws Exception{
		AdminBean adminDetails =new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query ="Select * from userlogin where username ='"+ username +"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				adminDetails.setUsername(rs.getString("username"));
				adminDetails.setPassword(rs.getString("password"));
				adminDetails.setUsertype(rs.getString("userrole"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return adminDetails;
	}
	
	
	public static boolean insertTrainerUser(AdminBean lb,Connection conn ) throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		java.sql.Timestamp creationdatetime = new java.sql.Timestamp(calendar.getTime().getTime());
		String username=lb.getUsername();
		String usertype=lb.getUsertype();
		String password=lb.getPassword();
		try
		{
			String SQL = "INSERT  INTO userlogin (username,password,userrole,creationdatetime,status) values (?, ?, ?, ?,?)";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, PasswordAuthentication.hashpw(password,PasswordAuthentication.gensalt()));
			stmt.setString(3, usertype);
			stmt.setTimestamp(4, creationdatetime);
			stmt.setString(5, lb.getLoginstatus());
	        stmt.executeUpdate();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			return false;
		}
	}
	
	
	public static boolean insertUser(AdminBean lb,Connection conn,Savepoint point) throws Exception{
		Calendar calendar = Calendar.getInstance(); 
		java.sql.Timestamp creationdatetime = new java.sql.Timestamp(calendar.getTime().getTime());
		String username=lb.getUsername();
		String usertype=lb.getUsertype();
		String password=lb.getPassword();
		try
		{
			String SQL = "INSERT  INTO userlogin (username,password,userrole,creationdatetime,status) values (?, ?, ?, ?,?)";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, PasswordAuthentication.hashpw(password,PasswordAuthentication.gensalt()));
			stmt.setString(3, usertype);
			stmt.setTimestamp(4, creationdatetime);
			stmt.setString(5, lb.getLoginstatus());
	        stmt.executeUpdate();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback(point);
			return false;
		}
	}
	
	 public boolean userChangePassword(String username, String password) throws Exception{
			Connection conn = (Connection) DatabaseUtility.getDatabaseConnection();
			try{
				String Query="update userlogin set password=? where username=?"; 
				PreparedStatement pstmt =conn.prepareStatement(Query);
				pstmt.setString(1, PasswordAuthentication.hashpw(password, PasswordAuthentication.gensalt()));
				pstmt.setString(2, username);
				pstmt.executeUpdate();
				conn.commit();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				conn.rollback();
			}
			 finally{
					if(conn!= null){
						try{
							conn.close();
						}catch(Exception e){
							e.printStackTrace();
							
						}
					}
				}
			return false;
		}
	
	 
	 public boolean deleteTrainerDetails(String username) throws Exception{
			Connection conn = (Connection) DatabaseUtility.getDatabaseConnection();
			try{
				String Query="delete from userlogin where username=? and status='active' and userrole='trainer'"; 
				PreparedStatement pstmt =conn.prepareStatement(Query);
				pstmt.setString(1, username);
				pstmt.executeUpdate();
				conn.commit();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				conn.rollback();
			}
			 finally{
					if(conn!= null){
						try{
							conn.close();
						}catch(Exception e){
							e.printStackTrace();
							
						}
					}
				}
			return false;
		}
	
	 
	public boolean insertTrainings(AdminBean trainingsBean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="insert into trainings(coursename,trainername,trainerinfo,duration,startdate,timings,fee,feeindollar,mode,creationdatetime,demolink,pagename) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainingsBean.getCoursename());
			pstmt.setString(2, trainingsBean.getTrainername());
			pstmt.setString(3, trainingsBean.getTrainerinfo());
			pstmt.setInt(4, trainingsBean.getDuration());
			pstmt.setString(5, trainingsBean.getStartdate());
			pstmt.setString(6, trainingsBean.getTimings());
			pstmt.setInt(7, trainingsBean.getFee());
			pstmt.setInt(8, trainingsBean.getFeeindollar());
			pstmt.setString(9, trainingsBean.getMode());
			pstmt.setTimestamp(10, creationdatetime);
			pstmt.setString(11, trainingsBean.getLink());
			pstmt.setString(12, "");
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public ArrayList<AdminBean> getAllTrainings() throws Exception{
		ArrayList<AdminBean> allTrainings = new ArrayList<AdminBean>();
		AdminBean trainingsBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainings";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				trainingsBean = new AdminBean();
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("coursename"));
				trainingsBean.setTrainername(rs.getString("trainername"));
				trainingsBean.setTrainerinfo(rs.getString("trainerinfo"));
				trainingsBean.setDuration(rs.getInt("duration"));
				trainingsBean.setStartdate(rs.getString("startdate"));
				trainingsBean.setTimings(rs.getString("timings"));
				trainingsBean.setMode(rs.getString("mode"));
				trainingsBean.setFee(rs.getInt("fee"));
				trainingsBean.setFeeindollar(rs.getInt("feeindollar"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainingsBean.setLink(rs.getString("demolink"));
				trainingsBean.setPagename(rs.getString("pagename"));
				allTrainings.add(trainingsBean);
				trainingsBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainings;
	}

	
	public AdminBean getTrainingDetailsById(int id) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainings where trainingid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("coursename"));
				trainingsBean.setTrainername(rs.getString("trainername"));
				trainingsBean.setTrainerinfo(rs.getString("trainerinfo"));
				trainingsBean.setDuration(rs.getInt("duration"));
				trainingsBean.setStartdate(rs.getString("startdate"));
				trainingsBean.setTimings(rs.getString("timings"));
				trainingsBean.setMode(rs.getString("mode"));
				trainingsBean.setFee(rs.getInt("fee"));
				trainingsBean.setFeeindollar(rs.getInt("feeindollar"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainingsBean.setLink(rs.getString("demolink"));
				trainingsBean.setPagename(rs.getString("pagename"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	
	public AdminBean getTrainingDetailsByTechnology(String technology) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainings where coursename like '"+technology+"' ";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("coursename"));
				trainingsBean.setTrainername(rs.getString("trainername"));
				trainingsBean.setTrainerinfo(rs.getString("trainerinfo"));
				trainingsBean.setDuration(rs.getInt("duration"));
				trainingsBean.setStartdate(rs.getString("startdate"));
				trainingsBean.setTimings(rs.getString("timings"));
				trainingsBean.setMode(rs.getString("mode"));
				trainingsBean.setFee(rs.getInt("fee"));
				trainingsBean.setFeeindollar(rs.getInt("feeindollar"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainingsBean.setLink(rs.getString("demolink"));
				trainingsBean.setPagename(rs.getString("pagename"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	
	public AdminBean getTrainingDetailsByCoursename(String technology) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainings where coursename='"+technology+"' ";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("coursename"));
				trainingsBean.setTrainername(rs.getString("trainername"));
				trainingsBean.setTrainerinfo(rs.getString("trainerinfo"));
				trainingsBean.setDuration(rs.getInt("duration"));
				trainingsBean.setStartdate(rs.getString("startdate"));
				trainingsBean.setTimings(rs.getString("timings"));
				trainingsBean.setMode(rs.getString("mode"));
				trainingsBean.setFee(rs.getInt("fee"));
				trainingsBean.setFeeindollar(rs.getInt("feeindollar"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainingsBean.setLink(rs.getString("demolink"));
				trainingsBean.setPagename(rs.getString("pagename"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	
	public boolean updateTrainingDetails(AdminBean trainingsBean) throws Exception{
		Connection conn=DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="update trainings set coursename=?,trainername=?,trainerinfo=?,duration=?,startdate=?,timings=?,fee=?,feeindollar=?,mode=?,creationdatetime=?,demolink=? where trainingid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainingsBean.getCoursename());
			pstmt.setString(2, trainingsBean.getTrainername());
			pstmt.setString(3, trainingsBean.getTrainerinfo());
			pstmt.setInt(4, trainingsBean.getDuration());
			pstmt.setString(5, trainingsBean.getStartdate());
			pstmt.setString(6, trainingsBean.getTimings());
			pstmt.setInt(7, trainingsBean.getFee());
			pstmt.setInt(8, trainingsBean.getFeeindollar());
			pstmt.setString(9, trainingsBean.getMode());
			pstmt.setTimestamp(10, creationdatetime);
			pstmt.setString(11, trainingsBean.getLink());
			pstmt.setInt(12, trainingsBean.getTrainingid());
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!= null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}
		}
		return false;
		
	}
	
	public boolean deleteTrainings(int id) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from trainings where trainingid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, id);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	
	/*careers start*/
	public boolean insertCareers(AdminBean careersBean) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="insert into careers(jobcode,description,position,experience,creationdatetime,description2,description3)values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, careersBean.getJobcode());
			pstmt.setString(2, careersBean.getDescription1());
			pstmt.setString(3, careersBean.getPosition());
			pstmt.setString(4, careersBean.getExperience());
			pstmt.setTimestamp(5, creationdatetime);
			pstmt.setString(6, careersBean.getDescription2());
			pstmt.setString(7, careersBean.getDescription3());
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	
	public ArrayList<AdminBean> getAllCareers() throws Exception{
		ArrayList<AdminBean> allCareers = new ArrayList<AdminBean>();
		AdminBean careersBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from careers";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				careersBean = new AdminBean();
				careersBean.setCareersid(rs.getInt("careersid"));
				careersBean.setDescription1(rs.getString("description"));
				careersBean.setPosition(rs.getString("position"));
				careersBean.setJobcode(rs.getInt("jobcode"));
				careersBean.setExperience(rs.getString("experience"));
				careersBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				careersBean.setDescription2(rs.getString("description2"));
				careersBean.setDescription3(rs.getString("description3"));
				allCareers.add(careersBean);
				careersBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allCareers;
	}
	
	
	public AdminBean getCareersByCode(int code) throws Exception{
		AdminBean careersBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		String Query="select * from careers where jobcode="+code;
		try{
			java.sql.PreparedStatement pstmt = conn.prepareStatement(Query);
			java.sql.ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				careersBean.setCareersid(rs.getInt("careersid"));
				careersBean.setDescription1(rs.getString("description"));
				careersBean.setPosition(rs.getString("position"));
				careersBean.setJobcode(rs.getInt("jobcode"));
				careersBean.setExperience(rs.getString("experience"));
				careersBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				careersBean.setDescription2(rs.getString("description2"));
				careersBean.setDescription3(rs.getString("description3"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return careersBean;
	}
	public boolean deleteCareers(int careersid) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from careers where careersid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, careersid);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	/*trainer functionality start*/
	
	public boolean insertTrainerDetails(AdminBean trainersBean,AdminBean loginbean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());
		Savepoint savePoint=null;
		savePoint = conn.setSavepoint("begin");
		try{
			String Query="insert into trainers(trainername,traineremailid ,technology ,trainerstatus,creationdatetime,trainingid) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainersBean.getTrainername());
			pstmt.setString(2, trainersBean.getTraineremail());
			pstmt.setString(3, trainersBean.getTechnology());
			pstmt.setString(4, trainersBean.getTrainerstatus());
			pstmt.setTimestamp(5, creationdatetime);
			pstmt.setInt(6, trainersBean.getTrainingid());
			pstmt.executeUpdate();
			boolean result= insertTrainerUser(loginbean,conn);
		      if(result)
		      {
		    	  conn.commit();
			        return true;
		      }
		      else
		      {
		    	conn.rollback(savePoint);
	   			return false; 
		      }
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback(savePoint);
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public ArrayList<AdminBean> getAllTrainers() throws Exception{
		ArrayList<AdminBean> allTrainers = new ArrayList<AdminBean>();
		AdminBean trainersBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainers";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				trainersBean = new AdminBean();
				trainersBean.setTrainerid(rs.getInt("trainerid"));
				trainersBean.setTrainername(rs.getString("trainername"));
				trainersBean.setTraineremail(rs.getString("traineremailid"));
				trainersBean.setTechnology(rs.getString("technology"));
				trainersBean.setTrainerstatus(rs.getString("trainerstatus"));
				trainersBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainersBean.setTrainingid(rs.getInt("trainingid"));
				allTrainers.add(trainersBean);
				trainersBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainers;
	}
	
	public ArrayList<AdminBean> getAllTrainersByStatus() throws Exception{
		ArrayList<AdminBean> allTrainers = new ArrayList<AdminBean>();
		AdminBean trainersBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainers where trainerstatus='active'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				trainersBean = new AdminBean();
				trainersBean.setTrainerid(rs.getInt("trainerid"));
				trainersBean.setTrainername(rs.getString("trainername"));
				trainersBean.setTraineremail(rs.getString("traineremailid"));
				trainersBean.setTechnology(rs.getString("technology"));
				trainersBean.setTrainerstatus(rs.getString("trainerstatus"));
				trainersBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainersBean.setTrainingid(rs.getInt("trainingid"));
				allTrainers.add(trainersBean);
				trainersBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainers;
	}
	
	public boolean deleteTrainers(int trainerid) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from trainers where trainerid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, trainerid);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	public AdminBean getTrainerDetailsById(int id) throws Exception{
		AdminBean trainerBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainers where trainerid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainerBean.setTrainerid(rs.getInt("trainerid"));
				trainerBean.setTrainername(rs.getString("trainername"));
				trainerBean.setTraineremail(rs.getString("traineremailid"));
				trainerBean.setTechnology(rs.getString("technology"));
				trainerBean.setTrainerstatus(rs.getString("trainerstatus"));
				trainerBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainerBean.setTrainingid(rs.getInt("trainingid"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainerBean;
	}
	
	public AdminBean getTrainerDetailsByEmail(String mail) throws Exception{
		AdminBean trainerBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainers where traineremailid='"+mail+"' and trainerstatus='active'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainerBean.setTrainerid(rs.getInt("trainerid"));
				trainerBean.setTrainername(rs.getString("trainername"));
				trainerBean.setTraineremail(rs.getString("traineremailid"));
				trainerBean.setTechnology(rs.getString("technology"));
				trainerBean.setTrainerstatus(rs.getString("trainerstatus"));
				trainerBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				trainerBean.setTrainingid(rs.getInt("trainingid"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainerBean;
	}
	
	public boolean updateTrainerDetails(AdminBean trainerBean) throws Exception{
		Connection conn=DatabaseUtility.getDatabaseConnection();
		try{
			String Query="update trainers set trainername=?,traineremailid =?,technology =?,trainerstatus =? where trainierid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainerBean.getTrainername());
			pstmt.setString(2, trainerBean.getTraineremail());
			pstmt.setString(3, trainerBean.getTechnology());
			pstmt.setString(4, trainerBean.getTrainerstatus());
			pstmt.setInt(5, trainerBean.getTrainerid());
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!= null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}
		}
		return false;
		
	}
	
	public boolean updateTrainerStatus(AdminBean trainerBean) throws Exception{
		Connection conn=DatabaseUtility.getDatabaseConnection();
		try{
			String Query="update trainers set trainerstatus =? where trainerid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainerBean.getTrainerstatus());
			pstmt.setInt(2, trainerBean.getTrainerid());
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!= null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}
		}
		return false;
		
	}
	
	/*trainer functionality end*/
	
	/*User Functionality Start*/
	
	public boolean insertRegisteredUserDetails(AdminBean usersBean,AdminBean loginBean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		Savepoint savePoint=null;
		savePoint = conn.setSavepoint("begin");
		try{
			String Query="insert into registeredusers(registeredname ,usertechnology  ,useremailid ,userstatus,loginexpiry ,expirydate ,creationdatetime,batchnumber,trainingstartdate,trainingid) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, usersBean.getRegisteredname());
			pstmt.setString(2, usersBean.getUsertechnology());
			pstmt.setString(3, usersBean.getUseremail());
			pstmt.setString(4, usersBean.getUserstatus());
			pstmt.setInt(5, usersBean.getLoginexpiry());
			pstmt.setString(6, usersBean.getExpirydate());
			pstmt.setTimestamp(7, creationdatetime);
			pstmt.setString(8, usersBean.getBatchnumber());
			pstmt.setString(9, usersBean.getStartdate());
			pstmt.setInt(10, usersBean.getTrainingid());
			pstmt.execute();
			boolean result= insertUser(loginBean,conn,savePoint);
		      if(result)
		      {
		    	  conn.commit();
			        return true;
		      }
		      else
		      {
		    	conn.rollback(savePoint);
	   			return false; 
		      }
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public ArrayList<AdminBean> getAllRegisteredUsers() throws Exception{
		ArrayList<AdminBean> allUsers = new ArrayList<AdminBean>();
		AdminBean userBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from registeredusers";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				userBean = new AdminBean();
				userBean.setUserid(rs.getInt("userid"));
				userBean.setRegisteredname(rs.getString("registeredname"));
				userBean.setUseremail(rs.getString("useremailid"));
				userBean.setUsertechnology(rs.getString("usertechnology"));
				userBean.setUserstatus(rs.getString("userstatus"));
				userBean.setExpirydate(rs.getString("expirydate"));
				userBean.setLoginexpiry(rs.getInt("loginexpiry"));
				userBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				userBean.setBatchnumber(rs.getString("batchnumber"));
				userBean.setStartdate(rs.getString("trainingstartdate"));
				userBean.setTrainingid(rs.getInt("trainingid"));
				allUsers.add(userBean);
				userBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allUsers;
	}
	
	public AdminBean getRegisteredUserDetailsByMail(String mail) throws Exception{
		AdminBean userBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from registeredusers where useremailid='"+mail+"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				userBean.setUserid(rs.getInt("userid"));
				userBean.setRegisteredname(rs.getString("registeredname"));
				userBean.setUseremail(rs.getString("useremailid"));
				userBean.setUsertechnology(rs.getString("usertechnology"));
				userBean.setUserstatus(rs.getString("userstatus"));
				userBean.setExpirydate(rs.getString("expirydate"));
				userBean.setLoginexpiry(rs.getInt("loginexpiry"));
				userBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				userBean.setBatchnumber(rs.getString("batchnumber"));
				userBean.setStartdate(rs.getString("trainingstartdate"));
				userBean.setTrainingid(rs.getInt("trainingid"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return userBean;
	}
	
	
	public AdminBean getRegisteredUserDetailsById(int id) throws Exception{
		AdminBean userBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from registeredusers where userid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				userBean.setUserid(rs.getInt("userid"));
				userBean.setRegisteredname(rs.getString("registeredname"));
				userBean.setUseremail(rs.getString("useremailid"));
				userBean.setUsertechnology(rs.getString("usertechnology"));
				userBean.setUserstatus(rs.getString("userstatus"));
				userBean.setExpirydate(rs.getString("expirydate"));
				userBean.setLoginexpiry(rs.getInt("loginexpiry"));
				userBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				userBean.setBatchnumber(rs.getString("batchnumber"));
				userBean.setStartdate(rs.getString("trainingstartdate"));
				userBean.setTrainingid(rs.getInt("trainingid"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return userBean;
	}
	
	
	
	public boolean deleteRegisteredUsers(int userid) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from registeredusers where userid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, userid);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	
	public boolean deleteRegUserDetails(String username) throws Exception{
		Connection conn = (Connection) DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from userlogin where username=? and status='active' and userrole='reguser'"; 
			PreparedStatement pstmt =conn.prepareStatement(Query);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		 finally{
				if(conn!= null){
					try{
						conn.close();
					}catch(Exception e){
						e.printStackTrace();
						
					}
				}
			}
		return false;
	}

	
	
	/*User Functionality End*/
	
	
	/*Trainer Videos Start*/
	public boolean insertTrainerVideos(AdminBean videoBean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());
		Savepoint savePoint=null;
		savePoint = conn.setSavepoint("begin");
		try{
			String Query="insert into trainervideos(videoheading ,bucketname,uniquestorageid,generationid  ,trainerid ,technology, creationdatetime,batchnumber,trainingid) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, videoBean.getVideoheading());
			pstmt.setString(2, videoBean.getBucketname());
			pstmt.setString(3, videoBean.getUniquestorageid());
			pstmt.setString(4, videoBean.getGenerationid());
			pstmt.setInt(5, videoBean.getTrainerid());
			pstmt.setString(6, videoBean.getTechnology());
			pstmt.setTimestamp(7, creationdatetime);
			pstmt.setString(8, videoBean.getBatchnumber());
			pstmt.setInt(9, videoBean.getTrainingid());
			pstmt.executeUpdate();
			  conn.commit();
			  return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback(savePoint);
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public ArrayList<AdminBean> getTrainerVideosByid(int trainerid) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where trainerid="+trainerid;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	
	public ArrayList<AdminBean> getTrainerVideosByTraineridandTech(int trainerid,int tech) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where trainerid="+trainerid+" and trainingid="+tech;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	public ArrayList<AdminBean> getStudentsVideosList(int technology,String batch) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where trainingid="+technology+" and batchnumber='"+batch+"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	public ArrayList<AdminBean> getTrainerVideosList(String technology) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where technology='"+technology+"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	
	public ArrayList<AdminBean> getTrainerVideosListById(int technology) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where trainingid="+technology;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	public ArrayList<AdminBean> getTrainerVideosForRegsiteredUser(String technology,String heading) throws Exception{
		ArrayList<AdminBean> allTrainerVideos = new ArrayList<AdminBean>();
		AdminBean videosBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where technology='"+technology+"' and videoheading='"+heading+"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				videosBean = new AdminBean();
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
				allTrainerVideos.add(videosBean);
				videosBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainerVideos;
	}
	
	public AdminBean getTrainerVideoByid(int id) throws Exception{
		AdminBean videosBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainervideos where videoid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs !=null && rs.next()){
				videosBean.setTrainerid(rs.getInt("trainerid"));
				videosBean.setVideoid(rs.getInt("videoid"));
				videosBean.setVideoheading(rs.getString("videoheading"));
				videosBean.setBucketname(rs.getString("bucketname"));
				videosBean.setGenerationid(rs.getString("generationid"));
				videosBean.setTechnology(rs.getString("technology"));
				videosBean.setUniquestorageid(rs.getString("uniquestorageid"));
				videosBean.setBatchnumber(rs.getString("batchnumber"));
				videosBean.setTrainingid(rs.getInt("trainingid"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return videosBean;
	}
	
	
	public boolean deleteVideoById(int videoid) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="delete from trainervideos where videoid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, videoid);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return false;
	}
	
	
	/*Trainer Videos End*/
	
	
	/*training content start*/
	public boolean insertTrainingContent(AdminBean trainingsBean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="insert into trainingcontent(trainingid,trainingname,trainingcontent,creationdatetime) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, trainingsBean.getTrainingid());
			pstmt.setString(2, trainingsBean.getCoursename());
			pstmt.setString(3, trainingsBean.getTrainingcontent());
			pstmt.setTimestamp(4, creationdatetime);
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public boolean updateTrainingContent(AdminBean trainingsBean) throws Exception{
		Connection conn=DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="update trainingcontent set trainingcontent=?,creationdatetime=? where contentid=?";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, trainingsBean.getTrainingcontent());
			pstmt.setTimestamp(2, creationdatetime);
			pstmt.setInt(3, trainingsBean.getContentid());
			pstmt.execute();
			conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!= null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}
		}
		return false;
		
	}
	
	
	public AdminBean getTrainingContentDetailsByTrainingid(int id) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainingcontent where trainingid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setContentid(rs.getInt("contentid"));
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("trainingname"));
				trainingsBean.setTrainingcontent(rs.getString("trainingcontent"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	public AdminBean getTrainingContentDetailsByTrainingname(String name) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainingcontent where trainingname='"+name+"'";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setContentid(rs.getInt("contentid"));
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("trainingname"));
				trainingsBean.setTrainingcontent(rs.getString("trainingcontent"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	public AdminBean getTrainingContentDetailsById(int id) throws Exception{
		AdminBean trainingsBean = new AdminBean();
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainingcontent where contentid="+id;
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				trainingsBean.setContentid(rs.getInt("contentid"));
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("trainingname"));
				trainingsBean.setTrainingcontent(rs.getString("trainingcontent"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return trainingsBean;
	}
	
	public ArrayList<AdminBean> getAllTrainingContents() throws Exception{
		ArrayList<AdminBean> allTrainings = new ArrayList<AdminBean>();
		AdminBean trainingsBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from trainingcontent";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				trainingsBean = new AdminBean();
				trainingsBean.setContentid(rs.getInt("contentid"));
				trainingsBean.setTrainingid(rs.getInt("trainingid"));
				trainingsBean.setCoursename(rs.getString("trainingname"));
				trainingsBean.setTrainingcontent(rs.getString("trainingcontent"));
				trainingsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				allTrainings.add(trainingsBean);
				trainingsBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainings;
	}
	
	/*training content end*/
	
	
	/*Student Registration Start*/
	public boolean insertUserDetails(UserBean usersBean) throws SQLException{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		Savepoint savePoint=null;
		savePoint = conn.setSavepoint("begin");
		try{
			String Query="insert into studentform(studentname ,studentmobilenumber ,studentemail ,interestedin ,futuregoal,creationdatetime,qualification,college,lastname) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, usersBean.getStudentname());
			pstmt.setString(2, usersBean.getStudentmobilenumber());
			pstmt.setString(3, usersBean.getStudentemail());
			pstmt.setString(4, usersBean.getInterestedin());
			pstmt.setString(5, usersBean.getGoal());
			pstmt.setTimestamp(6, creationdatetime);
			pstmt.setString(7, usersBean.getQualification());
			pstmt.setString(8, usersBean.getCollege());
			pstmt.setString(9, usersBean.getLastname());
			pstmt.execute();
		    conn.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback(savePoint);
			return false;
		}finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
	}
	
	public ArrayList<UserBean> getStudentDetails() throws Exception{
		ArrayList<UserBean> allTrainings = new ArrayList<UserBean>();
		UserBean studentsBean = null;
		Connection conn =DatabaseUtility.getDatabaseConnection();
		try{
			String Query="select * from studentform";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				studentsBean = new UserBean();
				studentsBean.setStudentid(rs.getInt("studentid"));
				studentsBean.setStudentname(rs.getString("studentname"));
				studentsBean.setStudentemail(rs.getString("studentemail"));
				studentsBean.setStudentmobilenumber(rs.getString("studentmobilenumber"));
				studentsBean.setInterestedin(rs.getString("interestedin"));
				studentsBean.setGoal(rs.getString("futuregoal"));
				studentsBean.setQualification(rs.getString("qualification"));
				studentsBean.setCollege(rs.getString("college"));
				studentsBean.setLastname(rs.getString("lastname"));
				studentsBean.setCreationdatetime(rs.getTimestamp("creationdatetime"));
				allTrainings.add(studentsBean);
				studentsBean = null;
			}
		}catch(Exception e)
	    {
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
			  
			  if(conn != null){
				  try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}
		return allTrainings;
	}
	
	/*Student registartion End*/
}