package gui.order;

import java.util.Date;
import java.util.EventObject;

import model.Customer;

public class OrderFormEvent extends EventObject {

	private int id;
	private Customer customer;
	private double totalPayment;
	private Date orderDate;

	public OrderFormEvent(Object source) {
		super(source);
	}

	public OrderFormEvent(Object source, int id, Customer customer, double totalPayment, Date orderDate) {
		super(source);
		this.id = id;
		this.customer = customer;
		this.totalPayment = totalPayment;
		this.orderDate = orderDate;
	}

	public OrderFormEvent(Object source, Customer customer, double totalPayment, Date orderDate) {
		super(source);
		this.customer = customer;
		this.totalPayment = totalPayment;
		this.orderDate = orderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
}
