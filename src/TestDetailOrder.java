import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gui.order.DetailOrderFormEvent;
import gui.product.ProductFormEvent;
import model.DetailOrder;
import model.Product;

public class TestDetailOrder {
	public static void main(String[] args) {
//		List<DetailOrder> list = new LinkedList<DetailOrder>();
//		Product p1 = new Product(1, "IphoneX", "", 500, 100, "");
//		Product p2 = new Product(2, "IphoneXs", "", 100, 100, "");
//		Product p3 = new Product(3, "IphoneXX", "", 300, 100, "");
//		DetailOrder d1 = new DetailOrder(p1, 10, 10 * p1.getPrice());
//		DetailOrder d2 = new DetailOrder(p2, 20, 20 * p1.getPrice());
//		DetailOrder d3 = new DetailOrder(3, 3, p3, 30, 30 * p1.getPrice());
//		list.add(d1);
//		list.add(d2);
//		list.add(d3);
//		DetailOrderFormEvent form = new DetailOrderFormEvent(d1);
//		DetailOrder d4 = new DetailOrder(form.getProduct(), form.getQuantityOrder(), form.getPayment());
//		System.out.println(list.remove(d4));
//		for (DetailOrder d : list) {
//			System.out.println(d);
//		}
		
		List<Product> products = new LinkedList<Product>();
		Product p1 = new Product(1, "IphoneX", "", 500, 100, "");
		Product p2 = new Product(2, "IphoneXs", "", 100, 100, "");
		Product p3 = new Product(3, "IphoneXX", "", 300, 100, "");
		products.add(p1);
		products.add(p2);
		products.add(p3);
		ProductFormEvent ev = new ProductFormEvent(p2,p2.getId(), p2.getName(), p2.getCategory(), p2.getPrice(), p2.getQuantity(), p2.getDescription());
		int id = ev.getId();
		String name = ev.getName();
		String category = ev.getCategory();
		double price = ev.getPrice();
		int quantity = ev.getQuantity();
		String description = ev.getDescription();
		Product p4 = new Product(id, name, category, price, quantity, description);
//		Product p4 = new Product(ev.getId(),ev.getName(), ev.getCategory(), ev.getPrice(), ev.getQuantity(), ev.getDescription());
		System.out.println(products.remove(p4));
//		for (Product p : products) {
//			System.out.println(p.getId()+" "+p4.getId());
//			if (p.getId() == p4.getId()) {
//				System.out.println(products.remove(p4));
//			}
//		}
		for (Product p : products) {
			System.out.println(p);
		}
	}
}
