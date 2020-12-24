package gui.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import model.Customer;
import model.DetailOrder;
import model.Order;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

public class OrderReportPanel extends JPanel {
	private Controller controller;
	private JButton btn;
	private OrderTablePanel orderTablePanel;

	private OrderEvent orderEvent;

	private List<Order> listOrder;
	private List<DetailOrder> listDetailOrder;

	public OrderReportPanel() {
		setPreferredSize(new Dimension(500,500));
		
		btn = new JButton("Print Selected Order");
		btn.setForeground(Color.WHITE);
		btn.setBackground(MaterialColors.LIGHT_BLUE_400);
		btn.addMouseListener(MaterialUIMovement.getMovement(btn, MaterialColors.LIGHT_BLUE_200));
		add(btn);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowReport(new Order(orderEvent.getId(), orderEvent.getCustomer(),orderEvent.getTotalPayment(), orderEvent.getOrderDate()));
			}
		});

		orderTablePanel = new OrderTablePanel();
		controller = new Controller();

		controller.connect();
		controller.loadListOrders();

		listOrder = controller.getOrders();
		orderTablePanel.setData(listOrder);

		orderTablePanel.setTableListener(new TableListener() {
			public void TableEventOccurred(OrderEvent oe) {
				orderEvent = oe;
			}
		});

		add(orderTablePanel);

	}

	private void ShowReport(Order order) {
		JDialog dig = new JDialog();
		controller.loadListDetaiOrder();
		listDetailOrder = controller.getDetailOrder();
		
		dig.show(true);
		dig.setBounds(300, 80, 900, 700);
		
		// set data for report
		try {

			List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
			
			for (DetailOrder d : listDetailOrder) {
				Map<String, Object> m = new HashMap<String, Object>();
				if (d.getIdOrder() == order.getId()) {
					m.put("cus", order.getCustomer().getName());
					m.put("id", d.getId());
					m.put("quantity", d.getQuantityOrder());
					m.put("payment", d.getPayment());
					m.put("productName", d.getProduct().getName());
					m.put("totalPayment", order.getTotalPayment());
					m.put("orderDate", order.getOrderDate());
					dataSource.add(m);
				}

			}

			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			String sourceName = "src/gui/order/Report.jrxml";

			JasperReport report = JasperCompileManager.compileReport(sourceName);
			JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);

			dig.add(new JRViewer(filledReport));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(dig, e.getMessage());
		}
	}
	
}
