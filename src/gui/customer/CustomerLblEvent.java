package gui.customer;

import java.util.EventObject;

public class CustomerLblEvent extends EventObject {
	private int productCount;
	private int orderCount;
	private double totalPrice;
	
	public CustomerLblEvent(Object source) {
		super(source);
	}

	public CustomerLblEvent(Object source, int productCount, int orderCount, double totalPrice) {
		super(source);
		this.productCount = productCount;
		this.orderCount = orderCount;
		this.totalPrice = totalPrice;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
