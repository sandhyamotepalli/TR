package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.AdminBean;
import daos.AdminDao;
import utilities.Storgaeutility;

/**
 * Servlet implementation class CreateTrainerVideoService
 */
@WebServlet("/CreateTrainerVideoService")
@MultipartConfig(fileSizeThreshold=1024*1024*50,  // 10 MB 
maxFileSize=1024*1024*500,       // 50 MB209715200
maxRequestSize=1024*1024*500)    // 100 MB
public class CreateTrainerVideoService extends HttpServlet {
	private static final long serialVersionUID = -1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTrainerVideoService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		AdminBean trainervideoBean = new AdminBean();
		AdminDao trainingsDao = new AdminDao();
		try{
			if(session != null && session.getAttribute("trainerid") != null){
			int trainerid = (int)session.getAttribute("trainerid");
			if(trainerid != 0){
				AdminBean trainerDetails = trainingsDao.getTrainerDetailsById(trainerid);
				String title = request.getParameter("videotitle");
				String batch = request.getParameter("batch");
				Part video = request.getPart("video");
				String videopath = Storgaeutility.uploadFile(video);
				//System.out.println("Videopath"+videopath);
				String[] videodetails = videopath.split("/");
				trainervideoBean.setVideoheading(title);
				trainervideoBean.setTrainerid(trainerid);
				trainervideoBean.setBucketname(videodetails[0]);
				trainervideoBean.setUniquestorageid(videodetails[1]);
				trainervideoBean.setGenerationid(videodetails[2]);
				trainervideoBean.setTechnology(trainerDetails.getTechnology());
				trainervideoBean.setTrainingid(trainerDetails.getTrainingid());
				trainervideoBean.setBatchnumber(batch);
				boolean istrainingVideoCreated = trainingsDao.insertTrainerVideos(trainervideoBean);
				if(istrainingVideoCreated){
				    out.println("<script>");
		 	        out.println("alert('Video Uploaded Succesfully');");
		 	        out.println("</script>");
		 	        session.removeAttribute("trainerid");
		 	        session.invalidate();
		 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		 	        rd.include(request, response);
		 	        return;
				}else{
				    out.println("<script>");
		 	        out.println("alert('Video not Uploaded. Please try again.!');");
		 	        out.println("</script>");
		 	        session.removeAttribute("trainerid");
		 	        session.invalidate();
		 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		 	        rd.include(request, response);
		 	        return;
				}
			 
			}else{
				out.println("<script>");
	 	        out.println("alert('Your Session is timed out because of inactivity Please Click OK to login again.');");
	 	        out.println("</script>");
	 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	 	        rd.include(request, response);
	 	        return;
			}
				 }else{
			    	out.println("<script>");
		 	        out.println("alert('Your Session is timed out because of inactivity Please Click OK to login again.');");
		 	        out.println("</script>");
		 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		 	        rd.include(request, response);
		 	        return;
			    }
			
		}catch(Exception e){
			e.printStackTrace();
			 out.println("<script>");
	 	     out.println("alert('Video not Uploaded. Please try again.!');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
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
