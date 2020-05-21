package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.AdminDao;

/**
 * Servlet implementation class DeleteStudentService
 */
@WebServlet("/DeleteStudentService")
public class DeleteStudentService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao userDao = new AdminDao();
		try{
			String id = request.getParameter("userid");
			String email = request.getParameter("useremail");
			
			boolean isUserDeleted = userDao.deleteRegisteredUsers(Integer.parseInt(id));
			if(isUserDeleted){
				 boolean updatedLogin = userDao.deleteRegUserDetails(email);
			     if(updatedLogin){
			    	 out.println("<script>");
			 	        out.println("alert('Student is deleted Succesfully');");
			 	        out.println("</script>");
			 	       request.getRequestDispatcher("/student-creation.jsp").include(request, response);
			            return;
			     }else{
			    	 out.println("<script>");
			 	        out.println("alert('Student is deleted Succesfully');");
			 	        out.println("</script>");
			 	       request.getRequestDispatcher("/student-creation.jsp").include(request, response);
			            return;
			     }
	 	        
			}else{
				out.println("<script>");
	 	        out.println("alert('Student not Deleted.Due to some technical reason, Please try again.!');");
	 	        out.println("</script>");
	            request.getRequestDispatcher("/student-creation.jsp").include(request, response);
	            return;
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
			out.println("<script>");
 	        out.println("alert('Student not Deleted.Due to some technical reason, Please try again.!');");
 	        out.println("</script>");
            request.getRequestDispatcher("/student-creation.jsp").include(request, response);
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
