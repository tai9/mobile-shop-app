package gui.account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.customer.CustomerFormEvent;
import gui.customer.CustomerTableListener;
import gui.customer.CustomerTableModel;
import model.Account;
import model.Customer;

public class AccountTablePanel extends JPanel {
	private JTable table;
	private AccountTableModel tableModel;

	private AccountTableListener tableListener;
	
	private final TableRowSorter<TableModel> sorter;

	public AccountTablePanel() {
		setLayout(new BorderLayout(0, 0));

		tableModel = new AccountTableModel();

		table = new JTable();
		table.setModel(tableModel);

		// filter
		JPanel filterPanel = new JPanel();
		filterPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
		JLabel filterLbl = new JLabel("Filter:");
		JTextField filTextField = new JTextField(10);
		filterPanel.add(filterLbl);
		filterPanel.add(filTextField);
		add(filterPanel, BorderLayout.NORTH);

		filTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter(filTextField.getText());
			}
		});

		// set virtual data for model
		List<Account> list = new ArrayList<Account>();
		tableModel.setData(list);

		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());

				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON1) {
					String username = String.valueOf(table.getValueAt(row, 0));
					Account acc = tableModel.getAccountAt(username);
					AccountEvent ae = new AccountEvent(this, acc.getUsername(), acc.getPassword(), acc.isRole());
					tableListener.tableEventOccurred(ae);

				}
			}
		});

		table.setRowHeight(30);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 316, 680, 245);
		add(scroll, BorderLayout.CENTER);
	}

	public void setData(List<Account> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setTableListener(AccountTableListener tableListener) {
		this.tableListener = tableListener;
	}

	public void filter(String text) {
		if (text.length() == 0) {
			sorter.setRowFilter(null);
		} else {
			try {
				sorter.setRowFilter(RowFilter.regexFilter(text));
			} catch (PatternSyntaxException pse) {
				System.out.println("Bad regex pattern");
			}
		}
	}
}
