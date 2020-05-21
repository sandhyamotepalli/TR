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
 * Servlet implementation class CreateCareerService
 */
@WebServlet("/CreateCareerService")
public class CreateCareerService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCareerService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminBean careersBean = new AdminBean();
		AdminDao careersDao = new AdminDao();
		try{
			String description1 = request.getParameter("description1");
			String position = request.getParameter("position");
			String experience = request.getParameter("experience");
			int jobcode = Integer.parseInt((String)request.getParameter("jobcode"));
			String description2 = request.getParameter("description2");
			String description3 = request.getParameter("description3");
			careersBean.setDescription1(description1.toString());
			careersBean.setDescription2(description2.toString());
			careersBean.setDescription3(description3.toString());
			careersBean.setPosition(position);
			careersBean.setJobcode(jobcode);
			careersBean.setExperience(experience);
			AdminBean jobBean = careersDao.getCareersByCode(jobcode);
			if(jobBean != null && jobBean.getJobcode() != 0 && jobBean.getJobcode() == jobcode){
				    out.println("<script>");
		 	        out.println("alert('Job code already exists!');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/careers-form.jsp");
		 	        rd.include(request, response);
		 	        return;
				
			}else{
			boolean isCareersCreated = careersDao.insertCareers(careersBean);
			if(isCareersCreated){
			    out.println("<script>");
	 	        out.println("alert('Career Created Succesfully');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/careers-form.jsp");
	 	        rd.include(request, response);
			}else{
			    out.println("<script>");
	 	        out.println("alert('Career not created. Please try again.!');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/careers-form.jsp");
	 	        rd.include(request, response);
			}
		}
		    
		}catch(Exception e){
			 e.printStackTrace();
			 out.println("<script>");
 	        out.println("alert('Career problem may occurred. Please check!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/careers-form.jsp");
 	        rd.include(request, response);
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
