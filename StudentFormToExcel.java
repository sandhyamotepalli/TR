package services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.UserBean;
import daos.AdminDao;
/**
 * Servlet implementation class StudentFormToExcel
 */
@WebServlet("/StudentFormToExcel")
public class StudentFormToExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentFormToExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		AdminDao adminDao = new AdminDao();
		try
	    {
		ArrayList<UserBean> studentsList =new ArrayList<UserBean>();
		UserBean studentsBean=null;
			studentsList = adminDao.getStudentDetails();
			if(studentsList !=null && studentsList.size() > 0)
			  {
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet();
			//FileOutputStream out = new FileOutputStream(new File(""+date+"purchasereport.xlsx"));
			XSSFRow row;
			row =sheet.createRow(0);
			//Iterator <Cell> cellIterator=row.cellIterator();
			XSSFCell cell0=row.createCell(0);
			cell0.setCellValue("S.No");
			XSSFCell cell1=row.createCell(1);
			cell1.setCellValue("Student Name");
			XSSFCell cell2=row.createCell(2);
			cell2.setCellValue("Last Name");
			XSSFCell cell3=row.createCell(3);
			cell3.setCellValue("Email-Id");
			XSSFCell cell4=row.createCell(4);
			cell4.setCellValue("Mobile Number");
			XSSFCell cell5=row.createCell(5);
			cell5.setCellValue("Qualification");
			XSSFCell cell6=row.createCell(6);
			cell6.setCellValue("College/University Name");
			XSSFCell cell7=row.createCell(7);
			cell7.setCellValue("Interested In");
			XSSFCell cell8=row.createCell(8);
			cell8.setCellValue("Future Goal");
				 XSSFCreationHelper createHelper = workbook.getCreationHelper();
				 XSSFCellStyle cell2Date         = workbook.createCellStyle();
				 cell2Date.setDataFormat(
				 createHelper.createDataFormat().getFormat("dd/MM/yyyy")); 
			
			 for(int j=0;j<studentsList.size();j++)
			 {
				 studentsBean=new UserBean();
				if(studentsList.get(j)!=null)
				{
					studentsBean=studentsList.get(j);
					row=sheet.createRow(j+1);
				    cell0=row.createCell(0);
				    cell0.setCellValue(j+1);
				    cell1=row.createCell(1);
				    cell1.setCellValue(studentsBean.getStudentname());
				    cell2=row.createCell(2);
				    cell2.setCellValue(studentsBean.getLastname());
				    cell3=row.createCell(3);
				    cell3.setCellValue(studentsBean.getStudentemail());
				    cell4=row.createCell(4);
				    cell4.setCellValue(studentsBean.getStudentmobilenumber());
				    cell5=row.createCell(5);
				    cell5.setCellValue(studentsBean.getQualification());
				    cell6=row.createCell(6);
				    cell6.setCellValue(studentsBean.getCollege());
				    cell7=row.createCell(7);
				    cell7.setCellValue(studentsBean.getInterestedin());
				    cell8=row.createCell(8);
				    cell8.setCellValue(studentsBean.getGoal());
				    studentsBean=null;
				}
			 }
			 Date today = new Date();
			 ServletContext context = getServletContext();
			 String fileName = today + " Student Form.xls";
			      // sets MIME type for the file download
	            String mimeType = context.getMimeType(fileName);
	            if (mimeType == null) {        
	                mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	            }
	            // set content properties and header attributes for the response
	            response.setContentType(mimeType);
	           // response.setContentLength(fileLength);
	            String headerKey = "Content-Disposition";
	            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
	            response.setHeader(headerKey, headerValue);

	            // writes the file to the client
	            OutputStream outStream = response.getOutputStream();
	            workbook.write(outStream);
	            response.flushBuffer();
	            workbook.close();
	            outStream.close();
			  }else
				{
				  response.getWriter().println("<script type=\"text/javascript\">");
				  response.getWriter().println("alert('No data available.!!');");
				  response.getWriter().println("</script>");
			    	  RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		             rd.include(request, response);		
		             return;
				}
	    }
	    catch(Exception e)
	    {
           e.printStackTrace();	    
           response.getWriter().println("<script type=\"text/javascript\">");
           response.getWriter().println("alert('Some technical problem occur.Please try Again.!!');");
           response.getWriter().println("</script>");
	    	  RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
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
