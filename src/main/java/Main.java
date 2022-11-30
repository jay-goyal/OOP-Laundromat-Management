import java.util.*;
import javax.swing.*;
import java.io.*;

public class Main {
	
	
	//this method is for the initial operations like register,drop laundry,check balance.
	//right now only register functionality is available on pressing S
	public static void action() {
		String check=Swing_classes.create_gui("Enter S to register and L to login");
		if(check.equals("S")) {
			try {
				StudentGUI.registerInput();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}			
		}
	}
	
	
	public static void main(String[] args)throws IOException {
		action();
		//Admin.adminRegister();
		//Admin.adminPrintDetails();
		//Swing_classes.multi_input();
		//Admin.adminScheduleDelivery();
	}
}
