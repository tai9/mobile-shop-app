package gui.account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controller;
import gui.customer.CustomerTablePanel;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AccountPanel extends JPanel implements ActionListener {
	private JPanel accountPanel;

	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private JButton addBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JCheckBox roleCkb;

	private AccountTablePanel accountTablePanel;

	private Controller controller;

	public AccountPanel() {
		setPreferredSize(new Dimension(700, 600));
		setBackground(MaterialColors.BLUE_GRAY_400);
		setLayout(null);

		controller = new Controller();
		controller.connect();
		controller.loadListAccount();

		accountPanel = new JPanel();
		accountPanel.setBackground(Color.WHITE);
		accountPanel.setBounds(10, 11, 680, 230);
		add(accountPanel);
		accountPanel.setLayout(null);

		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setBounds(66, 65, 80, 30);
		accountPanel.add(usernameLbl);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(66, 115, 80, 30);
		accountPanel.add(lblPassword);

		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(66, 160, 80, 30);
		accountPanel.add(lblRole);

		usernameTxt = new JTextField();
		usernameTxt.setBounds(145, 70, 120, 25);
		accountPanel.add(usernameTxt);
		usernameTxt.setColumns(10);

		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(145, 115, 120, 25);
		accountPanel.add(passwordTxt);

		roleCkb = new JCheckBox("Admin");
		roleCkb.setBounds(145, 164, 93, 25);
		accountPanel.add(roleCkb);

		addBtn = new JButton("Add");
		addBtn.setBounds(306, 70, 90, 35);
		accountPanel.add(addBtn);
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(MaterialColors.LIGHT_GREEN_400);
		addBtn.addMouseListener(MaterialUIMovement.getMovement(addBtn, MaterialColors.LIGHT_GREEN_200));
		addBtn.addActionListener(this);

		updateBtn = new JButton("Update");
		updateBtn.setBounds(306, 115, 90, 35);
		accountPanel.add(updateBtn);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBackground(MaterialColors.DEEP_ORANGE_400);
		updateBtn.addMouseListener(MaterialUIMovement.getMovement(updateBtn, MaterialColors.DEEP_ORANGE_200));
		updateBtn.addActionListener(this);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(306, 160, 90, 35);
		accountPanel.add(deleteBtn);
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(MaterialColors.RED_400);
		deleteBtn.addMouseListener(MaterialUIMovement.getMovement(deleteBtn, MaterialColors.RED_200));
		deleteBtn.addActionListener(this);

		accountTablePanel = new AccountTablePanel();
		accountTablePanel.setBounds(10, 255, 680, 300);
		add(accountTablePanel);
		accountTablePanel.setData(controller.getAccounts());
		accountTablePanel.setTableListener(new AccountTableListener() {
			public void tableEventOccurred(AccountEvent ae) {
				usernameTxt.setText(ae.getUsername());
				passwordTxt.setText(ae.getPassword());
				roleCkb.setSelected(ae.isRole());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn == addBtn) {
			try {
				String username = usernameTxt.getText().trim().toLowerCase();
				String password = passwordTxt.getText().trim().toLowerCase();
				boolean role = roleCkb.isSelected();

				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(this, "Username or password is empty.");
					return;
				}

				for (Account a : controller.getAccounts()) {
					if (a.getUsername().equalsIgnoreCase(username)) {
						JOptionPane.showMessageDialog(this, "That username is taken. Try another.");
						return;
					}
				}

				AccountEvent ev = new AccountEvent(this, username, password, role);
				controller.addAccount(ev);
//				controller.saveListAccounts();
				JOptionPane.showMessageDialog(this, "Account is added.");
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(this, e2.getMessage());
			}

		} else if (btn == updateBtn) {
			try {
				String username = usernameTxt.getText().trim().toLowerCase();
				String password = passwordTxt.getText().trim().toLowerCase();
				boolean role = roleCkb.isSelected();

				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(this, "Username or password is empty.");
					return;
				}

				for (Account a : controller.getAccounts()) {
					if (a.getUsername().equalsIgnoreCase(username)) {
						AccountEvent ev = new AccountEvent(this, username, password, role);
						controller.updateAccount(ev);
						JOptionPane.showMessageDialog(this, "Account is updated.");
						return;
					}
				}
				
				JOptionPane.showMessageDialog(this, "Username is valid.");

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(this, e2.getMessage());
			}

		} else if (btn == deleteBtn) {
			try {
				String username = usernameTxt.getText().trim().toLowerCase();
				String password = passwordTxt.getText().trim().toLowerCase();
				boolean role = roleCkb.isSelected();

				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(this, "Username or password is empty.");
					return;
				}

				for (Account a : controller.getAccounts()) {
					if (a.getUsername().equalsIgnoreCase(username)) {
						AccountEvent ev = new AccountEvent(this, username, password, role);
						controller.deleteAccount(ev);
						
						JOptionPane.showMessageDialog(this, "Account is deleted.");
						usernameTxt.setText("");
						passwordTxt.setText("");
						roleCkb.setSelected(false);

						return;
					}
				}
				
				JOptionPane.showMessageDialog(this, "Username is valid.");

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(this, e2.getMessage());
			}
		}
	}
}
