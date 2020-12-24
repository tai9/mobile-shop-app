package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.SwingConstants;

import controller.Controller;

import java.awt.Font;
import javax.swing.ImageIcon;

public class SideBar extends JPanel implements MouseListener{

	private JPanel logoPanel;
	private JLabel logoLbl;

	private JPanel menu;
	private JPanel homeItem;
	private JPanel productItem;
	private JPanel customerItem;
	private JPanel orderItem;
	private JPanel statisticsItem;
	private JPanel accountItem;

	private PanelListener iPanelListener;
	

	public SideBar(boolean isAdmin) {

		// Default setting
		setLayout(new BorderLayout());
		setBackground(new Color(68, 130, 255));
		setPreferredSize(new Dimension(250, 600));
		
		// Logo
		logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.setPreferredSize(new Dimension(10, 120));
		logoPanel.setLayout(new BorderLayout(0, 0));
		
		logoLbl = new JLabel("Mobile Shop ^^");
		logoLbl.setPreferredSize(new Dimension(100, 14));
		logoLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/logo.png")));
		logoLbl.setForeground(Color.WHITE);
		logoLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		logoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		logoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		logoPanel.add(logoLbl, BorderLayout.CENTER);
		add(logoPanel, BorderLayout.NORTH);

		// create menu
		menu = new JPanel();
		menu.setOpaque(false);
		menu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		homeItem = new JPanel();
		productItem = new JPanel();
		customerItem = new JPanel();
		orderItem = new JPanel();
		statisticsItem = new JPanel();
		accountItem = new JPanel();
		
		add(menu, BorderLayout.CENTER);
		
		// HOME
		homeItem.setLayout(null);
		JLabel homeLbl = new JLabel("Home");
		homeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		homeLbl.setBounds(40, 0, 185, 60);
		homeLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/home.png")));
		homeLbl.setForeground(Color.WHITE);
		homeItem.add(homeLbl);
		homeItem.setBounds(0, 0, 250, 60);
		homeItem.addMouseListener(new MouseHover(homeLbl));
		homeItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
		homeItem.addMouseListener(this);

		// PRODUCT
		productItem.setLayout(null);
		JLabel productLbl = new JLabel("Product");
		productLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productLbl.setBounds(40, 0, 185, 60);
		productLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/iphone.png")));
		productLbl.setForeground(Color.WHITE);
		productItem.add(productLbl);
		productItem.setBounds(0, 60, 250, 60);
		productItem.addMouseListener(new MouseHover(productLbl));
		productItem.setBackground(new Color(68, 130, 255));
		productItem.addMouseListener(this);

		// CUSTOMER
		customerItem.setLayout(null);
		JLabel customerLbl = new JLabel("Customer");
		customerLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerLbl.setBounds(40, 0, 185, 60);
		customerLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/customer.png")));
		customerLbl.setForeground(Color.WHITE);
		customerItem.add(customerLbl);
		customerItem.setBounds(0, 120, 250, 60);
		customerItem.addMouseListener(new MouseHover(customerLbl));
		customerItem.setBackground(new Color(68, 130, 255));
		customerItem.addMouseListener(this);

		// ORDER
		orderItem.setLayout(null);
		JLabel orderLbl = new JLabel("Order");
		orderLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderLbl.setBounds(40, 0, 185, 60);
		orderLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/order.png")));
		orderLbl.setForeground(Color.WHITE);
		orderItem.add(orderLbl);
		orderItem.setBounds(0, 180, 250, 60);
		orderItem.addMouseListener(new MouseHover(orderLbl));
		orderItem.setBackground(new Color(68, 130, 255));
		orderItem.addMouseListener(this);

		// STATISTICS
		statisticsItem.setLayout(null);
		JLabel statisticsLbl = new JLabel("Statistics");
		statisticsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statisticsLbl.setBounds(40, 0, 185, 60);
		statisticsLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/statistics.png")));
		statisticsLbl.setForeground(Color.WHITE);
		statisticsItem.add(statisticsLbl);
		statisticsItem.setBounds(0, 240, 250, 60);
		statisticsItem.addMouseListener(new MouseHover(statisticsLbl));
		statisticsItem.setBackground(new Color(68, 130, 255));
		statisticsItem.addMouseListener(this);

		// ACCOUNT
		accountItem.setLayout(null);
		JLabel accountLbl = new JLabel("Account");
		accountLbl.setForeground(Color.WHITE);
		accountLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		accountLbl.setBounds(40, 0, 185, 60);
		accountLbl.setIcon(new ImageIcon(SideBar.class.getResource("/images/staff.png")));
		accountItem.add(accountLbl);
		accountItem.setBounds(0, 300, 250, 60);
		accountItem.addMouseListener(new MouseHover(accountLbl));
		accountItem.setBackground(new Color(68, 130, 255));
		accountItem.addMouseListener(this);
		
		// add item to menu
		menu.setLayout(null);
		menu.add(homeItem);
		menu.add(productItem);
		menu.add(customerItem);
		menu.add(orderItem);
		menu.add(statisticsItem);
		if(isAdmin)
			menu.add(accountItem);

		// FOOTER
		JPanel copyrightPanel = new JPanel();
		copyrightPanel.setPreferredSize(new Dimension(10, 40));
		add(copyrightPanel, BorderLayout.SOUTH);
		copyrightPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Copyright \u00A9 2020 by Hi Team");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(new Rectangle(0, 0, 0, 100));
		copyrightPanel.add(lblNewLabel);
		copyrightPanel.setBackground(new Color(68, 130, 255));

	}

