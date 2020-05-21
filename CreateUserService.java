package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBean;
import daos.AdminDao;
import utilities.DateUtility;
import utilities.EmailUtility;
import utilities.PasswordGeneratorUtility;

/**
 * Servlet implementation class CreateUserService
 */
@WebServlet("/CreateUserService")
public class CreateUserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminBean userBean = new AdminBean();
		AdminBean loginBean = new AdminBean();
		AdminDao usersDao = new AdminDao();
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int trainingid = Integer.parseInt((String)request.getParameter("technology"));
			String batch = request.getParameter("batch");
			AdminBean techdetails = usersDao.getTrainingDetailsById(trainingid);
			if(techdetails != null && techdetails.getCoursename() != null && !(techdetails.getCoursename().equalsIgnoreCase(""))){
			if(techdetails.getStartdate() != null && !(techdetails.getStartdate().equalsIgnoreCase(""))){
				userBean.setRegisteredname(name);
				userBean.setUseremail(email);
				userBean.setTrainingid(trainingid);
				userBean.setUsertechnology(techdetails.getCoursename());
				userBean.setUserstatus("active");
				userBean.setLoginexpiry(90);
				userBean.setBatchnumber(batch);
				userBean.setStartdate(techdetails.getStartdate());
				userBean.setExpirydate(DateUtility.parseDateToDateAfter90Days(techdetails.getStartdate()));
				/*login credentials*/
				String password = String.valueOf(PasswordGeneratorUtility.tr_Password());
				loginBean.setUsername(email);
				loginBean.setPassword(password);
				loginBean.setUsertype("reguser");
				loginBean.setLoginstatus("active");
				boolean isUserCreated = usersDao.insertRegisteredUserDetails(userBean,loginBean);
				if(isUserCreated){
					String emailMessage = "";
					emailMessage = "<p>Dear Admin, <br> Credentials of the registered user. <br> Details Are : <br> Name : "+name+" <br> Emailid : "+email+" <br> Technology : "+techdetails.getCoursename()+" <br>Password : "+password+" <br>";
					  try {
					    	EmailUtility.sendEmail("User details:"+name+"", emailMessage, "info@technoreachit.com");
					    	  out.println("<script>");
					 	        out.println("alert('Registered User Created Succesfully');");
					 	        out.println("</script>");
					 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
					 	        rd.include(request, response);
				             return;

				    } catch (AddressException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Sorry!Your request can not be processed. Please try Again');");
				    	out.println("</script>");
			 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
				    	    rd.include(request, response);
				    	    return;

				    } catch (MessagingException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Sorry!Your Request is not submitted because of technical problem. Please try Again');");
				    	out.println("</script>");
			 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
				        rd.include(request, response);
				    }
				}else{
				    out.println("<script>");
		 	        out.println("alert('Registered User Failed. Please try again.!');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
		 	        rd.include(request, response);
		 	       return;
				}
				
			}
			else{
				out.println("<script>");
		 	        out.println("alert('Please enter training start date then only you are able to register student for this training.');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
		 	        rd.include(request, response);
	             return;
			}
		}else{
			out.println("<script>");
 	        out.println("alert('Please check the trainings list, the given technology "+techdetails.getTechnology()+" is not there in the list');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
 	        rd.include(request, response);
         return;
	   }
		
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Registered User Failed. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/student-creation.jsp");
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
