package gui.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import model.Customer;

public class CustomerTablePanel extends JPanel {

	private JTable table;
	private CustomerTableModel tableModel;

	private CustomerTableListener tableListener;
	
	private final TableRowSorter<TableModel> sorter;

	public CustomerTablePanel() {
		setBounds(355, 11, 315, 327);
		setLayout(new BorderLayout(0, 0));

		tableModel = new CustomerTableModel();

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		List<Customer> list = new LinkedList<Customer>();
		tableModel.setData(list);

		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());

				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON1) {
					int id = (int)table.getValueAt(row, 0);
					Customer cus = tableModel.getCustomerAt(id);
					CustomerFormEvent cusFormEvent = new CustomerFormEvent(this, cus.getId(), cus.getName(),
							cus.getBirthYear(), cus.getSex(), cus.getPhoneNumber(), cus.getAddress(),
							cus.getMoreInfo());
					tableListener.tableEventOccurred(cusFormEvent);

				}
			}
		});

		table.setRowHeight(30);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(35);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(120);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(150);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(355, 11, 315, 327);
		add(scroll, BorderLayout.CENTER);
	}

	public void setCustomerData(List<Customer> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setTableListener(CustomerTableListener tableListener) {
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
