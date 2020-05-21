package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.base.rest.PayPalRESTException;

import beans.PaypalBean;
import daos.PaypalDao;

/**
 * Servlet implementation class AuthorizePaymentServlet
 */
@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		 PaypalBean paymentRequestBean = new PaypalBean();
	     PaypalDao paymentResponseDao = new PaypalDao();
		String name = request.getParameter("description");
        String total = request.getParameter("total");
        String number = request.getParameter("number");
        session.setAttribute("mobilenumber", number);
        OrderDetail orderDetail = new OrderDetail(name,number,total);
 
        try {
        	PaypalBean result = paymentResponseDao.getPaymentDetailsByMobilenumber(number, name);
        	if(result != null && result.getMobilenumber() != null && !(result.getMobilenumber().equalsIgnoreCase("")) && result.getMobilenumber().equalsIgnoreCase(number) && result.getDescription() != null && !(result.getDescription().equalsIgnoreCase("")) && result.getDescription().equalsIgnoreCase(name)){
        		 PaymentServices paymentServices = new PaymentServices();
                 String approvalLink = paymentServices.authorizePayment(orderDetail);
      
                 response.sendRedirect(approvalLink);
        	}else{
        		paymentRequestBean.setAmount(Float.valueOf(total));
            	paymentRequestBean.setDescription(name);
            	paymentRequestBean.setMobilenumber(number);
            	
            	Boolean reqResult = paymentResponseDao.insertPaypalRequest(paymentRequestBean);
            	if(reqResult){
            		  PaymentServices paymentServices = new PaymentServices();
                      String approvalLink = paymentServices.authorizePayment(orderDetail);
           
                      response.sendRedirect(approvalLink);
            	}else{
            		 out.println("<script>");
     	 	        out.println("alert('Something went wrong,please check and try again later.Thank you');");
     	 	        out.println("</script>");
                 request.getRequestDispatcher("/our-payments.jsp").include(request, response);
                 return;
            	}
        	}
        	
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            out.println("<script>");
	 	        out.println("alert('Something went wrong,please check and try again later.Thank you');");
	 	        out.println("</script>");
            request.getRequestDispatcher("/our-payments.jsp").include(request, response);
            return;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 out.println("<script>");
	 	        out.println("alert('Something went wrong,please check and try again later.Thank you');");
	 	        out.println("</script>");
	            request.getRequestDispatcher("/our-payments.jsp").include(request, response);
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
