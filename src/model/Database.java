package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gui.HomeLabelEvent;
import gui.customer.CustomerFormEvent;
import gui.customer.CustomerLblEvent;

public class Database {
	private List<Product> products;
	private List<Customer> customers;
	private List<Order> orders;
	private List<DetailOrder> detailOrders;
	private List<Account> accounts;

	private Connection con;

	public Database() {
		products = new LinkedList<Product>();
		customers = new LinkedList<Customer>();
		orders = new LinkedList<Order>();
		detailOrders = new LinkedList<DetailOrder>();
		accounts = new ArrayList<Account>();
	}

	// connect to db
	public void connect() throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Driver not found");
		}

		String url = "jdbc:sqlserver://localhost;databaseName=MobileShopManagement;user=sa;password=sa";
		con = DriverManager.getConnection(url);
	}

	// disconnect to db
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
				e.printStackTrace();
			}
		}
	}
	
	// get list
		public List<Product> getProducts() {
			return Collections.unmodifiableList(this.products);
		}

		public List<Customer> getCustomers() {
			return Collections.unmodifiableList(this.customers);
		}

		public List<Order> getOrders() {
			return Collections.unmodifiableList(this.orders);
		}

		public List<DetailOrder> getDetailOrders() {
			return Collections.unmodifiableList(this.detailOrders);
		}
		
		public List<Account> getAccounts(){
			return Collections.unmodifiableList(this.accounts);
		}

	// save list
	public void saveListProducts() throws SQLException {
		String checkSql = "select count(*) as count from product where id=?";
		String insertSql = "insert into product (name, category, price, quantity, description) values (?,?,?,?,?)";
		String updateSql = "update product set name = ?, category = ?, price=?, quantity=?, description=? where id=?";

		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		PreparedStatement insertStmt = con.prepareStatement(insertSql);
		PreparedStatement updateStmt = con.prepareStatement(updateSql);

		for (Product product : products) {
			int id = product.getId();
			String name = product.getName();
			String category = product.getCategory();
			double price = product.getPrice();
			int quantity = product.getQuantity();
			String description = product.getDescription();

			checkStmt.setInt(1, id);
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);

			if (count == 0) {
				System.out.println("Inserting product with ID: " + id);

				insertStmt.setString(1, name);
				insertStmt.setString(2, category);
				insertStmt.setDouble(3, price);
				insertStmt.setInt(4, quantity);
				insertStmt.setString(5, description);

				insertStmt.executeUpdate();

			} else {
				System.out.println("Updating product with ID: " + id);

				updateStmt.setString(1, name);
				updateStmt.setString(2, category);
				updateStmt.setDouble(3, price);
				updateStmt.setInt(4, quantity);
				updateStmt.setString(5, description);
				updateStmt.setInt(6, id);

				updateStmt.executeUpdate();
			}
		}

		updateStmt.close();
		insertStmt.close();
		checkStmt.close();
	}

	public void saveListCustomers() throws SQLException {
		String checkSql = "select count(*) as count from customer where id=?";
		String insertSql = "insert into customer (name, birthyear, sex, phoneNumber, address, moreInfo) values (?,?,?,?,?,?)";
		String updateSql = "update customer set name = ?, birthyear = ?, sex=?, phoneNumber=?, address=?, moreInfo=? where id=?";

		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		PreparedStatement insertStmt = con.prepareStatement(insertSql);
		PreparedStatement updateStmt = con.prepareStatement(updateSql);

		for (Customer customer : customers) {
			int id = customer.getId();
			String name = customer.getName();
			int birthYear = customer.getBirthYear();
			boolean sex = customer.getSex();
			String phoneNumber = customer.getPhoneNumber();
			String address = customer.getAddress();
			String moreInfo = customer.getMoreInfo();

			checkStmt.setInt(1, id);
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);

			if (count == 0) {
				System.out.println("Inserting customer with ID: " + id);

				insertStmt.setString(1, name);
				insertStmt.setInt(2, birthYear);
				insertStmt.setBoolean(3, sex);
				insertStmt.setString(4, phoneNumber);
				insertStmt.setString(5, address);
				insertStmt.setString(6, moreInfo);

				insertStmt.executeUpdate();

			} else {
				System.out.println("Updating customer with ID: " + id);

				updateStmt.setString(1, name);
				updateStmt.setInt(2, birthYear);
				updateStmt.setBoolean(3, sex);
				updateStmt.setString(4, phoneNumber);
				updateStmt.setString(5, address);
				updateStmt.setString(6, moreInfo);

				updateStmt.setInt(7, id);

				updateStmt.executeUpdate();
			}
		}

		updateStmt.close();
		insertStmt.close();
		checkStmt.close();
	}
	
	public void saveListAccounts() throws SQLException {
		String checkSql = "select count(*) as count from account where username=?";
		String insertSql = "insert into account (username, userpassword, role) values (?,?,?)";
		String updateSql = "update account set userpassword = ?, role = ? where username=?";

		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		PreparedStatement insertStmt = con.prepareStatement(insertSql);
		PreparedStatement updateStmt = con.prepareStatement(updateSql);

		for (Account account : accounts) {
			String username = account.getUsername();
			String userpassword = account.getPassword();
			boolean role = account.isRole();

			checkStmt.setString(1, username);
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);

			if (count == 0) {
				insertStmt.setString(1, username);
				insertStmt.setString(2, userpassword);
				insertStmt.setBoolean(3, role);

				insertStmt.executeUpdate();

			} else {
				updateStmt.setString(1, userpassword);
				updateStmt.setBoolean(2, role);
				updateStmt.setString(3, username);

				updateStmt.executeUpdate();
			}
		}

		updateStmt.close();
		insertStmt.close();
		checkStmt.close();
	}

	// load list
	public void loadListProducts() throws SQLException {
		products.clear();

		String sql = "select id, name, category, price, quantity, description from product";

		Statement selectStmt = con.createStatement();
		ResultSet result = selectStmt.executeQuery(sql);

		while (result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			String category = result.getString("category");
			double price = result.getDouble("price");
			int quantity = result.getInt("quantity");
			String description = result.getString("description");

			Product product = new Product(id, name, category, price, quantity, description);
			products.add(product);
		}

		result.close();
		selectStmt.close();
	}

	public void loadListCustomers() throws SQLException {
		customers.clear();

		String sql = "select id, name, birthYear, sex, phoneNumber, address, moreInfo from customer";

		Statement selectStmt = con.createStatement();
		ResultSet result = selectStmt.executeQuery(sql);

		while (result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			int birthYear = result.getInt("birthYear");
			boolean sex = result.getBoolean("sex");
			String phoneNumber = result.getString("phoneNumber");
			String address = result.getString("address");
			String moreInfo = result.getString("moreInfo");

			Customer customer = new Customer(id, name, birthYear, sex, phoneNumber, address, moreInfo);
			customers.add(customer);
		}

		result.close();
		selectStmt.close();
	}

	public void loadListOrders() throws SQLException {
		orders.clear();

		String sql = "select id, idCustomer, totalPayment, orderDate from orders";
		Statement selectStmt = con.createStatement();
		ResultSet resultSet = selectStmt.executeQuery(sql);

		loadListCustomers();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int idCustomer = resultSet.getInt("idCustomer");
			double totalPayment = resultSet.getDouble("totalPayment");
			java.util.Date orderDate = (java.util.Date)resultSet.getDate("orderDate");

			Customer customer = new Customer();
			
			for (Customer c : customers) {
				if (idCustomer == c.getId()) {
					customer = c;
				}
			}
			Order order = new Order(id, customer, totalPayment, orderDate);
			orders.add(order);
		}

		resultSet.close();
		selectStmt.close();

	}
	
	public void loadListDetailOrder() throws SQLException {
		detailOrders.clear();

		String sql = "select id, idOrder, idProduct, quantity, payment from DetailOrder";
		Statement selectStmt = con.createStatement();
		ResultSet resultSet = selectStmt.executeQuery(sql);

		loadListProducts();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int idOrder = resultSet.getInt("idOrder");
			int idProduct = resultSet.getInt("idProduct");
			int quantity = resultSet.getInt("quantity");
			double payment = resultSet.getDouble("payment");
			
			Product product = new Product();
			for (Product p : products) {
				if (idProduct == p.getId()) {
					product = p;
				}
			}

			DetailOrder detail = new DetailOrder(id, idOrder, product, quantity, payment);
			detailOrders.add(detail);
		}

		resultSet.close();
		selectStmt.close();

	}
	
	public void loadListAccount() throws SQLException {
		accounts.clear();

		String sql = "select username, userpassword, role from Account";
		Statement selectStmt = con.createStatement();
		ResultSet resultSet = selectStmt.executeQuery(sql);

		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String password = resultSet.getString("userpassword");
			boolean role = resultSet.getBoolean("role");
			
			accounts.add(new Account(username, password, role));
		}

		resultSet.close();
		selectStmt.close();

	}
	
	// Order
	public void insertOrder(Order order) throws SQLException {
		if (detailOrders == null) {
			return;
		}
		String insertDetailSql = "insert into DetailOrder (IdOrder, IdProduct, Quantity, Payment) values (?,?,?,?)";
		String insertOrderSql = "insert into Orders (IdCustomer, TotalPayment, OrderDate) values (?,?,?)";

		PreparedStatement insertDetailStmt = con.prepareStatement(insertDetailSql);
		PreparedStatement insertOrderStmt = con.prepareStatement(insertOrderSql);

		int idCustomer = order.getCustomer().getId();
		double totalPayment = order.getTotalPayment();
		Date orderDate = (Date) order.getOrderDate();

		insertOrderStmt.setInt(1, idCustomer);
		insertOrderStmt.setDouble(2, totalPayment);
		insertOrderStmt.setDate(3, orderDate);

		insertOrderStmt.executeUpdate();

		String selectIdOrderSql = "select top 1 id from orders order by id desc";
		Statement selectIdOrderStmt = con.createStatement();
		ResultSet result = selectIdOrderStmt.executeQuery(selectIdOrderSql);
		result.next();
		int idOrder = result.getInt(1);

		for (DetailOrder detail : detailOrders) {
			int idProduct = detail.getProduct().getId();
			int quantity = detail.getQuantityOrder();
			double payment = detail.getPayment();

			insertDetailStmt.setInt(1, idOrder);
			insertDetailStmt.setInt(2, idProduct);
			insertDetailStmt.setInt(3, quantity);
			insertDetailStmt.setDouble(4, payment);

			insertDetailStmt.executeUpdate();
		}

		selectIdOrderStmt.close();
		insertOrderStmt.close();
		insertDetailStmt.close();

	}

	// Product
	public void addProduct(Product product) {
		products.add(product);
	}

	public void updateProduct(Product product) {

		for (Product p : products) {
			if (p.getId() == product.getId()) {
				p.setName(product.getName());
				p.setCategory(product.getCategory());
				p.setPrice(product.getPrice());
				p.setQuantity(product.getQuantity());
				p.setDescription(product.getDescription());
			}
		}

	}

	public void removeProduct(Product product) {

		for (Product p : products) {
			if (p.getId() == product.getId()) {
				products.remove(product);
				removeProductElement(product.getId());
			}
		}

	}

	private void removeProductElement(int id) {
		String removeSql = "delete product where id=?";

		try {
			PreparedStatement removeStmt = con.prepareStatement(removeSql);
			removeStmt.setInt(1, id);
			removeStmt.executeUpdate();
			removeStmt.close();
		} catch (SQLException e) {
			System.out.println("Can't remove");
			e.printStackTrace();
		}

	}

	// Customer
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void updateCustomer(Customer customer) {

		for (Customer c : customers) {
			if (c.getId() == customer.getId()) {
				c.setName(customer.getName());
				c.setBirthYear(customer.getBirthYear());
				c.setSex(customer.getSex());
				c.setPhoneNumber(customer.getPhoneNumber());
				c.setAddress(customer.getAddress());
				c.setMoreInfo(customer.getMoreInfo());
			}
		}

	}

	public void removeCustomer(Customer customer) {

		for (Customer c : customers) {
			if (c.getId() == customer.getId()) {
				customers.remove(customer);
				removeCustomerElement(customer.getId());
			}
		}

	}

	private void removeCustomerElement(int id) {
		String removeSql = "delete customer where id=?";

		try {
			PreparedStatement removeStmt = con.prepareStatement(removeSql);
			removeStmt.setInt(1, id);
			removeStmt.executeUpdate();
			removeStmt.close();
		} catch (SQLException e) {
			System.out.println("Can't remove");
			e.printStackTrace();
		}

	}

	public void loadThongSoCustomer(CustomerFormEvent customer, CustomerLblEvent lblEv) throws SQLException {
		int id = customer.getId();
		int productCount = 0;
		int orderCount = 0;
		double total = 0;

		// select product count
		String selectIdOrder = "SELECT Id FROM Orders WHERE IdCustomer = ?";
		String selectQuantity = "SELECT Quantity FROM dbo.DetailOrder, dbo.Orders WHERE Orders.Id = DetailOrder.IdOrder AND IdOrder = ?";

		String selectOrderCount = "SELECT COUNT(*) as count FROM Orders WHERE IdCustomer = ?";
		String selectTotalPayment = "select totalPayment from Orders where IdCustomer = ?";

		PreparedStatement selectIdOrderStmt = con.prepareStatement(selectIdOrder);
		PreparedStatement selectQuantityStmt = con.prepareStatement(selectQuantity);

		PreparedStatement orderCountStmt = con.prepareStatement(selectOrderCount);
		PreparedStatement totalStmt = con.prepareStatement(selectTotalPayment);

		selectIdOrderStmt.setInt(1, id);
		orderCountStmt.setInt(1, id);
		totalStmt.setInt(1, id);

		ResultSet idOderResult = selectIdOrderStmt.executeQuery();

		ResultSet orderResult = orderCountStmt.executeQuery();
		ResultSet totalResult = totalStmt.executeQuery();

		while (idOderResult.next()) {
			int idOrder = idOderResult.getInt(1);

			selectQuantityStmt.setInt(1, idOrder);
			ResultSet quantityOrderResult = selectQuantityStmt.executeQuery();

			while (quantityOrderResult.next()) {
				productCount += quantityOrderResult.getInt(1);
			}
		}

		while (totalResult.next()) {
			total += totalResult.getDouble(1);
		}

		orderResult.next();
		orderCount = orderResult.getInt(1);

		// close
		idOderResult.close();
		totalResult.close();
		orderResult.close();

		totalStmt.close();
		orderCountStmt.close();
		selectIdOrderStmt.close();
		selectQuantityStmt.close();

		lblEv.setProductCount(productCount);
		lblEv.setOrderCount(orderCount);
		lblEv.setTotalPrice(total);

	}

	// Order
	public void addOrder(Order order) {

		for (DetailOrder detail : detailOrders) {
			detail.setIdOrder(order.getId());
			System.out.println(detail);
		}
		orders.add(order);
	}

	// Detail Order
	public void addDetailOrderItem(DetailOrder detailOrder) {
		for (Product p : products) {
			if (p.getId() == detailOrder.getProduct().getId()) {
				p.setQuantity(p.getQuantity() - detailOrder.getQuantityOrder());
			}
		}

		for (DetailOrder o : detailOrders) {
			if (o.getProduct().getId() == detailOrder.getProduct().getId()) {
				o.setQuantityOrder(o.getQuantityOrder() + detailOrder.getQuantityOrder());
				return;
			}
		}
		detailOrders.add(detailOrder);
	}

	public void removeDetailOrderItem(DetailOrder detailOrder) {
		detailOrders.remove(detailOrder);
		for (Product p : products) {
			if (p.getId() == detailOrder.getProduct().getId()) {
				p.setQuantity(p.getQuantity() + detailOrder.getQuantityOrder());
				return;
			}
		}

	}

	public void removeAllDetailOrder() {
		detailOrders.removeAll(detailOrders);
	}
	
	// account
	public void addAccount(Account account) throws SQLException {
		String insertSql = "insert into account (username, userpassword, role) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(insertSql);
		stmt.setString(1, account.getUsername());
		stmt.setString(2, account.getPassword());
		stmt.setBoolean(3, account.isRole());
		stmt.executeUpdate();
		stmt.close();
		
		accounts.add(account);
	}
	
	public void updateAccount(Account account) throws SQLException {
		String updateSql = "update account set userpassword = ?, role = ? where username=?";
		PreparedStatement updateStmt = con.prepareStatement(updateSql);
		updateStmt.setString(1, account.getPassword());
		updateStmt.setBoolean(2,  account.isRole());
		updateStmt.setString(3, account.getUsername());

		updateStmt.executeUpdate();
		updateStmt.close();
		
		for(Account a : accounts) {
			if(a.getUsername().equalsIgnoreCase(account.getUsername())) {
				a.setPassword(account.getPassword());
				a.setRole(account.isRole());
				return;
			}
		}
	}
	
	public void deleteAccount(Account account) throws SQLException {
		for(Account a : accounts) {
			if(a.getUsername().equalsIgnoreCase(account.getUsername())) {
				
				String deleteSql = "delete account where username=?";
				PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
				deleteStmt.setString(1, account.getUsername());

				deleteStmt.executeUpdate();
				deleteStmt.close();
				
				accounts.remove(a);
				return;
			}
		}
		
	}

	// statistics
	public Map<Integer, Double> getTotalPaymentOfMonth() throws SQLException {
		Map<Integer, Double> m = new HashMap<Integer, Double>();
		
		String sql = "SELECT MONTH(OrderDate) as month, SUM(TotalPayment) as total FROM dbo.Orders GROUP BY MONTH(OrderDate)";
		Statement selectStmt = con.createStatement();
		ResultSet rs = selectStmt.executeQuery(sql);
		while(rs.next()) {
			int month = rs.getInt("month");
			Double total = rs.getDouble("total");
			m.put(month, total);
			
		}
		rs.close();
		selectStmt.close();
		return m;
	}
	

	// Home
	public void loadThongSoHome(HomeLabelEvent ev) throws SQLException {
		String countProductSql = "select count(*) from product";
		String countCustomerSql = "select count(*) from customer";
		String countOrderSql = "select count(*) from orders";

		PreparedStatement countProductStmt = con.prepareStatement(countProductSql);
		PreparedStatement countCustomerStmt = con.prepareStatement(countCustomerSql);
		PreparedStatement countOrderStmt = con.prepareStatement(countOrderSql);

		ResultSet resultProduct = countProductStmt.executeQuery();
		ResultSet resultCustomer = countCustomerStmt.executeQuery();
		ResultSet resultOrder = countOrderStmt.executeQuery();

		while (resultProduct.next()) {
			int count = resultProduct.getInt(1);
			ev.getProductCountLbl().setText("" + count);
		}

		while (resultCustomer.next()) {
			int count = resultCustomer.getInt(1);
			ev.getCustomerCountLbl().setText("" + count);
		}

		while (resultOrder.next()) {
			int count = resultOrder.getInt(1);
			ev.getOrderCountLbl().setText("" + count);
		}

		countOrderStmt.close();
		countCustomerStmt.close();
		countProductStmt.close();
	}
}
