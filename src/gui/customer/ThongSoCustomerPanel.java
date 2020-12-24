package gui.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;

public class ThongSoCustomerPanel extends JPanel {

	private JPanel productCountPanel;
	private JLabel productLbl;
	private JLabel productCountLbl;
	private JPanel totalPricePanel;
	private JLabel totalLbl;
	private JLabel totalPriceLbl;
	private JPanel orderCountPanel;
	private JLabel orderLbl;
	private JLabel orderCountLbl;

	private CustomerLblEvent lblEvent;

	private Controller controller;

	public ThongSoCustomerPanel() {
		setPreferredSize(new Dimension(680, 140));
		setLayout(null);

		// product count
		productCountPanel = new JPanel();
		productCountPanel.setBackground(new Color(66, 165, 245));
		productCountPanel.setBounds(35, 22, 180, 96);
		add(productCountPanel);
		productCountPanel.setLayout(new BorderLayout(0, 0));

		productLbl = new JLabel("Products Count:");
		productLbl.setForeground(Color.WHITE);
		productLbl.setBorder(new EmptyBorder(10, 10, 0, 0));
		productLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		productCountPanel.add(productLbl, BorderLayout.NORTH);

		productCountLbl = new JLabel("###");
		productCountLbl.setForeground(Color.WHITE);
		productCountLbl.setFont(new Font("Tahoma", Font.BOLD, 21));
		productCountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		productCountPanel.add(productCountLbl, BorderLayout.CENTER);

		// order count
		orderCountPanel = new JPanel();
		orderCountPanel.setBackground(new Color(247, 93, 129));
		orderCountPanel.setBounds(250, 22, 180, 96);
		add(orderCountPanel);
		orderCountPanel.setLayout(new BorderLayout(0, 0));

		orderCountLbl = new JLabel("###");
		orderCountLbl.setForeground(Color.WHITE);
		orderCountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		orderCountLbl.setFont(new Font("Tahoma", Font.BOLD, 21));
		orderCountPanel.add(orderCountLbl, BorderLayout.CENTER);

		orderLbl = new JLabel("Orders Count:");
		orderLbl.setForeground(Color.WHITE);
		orderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		orderLbl.setBorder(new EmptyBorder(10, 10, 0, 0));
		orderCountPanel.add(orderLbl, BorderLayout.NORTH);

		// total price
		totalPricePanel = new JPanel();
		totalPricePanel.setBackground(new Color(125, 212, 32));
		totalPricePanel.setBounds(465, 22, 180, 96);
		add(totalPricePanel);
		totalPricePanel.setLayout(new BorderLayout(0, 0));

		totalLbl = new JLabel("Total price:");
		totalLbl.setForeground(Color.WHITE);
		totalLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalLbl.setBorder(new EmptyBorder(10, 10, 0, 0));
		totalPricePanel.add(totalLbl, BorderLayout.NORTH);

		totalPriceLbl = new JLabel("###");
		totalPriceLbl.setForeground(Color.WHITE);
		totalPriceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceLbl.setFont(new Font("Tahoma", Font.BOLD, 21));
		totalPricePanel.add(totalPriceLbl, BorderLayout.CENTER);

		lblEvent = new CustomerLblEvent(this, 0, 0, 0);

		controller = new Controller();
		controller.connect();

	}

	public void loadThongSo(CustomerFormEvent ev) {

		controller.loadThongSoCustomer(ev, lblEvent);
		productCountLbl.setText(lblEvent.getProductCount() + "");
		orderCountLbl.setText(lblEvent.getOrderCount() + "");
		totalPriceLbl.setText(lblEvent.getTotalPrice() + "");
	}

	public void defaultThongSo() {
		productCountLbl.setText("###");
		orderCountLbl.setText("###");
		totalPriceLbl.setText("###");
	}

}
