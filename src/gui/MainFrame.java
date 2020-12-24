package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import gui.account.AccountPanel;
import gui.customer.CustomerPanel;
import gui.login.LoginFrame;
import gui.login.SignOutListener;
import gui.order.OrderPanel;
import gui.product.ProductPanel;
import gui.statistics.StatisticsPanel;
import mdlaf.utils.MaterialColors;
import model.Account;

public class MainFrame extends JFrame {

	private SideBar sideBarPanel;
	private JPanel contentPanel;

	private CardLayout cardLayout;
	private HomePanel homePanel;
	private ProductPanel productPanel;
	private CustomerPanel customerPanel;
	private OrderPanel orderPanel;
	private StatisticsPanel statisticsPanel;
	private AccountPanel staffPanel;

	private Controller controller;

	public MainFrame(Account account) {
		controller = new Controller();
		controller.connect();

		cardLayout = new CardLayout();

		contentPanel = new JPanel();
		sideBarPanel = new SideBar(account.isRole());

		homePanel = new HomePanel(account.getUsername());
		productPanel = new ProductPanel();
		customerPanel = new CustomerPanel();
		orderPanel = new OrderPanel();
		statisticsPanel = new StatisticsPanel();
		staffPanel = new AccountPanel();

		contentPanel.setLayout(cardLayout);
		contentPanel.add(homePanel, "home");
		contentPanel.add(productPanel, "product");
		contentPanel.add(customerPanel, "customer");
		contentPanel.add(orderPanel, "order");
		contentPanel.add(statisticsPanel, "statistics");
		contentPanel.add(staffPanel, "account");

		cardLayout.show(contentPanel, "home");
		homePanel.loadTs();
		sideBarPanel.setPanelListener(new PanelListener() {
			public void showPanelEventOccurred(String key) {
				if (key.equals("home"))
					homePanel.loadTs();
				cardLayout.show(contentPanel, key);
			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					controller.disconnect();
					dispose();
					System.gc();
				}
			}
		});

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(sideBarPanel, BorderLayout.WEST);

		// switch login frame and main frame
		homePanel.signOutListener(new SignOutListener() {
			public void signout() {
				controller.disconnect();
				dispose();
				System.gc();
				new LoginFrame();
			}
		});

		setLocation(300, 100);
		setSize(950, 600);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
