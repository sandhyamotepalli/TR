package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBean;
import daos.AdminDao;


/**
 * Servlet implementation class GetVideosListToView
 */
@WebServlet("/GetVideosListToView")
public class GetVideosListToView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVideosListToView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("video/mp4");
		PrintWriter out = response.getWriter();
		AdminBean videoBean = new AdminBean();
		AdminDao videoDao = new AdminDao();
		try{
			String trainerid = request.getParameter("trainerid");
			String technology = request.getParameter("tech");
			ArrayList<AdminBean> videosList = videoDao.getTrainerVideosByTraineridandTech(Integer.parseInt(trainerid),Integer.parseInt(technology));
		    StringBuilder htmlBuilder = new StringBuilder();
			if(videosList != null && videosList.size() > 0){
				for(int i=0;i<videosList.size();i++)
				{
					 if (videosList.get(i) != null)
					{
						 
						 videoBean=new AdminBean();
						   	
						 videoBean = videosList.get(i);
						 
							 if (videoBean.getTrainerid() != 0)
							 {
								    htmlBuilder.append("<li><a href=\"javascript:getTrainingVideo("+videoBean.getVideoid()+");\">"+videoBean.getVideoheading()+"("+videoBean.getBatchnumber()+")</a></li>");
							 }
					}
					 videoBean = null;
				}						          
			}else{
	 	       htmlBuilder.append("<li>Currently Not Available.!</li>");
			}
			
			    out.println(htmlBuilder); 
			
		}catch(Exception e){
			e.printStackTrace();
 	        out.println("Something went wrong due to some technical problem. Please try again.!");
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
