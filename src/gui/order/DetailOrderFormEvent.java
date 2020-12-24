package gui.order;

import java.util.Date;
import java.util.EventObject;

import model.DetailOrder;
import model.Product;

public class DetailOrderFormEvent extends EventObject {
	
	private int id;
	private int idOrder;
	private Product product;
	private int quantityOrder;
	private double payment;
	

	public DetailOrderFormEvent(Object source) {
		super(source);
	}
	
	public DetailOrderFormEvent(Object source, Product product, int quantityOrder, double payment) {
		super(source);
		this.product = product;
		this.quantityOrder = quantityOrder;
		this.payment = payment;
	}
	
	public DetailOrderFormEvent(Object source, int id, int idOrder, Product product, int quantityOrder,
			double payment) {
		super(source);
		this.id = id;
		this.idOrder = idOrder;
		this.product = product;
		this.quantityOrder = quantityOrder;
		this.payment = payment;
	}

	public DetailOrderFormEvent(Object source, int idOrder, Product product, int quantityOrder, double payment) {
		super(source);
		this.idOrder = idOrder;
		this.product = product;
		this.quantityOrder = quantityOrder;
		this.payment = payment;
	}
	
	public DetailOrderFormEvent(Object source, DetailOrder detailOrder) {
		super(source);
		this.id = detailOrder.getId();
		this.idOrder = detailOrder.getIdOrder();
		this.product = detailOrder.getProduct();
		this.quantityOrder = detailOrder.getQuantityOrder();
		this.payment = detailOrder.getPayment();
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
	
}
