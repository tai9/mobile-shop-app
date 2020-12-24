package gui.customer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import mdlaf.utils.MaterialColors;

import controller.Controller;


public class CustomerPanel extends JPanel {

	private JPanel firstSectionPanel;

	private CustomerFormPanel customerFormPanel;
	private CustomerTablePanel customerTablePanel;
	private FunctionalCustomerPanel funcCustomer;
	private ThongSoCustomerPanel thongSoCustomerPanel;

	private Controller controller;

	public CustomerPanel() {
		setPreferredSize(new Dimension(700, 600));
		setBackground(MaterialColors.BLUE_GRAY_400);
		setLayout(null);

		controller = new Controller();
		controller.connect();
		controller.loadListCustomers();

		// set up the first section
		firstSectionPanel = new JPanel();
		firstSectionPanel.setBackground(Color.WHITE);
		firstSectionPanel.setBounds(10, 11, 680, 395);
		add(firstSectionPanel);
		firstSectionPanel.setLayout(null);

		customerFormPanel = new CustomerFormPanel();
		customerFormPanel.setBounds(10, 11, 332, 327);
		firstSectionPanel.add(customerFormPanel);

		customerTablePanel = new CustomerTablePanel();
		customerTablePanel.setBounds(355, 11, 315, 327);
		firstSectionPanel.add(customerTablePanel);
		customerTablePanel.setCustomerData(controller.getCustomers());

		funcCustomer = new FunctionalCustomerPanel();
		firstSectionPanel.add(funcCustomer);

		// set up the second section
		thongSoCustomerPanel = new ThongSoCustomerPanel();
		thongSoCustomerPanel.setBounds(10, 417, 680, 140);
		add(thongSoCustomerPanel);

		customerTablePanel.setTableListener(new CustomerTableListener() {
			public void tableEventOccurred(CustomerFormEvent ev) {
				customerFormPanel.setValueForm(ev);
				thongSoCustomerPanel.loadThongSo(ev);
			}
		});

		funcCustomer.setFunctionListener(new FunctionListener() {
			public void addEventOccurred(CustomerFormEvent ev) {
				controller.addCustomer(ev);
				controller.saveListCustomers();
				controller.loadListCustomers();
				customerTablePanel.refresh();
			}

			public void updateEventOccurred(CustomerFormEvent ev) {
				controller.updateCustomer(ev);
				controller.saveListCustomers();
				controller.loadListCustomers();
				customerTablePanel.refresh();
			}

			public void removeEventOccurred(CustomerFormEvent ev) {
				controller.removeCustomer(ev);
				controller.loadListCustomers();
				customerTablePanel.refresh();
			}

			public void clearEventOccurred(CustomerFormEvent ev) {
				customerFormPanel.setValueForm(ev);
				thongSoCustomerPanel.defaultThongSo();
			}
		}, customerFormPanel);

	}
}