	private class MouseHover implements MouseListener {
		private JLabel lbl;

		public MouseHover(JLabel lbl) {
			this.lbl = lbl;
		}

		public void mouseEntered(MouseEvent e) {
			lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
			lbl.setBackground(MaterialColors.LIGHT_BLUE_A200);
		}
		public void mouseExited(MouseEvent e) {
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl.setBackground(new Color(68, 130, 255));
		}
		
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}

	public void setPanelListener(PanelListener iPanelListener) {
		this.iPanelListener = iPanelListener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();

		if (panel == homeItem) {
			iPanelListener.showPanelEventOccurred("home");
			setColorPanel(homeItem, "home");
		} else if (panel == productItem) {
			iPanelListener.showPanelEventOccurred("product");
			setColorPanel(productItem, "product");
		} else if (panel == customerItem) {
			iPanelListener.showPanelEventOccurred("customer");
			setColorPanel(customerItem, "customer");
		} else if (panel == orderItem) {
			iPanelListener.showPanelEventOccurred("order");
			setColorPanel(orderItem, "order");
		} else if (panel == statisticsItem) {
			iPanelListener.showPanelEventOccurred("statistics");
			setColorPanel(statisticsItem, "statistics");
		} else if (panel == accountItem) {
			iPanelListener.showPanelEventOccurred("account");
			setColorPanel(accountItem, "account");
		}

	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private void setColorPanel(JPanel panel, String key) {
		switch (key) {
		case "home": {
			homeItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			productItem.setBackground(new Color(68, 130, 255));
			customerItem.setBackground(new Color(68, 130, 255));
			orderItem.setBackground(new Color(68, 130, 255));
			statisticsItem.setBackground(new Color(68, 130, 255));
			accountItem.setBackground(new Color(68, 130, 255));
			break;
		}
		case "product": {
			homeItem.setBackground(new Color(68, 130, 255));
			productItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			customerItem.setBackground(new Color(68, 130, 255));
			orderItem.setBackground(new Color(68, 130, 255));
			statisticsItem.setBackground(new Color(68, 130, 255));
			accountItem.setBackground(new Color(68, 130, 255));
			break;
		}
		case "customer": {
			homeItem.setBackground(new Color(68, 130, 255));
			productItem.setBackground(new Color(68, 130, 255));
			customerItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			orderItem.setBackground(new Color(68, 130, 255));
			statisticsItem.setBackground(new Color(68, 130, 255));
			accountItem.setBackground(new Color(68, 130, 255));
			break;
		}
		case "order": {
			homeItem.setBackground(new Color(68, 130, 255));
			productItem.setBackground(new Color(68, 130, 255));
			customerItem.setBackground(new Color(68, 130, 255));
			orderItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			statisticsItem.setBackground(new Color(68, 130, 255));
			accountItem.setBackground(new Color(68, 130, 255));
			break;
		}
		case "statistics": {
			homeItem.setBackground(new Color(68, 130, 255));
			productItem.setBackground(new Color(68, 130, 255));
			customerItem.setBackground(new Color(68, 130, 255));
			orderItem.setBackground(new Color(68, 130, 255));
			statisticsItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			accountItem.setBackground(new Color(68, 130, 255));
			break;
		}
		case "account": {
			homeItem.setBackground(new Color(68, 130, 255));
			productItem.setBackground(new Color(68, 130, 255));
			customerItem.setBackground(new Color(68, 130, 255));
			orderItem.setBackground(new Color(68, 130, 255));
			statisticsItem.setBackground(new Color(68, 130, 255));
			accountItem.setBackground(MaterialColors.LIGHT_BLUE_A200);
			break;
		}
		}
	}
}
