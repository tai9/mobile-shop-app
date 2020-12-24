package gui.order;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import model.DetailOrder;

public class DetailOrderTablePanel extends JPanel {
	private JTable table;
	private OrderDetailTableModel tableModel;

	private DetailOrderListener orderListener;

	public DetailOrderTablePanel() {
		setBounds(10, 342, 660, 142);
		setLayout(new BorderLayout(0, 0));

		tableModel = new OrderDetailTableModel();

		table = new JTable();
//		table.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
		table.setModel(tableModel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());

				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON1) {
					int rowIndex = table.getSelectedRow();
					DetailOrder detail = tableModel.getValueAt(rowIndex);
					DetailOrderFormEvent detailEv = new DetailOrderFormEvent(this, detail);
					orderListener.tableEventOccurred(detailEv);

				}
			}
		});

		table.setRowHeight(30);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(45);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(70);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(150);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(355, 11, 315, 327);
		add(scroll, BorderLayout.CENTER);
	}

	public void setData(List<DetailOrder> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setTableListener(DetailOrderListener orderListener) {
		this.orderListener = orderListener;
	}

}
