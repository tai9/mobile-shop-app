package gui.login;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import gui.MainFrame;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JButton loginBtn;
	
	private List<Account> listAccout;
	
	private Controller controller;

	public LoginFrame() {
		
		// default setting
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(600, 100, 407, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(MaterialColors.LIGHT_BLUE_400);
		
		// set up login ui
		JLabel signInLbl = new JLabel("Sign In");
		signInLbl.setForeground(Color.WHITE);
		signInLbl.setHorizontalAlignment(SwingConstants.CENTER);
		signInLbl.setFont(new Font("SVN-Zero", Font.PLAIN, 38));
		signInLbl.setBounds(70, 34, 253, 105);
		contentPane.add(signInLbl);
		
		JLabel userNameLbl = new JLabel("Username:");
		userNameLbl.setForeground(Color.WHITE);
		userNameLbl.setFont(new Font("SVN-Harabaras", Font.PLAIN, 16));
		userNameLbl.setBounds(100, 149, 144, 24);
		contentPane.add(userNameLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setForeground(Color.WHITE);
		passwordLbl.setFont(new Font("SVN-Harabaras", Font.PLAIN, 14));
		passwordLbl.setBounds(100, 210, 144, 24);
		contentPane.add(passwordLbl);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("SVN-Harabaras", Font.PLAIN, 14));
		userNameTextField.setBounds(100, 178, 197, 31);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(100, 235, 198, 31);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JCheckBox rememberCkb = new JCheckBox("Remember me");
		rememberCkb.setFont(new Font("SVN-Harabaras", Font.PLAIN, 14));
		rememberCkb.setBounds(102, 275, 142, 21);
		contentPane.add(rememberCkb);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("SVN-Harabaras", Font.PLAIN, 14));
		loginBtn.setBounds(100, 310, 197, 31);
		contentPane.add(loginBtn);
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(MaterialColors.LIGHT_BLUE_500);
		loginBtn.setBorder(new LineBorder(Color.WHITE));
		loginBtn.addMouseListener(MaterialUIMovement.getMovement(loginBtn, MaterialColors.LIGHT_BLUE_200));
		
		// load data account
		controller = new Controller();
		controller.connect();
		controller.loadListAccount();
		listAccout = controller.getAccounts();
		
		// login action listener
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = userNameTextField.getText().trim().toLowerCase();
					String password = String.valueOf(passwordField.getPassword()).trim().toLowerCase();
					Account account = new Account();
					
					if(checkAccount(username, password, account)) {
						dispose();
						System.gc();
						new MainFrame(account);
						controller.disconnect();
					}
					else
						JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect username or password.");
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(LoginFrame.this, e2.getMessage());
				}
			}
		});
		
		// exit
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int action = JOptionPane.showConfirmDialog(LoginFrame.this,
						"Do you really want to exit the application?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.YES_OPTION) {
					dispose();
					System.gc();
				}
			}

		});
	}
	
	// check account exists in db
	private boolean checkAccount(String usr, String pass, Account account) {
		for(Account a : listAccout) {
			if (a.getUsername().equalsIgnoreCase(usr) && a.getPassword().equalsIgnoreCase(pass)){
				account.setUsername(a.getUsername());
				account.setPassword(a.getPassword());
				account.setRole(a.isRole());
				return true;
			}
		}
		return false;
	}
}
