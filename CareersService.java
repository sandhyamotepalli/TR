package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.AdminBean;
import daos.AdminDao;
import utilities.EmailUtility;
import utilities.Utility;

/**
 * Servlet implementation class CareersService
 */
@WebServlet("/CareersService")
@MultipartConfig(maxFileSize = 16177215)
public class CareersService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareersService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AdminDao adminDao = new AdminDao();
		int itexperience = 0;
		int relevantexperience=0;
		 List<File> uploadedFiles = null;
		try{
			
			String name = request.getParameter("fullname");
			String mobileNumber = request.getParameter("contactnumber");
			String locationpreference = request.getParameter("joblocation");
			if(request.getParameter("itexperience") !=null &&!request.getParameter("itexperience").equalsIgnoreCase("")){
				 itexperience = Integer.parseInt(request.getParameter("itexperience"));
			}
			if(request.getParameter("relevantexperience") !=null &&!request.getParameter("relevantexperience").equalsIgnoreCase("")){
				 relevantexperience = Integer.parseInt(request.getParameter("relevantexperience"));
			}
			String emailid = request.getParameter("emailid");
			String message = request.getParameter("message");
			String jobcode = request.getParameter("jobcode");
			uploadedFiles = saveUploadedFiles(request);
			AdminBean jobBean = adminDao.getCareersByCode(Integer.parseInt(jobcode));
			if(jobBean != null && jobBean.getJobcode() != 0 && jobBean.getJobcode() == Integer.parseInt(jobcode)){
				String emailMessage = "";
				emailMessage = "<p>Dear Admin, <br> One user apply for job. <br> User Details Are : <br> Name : "+name+" <br> Mobile Number : "+mobileNumber+" <br> Email Id : "+emailid+" <br>";
				    if(message != null && !message.equalsIgnoreCase("")){
						emailMessage += "Message : "+message+". <br>";
				    }
				    if(locationpreference != null && !locationpreference.equalsIgnoreCase("")){
				    	 emailMessage += "Preference Location: "+locationpreference+". <br>";
				    }
				    if(itexperience != 0){
				    	 emailMessage += "IT Experience: "+itexperience+".<br>";
				    }
				    if(relevantexperience != 0){
				    	 emailMessage += "Relevant Experience: "+relevantexperience+".<br>";
				    }
				    emailMessage += "Thank You.";
				    try {
					    	EmailUtility.sendEmailAttachment("Job Code:"+jobcode, emailMessage, "hr@technoreachit.com", uploadedFiles);
						    out.println("<script type=\"text/javascript\">");
				 	    	out.println("alert('Thank You! For showing in your request, Will get back to you at the earliest possible.');");
				 	    	out.println("</script>");
				 	    	 RequestDispatcher rd = request.getRequestDispatcher("/apply-now.jsp");
				             rd.include(request, response);
				             return;

				    } catch (AddressException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Sorry!Your request can not be processed. Please try Again');");
				    	out.println("</script>");
			 	    	 RequestDispatcher rd = request.getRequestDispatcher("/apply-now.jsp");
				    	    rd.include(request, response);


				    } catch (MessagingException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Sorry!Your Request is not submitted because of technical problem. Please try Again');");
				    	out.println("</script>");
			 	    	 RequestDispatcher rd = request.getRequestDispatcher("/apply-now.jsp");
				        rd.include(request, response);
				    }
			}
			/*Part resume = request.getPart("resume");
		     InputStream fileContent = filePart.getInputStream();
			String filename = Utility.extractFileNames(resume);
			System.out.println(filename);
		    if (resume != null && resume.getSize() > 1){
				   resumeContent =Utility.inputStreamToByteArray(resume.getInputStream());
				   careersBean.setResume(new SerialBlob(resumeContent));
			}else{
				 careersBean.setResume(null);
			}*/
			else{
				 out.println("<script type=\"text/javascript\">");
		 	    	out.println("alert('Entered Job Code is not Matched.Please Check Job Code.');");
		 	    	out.println("</script>");
		 	    	 RequestDispatcher rd = request.getRequestDispatcher("/apply-now.jsp");
		             rd.include(request, response);
		             return;
			} 
		}catch(Exception e){
			 out.println("<script>");
		    	out.println("alert('Sorry!Your Request is not submitted because of technical problem. Please try Again');");
 	        out.println("</script>");
 	        RequestDispatcher rd = request.getRequestDispatcher("/apply-now.jsp");
 	        rd.include(request, response);
			e.printStackTrace();
		}
	}
	  private List<File> saveUploadedFiles(HttpServletRequest request)
	            throws IllegalStateException, IOException, ServletException,FileNotFoundException,SecurityException {
	        List<File> listFiles = new ArrayList<File>();
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        Collection<Part> multiparts = request.getParts();
	        if (multiparts.size() > 0) {
	            for (Part part : request.getParts()) {
	                // creates a file to be saved
	                String fileName = Utility.extractFileName(part);
	                if (fileName == null || fileName.equals("")) {
	                    // not attachment part, continue
	                    continue;
	                }
	                 
	                File saveFile = new File(fileName);
	                if(saveFile.exists() && !saveFile.canWrite()){
	                    
	                    System.out.println("File exists and it is read only, making it writable");
	                    saveFile.setWritable(true);
	                }
	                
	                //System.out.println("saveFile: " + saveFile.getAbsolutePath());
	                FileOutputStream outputStream = new FileOutputStream(saveFile);
	                 
	                // saves uploaded file
	                InputStream inputStream = part.getInputStream();
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	                outputStream.close();
	                inputStream.close();
	                 
	                listFiles.add(saveFile);
	            }
	        }
	        return listFiles;
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
