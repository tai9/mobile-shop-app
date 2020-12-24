package gui.order;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import model.Customer;
import model.DetailOrder;
import model.Order;
import model.Product;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.customer.CustomerFormEvent;
import gui.product.ProductFormEvent;
import gui.product.ProductTablePanel;
import gui.product.ProductTableListener;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

public class OrderPanel extends JPanel {
	private JPanel orderPanel;

	private JTextField totalField;
	private JLabel customerNameLbl;

	private JButton btnInsertOrder;
	private JButton btnShowAllOrders;
	private JButton btnRemoveProduct;

	private JSpinner spinnerDate;
	private JButton btnAdd;

	private ProductTablePanel productTablePanel;
	private DetailOrderTablePanel detailOrderTablePanel;

	private Controller controller;

	private ProductFormEvent productFormEvent;
	private DetailOrderFormEvent detailOrderFormEvent;

	private JDialog ordersDialog;
	private OrderReportPanel orderReport;;

	private double total = 0;
	private JTextField idTxt;

	private int idCus = 0;
	private Customer customerPayment;
	
	private JButton refreshBtn;

	public OrderPanel() {

		customerPayment = new Customer();

		setPreferredSize(new Dimension(700, 600));
		setBackground(MaterialColors.BLUE_GRAY_400);
		setLayout(null);

		controller = new Controller();
		controller.connect();
		controller.loadListProducts();
		controller.loadListCustomers();

		orderPanel = new JPanel();
		orderPanel.setBackground(Color.WHITE);
		orderPanel.setBounds(10, 11, 680, 548);
		add(orderPanel);
		orderPanel.setLayout(null);

		detailOrderTablePanel = new DetailOrderTablePanel();
		detailOrderTablePanel.setBackground(Color.LIGHT_GRAY);
		detailOrderTablePanel.setBounds(10, 297, 660, 176);
		orderPanel.add(detailOrderTablePanel);
		detailOrderTablePanel.setData(controller.getDetailOrder());
		detailOrderTablePanel.setTableListener(new DetailOrderListener() {
			public void tableEventOccurred(DetailOrderFormEvent ev) {
				detailOrderFormEvent = ev;
			}
		});

		JLabel totalLbl = new JLabel("Total:");
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLbl.setForeground(Color.BLACK);
		totalLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalLbl.setBackground(Color.WHITE);
		totalLbl.setBounds(522, 497, 56, 25);
		orderPanel.add(totalLbl);
		
		SpinnerDateModel model = new SpinnerDateModel();
		spinnerDate = new JSpinner(model);
		spinnerDate.setBounds(460, 48, 170, 30);
		orderPanel.add(spinnerDate);

		btnInsertOrder = new JButton("Insert Order");
		btnInsertOrder.setForeground(Color.WHITE);
		btnInsertOrder.setBackground(MaterialColors.COSMO_GREEN);
		btnInsertOrder.addMouseListener(MaterialUIMovement.getMovement(btnInsertOrder, MaterialColors.GREEN_300));
		btnInsertOrder.setBounds(10, 495, 150, 35);
		orderPanel.add(btnInsertOrder);
		btnInsertOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (total <= 0) {
					JOptionPane.showMessageDialog(detailOrderTablePanel, "Can't insert order!", "",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (customerPayment.getId() <= 0) {
					JOptionPane.showMessageDialog(detailOrderTablePanel, "Id Customer Invalid!", "",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					Date orderDate = new Date((model.getDate().getYear()), (model.getDate().getMonth()), model.getDate().getDate());
					OrderFormEvent oev = new OrderFormEvent(this, customerPayment, total, orderDate);
					controller.addOrder(oev);

					Order order = new Order(oev.getCustomer(), oev.getTotalPayment(), oev.getOrderDate());
					controller.insertOrder(order);
					controller.removeAllDetailOrder();
					controller.saveListProducts();
					detailOrderTablePanel.refresh();

					JOptionPane.showMessageDialog(orderPanel, "Insert order success", "",
							JOptionPane.INFORMATION_MESSAGE);

					idTxt.setText("");
					customerNameLbl.setText("");
					total = 0;
					totalField.setText(total + "");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(orderPanel, e2.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btnShowAllOrders = new JButton("Show All Orders");
		btnShowAllOrders.setForeground(Color.WHITE);
		btnShowAllOrders.setBackground(MaterialColors.AMBER_400);
		btnShowAllOrders.addMouseListener(MaterialUIMovement.getMovement(btnShowAllOrders, MaterialColors.AMBER_200));
		btnShowAllOrders.setBounds(180, 495, 150, 35);
		orderPanel.add(btnShowAllOrders);

		
		btnShowAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderReport = new OrderReportPanel();
				ordersDialog = new JDialog();
				ordersDialog.setLocation(450, 100);
				ordersDialog.setSize(500, 530);
				ordersDialog.show(true);
				ordersDialog.setTitle("Report");
				ordersDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

				ordersDialog.getContentPane().add(orderReport);
			}
		});

		btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.setForeground(Color.WHITE);
		btnRemoveProduct.setBackground(MaterialColors.RED_400);
		btnRemoveProduct.addMouseListener(MaterialUIMovement.getMovement(btnRemoveProduct, MaterialColors.RED_200));
		btnRemoveProduct.setBounds(350, 495, 150, 35);
		orderPanel.add(btnRemoveProduct);
		btnRemoveProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					total -= detailOrderFormEvent.getPayment();

					if (total < 0) {
						JOptionPane.showMessageDialog(detailOrderTablePanel, "Can't remove order item", "",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					controller.removeDetailOrderItem(detailOrderFormEvent);
					totalField.setText(total + "");
					detailOrderTablePanel.refresh();
					productTablePanel.refresh();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(detailOrderTablePanel, "Can't remove order item", "",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		totalField = new JTextField();
		totalField.setText("0.0");
		totalField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalField.setBorder(new EmptyBorder(10, 3, 10, 0));
		totalField.setBackground(new Color(238, 238, 238));
		totalField.setBounds(580, 490, 90, 40);
		totalField.setEditable(false);
		orderPanel.add(totalField);

		btnAdd = new JButton("\u00BB ");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(300, 256, 80, 30);
		orderPanel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String value = JOptionPane.showInputDialog(productTablePanel, "Quantity order?");
				if (value == null)
					return;

				int quantity = 0;
				try {
					quantity = Integer.parseInt(value);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(productTablePanel, "Invalid Value");
					return;
				}

				if (quantity > productFormEvent.getQuantity()) {
					JOptionPane.showMessageDialog(productTablePanel, "So luong dat > so luong co");
					return;
				}

				Product product = productFormEvent.getProduct();
				double payment = quantity * productFormEvent.getPrice();

				DetailOrderFormEvent dev = new DetailOrderFormEvent(this, product, quantity, payment);
				controller.addDetailOrder(dev);
				detailOrderTablePanel.refresh();
				productTablePanel.refresh();
				total = Double.parseDouble(totalField.getText()) + dev.getPayment();
				totalField.setText(total + "");
				
			}
		});

		productTablePanel = new ProductTablePanel();
		productTablePanel.setBounds(10, 99, 660, 146); // 10, 11, 660, 131
		orderPanel.add(productTablePanel);
		productTablePanel.setData(controller.getProducts());
		productTablePanel.setTableListener(new ProductTableListener() {
			@Override
			public void tableEventOccurred(ProductFormEvent ev) {
				productFormEvent = ev;
			}
		});

		customerNameLbl = new JLabel();
		customerNameLbl.setBorder(new LineBorder(MaterialColors.BLUE_400, 1, true));
		customerNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		customerNameLbl.setBounds(269, 48, 170, 30);
		orderPanel.add(customerNameLbl);

		JLabel cusNameLbl = new JLabel("Name:");
		cusNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cusNameLbl.setBounds(230, 50, 41, 30);
		orderPanel.add(cusNameLbl);

		JLabel cusIdLbl = new JLabel("Customer ID:");
		cusIdLbl.setBounds(36, 50, 114, 25);
		orderPanel.add(cusIdLbl);

		idTxt = new JTextField();
		idTxt.setBounds(150, 48, 50, 30);
		orderPanel.add(idTxt);
		idTxt.setColumns(10);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(36, 10, 120, 30);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setBackground(MaterialColors.BLUE_400);
		refreshBtn.addMouseListener(MaterialUIMovement.getMovement(refreshBtn, MaterialColors.LIGHT_BLUE_A200));
		orderPanel.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadListCustomers();
				controller.loadListProducts();
				productTablePanel.refresh();
				idTxt.setText("");
				cusNameLbl.setText("");
				JOptionPane.showMessageDialog(orderPanel, "Refresh!");
			}
		});

		idTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					idCus = Integer.parseInt(idTxt.getText());
					for (Customer c : controller.getCustomers()) {
						if (idCus == c.getId()) {
							customerNameLbl.setText(c.getName());
							customerPayment = c;
							return;
						}
					}
					JOptionPane.showMessageDialog(orderPanel, "ID not found!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(orderPanel, e2.getMessage());
				}
			}
		});

	}
}
