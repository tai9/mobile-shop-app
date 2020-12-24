import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

import javax.sound.sampled.Port;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Controller;
import gui.product.ProductTableModel;
import gui.product.ProductTablePanel;

public class TestFilter extends JFrame {

	private JTable table;
//	private DefaultTableModel model;

	private ProductTableModel productTableModel;
//	private ProductTablePanel productTablePanel;
	
	private Controller controller;

	public TestFilter() {
		
		productTableModel = new ProductTableModel();
//		productTablePanel = new ProductTablePanel();
		controller = new Controller();
		
		controller.connect();
		controller.loadListProducts();
		productTableModel.setData(controller.getProducts());
		
		table = new JTable();
		table.setModel(productTableModel);
		
		setTitle("FilterTable Test");
//		Object rows[][] = { { "Adithya", "Content Developer", 25000 }, { "Jai", "SME", 30000 },
//				{ "Chaitanya", "Java Engineer", 45000 }, { "Ramesh", "Scala Developer", 40000 },
//				{ "Ravi", "SAP  Consultant", 70000 } };
//		Object columns[] = { "Name", "Designation", "Salary" };
//		model = new DefaultTableModel(rows, columns) {
//			public Class getColumnClass(int column) {
//				Class returnValue;
//				if ((column >= 0) && (column < getColumnCount())) {
//					returnValue = getValueAt(0, column).getClass();
//				} else {
//					returnValue = Object.class;
//				}
//				return returnValue;
//			}
//		};
//		table = new JTable(model);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productTableModel);
		table.setRowSorter(sorter);
		add(new JScrollPane(table), BorderLayout.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Filter");
		panel.add(label, BorderLayout.WEST);
		final JTextField filterText = new JTextField("");
		filterText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = filterText.getText();
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
		});
		panel.add(filterText, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = filterText.getText();
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
		});
		add(button, BorderLayout.SOUTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TestFilter();
	}
}
