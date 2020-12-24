package gui.product;


import javax.swing.JPanel;

import mdlaf.utils.MaterialColors;
import model.Product;

import java.awt.Dimension;
import javax.swing.JOptionPane;

import controller.Controller;

import java.util.List;


public class ProductPanel extends JPanel {
	private ProductFormPanel formPanel;
	private ProductTablePanel tableProduct;

	private Controller controller;
	private List<Product> products;

	public ProductPanel() {
		setPreferredSize(new Dimension(700, 600));
		setBackground(MaterialColors.BLUE_GRAY_400);
		setLayout(null);

		controller = new Controller();

		formPanel = new ProductFormPanel();
		add(formPanel);

		controller.connect();
		controller.loadListProducts();
		tableProduct = new ProductTablePanel();
		tableProduct.setBounds(10, 262, 680, 299);
		tableProduct.setData(controller.getProducts());

		tableProduct.setTableListener(new ProductTableListener() {
			public void tableEventOccurred(ProductFormEvent ev) {
				formPanel.setValueForm(ev);
			}
		});

		products = controller.getProducts();

		formPanel.setFormListener(new FormListener() {
			public void addEventOccurred(ProductFormEvent ev) {

				for (Product p : products) {
					if (p.getName().compareToIgnoreCase(ev.getName()) == 0
							&& p.getCategory().compareToIgnoreCase(ev.getCategory()) == 0) {
						JOptionPane.showMessageDialog(ProductPanel.this, "The product already exists! ", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				// Bắt lỗi khi không thêm sản phẩm được
				try {
					controller.addProduct(ev);
					JOptionPane.showMessageDialog(ProductPanel.this, "Add to product successfully!", "",
							JOptionPane.INFORMATION_MESSAGE);
					controller.saveListProducts();
					controller.loadListProducts();
					tableProduct.refresh();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ProductPanel.this, "Unable to add product", "",
							JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}

			}

			@Override
			public void updateEventOccurred(ProductFormEvent ev) {
				try {
					controller.updateProduct(ev);
					JOptionPane.showMessageDialog(ProductPanel.this, "Update product successfully!", "Update",
							JOptionPane.INFORMATION_MESSAGE);
					controller.saveListProducts();
					controller.loadListProducts();
					tableProduct.refresh();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ProductPanel.this, "Unable to update product", "Error",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}

			@Override
			public void removeEventOccurred(ProductFormEvent ev) {
				try {
					controller.removeProduct(ev);
					JOptionPane.showMessageDialog(ProductPanel.this, "Remove product successfully!", "",
							JOptionPane.INFORMATION_MESSAGE);
					controller.loadListProducts();
					tableProduct.refresh();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ProductPanel.this, "Unable to remove product", "Error",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}
		});

		add(tableProduct);

	}
}
