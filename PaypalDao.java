package daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import beans.PaypalBean;
import utilities.DatabaseUtility;

public class PaypalDao {
	
	public boolean insertPaypalResponse(PaypalBean paypalBean) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="insert into paypalresponse(orderid,amount ,description ,firstname ,lastname ,mobilenumber, emailid,line1,line2,city ,state,countrycode,postalcode,payerid,paymentid,invoicenumber,referenceid,creationdatetime,transactionstatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setInt(1, paypalBean.getOrderid());
			pstmt.setFloat(2, paypalBean.getAmount());
			pstmt.setString(3, paypalBean.getDescription());
			pstmt.setString(4, paypalBean.getFirstname());
			pstmt.setString(5, paypalBean.getLastname());
			pstmt.setString(6, paypalBean.getMobilenumber());
			pstmt.setString(7, paypalBean.getEmail());
			pstmt.setString(8, paypalBean.getLine1());
			pstmt.setString(9, paypalBean.getLine2());
			pstmt.setString(10, paypalBean.getCity());
			pstmt.setString(11, paypalBean.getState());
			pstmt.setString(12, paypalBean.getCountrycode());
			pstmt.setString(13, paypalBean.getPostalcode());
			pstmt.setString(14, paypalBean.getPayerid());
			pstmt.setString(15, paypalBean.getPaymentid());
			pstmt.setString(16, paypalBean.getInvoicenumber());
			pstmt.setString(17, paypalBean.getReferenceId());
			pstmt.setTimestamp(18, creationdatetime);
			pstmt.setString(19, "Completed");
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
	
	
	public boolean insertPaypalRequest(PaypalBean paypalBean) throws Exception{
		Connection conn =DatabaseUtility.getDatabaseConnection();
		 Calendar calendar = Calendar.getInstance();
		Timestamp creationdatetime = new Timestamp(calendar.getTime().getTime());	
		try{
			String Query="insert into paypalrequest(amount ,description,mobilenumber,requeststatus ,creationdatetime) values(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			//pstmt.setString(1, paypalBean.getOrderid());
			pstmt.setFloat(1, paypalBean.getAmount());
			pstmt.setString(2, paypalBean.getDescription());
			pstmt.setString(3, paypalBean.getMobilenumber());
			pstmt.setString(4, "Initiated");
			pstmt.setTimestamp(5, creationdatetime);
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
	

	 public boolean updateRequestStatus(PaypalBean paypalBean) throws Exception{
			Connection conn =(Connection) DatabaseUtility.getDatabaseConnection();
			try
			{
				String SQL = "update paypalrequest set requeststatus = ?,payerid=?,paymentid=? where mobilenumber=? and description=?";
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQL);
				stmt.setString(1, "Reviewed");
				stmt.setString(2, paypalBean.getPayerid());
				stmt.setString(3, paypalBean.getPaymentid());
				stmt.setString(4, paypalBean.getMobilenumber());
				stmt.setString(5, paypalBean.getDescription());
		        stmt.executeUpdate();
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
	 

	 public PaypalBean getPaymentDetailsById(String payerid,String paymentid,String number) throws Exception{
		 PaypalBean paypalBean = new PaypalBean();
			Connection conn =DatabaseUtility.getDatabaseConnection();
			try{
				String Query="select * from paypalrequest where payerid='"+payerid+"' and paymentid='"+paymentid+"' and mobilenumber='"+number+"'";
				PreparedStatement pstmt = conn.prepareStatement(Query);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null && rs.next()){
					paypalBean.setOrderid(rs.getInt("orderid"));
					paypalBean.setMobilenumber(rs.getString("mobilenumber"));
					paypalBean.setPayerid(rs.getString("payerid"));
					paypalBean.setPaymentid(rs.getString("paymentid"));
					paypalBean.setDescription(rs.getString("description"));
					paypalBean.setAmount(rs.getFloat("amount"));
					paypalBean.setPaymentstatus("requeststatus");
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
			return paypalBean;
		}
	 
	 public PaypalBean getPaymentDetailsByMobilenumber(String number,String description) throws Exception{
		 PaypalBean paypalBean = new PaypalBean();
			Connection conn =DatabaseUtility.getDatabaseConnection();
			try{
				String Query="select * from paypalrequest where mobilenumber='"+number+"' and description='"+description+"' ORDER BY creationdatetime DESC LIMIT 1";
				PreparedStatement pstmt = conn.prepareStatement(Query);
				ResultSet rs = pstmt.executeQuery();
				if(rs != null && rs.next()){
					paypalBean.setOrderid(rs.getInt("orderid"));
					paypalBean.setMobilenumber(rs.getString("mobilenumber"));
					paypalBean.setPayerid(rs.getString("payerid"));
					paypalBean.setPaymentid(rs.getString("paymentid"));
					paypalBean.setDescription(rs.getString("description"));
					paypalBean.setAmount(rs.getFloat("amount"));
					paypalBean.setPaymentstatus("requeststatus");
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
			return paypalBean;
		}
}
