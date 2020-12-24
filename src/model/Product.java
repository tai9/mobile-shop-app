package model;

public class Product {

	private static int count = 1;

	private int id;
	private String name;
	private String category;
	private double price;
	private int quantity;
	private String description;

	public Product() {
	}

	public Product(String name, String category, double price, int quantity, String description) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.description = description;

		this.id = count;
		count++;
	}

	public Product(int id, String name, String category, double price, int quantity, String description) {
		this(name, category, price, quantity, description);
		this.id = id;
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		Product p = (Product) obj;
		if (this.id == p.id)
			return true;
		return false;
	}
}
