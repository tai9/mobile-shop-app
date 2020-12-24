package gui.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.Utils;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class ProductFormPanel extends JPanel implements ActionListener {

	private JLabel idLbl;
	private JLabel nameLbl;
	private JLabel categoryLbl;
	private JLabel quantityLbl;
	private JLabel priceLbl;
	private JLabel descriptionLbl;

	private JTextField idField;
	private JTextField nameField;
	private JComboBox categoryCombo;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextArea descriptionArea;

	private JButton addBtn;
	private JButton updateBtn;
	private JButton removeBtn;
	private JButton clearBtn;

	private FormListener formListener;

	public ProductFormPanel() {
		setLayout(null);
		setBounds(10, 11, 680, 241);

		idLbl = new JLabel("ID");
		idLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idLbl.setBounds(74, 40, 49, 20);
		add(idLbl);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idField.setBounds(141, 40, 100, 20);
		add(idField);
		idField.setColumns(10);

		nameLbl = new JLabel("Name:");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLbl.setBounds(265, 40, 49, 20);
		add(nameLbl);

		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setBounds(308, 40, 100, 20);
		nameField.setColumns(10);
		add(nameField);

		categoryLbl = new JLabel("Category:");
		categoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryLbl.setBounds(440, 40, 80, 20);
		add(categoryLbl);

		categoryCombo = new JComboBox();
		categoryCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryCombo.setBounds(530, 40, 100, 25);
		categoryCombo.setModel(Utils.setProductCategoryModel());
		categoryCombo.setSelectedIndex(0);
		add(categoryCombo);

		quantityLbl = new JLabel("Quantity:");
		quantityLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityLbl.setBounds(74, 100, 80, 20);
		add(quantityLbl);

		quantityField = new JTextField();
		quantityField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityField.setColumns(10);
		quantityField.setBounds(141, 100, 100, 20);
		add(quantityField);

		priceLbl = new JLabel("Price:");
		priceLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLbl.setBounds(265, 100, 49, 20);
		add(priceLbl);

		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setColumns(10);
		priceField.setBounds(308, 100, 100, 20);
		add(priceField);

		descriptionLbl = new JLabel("Description:");
		descriptionLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLbl.setBounds(440, 100, 80, 20);
		add(descriptionLbl);

		descriptionArea = new JTextArea();
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setLineWrap(true);
		descriptionArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		descriptionArea.setColumns(10);
		descriptionArea.setBounds(530, 140, 100, 50);
		JScrollPane scroll = new JScrollPane(descriptionArea);
		scroll.setBounds(530, 100, 100, 50);
		add(scroll);

		addBtn = new JButton("Add");
		addBtn.setBounds(74, 180, 90, 35);
		add(addBtn);
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(MaterialColors.LIGHT_GREEN_400);
		addBtn.addMouseListener(MaterialUIMovement.getMovement(addBtn, MaterialColors.LIGHT_GREEN_200));
		addBtn.addActionListener(this);

		updateBtn = new JButton("Update");
		updateBtn.setBounds(220, 180, 90, 35);
		add(updateBtn);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBackground(MaterialColors.DEEP_ORANGE_400);
		updateBtn.addMouseListener(MaterialUIMovement.getMovement(updateBtn, MaterialColors.DEEP_ORANGE_200));
		updateBtn.addActionListener(this);

		removeBtn = new JButton("Remove");
		removeBtn.setBounds(366, 180, 90, 35);
		add(removeBtn);
		removeBtn.setForeground(Color.WHITE);
		removeBtn.setBackground(MaterialColors.RED_400);
		removeBtn.addMouseListener(MaterialUIMovement.getMovement(removeBtn, MaterialColors.RED_200));
		removeBtn.addActionListener(this);

		clearBtn = new JButton("Clear");
		clearBtn.setBounds(512, 180, 90, 35);
		add(clearBtn);
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setBackground(MaterialColors.LIGHT_BLUE_400);
		clearBtn.addMouseListener(MaterialUIMovement.getMovement(clearBtn, MaterialColors.LIGHT_BLUE_200));
		clearBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn == addBtn) {
			if (formListener != null) {
				if (!isValidate())
					return;

				String name = nameField.getText();
				String category = categoryCombo.getSelectedItem().toString();
				double price = Double.parseDouble(priceField.getText());
				int quantity = Integer.parseInt(quantityField.getText());
				String description = descriptionArea.getText();

				ProductFormEvent ev = new ProductFormEvent(this, name, category, price, quantity, description);
				formListener.addEventOccurred(ev);
			}
		} else if (btn == updateBtn) {
			if (idField.getText().equals("")) {
				JOptionPane.showMessageDialog(ProductFormPanel.this, "Invalid id value", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isValidate())
				return;

			int id = Integer.parseInt(idField.getText());
			String name = nameField.getText();
			String category = categoryCombo.getSelectedItem().toString();
			double price = Double.parseDouble(priceField.getText());
			int quantity = Integer.parseInt(quantityField.getText());
			String description = descriptionArea.getText();

			ProductFormEvent ev = new ProductFormEvent(this, id, name, category, price, quantity, description);
			formListener.updateEventOccurred(ev);

		} else if (btn == removeBtn) {
			if (idField.getText().equals("")) {
				JOptionPane.showMessageDialog(ProductFormPanel.this, "Invalid id value", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isValidate())
				return;

			int id = Integer.parseInt(idField.getText());
			String name = nameField.getText();
			String category = categoryCombo.getSelectedItem().toString();
			double price = Double.parseDouble(priceField.getText());
			int quantity = Integer.parseInt(quantityField.getText());
			String description = descriptionArea.getText();

			ProductFormEvent ev = new ProductFormEvent(this, id, name, category, price, quantity, description);
			formListener.removeEventOccurred(ev);
		} else if (btn == clearBtn) {
			idField.setText("");
			nameField.setText("");
			categoryCombo.setSelectedIndex(0);
			priceField.setText("");
			quantityField.setText("");
			descriptionArea.setText(null);
		}

	}

	private boolean isValidate() {
		boolean check = true;
		if (nameField.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(ProductFormPanel.this, "Invalid name value", "Error",
					JOptionPane.ERROR_MESSAGE);
			nameField.requestFocus();
			check = false;
		}

		try {
			Double.parseDouble(priceField.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(ProductFormPanel.this, "Invalid price value", "Error",
					JOptionPane.ERROR_MESSAGE);
			priceField.requestFocus();
			check = false;
		}

		try {
			Integer.parseInt(quantityField.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(ProductFormPanel.this, "Invalid quantity value", "Error",
					JOptionPane.ERROR_MESSAGE);
			quantityField.requestFocus();
			check = false;
		}

		return check;
	}

	public void setValueForm(ProductFormEvent ev) {
		idField.setText(ev.getId() + "");
		nameField.setText(ev.getName());
		categoryCombo.setSelectedItem(ev.getCategory());
		quantityField.setText(ev.getQuantity() + "");
		priceField.setText(ev.getPrice() + "");
		descriptionArea.setText(ev.getDescription());

	}

	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
	}
}
