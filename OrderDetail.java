package services;

public class OrderDetail {
	    private String name;
	    private float total;
	    private String number;
	    
	    public OrderDetail(String name,String number, String total) {
	        this.name = name;
	        this.number = number;
	        this.total = Float.parseFloat(total);
	    }
	    
	    
	    public String getName() {
	        return name;
	    }
	    
	    public String getNumber() {
	        return number;
	    }
	    public String getTotal() {
	        return String.format("%.2f", total);
	    }
	 
}
