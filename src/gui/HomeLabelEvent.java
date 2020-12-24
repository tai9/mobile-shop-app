package gui;

import java.util.EventObject;

import javax.swing.JLabel;

public class HomeLabelEvent extends EventObject {

	private JLabel productCountLbl;
	private JLabel customerCountLbl;
	private JLabel orderCountLbl;
	
	public HomeLabelEvent(Object source) {
		super(source);
	}

	public HomeLabelEvent(Object source, JLabel productCountLbl, JLabel customerCountLbl, JLabel orderCountLbl) {
		super(source);
		this.productCountLbl = productCountLbl;
		this.customerCountLbl = customerCountLbl;
		this.orderCountLbl = orderCountLbl;
	}

	public JLabel getProductCountLbl() {
		return productCountLbl;
	}

	public void setProductCountLbl(JLabel productCountLbl) {
		this.productCountLbl = productCountLbl;
	}

	public JLabel getCustomerCountLbl() {
		return customerCountLbl;
	}

	public void setCustomerCountLbl(JLabel customerCountLbl) {
		this.customerCountLbl = customerCountLbl;
	}

	public JLabel getOrderCountLbl() {
		return orderCountLbl;
	}

	public void setOrderCountLbl(JLabel orderCountLbl) {
		this.orderCountLbl = orderCountLbl;
	}


}
