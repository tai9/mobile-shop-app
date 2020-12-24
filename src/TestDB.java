import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import gui.HomeLabelEvent;
import gui.customer.CustomerFormEvent;
import gui.customer.CustomerLblEvent;
import model.Customer;
import model.Database;

public class TestDB {
	public static void main(String[] args) {
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			System.out.println("Unable to connect");
			e.printStackTrace();
		}
		
//		JSpinner spinnerDate = new JSpinner(new SpinnerDateModel());
//		System.out.println(spinnerDate.getValue());
		
//		db.addCustomer(new Customer("Tai",123 , false, "123", null, null));
//		try {
//			db.saveListCustomers();
//		} catch (SQLException e1) {
//			System.out.println("Can't save");
//			e1.printStackTrace();
//		}
		
		

//		JLabel productCountLbl = new JLabel("product");
//		JLabel customerCountLbl = new JLabel("customer");
//		JLabel orderCountLbl = new JLabel("order");
//
//		LabelHomeEvent lblHomeEvent = new LabelHomeEvent(args, productCountLbl, customerCountLbl, orderCountLbl);
//		try {
//			db.loadThongSo(lblHomeEvent);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(productCountLbl.getText());
//		System.out.println(customerCountLbl.getText());
//		System.out.println(orderCountLbl.getText());
		
//		try {
//			db.loadListCustomers();
//		} catch (SQLException e) {
//			System.out.println("Can't load list customer");
//			e.printStackTrace();
//		}
//		List<Customer> list = db.getCustomers();
//		for(Customer cus : list) {
//			System.out.println(cus);
//		}
		
//		try {
//			System.out.println(db.testLoad(7,0));
//		} catch (SQLException e1) {
//			System.out.println("Unable to load");
//			e1.printStackTrace();
//		}
		
		try {
			db.disconnect();
		} catch (Exception e) {
			System.out.println("Unable to disconnect");
			e.printStackTrace();
		}
	}
}
