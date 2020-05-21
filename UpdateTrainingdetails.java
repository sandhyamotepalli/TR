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
 * Servlet implementation class UpdateTrainingdetails
 */
@WebServlet("/UpdateTrainingdetails")
public class UpdateTrainingdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTrainingdetails() {
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
			String name = request.getParameter("ucoursename");
			String trainername = request.getParameter("utrainername");
			String trainerinfo = request.getParameter("utrainerinfo");	
			String link = request.getParameter("ulink");
			int duration = Integer.parseInt(request.getParameter("uduration"));
			String startdate = request.getParameter("ustartdate");
			String fees = request.getParameter("ufees");
			if(fees != "" && fees != null && !fees.equalsIgnoreCase("")){
				trainingsBean.setFee(Integer.parseInt(fees));
			}
			String feeindollar = request.getParameter("ufeeindollar");
			if(feeindollar != "" && feeindollar != null && !feeindollar.equalsIgnoreCase("")){
				trainingsBean.setFeeindollar(Integer.parseInt(feeindollar));
			}
			String mode = request.getParameter("umode");
			String timings = request.getParameter("utimings");
			trainingsBean.setCoursename(name);
			trainingsBean.setTrainername(trainername);
			trainingsBean.setTrainerinfo(trainerinfo);
			trainingsBean.setDuration(duration);
			trainingsBean.setStartdate(startdate);
			trainingsBean.setMode(mode);
			trainingsBean.setTimings(timings);
			trainingsBean.setLink(link);
			trainingsBean.setTrainingid(Integer.parseInt(request.getParameter("trainingid")));
			
			boolean istrainingsUpdated = trainingsDao.updateTrainingDetails(trainingsBean);
			if(istrainingsUpdated){
			    out.println("<script>");
	 	        out.println("alert('Training updated Succesfully');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-admin.jsp");
	 	        rd.include(request, response);
			}else{
			    out.println("<script>");
	 	        out.println("alert('Training not updated. Please try again.!');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-admin.jsp");
	 	        rd.include(request, response);
			}
			
		
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Training not updated. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-admin.jsp");
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
