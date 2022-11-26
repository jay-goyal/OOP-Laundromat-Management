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
	public static void register_student()throws IOException {
		Writer out = null;
		
		//We have printing the details of the plans in a new window
		JFrame f1=new JFrame();
		String s="";
		for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
			s+="\n"+plan.toString()+" "+"Iron included:"+plan.isIron+" "+"Number of washes in plan:"+plan.numWashes+" "+"Cost of each wash:"+plan.costPerWash;	 
		}
		
		//Start taking input from user
		String username=Swing_classes.create_gui("Enter username");
		
		//Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
		File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Student_data.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] student_data=line.split(",");
		        if(student_data[0].equals(username)) { 
		        	
		        	JFrame f2=new JFrame();  
		        	JOptionPane.showMessageDialog(f2,"Sorry, choose a different username! "); 
		        	username=Swing_classes.create_gui("Enter username");
		        }
		        
		    }
		} catch(FileNotFoundException e) { 
			System.out.println(e.getMessage());
		}
		//Take rest of the input
		String full_name=Swing_classes.create_gui("Enter full name");
		String password=Swing_classes.create_gui("Enter password");
		String secret_word=Swing_classes.create_gui("Enter secret_word");
//		Hostel hostel=Swing_classes.create_gui("Enter hostel");
//		WashPlan washPlan=Swing_classes.create_gui("Enter washplan");
		
		Student bits_student=Student.register(username,full_name,password, secret_word, Hostel.VY,WashPlan.F4);
		
		//write this data into the file
		try {
			String string_data=username+","+full_name+","+password+","+secret_word+",VY,F4";
			out = new FileWriter("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Student_data.txt",true);
			out.write(System.lineSeparator());
			out.write(string_data);
			}
		catch(Exception e) {
			System.out.println(e);
			}
		finally {
			out.close();
			}
		
		Swing_classes.close_gui();
	}
	
	public static void main(String[] args)throws IOException {
		action();
	}
}
