package gui.customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mdlaf.utils.MaterialColors;

public class CustomerFormPanel extends JPanel {

	private JTextField idField;
	private JTextField nameField;
	private JTextField birthYearField;
	private JComboBox sexCombo;
	private JTextField phoneNumberField;
	private JTextArea addressArea;
	private JTextArea moreInfoArea;

	private CustomerFormListener formListener;

	public CustomerFormPanel() {
		setBackground(MaterialColors.BLUE_GRAY_50);
		setPreferredSize(new Dimension(332, 327));
		setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(38, 16, 80, 20);
		add(lblId);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(38, 54, 80, 20);
		add(lblName);

		JLabel lblBirthday = new JLabel("Birth Year:");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setBounds(38, 92, 80, 20);
		add(lblBirthday);

		JLabel lblSex = new JLabel("Sex:");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setBounds(38, 130, 80, 20);
		add(lblSex);

		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setBounds(-12, 168, 130, 20);
		add(lblPhoneNumber);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(38, 209, 80, 20);
		add(lblAddress);

		JLabel lblMoreInfo = new JLabel("More info:");
		lblMoreInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoreInfo.setBounds(38, 263, 80, 20);
		add(lblMoreInfo);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(128, 16, 80, 25);
		add(idField);
		idField.setColumns(10);

		nameField = new JTextField();
		nameField.setColumns(20);
		nameField.setBounds(128, 54, 171, 25);
		add(nameField);

		birthYearField = new JTextField();
		birthYearField.setColumns(10);
		birthYearField.setBounds(128, 92, 171, 25);
		add(birthYearField);

		sexCombo = new JComboBox();
		sexCombo.setModel(new DefaultComboBoxModel(new String[] { "male", "female" }));
		sexCombo.setBounds(128, 130, 100, 25);
		add(sexCombo);

		phoneNumberField = new JTextField();
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(128, 168, 171, 25);
		add(phoneNumberField);

		addressArea = new JTextArea();
		addressArea.setBackground(Color.WHITE);
		addressArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addressArea.setColumns(10);
		addressArea.setBounds(128, 207, 171, 40);
		JScrollPane spaddress = new JScrollPane(addressArea);
		spaddress.setBounds(128, 207, 171, 40);
		add(spaddress);

		moreInfoArea = new JTextArea();
		moreInfoArea.setBackground(Color.WHITE);
		moreInfoArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		moreInfoArea.setColumns(10);
		moreInfoArea.setBounds(128, 261, 171, 40);
		JScrollPane spMI = new JScrollPane(moreInfoArea);
		spMI.setBounds(128, 261, 171, 40);
		add(spMI);

	}

	public void setValueForm(CustomerFormEvent ev) {
		idField.setText(ev.getId() + "");
		nameField.setText(ev.getName());
		birthYearField.setText(ev.getBirthYear() + "");

		if (ev.isSex())
			sexCombo.setSelectedIndex(0);
		else
			sexCombo.setSelectedIndex(1);

		phoneNumberField.setText(ev.getPhoneNumber());
		addressArea.setText(ev.getAddress());
		moreInfoArea.setText(ev.getMoreInfo());

	}

	public CustomerFormEvent getForm() {
		int id = 0;
		if (!idField.getText().equals(""))
			id = Integer.valueOf(idField.getText());
		String name = nameField.getText();

		int birthYear = 0;
		if (!birthYearField.getText().equals(""))
			try {
				birthYear = Integer.parseInt(birthYearField.getText());
			} catch (Exception e) {
				birthYear = 0;
			}
		

		boolean sex = false;
		if (sexCombo.getSelectedIndex() == 0)
			sex = true;

		String phoneNumber = phoneNumberField.getText();
		if(phoneNumber != null) {
			try {
				Integer.parseInt(phoneNumberField.getText());
				
			} catch (Exception e) {
				phoneNumber = phoneNumberField.getText();
			}
		}
		
		
		String address = addressArea.getText();
		String moreInfo = moreInfoArea.getText();

		return new CustomerFormEvent(this, id, name, birthYear, sex, phoneNumber, address, moreInfo);
	}

}
