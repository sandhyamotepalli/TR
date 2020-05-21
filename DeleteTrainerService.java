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
 * Servlet implementation class DeleteTrainerService
 */
@WebServlet("/DeleteTrainerService")
public class DeleteTrainerService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTrainerService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao trainersDao = new AdminDao();
		try{
			int id = Integer.parseInt(request.getParameter("trainerid"));
			
			boolean isTrainerDeleted = trainersDao.deleteTrainers(id);
			if(isTrainerDeleted){
			   
	 	        out.println("Trainer Deleted Succesfully");
	 	        
			}else{
		 	     out.println("Trainer not Deleted.Due to some technical reason, Please try again.");
	 	      
			}
			
		
		}catch(Exception e){
	 	     out.println("Trainer not Deleted.Due to some technical reason, Please try again.!");
			e.printStackTrace();
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
