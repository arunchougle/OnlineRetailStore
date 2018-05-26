package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author arun.chougule
 * Class which defines Cart object. It holds list of the products added in cart aliong with:
 *  - total price of all products
 *  - total tax on all products considering the type (A,B,C)
 *  - checkOut price after taxes applicable 
 */
public class Cart {

	private List<Product> productsList;
	private double totalPrice;
	private double totalTax;
	private double checkoutPrice;	
	
	public List<Product> getProductsList() {
		if(productsList==null)
			return productsList=new ArrayList<Product>();
		return productsList;
	}
	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getCheckoutPrice() {
		return checkoutPrice;
	}
	public void setCheckoutPricet(double checkoutPrice) {
		this.checkoutPrice = checkoutPrice;
	}	
}
