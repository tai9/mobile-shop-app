package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.login.LoginFrame;
import gui.login.SignOutListener;
import mdlaf.utils.MaterialColors;
import javax.swing.JButton;

public class HomePanel extends JPanel {

	private JPanel firstSectionPanel;
	private JPanel productCountPanel;
	private JLabel productLbl;
	private JLabel productCountLbl;
	private JPanel customerCountPanel;
	private JLabel customerLbl;
	private JLabel customerCountLbl;
	private JPanel orderCountPanel;
	private JLabel orderLbl;
	private JLabel orderCountLbl;

	private JPanel secondSectionPanel;
	private JLabel logoHomeLbl;

	private Controller controller;
	private HomeLabelEvent lblHomeEvent;
	
	private JLabel userNameLbl;
	private JButton signOutBtn;
	
	private SignOutListener signOutListener;
	
	// user name
	
	public HomePanel(String username) {
		
		setPreferredSize(new Dimension(700, 600));
		setBackground(MaterialColors.BLUE_GRAY_400);
		controller = new Controller();
		setLayout(null);
		// section 1
		firstSectionPanel = new JPanel();
		firstSectionPanel.setBounds(10, 11, 680, 290);
		firstSectionPanel.setPreferredSize(new Dimension(700, 300));
		firstSectionPanel.setBackground(Color.WHITE);
		add(firstSectionPanel);
		firstSectionPanel.setLayout(null);

		// product count
		productCountPanel = new JPanel();
		productCountPanel.setBackground(new Color(66, 165, 245));
		productCountPanel.setBounds(50, 90, 180, 96);
		firstSectionPanel.add(productCountPanel);
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

		// customer count
		customerCountPanel = new JPanel();
		customerCountPanel.setBackground(new Color(125, 212, 32));
		customerCountPanel.setBounds(260, 90, 180, 96);
		firstSectionPanel.add(customerCountPanel);
		customerCountPanel.setLayout(new BorderLayout(0, 0));

		customerLbl = new JLabel("Customers Count:");
		customerLbl.setForeground(Color.WHITE);
		customerLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		customerLbl.setBorder(new EmptyBorder(10, 10, 0, 0));
		customerCountPanel.add(customerLbl, BorderLayout.NORTH);

		customerCountLbl = new JLabel("###");
		customerCountLbl.setForeground(Color.WHITE);
		customerCountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		customerCountLbl.setFont(new Font("Tahoma", Font.BOLD, 21));
		customerCountPanel.add(customerCountLbl, BorderLayout.CENTER);

		// order count
		orderCountPanel = new JPanel();
		orderCountPanel.setBackground(new Color(247, 93, 129));
		orderCountPanel.setBounds(470, 90, 180, 96);
		firstSectionPanel.add(orderCountPanel);
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
		
		userNameLbl = new JLabel("Username");
		userNameLbl.setFont(new Font("Tahoma", Font.ITALIC, 15));
		userNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLbl.setBounds(550, 3, 90, 35);
		firstSectionPanel.add(userNameLbl);
		userNameLbl.setText(username);
		
		signOutBtn = new JButton("");
		signOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		signOutBtn.setBounds(637, 10, 33, 23);
		firstSectionPanel.add(signOutBtn);
		signOutBtn.setIcon(new ImageIcon(HomePanel.class.getResource("/images/signout.png")));
		signOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(HomePanel.this,
						"Do you want to sign out my account?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
					signOutListener.signout();
				}				
			}
		});
		
		// set up flat style for button
		signOutBtn.setBorderPainted(false);
		signOutBtn.setFocusPainted(false);
		signOutBtn.setContentAreaFilled(false);
		
		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLbl.setBounds(450, 3, 90, 35);
		firstSectionPanel.add(usernameLbl);

		// section 2
		secondSectionPanel = new JPanel();
		secondSectionPanel.setBounds(10, 296, 680, 262);
		add(secondSectionPanel);
		secondSectionPanel.setLayout(new BorderLayout(0, 0));

		logoHomeLbl = new JLabel();
		logoHomeLbl.setIcon(new ImageIcon(HomePanel.class.getResource("/images/banner.png")));
		secondSectionPanel.add(logoHomeLbl, BorderLayout.CENTER);
		secondSectionPanel.setBackground(MaterialColors.LIGHT_BLUE_100);

		controller.connect();

		loadTs();
		
	}
	
	public void loadTs() {
		lblHomeEvent = new HomeLabelEvent(this, productCountLbl, customerCountLbl, orderCountLbl);
		controller.loadThongSo(lblHomeEvent);
	}

	public void signOutListener(SignOutListener signOutListener) {
		this.signOutListener = signOutListener;
	}
}
