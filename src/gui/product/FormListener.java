package gui.product;

public interface FormListener {
	public void addEventOccurred(ProductFormEvent ev);
	public void updateEventOccurred(ProductFormEvent ev);
	public void removeEventOccurred(ProductFormEvent ev);
}
