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
 * Servlet implementation class CreateContentService
 */
@WebServlet("/CreateContentService")
public class CreateContentService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateContentService() {
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
			int trainingid = Integer.parseInt((String)request.getParameter("trainingid"));
			AdminBean trainingdetails =  trainingsDao.getTrainingDetailsById(trainingid);
			String trainingcontent = request.getParameter("trainingcontent");
			trainingsBean.setTrainingid(trainingid);
			trainingsBean.setCoursename(trainingdetails.getCoursename());
			trainingsBean.setTrainingcontent(trainingcontent.toString());
			
			boolean istrainingsCreated = trainingsDao.insertTrainingContent(trainingsBean);
			if(istrainingsCreated){
			    out.println("<script>");
	 	        out.println("alert('Training Content Created Succesfully');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/content-creation.jsp");
	 	        rd.include(request, response);
			}else{
			    out.println("<script>");
	 	        out.println("alert('Training Content not created. Please try again.!');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/content-creation.jsp");
	 	        rd.include(request, response);
			}
			
		
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Training Content not created. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/content-creation.jsp");
 	        rd.include(request, response);
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
