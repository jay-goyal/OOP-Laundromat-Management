package OOP_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentGUI {
	public static int registerInput()throws IOException {
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		JTextField fullname = new JTextField();
		JTextField secretWord = new JTextField();
		JTextField ID = new JTextField();
		JTextField phoneNumber = new JTextField();
		JTextField hostel = new JTextField();
		
		Object[] message = {
			"Username:",username,
			"Password:",password,
			"Full name:",fullname,
			"Secret Word:",secretWord,
			"ID:",ID,
			"Phone Number:",phoneNumber,
		    "Hostel:", hostel,
		};
		JOptionPane.showConfirmDialog(null,message);
		
		Writer out = null;
		
				
		//Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
		File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Student_data.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] student_data=line.split(",");
		        
		        if(student_data[0].equals(username.getText())&& student_data[2].equals(password.getText())) {   
		        	Swing_classes.show_message("You have already registered!! "); 
		        	Swing_classes.close_gui();
		        	return 0;
		        }

		        
		        else if(student_data[0].equals(username.getText())) {
//		        	Swing_classes.show_message("Sorry, choose a different username! "); 
//		        	Swing_classes.close_gui();
//		        	username=Swing_classes.create_gui("Enter username");
//		        	password=Swing_classes.create_gui("Enter password");
		        }
		        
		    }
		} catch(FileNotFoundException e) { 
			System.out.println(e.getMessage());
		}
		
		//We have printing the details of the plans in a new window
		String s="DETAILS OF WASHPLANS:\n";
		for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
				s+="\n"+plan.toString()+" "+"Iron included:"+plan.isIron+" "+"Number of washes in plan:"+plan.numWashes+" "+"Cost of each wash:"+plan.costPerWash;	 
			}
		Swing_classes.show_message(s);
		String washPlan=Swing_classes.create_gui("WashPlan");

		
		
		//write this data into the file
		Student.register(username.getText(),fullname.getText(),password.getText(), secretWord.getText(),ID.getText(),phoneNumber.getText(), Hostel.valueOf(hostel.getText()),WashPlan.valueOf(washPlan));
		
		Swing_classes.close_gui();
		return 0;
	}

}
