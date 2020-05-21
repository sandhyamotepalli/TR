package services;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;	
public class PaymentServices {
	 private static final String CLIENT_ID = "ATP5rrkyIxmkH6gPjFVoLZ_Ix070hbETES3wS3sP5Enz91QADF4xDlR5nTxeu5LGGeZpe8wFQelE0aEp";
	  private static final String CLIENT_SECRET = "EEskTennlAVNXarpQN7tu6_HasCIpDSmpLyjvjZ7hpsF4nBsynVIn73pa8Jx2sOqjNg_nPX1mBHZRnIY";
	// test- private static final String CLIENT_ID = "Ad8xG3MsCU3LQM3bkGX6WmyH8SVTSpxm3HNHPJCIoTu_VicuxBiiBuaqL5dx0u0ZASvvJiUNBdGiVLV7";
	//test-  private static final String CLIENT_SECRET = "EJAonqQkFRoUNZFlbIA5MsPILUQffjyHl_6tp47AByqeVs4E8lNmiegeXg1MRT1W2biQa7huMjVFK69i";
	    private static final String MODE = "live";
	 
	    public String authorizePayment(OrderDetail orderDetail)        
	            throws PayPalRESTException {       
	 
	        Payer payer = getPayerInformation();
	        RedirectUrls redirectUrls = getRedirectURLs();
	        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
	         
	        Payment requestPayment = new Payment();
	        requestPayment.setTransactions(listTransaction);
	        requestPayment.setRedirectUrls(redirectUrls);
	        requestPayment.setPayer(payer);
	        requestPayment.setIntent("sale");
	 
	        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	 
	        Payment approvedPayment = requestPayment.create(apiContext);
	 
	        return getApprovalLink(approvedPayment);
	 
	    }
	     
	    private Payer getPayerInformation() {
	    	Payer payer = new Payer();
	        payer.setPaymentMethod("paypal");
	         
	        /*PayerInfo payerInfo = new PayerInfo();
	         payerInfo.setFirstName("Sandhya")
	                 .setLastName("Motepalli")
	                 .setEmail("sandhyamotepalli95@gmail.com");
	           
	        payer.setPayerInfo(payerInfo);*/
	         
	        return payer;
	    }
	     
	    private RedirectUrls getRedirectURLs() {
	    	RedirectUrls redirectUrls = new RedirectUrls();
	    	 // redirectUrls.setCancelUrl("http://localhost:8080/TechnoReachPaypal/our-payments.jsp");
	         // redirectUrls.setReturnUrl("http://localhost:8080/TechnoReachPaypal/review_payment");
	       // redirectUrls.setCancelUrl("https://208.109.10.89/TechnoReachPaypal/our-payments.jsp");
	    	//redirectUrls.setReturnUrl("https://208.109.10.89/TechnoReachPaypal/review_payment");
	    	redirectUrls.setCancelUrl("https://technoreachit.com/our-payments.jsp");
	    	redirectUrls.setReturnUrl("https://technoreachit.com/review_payment");

	        return redirectUrls;
	    }
	     
	    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
	    	   Details details = new Details();
	    	    details.setSubtotal(orderDetail.getTotal());
	    	 
	    	    Amount amount = new Amount();
	    	    amount.setCurrency("USD");
	    	    amount.setTotal(orderDetail.getTotal());
	    	    amount.setDetails(details);
	    	 
	    	    Transaction transaction = new Transaction();
	    	    transaction.setAmount(amount);
	    	    transaction.setDescription(orderDetail.getName());

	    	    ItemList itemList = new ItemList();
	    	    List<Item> items = new ArrayList<>();
	    	     
	    	    Item item = new Item();
	    	    item.setCurrency("USD");
	    	    item.setName(orderDetail.getName());
	    	    item.setPrice(orderDetail.getTotal());
                item.setQuantity("1");
	    	     
	    	    items.add(item);
	    	    itemList.setItems(items);
	    	    transaction.setItemList(itemList);
	    	 
	    	    List<Transaction> listTransaction = new ArrayList<>();
	    	    listTransaction.add(transaction);  
	    	     
	    	    return listTransaction;
	    }
	     
	    private String getApprovalLink(Payment approvedPayment) {
	    	List<Links> links = approvedPayment.getLinks();
	        String approvalLink = null;
	         
	        for (Links link : links) {
	            if (link.getRel().equalsIgnoreCase("approval_url")) {
	                approvalLink = link.getHref();
	                break;
	            }
	        }      
	         
	        return approvalLink;
	    }
	    
	    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
	        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	        return Payment.get(apiContext, paymentId);
	    }
	    public Payment executePayment(String paymentId, String payerId)
	            throws PayPalRESTException {
	        PaymentExecution paymentExecution = new PaymentExecution();
	        paymentExecution.setPayerId(payerId);
	     
	        Payment payment = new Payment().setId(paymentId);
	     
	        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	     
	        return payment.execute(apiContext, paymentExecution);
	    }
}
