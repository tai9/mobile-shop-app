package gui.customer;

public interface FunctionListener {
	public void addEventOccurred(CustomerFormEvent ev);
	public void updateEventOccurred(CustomerFormEvent ev);
	public void removeEventOccurred(CustomerFormEvent ev);
	public void clearEventOccurred(CustomerFormEvent ev);
}
