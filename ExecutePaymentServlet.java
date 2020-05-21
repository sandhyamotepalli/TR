package services;


import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import beans.PaypalBean;
import daos.PaypalDao;
import utilities.EmailUtility;

/**
 * Servlet implementation class ExecutePaymentServlet
 */
@WebServlet("/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutePaymentServlet() {
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
		   String paymentId = request.getParameter("paymentId");
	        String payerId = request.getParameter("PayerID");
	        String number = request.getParameter("mobilenumber");
	        PaypalBean paymentResponseBean = new PaypalBean();
	        PaypalDao paymentResponseDao = new PaypalDao();
	        try {
	            PaymentServices paymentServices = new PaymentServices();
	            Payment payment = paymentServices.executePayment(paymentId, payerId);
	             
	            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
	            Transaction transaction = payment.getTransactions().get(0);
	            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
	            
	            request.setAttribute("payer", payerInfo);
	            request.setAttribute("transaction", transaction);
	            
	            
	            if(paymentId != null && !paymentId.equalsIgnoreCase("")){
	            	paymentResponseBean.setPaymentid(paymentId);
	            }
	            if(payerId != null && !payerId.equalsIgnoreCase("")){
	            	paymentResponseBean.setPayerid(payerId);
	            }
	            if(payerInfo.getFirstName() != null && !payerInfo.getFirstName().equalsIgnoreCase("")){
	            	 paymentResponseBean.setFirstname(payerInfo.getFirstName());
	            }
	            if(payerInfo.getLastName() != null && !payerInfo.getLastName().equalsIgnoreCase("")){
	            	paymentResponseBean.setLastname(payerInfo.getLastName());
	            }
                if(payerInfo.getEmail() != null && !payerInfo.getEmail().equalsIgnoreCase("")){
                	 paymentResponseBean.setEmail(payerInfo.getEmail());
	            }
                if(transaction.getDescription() != null && !transaction.getDescription().equalsIgnoreCase("")){
                	paymentResponseBean.setDescription(transaction.getDescription());
	            }
                if(number != null && !number.equalsIgnoreCase("")){
                	paymentResponseBean.setMobilenumber(number);
	            }
	            
                 if(transaction.getAmount().getTotal() != null && !transaction.getAmount().getTotal().equalsIgnoreCase("")){
                	 paymentResponseBean.setAmount(Float.valueOf(transaction.getAmount().getTotal()));
	            }
	            if(shippingAddress.getLine1() != null && !shippingAddress.getLine1().equalsIgnoreCase("")){
	            	 paymentResponseBean.setLine1(shippingAddress.getLine1());
	            }
	            if(shippingAddress.getLine2() != null && !shippingAddress.getLine2().equalsIgnoreCase("")){
	            	 paymentResponseBean.setLine2(shippingAddress.getLine2());
	            }
	            if(shippingAddress.getCity() != null && !shippingAddress.getCity().equalsIgnoreCase("")){
	            	 paymentResponseBean.setCity(shippingAddress.getCity());
	            }
	            if(shippingAddress.getCountryCode() != null && !shippingAddress.getCountryCode().equalsIgnoreCase("")){
	            	paymentResponseBean.setCountrycode(shippingAddress.getCountryCode());
	            }
	            if(shippingAddress.getPostalCode() != null && !shippingAddress.getPostalCode().equalsIgnoreCase("")){
	            	paymentResponseBean.setPostalcode(shippingAddress.getPostalCode());
	            }
	            if(transaction.getInvoiceNumber() != null && !transaction.getInvoiceNumber().equalsIgnoreCase("")){
	            	paymentResponseBean.setInvoicenumber(transaction.getInvoiceNumber());
	            }
	            if(transaction.getReferenceId() != null && !transaction.getReferenceId().equalsIgnoreCase("")){
	            	 paymentResponseBean.setReferenceId(transaction.getReferenceId());
	            }
	        	PaypalBean result = paymentResponseDao.getPaymentDetailsById(payerId, paymentId, number);
                if(result != null && result.getMobilenumber() != null && !(result.getMobilenumber().equalsIgnoreCase(""))){
                	 paymentResponseBean.setOrderid(result.getOrderid());
                }
	            Boolean paymentResult = paymentResponseDao.insertPaypalResponse(paymentResponseBean);
	            if(paymentResult){
	            	String emailMessage = "";
					emailMessage = "<p>Dear Admin, <br> payment done by user. <br> Details Are : <br> Name : "+payerInfo.getFirstName()+" "+payerInfo.getLastName()+" <br> Emailid : "+payerInfo.getEmail()+" <br> Mobilenumber : "+number+" <br>Amount : "+transaction.getAmount().getTotal()+" <br>";
					  try {
					    	EmailUtility.sendEmail("Payment Done:"+number+" ", emailMessage, "info@technoreachit.com");
					    	out.println("<script>");
			 	 	        out.println("alert('Payment Done Succesfully.Thank you');");
			 	 	        out.println("</script>");
			 	 	        session.removeAttribute("mobilenumber");
			 	 	        session.invalidate();
			 	 	        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 	 	        rd.include(request, response);
				             return;

				    } catch (AddressException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	 session.removeAttribute("mobilenumber");
			 	 	     session.invalidate();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Payment Done Succesfully.Thank you');");
				    	out.println("</script>");
				    	 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 	 	        rd.include(request, response);
			 	 	        return;

				    } catch (MessagingException e) {
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
				    	 session.removeAttribute("mobilenumber");
			 	 	     session.invalidate();
				    	out.println("<script type=\"text/javascript\">");
				    	out.println("alert('Payment Done Succesfully.Thank you');");
				    	out.println("</script>");
				    	 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 	 	        rd.include(request, response);
			 	 	        return;
				    }
	            }else{
	            	out.println("<script>");
	 	 	        out.println("alert('Payment not Done Succesfully.Thank you');");
	 	 	        out.println("</script>");
	 	 	        session.removeAttribute("mobilenumber");
	 	 	        session.invalidate();
	 	 	        RequestDispatcher rd = request.getRequestDispatcher("/our-payments.jsp");
	 	 	        rd.include(request, response);
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
