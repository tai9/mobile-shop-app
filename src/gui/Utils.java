package gui;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Utils {
	public static String getFileExtension(String name) {
		int pointIndex = name.lastIndexOf('.');
		
		if(pointIndex == -1)
			return null;
		
		if(pointIndex == name.length() - 1)
			return null;
		
		return name.substring(pointIndex + 1, name.length());
	}
	
	public static ImageIcon createIcon(String path) {
		URL url = Utils.class.getResource(path);
		
		if(url == null) {
			System.out.println("Unable to load image icon");
		}
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
	}
	
	public static Dimension setDimension() {
		return new Dimension(1100,600);
	}
	
	public static Point setLocation() {
		return new Point(100,50);
	}
	
	public static String showDate() {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		return s.format(date);
	}
	
	public static String showTimer() {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
		return s.format(date);
	}
	
	public static DefaultComboBoxModel<String> setProductCategoryModel() {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
		String[] listCat = { "Apple", "Samsung", "Xiaomi", "Huawei", "Oppo", "Realme", "Nokia", "Vsmart", "Other" };
		for (String s : listCat) {
			comboBoxModel.addElement(s);
		}
		return comboBoxModel;
	}
}
