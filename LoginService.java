package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import daos.AdminDao;
import utilities.DateUtility;
import utilities.PasswordAuthentication;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		AdminDao dao=new AdminDao();
		try
		{
			  String username =request.getParameter("username");
		      String password = request.getParameter("password");
		      HttpSession session = request.getSession(true);
		      AdminBean lb=new AdminBean();
		      lb=dao.getAdminDetails(username);
		      if (lb.getUsername() != null && !lb.getUsername().equalsIgnoreCase("") && lb.getPassword() != null && !lb.getPassword().equalsIgnoreCase(""))
	            {
	            if (lb.getUsername().equalsIgnoreCase(username) && PasswordAuthentication.checkpw(password, lb.getPassword()))
	                {
	            	
	            	 if (lb.getUsertype() != null && !lb.getUsertype().equalsIgnoreCase("") && lb.getUsertype().equalsIgnoreCase("admin"))
                     {
	            		 session.setAttribute("adminid",username);
	                 	 session.setAttribute("usertype",lb.getUsertype());
	          		     RequestDispatcher rd = request.getRequestDispatcher("/index-admin.jsp");
	                     rd.forward(request, response);
	                     return;
	            		 
                     }else if(lb.getUsertype() != null && !lb.getUsertype().equalsIgnoreCase("") && lb.getUsertype().equalsIgnoreCase("trainer")){
                    	 AdminBean trainerDetails = dao.getTrainerDetailsByEmail(lb.getUsername());
                    	 if(trainerDetails.getTrainerstatus() != null && !trainerDetails.getTrainerstatus().equalsIgnoreCase("") && trainerDetails.getTrainerstatus().equalsIgnoreCase("active")){
                    		 session.setAttribute("trainerid",trainerDetails.getTrainerid());
    	                 	 session.setAttribute("usertype",lb.getUsertype());
    	          		     RequestDispatcher rd = request.getRequestDispatcher("/index-trainer.jsp");
    	                     rd.forward(request, response);
    	                     return;
                    	 }else{
                    		 out.println("<script type=\"text/javascript\">");
          		        	out.println("alert('No Longer able to Login.Contact Technoreach Administrator.');");
          		        	out.println("</script>");
          		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
          		        	  rd.include(request, response);
          		        	  return;
                    	 }
                     }else if(lb.getUsertype() != null && !lb.getUsertype().equalsIgnoreCase("") && lb.getUsertype().equalsIgnoreCase("reguser")){
                    	 Date today = new Date();
                    	 AdminBean userDetails = dao.getRegisteredUserDetailsByMail(lb.getUsername());
                    	 if(userDetails.getExpirydate() != null && !(userDetails.getExpirydate().equalsIgnoreCase(""))){
                    		 
                    		   if(today.after(DateUtility.parseStringToDate(userDetails.getExpirydate()))){
                    			   out.println("<script type=\"text/javascript\">");
               		        	out.println("alert('No Longer able to Login. 90 days Validity expires.');");
               		        	out.println("</script>");
               		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
               		        	  rd.include(request, response);
               		        	  return;
                    		   }else{
                    			   session.setAttribute("registereduserid",userDetails.getUserid());
          	                 	 session.setAttribute("usertype",lb.getUsertype());
          	          		     RequestDispatcher rd = request.getRequestDispatcher("/index-student.jsp");
          	                     rd.forward(request, response);
          	                     return;
                    		   }
                    		 
                    		 /*if(today.after(DateUtility.parseStringToDate(userDetails.getStartdate()))){
                    			 if(today.before(DateUtility.parseStringToDate(userDetails.getExpirydate()))){
                    				 session.setAttribute("registereduserid",userDetails.getUserid());
            	                 	 session.setAttribute("usertype",lb.getUsertype());
            	          		     RequestDispatcher rd = request.getRequestDispatcher("/index-student.jsp");
            	                     rd.forward(request, response);
            	                     return;
                    			 }else{
                    				    out.println("<script type=\"text/javascript\">");
                    		        	out.println("alert('No Longer able to Login. 90 days Validity expires.');");
                    		        	out.println("</script>");
                    		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    		        	  rd.include(request, response);
                    		        	  return;
                    			 }
                    		 }else if(DateUtility.parseDateToString(today).equalsIgnoreCase(userDetails.getStartdate()) || DateUtility.parseDateToString(today).equalsIgnoreCase(userDetails.getExpirydate())){
                        			 session.setAttribute("registereduserid",userDetails.getUserid());
            	                 	 session.setAttribute("usertype",lb.getUsertype());
            	          		     RequestDispatcher rd = request.getRequestDispatcher("/index-student.jsp");
            	                     rd.forward(request, response);
            	                     return;
                    		 }
                    		 else{
                    			 out.println("<script type=\"text/javascript\">");
             		        	out.println("alert('Able to Login after course starts.');");
             		        	out.println("</script>");
             		        	 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
             		        	  rd.include(request, response);
             		        	  return;
                    		 }*/
                    	 }else{
                    		 out.println("<script type=\"text/javascript\">");
          		        	out.println("alert('Invalid Username or Password');");
          		        	out.println("</script>");
          		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
          		        	  rd.include(request, response);
          		        	  return;
                    	 }
                    	
                     }else{
                    	 out.println("<script type=\"text/javascript\">");
     		        	out.println("alert('Invalid Username or Password');");
     		        	out.println("</script>");
     		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
     		        	  rd.include(request, response);
     		        	  return;
                     }
	               } else {
	            	out.println("<script type=\"text/javascript\">");
		        	out.println("alert('Invalid Username or Password');");
		        	out.println("</script>");
		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		        	  rd.include(request, response);
		        	  return;
	             }
	      }
		      else {
	            	out.println("<script type=\"text/javascript\">");
		        	out.println("alert('Invalid Username or Password');");
		        	out.println("</script>");
		        	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		        	rd.include(request, response);
		        	return;
		      }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("<script type=\"text/javascript\">");
        	out.println("alert('Invalid Username or Password');");
        	out.println("</script>");
       	 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        	rd.include(request, response);
        	return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
