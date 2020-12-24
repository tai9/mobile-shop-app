package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import gui.HomeLabelEvent;
import gui.account.AccountEvent;
import gui.customer.CustomerFormEvent;
import gui.customer.CustomerLblEvent;
import gui.order.DetailOrderFormEvent;
import gui.order.OrderFormEvent;
import gui.product.ProductFormEvent;
import model.Account;
import model.Customer;
import model.Database;
import model.DetailOrder;
import model.Order;
import model.Product;

public class Controller {
	Database db = new Database();

	// Connection
	public void connect() {
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		db.disconnect();
	}

	public List<Product> getProducts() {
		return db.getProducts();
	}

	public List<Customer> getCustomers() {
		return db.getCustomers();
	}

	public List<Order> getOrders() {
		return db.getOrders();
	}

	public List<DetailOrder> getDetailOrder() {
		return db.getDetailOrders();
	}

	public List<Account> getAccounts() {
		return db.getAccounts();
	}

	// product
	public void addProduct(ProductFormEvent ev) {

		String name = ev.getName();
		String category = ev.getCategory();
		double price = ev.getPrice();
		int quantity = ev.getQuantity();
		String description = ev.getDescription();

		Product product = new Product(name, category, price, quantity, description);
		db.addProduct(product);
	}

	public void updateProduct(ProductFormEvent ev) {
		int id = ev.getId();
		String name = ev.getName();
		String category = ev.getCategory();
		double price = ev.getPrice();
		int quantity = ev.getQuantity();
		String description = ev.getDescription();

		Product product = new Product(id, name, category, price, quantity, description);
		db.updateProduct(product);
	}

	public void removeProduct(ProductFormEvent ev) {
		int id = ev.getId();
		String name = ev.getName();
		String category = ev.getCategory();
		double price = ev.getPrice();
		int quantity = ev.getQuantity();
		String description = ev.getDescription();

		Product product = new Product(id, name, category, price, quantity, description);
		db.removeProduct(product);
	}

	// Customer
	public void addCustomer(CustomerFormEvent ev) {

		String name = ev.getName();
		int birthYear = ev.getBirthYear();
		boolean sex = ev.isSex();
		String phoneNumber = ev.getPhoneNumber();
		String address = ev.getAddress();
		String moreInfo = ev.getMoreInfo();

		Customer customer = new Customer(name, birthYear, sex, phoneNumber, address, moreInfo);
		db.addCustomer(customer);
	}

	public void updateCustomer(CustomerFormEvent ev) {
		int id = ev.getId();
		String name = ev.getName();
		int birthYear = ev.getBirthYear();
		boolean sex = ev.isSex();
		String phoneNumber = ev.getPhoneNumber();
		String address = ev.getAddress();
		String moreInfo = ev.getMoreInfo();

		Customer customer = new Customer(id, name, birthYear, sex, phoneNumber, address, moreInfo);
		db.updateCustomer(customer);
	}

	public void removeCustomer(CustomerFormEvent ev) {
		int id = ev.getId();
		String name = ev.getName();
		int birthYear = ev.getBirthYear();
		boolean sex = ev.isSex();
		String phoneNumber = ev.getPhoneNumber();
		String address = ev.getAddress();
		String moreInfo = ev.getMoreInfo();

		Customer customer = new Customer(id, name, birthYear, sex, phoneNumber, address, moreInfo);
		db.removeCustomer(customer);
	}

	// Order
	public void addOrder(OrderFormEvent ev) {

		Order order = new Order(ev.getCustomer(), ev.getTotalPayment(), ev.getOrderDate());
		db.addOrder(order);
	}

	// Detail Order
	public void addDetailOrder(DetailOrderFormEvent ev) {
		DetailOrder detailOrder = new DetailOrder(ev.getProduct(), ev.getQuantityOrder(), ev.getPayment());
		db.addDetailOrderItem(detailOrder);
	}

	public void removeDetailOrderItem(DetailOrderFormEvent ev) {
		DetailOrder detailOrder = new DetailOrder(ev.getId(), ev.getIdOrder(), ev.getProduct(), ev.getQuantityOrder(),
				ev.getPayment());
		db.removeDetailOrderItem(detailOrder);
	}

	public void removeAllDetailOrder() {
		db.removeAllDetailOrder();
	}
	
	// Account
	public void addAccount(AccountEvent ev) {
		try {
			db.addAccount(new Account(ev.getUsername(), ev.getPassword(), ev.isRole()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(AccountEvent ev) {
		try {
			db.updateAccount(new Account(ev.getUsername(), ev.getPassword(), ev.isRole()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAccount(AccountEvent ev) {
		try {
			db.deleteAccount(new Account(ev.getUsername(), ev.getPassword(), ev.isRole()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statistics
	public Map<Integer, Double> getTotalPaymentOfMonth(){
		try {
			return db.getTotalPaymentOfMonth();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// load list
	public void loadListProducts() {
		try {
			db.loadListProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadListCustomers() {
		try {
			db.loadListCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadListOrders() {
		try {
			db.loadListOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadListDetaiOrder() {
		try {
			db.loadListDetailOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadListAccount() {
		try {
			db.loadListAccount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// save list
	public void saveListProducts() {
		try {
			db.saveListProducts();
		} catch (SQLException e) {
			System.out.println("Unable to save products");
			e.printStackTrace();
		}
	}

	public void saveListCustomers() {
		try {
			db.saveListCustomers();
		} catch (SQLException e) {
			System.out.println("Unable to save customers");
			e.printStackTrace();
		}
	}

	public void insertOrder(Order order) {
		try {
			db.insertOrder(order);
		} catch (SQLException e) {
			System.out.println("Unable to save order");
			e.printStackTrace();
		}
	}
	
	public void saveListAccounts() {
		try {
			db.saveListAccounts();
		} catch (SQLException e) {
			System.out.println("Unable to save accounts");
			e.printStackTrace();
		}
	}

	// load thong so
	public void loadThongSo(HomeLabelEvent lblHome) {
		try {
			db.loadThongSoHome(lblHome);
		} catch (SQLException e) {
			System.out.println("Can't load count");
			e.printStackTrace();
		}
	}

	public void loadThongSoCustomer(CustomerFormEvent customer, CustomerLblEvent lblEvent) {
		try {
			db.loadThongSoCustomer(customer, lblEvent);
		} catch (SQLException e) {
			System.out.println("can't load thong so");
			e.printStackTrace();
		}
	}
}
