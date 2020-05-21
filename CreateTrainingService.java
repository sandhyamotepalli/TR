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
 * Servlet implementation class CreateTrainingService
 */
@WebServlet("/CreateTrainingService")
public class CreateTrainingService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTrainingService() {
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
			String name = request.getParameter("coursename");
			String trainername = request.getParameter("trainername");
			String trainerinfo = request.getParameter("trainerinfo");
			String link = request.getParameter("link");
			int duration = Integer.parseInt(request.getParameter("duration"));
			/*String sdobday = request.getParameter("sdobday");
			String sdobmonth = request.getParameter("sdobmonth");
			String sdobyear = request.getParameter("sdobyear");
			java.util.Date startdate = DateUtility.parseStringToDate(sdobday+"/"+sdobmonth+"/"+sdobyear);*/
			String startdate = request.getParameter("startdate");
			String fees = request.getParameter("fees");
			if(fees != "" && fees != null && !fees.equalsIgnoreCase("")){
				trainingsBean.setFee(Integer.parseInt(fees));
			}
			String feeindollar = request.getParameter("feeindollar");
			if(feeindollar != "" && feeindollar != null && !feeindollar.equalsIgnoreCase("")){
				trainingsBean.setFeeindollar(Integer.parseInt(feeindollar));
			}
			String mode = request.getParameter("mode");
			String timings = request.getParameter("timings");
			trainingsBean.setCoursename(name);
			trainingsBean.setTrainername(trainername);
			trainingsBean.setTrainerinfo(trainerinfo);
			trainingsBean.setDuration(duration);
			trainingsBean.setStartdate(startdate);
			trainingsBean.setMode(mode);
			trainingsBean.setTimings(timings);
			trainingsBean.setLink(link);
			
			boolean istrainingsCreated = trainingsDao.insertTrainings(trainingsBean);
			if(istrainingsCreated){
			    out.println("<script>");
	 	        out.println("alert('Training Created Succesfully');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-form.jsp");
	 	        rd.include(request, response);
			}else{
			    out.println("<script>");
	 	        out.println("alert('Training not created. Please try again.!');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-form.jsp");
	 	        rd.include(request, response);
			}
			
		
		}catch(Exception e){
			 out.println("<script>");
	 	     out.println("alert('Training not created. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/our-trainings-form.jsp");
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
