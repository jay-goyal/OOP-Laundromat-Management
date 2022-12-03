import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Student extends User {
    
    public Hostel hostel;
    private String bitsID;
    Plan plan;
    
    public static StudentFileWriter studentFileWriter;

    public Student(String userName, String fullName, String password, String secretWord, Hostel hostel, String bitsID, StudentFileWriter studentFileWriter) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.bitsID = bitsID;
        this.secretWord = secretWord;
        this.hostel = hostel;
        
        
        Student.studentFileWriter = studentFileWriter;
    }


    //I have updated this method
    public static void register(String userName, String fullName, String password, String secretWord, String ID, String phoneNumber, Hostel hostel, WashPlan washPlan) throws IOException {
        try {
            synchronized (studentFileWriter.writeLock) {
                studentFileWriter.regUserToFile(userName, fullName, password, secretWord, ID, phoneNumber, hostel,washPlan);
                studentFileWriter.writeLock.notify();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            Student student = new Student(userName, fullName, password, secretWord, hostel, ID, studentFileWriter);
            synchronized (studentFileWriter.writeLock) {
                studentFileWriter.writeStudentToFile(student, false);
                studentFileWriter.writeLock.notify();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Plan getPlan() {
    	return plan;
    }


    public void dropWash(double Weight,String today) {
    	String allotedDay=hostel.getDay();
		
		if(!today.equals(allotedDay)) {
			Swing_classes.show_message("You can only drop laundry on your alloted day. Drop is cancelled");
			return;
		}
		WashPlan washplan=plan.getWashPlan();
		
		if(plan.getNumWashGiven()>=washplan.numWashes) {
			plan.incrementNumWashGiven();
			plan.incrementExtraCharge(washplan.weightPerWash);
			Swing_classes.show_message("You have been charged the cost of one extra wash as you have exhausted the number of washes available in your plan");
			plan.setExpense(plan.getExpense()+plan.getExtraCharge());
		}
		
		if(washplan.weightPerWash<Weight) {
			plan.incrementExtraCharge((Weight-washplan.weightPerWash)*20);
			Swing_classes.show_message("You have been charged Rs "+ Double.toString((Weight-washplan.weightPerWash)*20)+"extra");
			plan.setExpense(plan.getExpense()+plan.getExtraCharge());
		}
		plan.addWash(new Wash(today,"Dropped",plan.getExpense()));
		String deliveryDate=LocalDate.parse(today).plusDays(2).toString();
		
		Swing_classes.show_message("Laundry dropped. It will be delivered on "+deliveryDate);
		
    }
    
    
    public void checkStatus() {
    	ArrayList<Wash> allWash=plan.getWashList();
    	Wash currentWash=allWash.get(0);
    	Swing_classes.show_message(currentWash.getstatus());
    }
    String s="";
    
    public void checkExpense() {
    	for(Wash wash:plan.getWashList()) {
    		s+="\n"+wash.toString();
    	}
    	s+="\n"+plan.getExpense();
    	Swing_classes.show_message(s);
    }


    public String getID() {
        return this.bitsID;
    }
}


