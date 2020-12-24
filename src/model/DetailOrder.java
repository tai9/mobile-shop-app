package model;

import java.util.Date;

public class DetailOrder {
	private static int count = 1;

	private int id;
	private int idOrder;
	private Product product;
	private int quantityOrder;
	private double payment;
	
	public DetailOrder(int idOrder, Product product, int quantityOrder, double payment) {
		super();

		this.idOrder = idOrder;
		this.product = product;
		this.quantityOrder = quantityOrder;
		this.payment = payment;

		this.id = count;
		count++;
	}

	public DetailOrder(int id, int idOrder, Product product, int quantityOrder, double payment) {
		this(idOrder, product, quantityOrder, payment);
		this.id = id;
	}
	
	public DetailOrder(Product product, int quantityOrder, double payment) {
		this.product = product;
		this.quantityOrder = quantityOrder;
		this.payment = payment;
		this.id = count;
		count++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}


	@Override
	public String toString() {
		return "id detail: " + id + " id order: " + idOrder + " name product:" + product + " quantity: " + quantityOrder
				+ " payment: " + payment;
	}
	@Override
	public boolean equals(Object obj) {
		DetailOrder p = (DetailOrder)obj;
		return this.product.equals(p.product);
	}
}
