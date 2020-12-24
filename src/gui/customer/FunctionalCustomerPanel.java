package gui.customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class FunctionalCustomerPanel extends JPanel implements ActionListener {

	private JButton addBtn;
	private JButton updateBtn;
	private JButton removeBtn;
	private JButton clearBtn;

	private FunctionListener functionListener;
	private CustomerFormPanel customerFormPanel;
	

	public FunctionalCustomerPanel() {
		setPreferredSize(new Dimension(660, 47));
		setBounds(10, 337, 660, 47);
		setLayout(null);

		addBtn = new JButton("Add");
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(MaterialColors.LIGHT_GREEN_400);
		addBtn.addMouseListener(MaterialUIMovement.getMovement(addBtn, MaterialColors.LIGHT_GREEN_200));
		addBtn.setBounds(60, 11, 90, 35);
		add(addBtn);
		addBtn.addActionListener(this);

		updateBtn = new JButton("Update");
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBackground(MaterialColors.DEEP_ORANGE_400);
		updateBtn.addMouseListener(MaterialUIMovement.getMovement(updateBtn, MaterialColors.DEEP_ORANGE_200));
		updateBtn.setBounds(210, 11, 90, 35);
		add(updateBtn);
		updateBtn.addActionListener(this);

		removeBtn = new JButton("Remove");
		removeBtn.setForeground(Color.WHITE);
		removeBtn.setBackground(MaterialColors.RED_400);
		removeBtn.addMouseListener(MaterialUIMovement.getMovement(removeBtn, MaterialColors.RED_200));
		removeBtn.setBounds(360, 11, 90, 35);
		add(removeBtn);
		removeBtn.addActionListener(this);

		clearBtn = new JButton("Clear");
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setBackground(MaterialColors.LIGHT_BLUE_400);
		clearBtn.addMouseListener(MaterialUIMovement.getMovement(clearBtn, MaterialColors.LIGHT_BLUE_200));
		clearBtn.setBounds(510, 11, 90, 35);
		add(clearBtn);
		clearBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		CustomerFormEvent ev = customerFormPanel.getForm();

		if (btn == addBtn) {
			if (!isValidate(ev))
				return;

			JOptionPane.showMessageDialog(this, "Add customer successfully!", "",
					JOptionPane.INFORMATION_MESSAGE);
			
			CustomerFormEvent event = new CustomerFormEvent(this, ev.getName(), ev.getBirthYear(), ev.isSex(),
					ev.getPhoneNumber(), ev.getAddress(), ev.getMoreInfo());
			functionListener.addEventOccurred(event);
		}
		else if(btn == updateBtn) {
			if(ev.getId() == 0) {
				JOptionPane.showMessageDialog(this, "Invalid ID value", "",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isValidate(ev))
				return;
			
			JOptionPane.showMessageDialog(this, "Update customer successfully!", "",
					JOptionPane.INFORMATION_MESSAGE);
			
			functionListener.updateEventOccurred(ev);
		}
		else if(btn == removeBtn) {
			if(ev.getId() == 0) {
				JOptionPane.showMessageDialog(this, "Invalid ID value", "",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isValidate(ev))
				return;
			
			JOptionPane.showMessageDialog(this, "Remove customer successfully!", "",
					JOptionPane.INFORMATION_MESSAGE);
			
			functionListener.removeEventOccurred(ev);
		}
		else if(btn == clearBtn) {
			CustomerFormEvent event = new CustomerFormEvent(this, 0, null, 0, true, null, null, null);
			functionListener.clearEventOccurred(event);
		}
	}

	public void setFunctionListener(FunctionListener functionListener, CustomerFormPanel customerFormPanel) {
		this.customerFormPanel = customerFormPanel;
		this.functionListener = functionListener;
	}

	private boolean isValidate(CustomerFormEvent ev) {
		boolean check = true;
		if (ev.getName().compareTo("") == 0) {
			JOptionPane.showMessageDialog(this, "Invalid name value", "Error", JOptionPane.ERROR_MESSAGE);
			check = false;
		}

		if (ev.getBirthYear() == 0) {
			JOptionPane.showMessageDialog(this, "Invalid Birth Year Value", "Error", JOptionPane.ERROR_MESSAGE);
			check = false;
		}
		
		if (ev.getPhoneNumber().equals("")) {
			JOptionPane.showMessageDialog(this, "Invalid phone number Value", "Error",
					JOptionPane.ERROR_MESSAGE);
			check = false;
		}

		try {
			Integer.parseInt(ev.getPhoneNumber());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Phone  is not numbers", "Error",
					JOptionPane.ERROR_MESSAGE);
			check = false;
		}

		return check;
	}

}
