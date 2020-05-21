package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBean;
import daos.AdminDao;
import utilities.Storgaeutility;

/**
 * Servlet implementation class GetVideoToViewFromStorage
 */
@WebServlet("/GetVideoToViewFromStorage")
public class GetVideoToViewFromStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVideoToViewFromStorage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("video/mp4");
		PrintWriter out = response.getWriter();
		AdminDao videoDao = new AdminDao();
		try{
			String videoid = request.getParameter("videoid");
			AdminBean getVideo = videoDao.getTrainerVideoByid(Integer.parseInt(videoid));
			if(getVideo != null && getVideo.getVideoid() !=0 && getVideo.getVideoid() == Integer.parseInt(videoid)){
			     URL url = Storgaeutility.signUrl(getVideo.getUniquestorageid());
			    StringBuilder htmlBuilder = new StringBuilder();
			    htmlBuilder.append("<video width=\"100%\" height=\"500\" controls controlsList=\"nodownload\">");
			    htmlBuilder.append("<source src=\""+url+"\" type='video/mp4'>");
			    htmlBuilder.append("</video>");
			    out.println(htmlBuilder);
			}else{
	 	        out.println("Something went wrong due to some technical problem. Please try again.!");
			}
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
