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
 * Servlet implementation class DeleteTrainingService
 */
@WebServlet("/DeleteTrainingService")
public class DeleteTrainingService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTrainingService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao trainingsDao = new AdminDao();
		try{
			int id = Integer.parseInt(request.getParameter("did"));
			
			boolean istrainingsDeleted = trainingsDao.deleteTrainings(id);
			if(istrainingsDeleted){
				out.println("<script>");
	 	        out.println("alert('Training Deleted Succesfully');");
	 	        out.println("</script>");
	            request.getRequestDispatcher("/our-trainings-admin.jsp").include(request, response);
	            return;
	 	      
			}else{
				out.println("<script>");
	 	        out.println("alert('Training not Deleted.Due to some technical reason, Please try again.!');");
	 	        out.println("</script>");
	            request.getRequestDispatcher("/our-trainings-admin.jsp").include(request, response);
	            return;
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
			out.println("<script>");
 	        out.println("alert('Training not Deleted.Due to some technical reason, Please try again.!');");
 	        out.println("</script>");
            request.getRequestDispatcher("/our-trainings-admin.jsp").include(request, response);
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
