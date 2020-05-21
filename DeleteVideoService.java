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
 * Servlet implementation class DeleteVideoService
 */
@WebServlet("/DeleteVideoService")
public class DeleteVideoService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVideoService() {
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
			String id = request.getParameter("videoid");
			boolean isVideoDeleted = userDao.deleteVideoById(Integer.parseInt(id));
			if(isVideoDeleted){
			 	        out.println("Video is deleted Succesfully");
			}else{
		 	     out.println("Video not Deleted.Due to some technical reason, Please try again.");
			}
		}catch(Exception e){
	 	     out.println("Video not Deleted.Due to some technical reason, Please try again.!");
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
