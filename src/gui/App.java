package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.login.LoginFrame;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				new LoginFrame();
			}
		});
		
	}

}
