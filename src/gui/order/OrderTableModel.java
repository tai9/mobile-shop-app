package gui.order;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Order;

public class OrderTableModel extends AbstractTableModel {

	private List<Order> db;

	private String[] colNames = { "ID", "Date", "Customer" };

	public OrderTableModel() {
		db = new LinkedList<Order>();
	}

	public void setData(List<Order> db) {
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
		Order order = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return order.getId();
		case 1:
			return order.getOrderDate();
		case 2:
			return order.getCustomer().getId();
		}

		return null;
	}

	public Order getValueAt(int row) {
		return db.get(row);
	}
}
