package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import daos.AdminDao;
import utilities.PasswordAuthentication;

/**
 * Servlet implementation class ChangePasswordService
 */
@WebServlet("/ChangePasswordService")
public class ChangePasswordService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(false);
		   AdminDao dao=new AdminDao();
		     AdminBean lb=new AdminBean();

	     try{
				 String username =(String)session.getAttribute("adminid");
			     String password = request.getParameter("newpassword");
			     String oldpassword=request.getParameter("oldpassword");
			      lb=dao.getAdminDetails(username);
			      if (lb.getUsername() != null && !lb.getUsername().equalsIgnoreCase(""))
		            {
			     if(!PasswordAuthentication.checkpw(oldpassword, lb.getPassword()))
		   	    {
			    	    out.println("<script type=\"text/javascript\">");
				    	out.println("alert(\"The Entered old password is invalid\");");
				    	out.println("</script>");
				    	RequestDispatcher rd = request.getRequestDispatcher("/change-password.jsp");
			              rd.include(request, response);
			              return;
		   	    }
			    else{
		   	           boolean res=dao.userChangePassword(username, password);
							if(res)
							{
								out.println("<script type=\"text/javascript\">");
						    	out.println("alert(\"Password Updated Successfully\");");
						    	out.println("</script>");
								session.invalidate();
						     	  RequestDispatcher rd = request.getRequestDispatcher("/admin-login.jsp");
					              rd.include(request, response);
					              return;
				         }else{
				        	 out.println("<script type=\"text/javascript\">");
						    	out.println("alert(\"Password Not Updated Successfully.Please Try Again!!\");");
						    	out.println("</script>");
						     	  RequestDispatcher rd = request.getRequestDispatcher("/change-password.jsp");
					              rd.include(request, response);
					              return;
				         }
		   	     }
			    	 
		            }else{
			        	   out.println("<script type=\"text/javascript\">");
				        	out.println("alert('Invalid Username.No user Exists with the given username "+username+"');");
				        	out.println("</script>");
					     	  RequestDispatcher rd = request.getRequestDispatcher("/change-password.jsp");
				        	rd.include(request, response);
				        	return;
			          }
	     }
	     catch(Exception e) {
	    	    e.printStackTrace();
	    	    out.println("<script type=\"text/javascript\">");
		    	out.println("alert(\"Password Not Updated.Please Try Again!!\");");
		    	out.println("</script>");
		     	  RequestDispatcher rd = request.getRequestDispatcher("/change-password.jsp");
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
