package OOP_project;

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
				register_student();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	public static int register_student()throws IOException {
		Writer out = null;
		
		//We have printing the details of the plans in a new window
		JFrame f1=new JFrame();
		String s="";
		for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
			s+="\n"+plan.toString()+" "+"Iron included:"+plan.isIron+" "+"Number of washes in plan:"+plan.numWashes+" "+"Cost of each wash:"+plan.costPerWash;	 
		}
		
		//Start taking input from user
		String username=Swing_classes.create_gui("Enter username");
		String password=Swing_classes.create_gui("Enter password");
		
		//Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
		File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Student_data.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] student_data=line.split(",");
		        
		        if(student_data[0].equals(username)&& student_data[2].equals(password)) {   
		        	Swing_classes.show_message("You have already registered!! "); 
		        	Swing_classes.close_gui();
		        	return 0;
		        }

		        
		        else if(student_data[0].equals(username)) {
		        	Swing_classes.show_message("Sorry, choose a different username! "); 
		        	Swing_classes.close_gui();
		        	username=Swing_classes.create_gui("Enter username");
		        	password=Swing_classes.create_gui("Enter password");
		        }
		        
		    }
		} catch(FileNotFoundException e) { 
			System.out.println(e.getMessage());
		}
		//Take rest of the input
		String full_name=Swing_classes.create_gui("Enter full name");
		
		String secret_word=Swing_classes.create_gui("Enter secret word");
		String ID=Swing_classes.create_gui("Enter ID");
		String phoneNumber=Swing_classes.create_gui("Enter phoneNumber");
		String hostel=Swing_classes.create_gui("Enter hostel");
		String washPlan=Swing_classes.create_gui("Enter washplan");
		
		//write this data into the file
		Student.register(username,full_name,password, secret_word,ID,phoneNumber, Hostel.valueOf(hostel),WashPlan.valueOf(washPlan));
		
		Swing_classes.close_gui();
		return 0;
	}
	
	public static void main(String[] args)throws IOException {
		//action();
		//Admin.admin_register();
		//Admin.admin_print_details();
		//Swing_classes.multi_input();
		Admin.adminScheduleDelivery();
	}
}
