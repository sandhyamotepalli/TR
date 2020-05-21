package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import daos.AdminDao;

/**
 * Servlet implementation class TrainerCourseVideos
 */
@WebServlet("/TrainerCourseVideos")
public class TrainerCourseVideos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainerCourseVideos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao adminDao = new AdminDao();
		HttpSession session = request.getSession(false);
	    AdminBean trainerDetails = new AdminBean();
		try{
			
		    if(session != null && session.getId() != null && !(session.getId().equalsIgnoreCase(""))){
		    	if(session.getAttribute("trainerid") != null){
		        int trainer = (int)session.getAttribute("trainerid");
		        System.out.println("id:::"+trainer);
		        if(trainer != 0){
		            trainerDetails = adminDao.getTrainerDetailsById(trainer);
		            ArrayList<AdminBean> videoDetails = adminDao.getTrainerVideosListById(trainerDetails.getTrainingid());
		            request.setAttribute("videodetails", videoDetails);
		            RequestDispatcher rd = request.getRequestDispatcher("/trainer-course-videos.jsp");
		 	        rd.include(request, response);
		 	        return;
		         }else{
		        	    out.println("<script>");
			 	        out.println("alert('Your Session is timed out because of inactivity Please Click OK to login again.');");
			 	        out.println("</script>");
			 	        session.removeAttribute("trainerid");
			 	        session.invalidate();
			 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			 	        rd.include(request, response);
			 	        return;
		          }
		    }else{
		    	out.println("<script>");
	 	        out.println("alert('Your Session is timed out because of inactivity Please Click OK to login again.');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	 	        rd.include(request, response);
	 	        return;
		    }
		    }else{
		    	out.println("<script>");
	 	        out.println("alert('Your Session is timed out because of inactivity Please Click OK to login again.');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	 	        rd.include(request, response);
	 	        return;
		    }
		    
		}catch(Exception e){
			 e.printStackTrace();
			 out.println("<script>");
 	        out.println("alert('Something went wrong. Please try again later!');");
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
