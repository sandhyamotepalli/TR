package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBean;
import daos.AdminDao;

/**
 * Servlet implementation class ChangeTrainerStatusService
 */
@WebServlet("/ChangeTrainerStatusService")
public class ChangeTrainerStatusService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTrainerStatusService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao statusDao = new AdminDao();
		AdminBean statusBean = new AdminBean();
		try{
			
			String id = request.getParameter("trainerid");
			AdminBean trainerBean = statusDao.getTrainerDetailsById(Integer.parseInt(id));
			String status = request.getParameter("status");
			String email = request.getParameter("email");
			statusBean.setTrainerid(Integer.parseInt(id));
			statusBean.setTrainerstatus(status);
			boolean isStatusChanged = statusDao.updateTrainerStatus(statusBean);
			if(isStatusChanged){
			     boolean updatedLogin = statusDao.deleteTrainerDetails(email);
			     if(updatedLogin){
			    	 out.println("<script>");
			 	        out.println("alert('Trainer "+trainerBean.getTrainername()+" is deleted Succesfully');");
			 	        out.println("</script>");
			 	       request.getRequestDispatcher("/trainer-creation.jsp").include(request, response);
			            return;
			     }else{
			    	   out.println("<script>");
			 	        out.println("alert('Trainer "+trainerBean.getTrainername()+" is deleted Succesfully');");
			 	        out.println("</script>");
			 	       request.getRequestDispatcher("/trainer-creation.jsp").include(request, response);
			            return;
			     }
	 	        
			}else{
				out.println("<script>");
	 	        out.println("alert('Trainer Status is not updated.Due to some technical reason, Please try again.');");
	 	        out.println("</script>");
	 	       request.getRequestDispatcher("/trainer-creation.jsp").include(request, response);
	            return;
			}
		}catch(Exception e){
			e.printStackTrace();
			out.println("<script>");
 	        out.println("alert('Trainer Status is not updated.Due to some technical reason, Please try again.');");
 	        out.println("</script>");
 	       request.getRequestDispatcher("/trainer-creation.jsp").include(request, response);
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
