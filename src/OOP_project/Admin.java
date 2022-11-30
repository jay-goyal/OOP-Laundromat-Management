package OOP_project;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Admin extends User {
	public final String userName;
	private String fullName;
	private String password;
	private String secretWord;

	static {
		ScheduledExecutorService executorService;
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(Admin::checkDelivery, 0, 1, TimeUnit.DAYS);
	}

	public Admin(String userName, String fullName, String password, String secretWord) {
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.secretWord = secretWord;
	}

	public void changeStudentPlan(Student student, boolean isIron, int numWashes, double costPerWash) {
		ArrayList<Student.Plan> plans = student.getPlans();
		Student.Plan activePlan = plans.get(plans.size() - 1);
		if (activePlan.getWashesGiven() >= numWashes) {
			student.setPlans(isIron, activePlan.getWashesGiven() + 1, costPerWash);
		}
		student.setPlans(isIron, numWashes, costPerWash);
	}

	public void getWeekLaundry(Student[] students) {
		ArrayList<Student.Wash> washes = new ArrayList();
		for (Student student : students) {
			if (ChronoUnit.DAYS.between(student.getLastWash().getDateGiven(), LocalDate.now()) < 7) {
				washes.add(student.getLastWash());
			}
		}
	}

	public static void checkDelivery() {
				Student[] students = new Student[10];
		for (Student student : students) {
			if (ChronoUnit.DAYS.between(student.getLastWash().getDateGiven(), LocalDate.now()) >= 2) {
				ArrayList<Student.Plan> plans = student.getPlans();
				Student.Plan activePlan = plans.get(plans.size() - 1);
				activePlan.returnWash();
			}
		}
	}

	public double getRevenue(Student[] students) {
		double revenue = 0;
		for (Student student: students) {
			revenue += student.cost();
		}
		return revenue;
	}  
	
	//following are the new classes added
	public static void adminRegister() throws IOException {
		
		Writer out = null;
		Swing_classes.show_message("You will be allowed to register as admin only if you can correctly answer the secret question in the first attempt");
		String check=Swing_classes.create_gui("Which detergent is used in laundromat");
		if(check.equals("Surf Excel")) {
			String username=Swing_classes.create_gui("Enter username");
			String password=Swing_classes.create_gui("Enter password");
			File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Admin_data.txt");

			try {
			    Scanner scanner = new Scanner(file);

			    //now read the file line by line
			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        String[] admin_data=line.split(",");
			        
			        if(admin_data[0].equals(username)&& admin_data[1].equals(password)) {  
			        	Swing_classes.show_message("You have already registered!! "); 
			        	return ;
			        }
			    }
			    try {
					String string_data=username+","+password;
					out = new FileWriter("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Admin_data.txt",true);
					out.write(System.lineSeparator());
					out.write(string_data);
					}
				catch(Exception e) {
					System.out.println(e);
					}
				finally {
					out.close();
					}
			} catch(FileNotFoundException e) { 
				System.out.println(e.getMessage());
			}
		}
		else {
			Swing_classes.show_message("Wrong answer.Bye!!");
			return;
		}
	}
	
	public static void adminPrintDetails() {
		String s="The Student details are as follows-";
		File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Student_data.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] student_data=line.split(",");
		        s+="\n Name:"+student_data[1]+",  Hostel:"+student_data[4]+",  Plan:"+student_data[5];
		        
		    }
		} catch(FileNotFoundException e) { 
			System.out.println(e.getMessage());
		}
		Swing_classes.show_message(s);
	}
	
	public static void adminScheduleDelivery() throws IOException {
		String s=Swing_classes.multi_input();
		String[] data=s.split(",");
		String hostel=data[0];
		//String day=data[1];
		//String time=data[2];
		System.out.println(s);
		
		Writer out = null;
		File file = new File("C:\\Bits pilani\\OOP-Laundromat-Management-main\\Hostel_data.txt");
		
		String contents="";
		
		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        
		        String[] hostel_data=line.split(",");
		        System.out.println(hostel_data[0]+" "+hostel_data[1]+" "+hostel_data[2]);
		        if(hostel_data[0].equals(hostel)) {
		        	line=s;
		        }
		        contents+="\n"+line;
		}
		    try {
		    	out = new FileWriter(file);
	        	out.write(contents);
		    }
		    catch(IOException e) {
		    	System.out.println(e.getMessage());
		    }
		    
		}
		    catch(FileNotFoundException e) { 
			System.out.println(e.getMessage());
		}
		
	}
}
	