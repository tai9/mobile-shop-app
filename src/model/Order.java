package model;

import java.util.Date;

public class Order {
	private static int count = 1;

	private int id;
	private Customer customer;
	private double totalPayment;
	private Date orderDate;

	public Order(Customer customer, double totalPayment, Date orderDate) {

		this.customer = customer;
		this.totalPayment = totalPayment;
		this.orderDate = orderDate;

		this.id = count;
		count++;
	}

	public Order(int id, Customer customer, double totalPayment, Date orderDate) {
		this(customer, totalPayment, orderDate);
		this.id = id;
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

	@Override
	public String toString() {
		return this.id + " " + this.customer.getName() + " " + this.totalPayment;
	}
}
