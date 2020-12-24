package gui.account;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Account;

public class AccountTableModel extends AbstractTableModel{
	private List<Account> db;

	private String[] colNames = { "Username", "Password", "Role" };

	public AccountTableModel() {
		db = new ArrayList<Account>();
	}

	public void setData(List<Account> db) {
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
		Account account = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return account.getUsername();
		case 1:
			return account.getPassword();
		case 2:
			return account.isRole();
		}

		return null;
	}

	public Account getAccountAt(String username){
		for(Account a : db) {
			if(a.getUsername().equalsIgnoreCase(username))
				return a;
		}
		return null;
	}
}
