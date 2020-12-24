import gui.customer.CustomerFormEvent;
import gui.customer.CustomerFormPanel;

public class TestCustomer {
	public static void main(String[] args) {
		CustomerFormPanel cus = new CustomerFormPanel();
		CustomerFormEvent customerFormEvent = cus.getForm();
		
		System.out.println(customerFormEvent.getId());
		System.out.println("birth "+customerFormEvent.getBirthYear());
		System.out.println("address "+customerFormEvent.getAddress());
		System.out.println("more "+customerFormEvent.getMoreInfo());
		System.out.println("sex "+customerFormEvent.isSex());
		System.out.println("phone "+customerFormEvent.getPhoneNumber());
		System.out.println("name "+customerFormEvent.getName());
		String s = "";
		if(s.equals(null))
			System.out.println("true");
	}
}
