package gui.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.Utils;
import model.Product;

public class ProductTablePanel extends JPanel {
	private JTable table;
	private ProductTableModel tableModel;

	private ProductTableListener tableListener;

	private final TableRowSorter<TableModel> sorter;

	public ProductTablePanel() {
		setBounds(10, 316, 680, 245);
		setLayout(new BorderLayout(0, 0));

		tableModel = new ProductTableModel();

		table = new JTable();
		table.setModel(tableModel);

		// filter
		JPanel filterPanel = new JPanel();
		filterPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
		JLabel filterLbl = new JLabel("Filter:");
		JTextField filTextField = new JTextField(10);
		JComboBox<String> cateCbx = new JComboBox<String>(Utils.setProductCategoryModel());
		filterPanel.add(filterLbl);
		filterPanel.add(filTextField);
		filterPanel.add(cateCbx);
		add(filterPanel, BorderLayout.NORTH);

		filTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter(filTextField.getText());
			}
		});
		
		cateCbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String)cateCbx.getSelectedItem();
				if(selectedValue.equalsIgnoreCase("other"))
					filter("");
				else
					filter(selectedValue);
				
			}
		});

		// set virtual data for model
		List<Product> list = new LinkedList<Product>();
		tableModel.setData(list);

		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());

				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON1) {
					int id = (int) table.getValueAt(row, 0); // get id value
					Product p = tableModel.getProductAt(id);
					ProductFormEvent ev = new ProductFormEvent(this, p.getId(), p.getName(), p.getCategory(),
							p.getPrice(), p.getQuantity(), p.getDescription());
					tableListener.tableEventOccurred(ev);

				}
			}
		});

		table.setRowHeight(30);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(35);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(50);
		columnModel.getColumn(5).setPreferredWidth(100);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 316, 680, 245);
		add(scroll, BorderLayout.CENTER);
	}

	public void setData(List<Product> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setTableListener(ProductTableListener tableListner) {
		this.tableListener = tableListner;
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
