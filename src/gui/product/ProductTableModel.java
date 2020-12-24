package gui.product;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Product;

public class ProductTableModel extends AbstractTableModel {
	private List<Product> db;

	private String[] colNames = { "ID", "Name", "Category", "Price", "Quantity", "Description" };

	public ProductTableModel() {
	}

	public void setData(List<Product> db) {
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
		Product product = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return product.getId();
		case 1:
			return product.getName();
		case 2:
			return product.getCategory();
		case 3:
			return product.getPrice();
		case 4:
			return product.getQuantity();
		case 5:
			return product.getDescription();
		}

		return null;
	}

	public Product getProductAt(int id){
		for(Product p : db) {
			if(p.getId() == id)
				return p;
		}
		return null;
	}
}
