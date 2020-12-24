package gui.order;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Order;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderTablePanel extends JPanel {

	private JTable table;
	private OrderTableModel model;
	
	private TableListener tableListener;
	
	public OrderTablePanel() {
		table = new JTable();
		model = new OrderTableModel();
		table.setModel(model);
		JScrollPane sc = new JScrollPane(table);
		add(sc);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					Order o = model.getValueAt(row);
					OrderEvent oe = new OrderEvent(this, o.getId(), o.getCustomer(), o.getTotalPayment(), o.getOrderDate());
					tableListener.TableEventOccurred(oe);
				}
				
			}
		});
		
	}
	
	public void setData(List<Order> db) {
		model.setData(db);
	}
	
	public void refresh() {
		model.fireTableDataChanged();
	}

	public void setTableListener(TableListener tableListener) {
		this.tableListener = tableListener;
	}

}
