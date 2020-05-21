package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBean;
import daos.AdminDao;

/**
 * Servlet implementation class UpdateTrainingContent
 */
@WebServlet("/UpdateTrainingContent")
public class UpdateTrainingContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTrainingContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminBean trainingsBean = new AdminBean();
		AdminDao trainingsDao = new AdminDao();
		try{
			
			String contentid = request.getParameter("ucontentid");
			String trainercontent = request.getParameter("utrainercontent");	
			
			trainingsBean.setContentid(Integer.parseInt(contentid));
			trainingsBean.setTrainingcontent(trainercontent.toString());
			boolean trainingContentUpdated = trainingsDao.updateTrainingContent(trainingsBean);
			if(trainingContentUpdated){
			    out.println("<script>");
	 	        out.println("alert('Training Content updated Succesfully');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/trainings-content-admin.jsp");
	 	        rd.include(request, response);
	 	        return;
			}else{
			    out.println("<script>");
	 	        out.println("alert('Training Content not updated. Please try again.!');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/trainings-content-admin.jsp");
	 	        rd.include(request, response);
	 	        return;
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
			 out.println("<script>");
	 	     out.println("alert('Training Content not updated. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/trainings-content-admin.jsp");
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
