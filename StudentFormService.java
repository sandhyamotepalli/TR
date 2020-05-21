package services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import daos.AdminDao;

/**
 * Servlet implementation class StudentFormService
 */
@WebServlet("/StudentFormService")
public class StudentFormService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentFormService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserBean userBean = new UserBean();
		AdminDao usersDao = new AdminDao();
		try{
			String name = request.getParameter("name");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String college = request.getParameter("college");
			String qual = request.getParameter("qualification");
			String mobilenumber = request.getParameter("mobilenumber");
			String interestedin = request.getParameter("interestedin");
			String goal = request.getParameter("goal");
				userBean.setStudentname(name);
				userBean.setLastname(lastname);
				userBean.setStudentemail(email);
				userBean.setQualification(qual);
				userBean.setCollege(college);
				userBean.setStudentmobilenumber(mobilenumber);
				userBean.setInterestedin(interestedin);
				userBean.setGoal(goal);
				boolean isUserCreated = usersDao.insertUserDetails(userBean);
				if(isUserCreated){
					    out.println("<script>");
			 	        out.println("alert('Data has been submitted successfully.!');");
			 	        out.println("</script>");
			 	        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 	        rd.include(request, response);
			 	       return;
				}else{
				    out.println("<script>");
		 	        out.println("alert('Data Not subbmitted,Please try again.!');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/studentForm/student-form.jsp");
		 	        rd.include(request, response);
		 	       return;
				}
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Data Not subbmitted,Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/studentForm/student-form.jsp");
 	        rd.include(request, response);
			e.printStackTrace();
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
