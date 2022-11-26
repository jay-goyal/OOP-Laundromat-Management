package OOP_project;

import javax.swing.JOptionPane;

public class Swing_classes {
	public static String create_gui(String message) {
		String name = JOptionPane.showInputDialog(message);
	    return name;
	}
	public static void close_gui() {
		JOptionPane.getRootFrame().dispose(); 
	}
	
}
