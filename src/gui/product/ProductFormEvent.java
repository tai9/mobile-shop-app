package gui.product;

import java.util.EventObject;

import model.Product;

public class ProductFormEvent extends EventObject{
	private int id;
	
	private String name;
	private String category;
	private double price;
	private int quantity;
	private String description;
	
	public ProductFormEvent(Object source) {
		super(source);
	}
	
	public ProductFormEvent(Object source,int id, String name, String category, double price, int quantity,
			String description) {
		super(source);
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	
	public ProductFormEvent(Object source, String name, String category, double price, int quantity,
			String description) {
		super(source);
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Product getProduct() {
		return new Product(id, name, category, price, quantity, description);
	}
	
	@Override
	public String toString() {
		return id + " "+name;
	}
}
