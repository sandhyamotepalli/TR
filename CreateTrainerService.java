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
 * Servlet implementation class CreateTrainerService
 */
@WebServlet("/CreateTrainerService")
public class CreateTrainerService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTrainerService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminBean trainersBean = new AdminBean();
		AdminBean loginBean = new AdminBean();
		AdminDao trainingsDao = new AdminDao();
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int trainingid = Integer.parseInt((String)request.getParameter("technology"));
			AdminBean techdetails = trainingsDao.getTrainingDetailsById(trainingid);
			AdminBean trainerDetails = trainingsDao.getTrainerDetailsByEmail(email);
			if(trainerDetails != null && trainerDetails.getTraineremail() !=null && !trainerDetails.getTraineremail().equalsIgnoreCase("") && trainerDetails.getTraineremail().equalsIgnoreCase(email)){
					    out.println("<script>");
			 	        out.println("alert('Training Emailid "+email+" already associated with some other trainer. Please use another Emailid!');");
			 	        out.println("</script>");
			 	        RequestDispatcher rd = request.getRequestDispatcher("/trainer-creation.jsp");
			 	        rd.include(request, response);
			 	        return;
			}else{
				trainersBean.setTrainername(name);
				trainersBean.setTraineremail(email);
				trainersBean.setTrainingid(trainingid);
				trainersBean.setTechnology(techdetails.getCoursename());
				trainersBean.setTrainerstatus("active");
				loginBean.setUsername(email);
				loginBean.setPassword("Sairam@123@");
				loginBean.setUsertype("trainer");
				loginBean.setLoginstatus("active");
				boolean istrainingsCreated = trainingsDao.insertTrainerDetails(trainersBean,loginBean);
				if(istrainingsCreated){
				    out.println("<script>");
		 	        out.println("alert('Trainer Created Succesfully');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/trainer-creation.jsp");
		 	        rd.include(request, response);
		 	        return;
				}else{
				    out.println("<script>");
		 	        out.println("alert('Trainer not created. Please try again.!');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/trainer-creation.jsp");
		 	        rd.include(request, response);
		 	        return;
				}
			}
		
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Trainer not created. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/trainer-creation.jsp");
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
