package gui.order;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.DetailOrder;

public class OrderDetailTableModel extends AbstractTableModel{
	private List<DetailOrder> db;

	private String[] colNames = {"ID","Product", "Price", "Quantity Order", "Payment"};
	
	public OrderDetailTableModel() {
		db= new LinkedList<DetailOrder>();
	}
	
	public void setData(List<DetailOrder> db) {
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
		DetailOrder order = db.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return order.getProduct().getId();
		case 1:
			return order.getProduct().getName();
		case 2:
			return order.getProduct().getPrice();
		case 3:
			return order.getQuantityOrder();
		case 4:
			return order.getPayment();
		}
		
		return null;
	}

	public DetailOrder getValueAt(int row) {
		return db.get(row);
	}
}
