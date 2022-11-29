package OOP_project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Swing_classes {
	public static String create_gui(String message) {
		String name = JOptionPane.showInputDialog(message);
	    return name;
	}
	public static void close_gui() {
		JOptionPane.getRootFrame().dispose(); 
	}
	public static void show_message(String message) {
		JFrame f2=new JFrame();  
    	JOptionPane.showMessageDialog(f2,message);
	}
	public static String multi_input() {
		JTextField hostel = new JTextField();
		JTextField day = new JTextField();
		JTextField time = new JTextField();
		
		Object[] message = {
		    "Hostel:", hostel,
		    "Day:", day,
		    "Time(in terms of a 24 hr clock):",time,
		};
		JOptionPane.showConfirmDialog(null,message);
		return hostel.getText()+","+day.getText()+","+time.getText();
	}
	
	
}
