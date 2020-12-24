package gui.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.Controller;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import model.Order;

public class StatisticsPanel extends JPanel {

	private JPanel panel;
	private JButton refreshBtn;

	private List<Order> orderList;
	private Controller controller;

	public StatisticsPanel() {
		setBackground(MaterialColors.BLUE_GRAY_400);
		setPreferredSize(new Dimension(700, 600));
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 10, 680, 545);
		panel.setLayout(null);
		add(panel);

		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(36, 10, 120, 30);
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setBackground(MaterialColors.BLUE_400);
		refreshBtn.addMouseListener(MaterialUIMovement.getMovement(refreshBtn, MaterialColors.LIGHT_BLUE_A200));
		panel.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadListOrders();
				panel.removeAll();
				panel.updateUI();
				panel.add(refreshBtn);
				showChart();
				JOptionPane.showMessageDialog(panel, "Refresh!");
			}
		});
		
		controller = new Controller();
		orderList = new ArrayList<Order>();
		controller.connect();
		showChart();

	}

	private void showChart() {
		Map<Integer, Double> map = controller.getTotalPaymentOfMonth();

		DefaultCategoryDataset dcd = new DefaultCategoryDataset();

		String row = "sales";

		for (Map.Entry<Integer, Double> entry : map.entrySet()) {

			switch (entry.getKey()) {
			case 1:
				dcd.setValue(entry.getValue(), row, "January");
				break;
			case 2:
				dcd.setValue(entry.getValue(), row, "February");
				break;
			case 3:
				dcd.setValue(entry.getValue(), row, "March");
				break;
			case 4:
				dcd.setValue(entry.getValue(), row, "April");
				break;
			case 5:
				dcd.setValue(entry.getValue(), row, "May");
				break;
			case 6:
				dcd.setValue(entry.getValue(), row, "June");
				break;
			case 7:
				dcd.setValue(entry.getValue(), row, "July");
				break;
			case 8:
				dcd.setValue(entry.getValue(), row, "August");
				break;
			case 9:
				dcd.setValue(entry.getValue(), row, "September");
				break;
			case 10:
				dcd.setValue(entry.getValue(), row, "October");
				break;
			case 11:
				dcd.setValue(entry.getValue(), row, "November");
				break;
			case 12:
				dcd.setValue(entry.getValue(), row, "December");
				break;
			}
		}

		JFreeChart chart = ChartFactory.createBarChart("Monthly Revenue", "Month of The Year", "Million VND Unit", dcd,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		plot.setBackgroundPaint(SystemColor.inactiveCaption);

		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

		BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();
		r.setSeriesPaint(0, MaterialColors.BLUE_400);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 50, 660, 480);
		panel.add(chartPanel);
	}
}
