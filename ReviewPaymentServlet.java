package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import beans.PaypalBean;
import daos.PaypalDao;
/**
 * Servlet implementation class ReviewPaymentServlet
 */
@WebServlet("/review_payment")
public class ReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		 PaypalBean paymentReviewBean = new PaypalBean();
	     PaypalDao paymentResponseDao = new PaypalDao();
	        try {
	        	 String paymentId = request.getParameter("paymentId");
	 	        String payerId = request.getParameter("PayerID");
	 	         String number = (String)session.getAttribute("mobilenumber");
	 	         
	            PaymentServices paymentServices = new PaymentServices();
	            Payment payment = paymentServices.getPaymentDetails(paymentId);
	            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
	            Transaction transaction = payment.getTransactions().get(0);
	            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
	             
	            paymentReviewBean.setPaymentid(paymentId);
	            paymentReviewBean.setPayerid(payerId);
	            paymentReviewBean.setDescription(transaction.getDescription());
	            paymentReviewBean.setMobilenumber(number);
	             Boolean updateResult = paymentResponseDao.updateRequestStatus(paymentReviewBean);
	            if(updateResult){
	            	request.setAttribute("payer", payerInfo);
		            request.setAttribute("transaction", transaction);
		            request.setAttribute("shippingAddress", shippingAddress);
		             
		            String url = "payment-review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId+"&mobileNumber="+number;
		             
		            request.getRequestDispatcher(url).forward(request, response);
	            }else{
	            	    out.println("<script>");
		 	 	        out.println("alert('Something went wrong,please check and try again later.Thank you');");
		 	 	        out.println("</script>");
			            request.getRequestDispatcher("/our-payments.jsp").include(request, response);
			            return;
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
