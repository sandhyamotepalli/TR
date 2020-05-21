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

import utilities.EmailUtility;

/**
 * Servlet implementation class ContactusService
 */
@WebServlet("/ContactusService")
public class ContactusService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactusService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			String emailid = request.getParameter("email");// +"@"+request.getParameter("domain");
			String name = request.getParameter("name");
			String mobileNumber = request.getParameter("phone");
			String message = request.getParameter("message");
			String emailMessage = "";
			emailMessage = "<p>Dear Admin, <br> One user requesting you to contact. <br> User Details Are : <br> Name : "+name+" <br> Mobile Number : "+mobileNumber+" <br> Email Id : "+emailid+" <br>";
			emailMessage += "Message/Query/Suggestion : "+message+" <br> Thank You.";
			  try {
			EmailUtility.conactusEmail("Contact Form Submission", emailMessage, "info@technoreachit.com");
			out.println("<script type=\"text/javascript\">");
 	    	out.println("alert('Thank You! For sending in your request, Will get back to you at the earliest possible.');");
 	    	out.println("</script>");
			 RequestDispatcher rd = request.getRequestDispatcher("/contact-us.jsp");
             rd.include(request, response);
             return;
			    } catch (AddressException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    	out.println("<script type=\"text/javascript\">");
			    	out.println("alert('Sorry!Your request can not be processed. Please try Again');");
			    	out.println("</script>");
					 RequestDispatcher rd = request.getRequestDispatcher("/contact-us.jsp");
			    	    rd.include(request, response);
			    	    return;

			    } catch (MessagingException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    	out.println("<script type=\"text/javascript\">");
			    	out.println("alert('Sorry!Your Request is not submitted because of technical problem. Please try Again');");
			    	out.println("</script>");
					 RequestDispatcher rd = request.getRequestDispatcher("/contact-us.jsp");
			        rd.include(request, response);
			        return;
			    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("<script type=\"text/javascript\">");
 	    	out.println("alert('Could not send in your request, Please try resending again.');");
 	    	out.println("</script>");
 	    	 RequestDispatcher rd = request.getRequestDispatcher("/contact-us.jsp");
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
