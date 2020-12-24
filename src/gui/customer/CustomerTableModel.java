package gui.customer;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class CustomerTableModel extends AbstractTableModel {
	private List<Customer> db;

	private String[] colNames = { "ID", "Name", "Birth year", "Sex", "Phone", "Adress", "More Infor" };

	public CustomerTableModel() {
		db = new LinkedList<Customer>();
	}

	public void setData(List<Customer> db) {
		this.db = db;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer customer = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return customer.getId();
		case 1:
			return customer.getName();
		case 2:
			return customer.getBirthYear();
		case 3:
			return customer.getSex();
		case 4:
			return customer.getPhoneNumber();
		case 5:
			return customer.getAddress();
		case 6:
			return customer.getMoreInfo();
		}

		return null;
	}

	public Customer getCustomerAt(int id){
		for(Customer c : db) {
			if(c.getId() == id)
				return c;
		}
		return null;
	}
}
