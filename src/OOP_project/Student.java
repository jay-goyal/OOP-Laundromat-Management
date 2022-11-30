package OOP_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Student extends User {
	public class Wash {
		private final double weight;
		private LocalDate dateGiven;
		private boolean isReady;
		private LocalDate dateRecvd;

		private Wash(double weight) {
			this.weight = weight;
			this.dateGiven = LocalDate.now();
			this.isReady = false;
			this.dateRecvd = null;
		}

		private Wash(double weight, LocalDate dateGiven) {
			this.weight = weight;
			this.dateGiven = dateGiven;
			this.isReady = false;
			this.dateRecvd = null;
		}

		private void setDateRecvd(LocalDate date) {
			dateRecvd = date;
		}

		public LocalDate getDateGiven() {
			return dateGiven;
		}
	}

	public class Plan {
		private final boolean isIron;
		private final int numWashes;
		private final double costPerWash;
		private int washesGiven;
		private boolean isFinished;
		private boolean isWashGiven;
		private boolean isWashBack;
		private Wash[] washes;

		private Plan(boolean isIron, int numWashes) {
			this.isIron = isIron;
			this.numWashes = numWashes;
			this.washesGiven = 0;
			this.washes = new Wash[numWashes];
			this.isFinished = false;
			this.isWashGiven = false;
			this.isWashBack = false;
			this.costPerWash = 220;
		}

		private Plan(boolean isIron, int numWashes, double costPerWash) {
			this.isIron = isIron;
			this.numWashes = numWashes;
			this.washesGiven = 0;
			this.washes = new Wash[numWashes];
			this.isFinished = false;
			this.isWashGiven = false;
			this.isWashBack = false;
			this.costPerWash = costPerWash;
		}

		private void addWash(Wash wash) {
			if (washesGiven == numWashes) {
				return;
			}
			isWashGiven = true;
			isWashBack = false;
			washes[washesGiven] = wash;
			washesGiven++;
		}

		public void returnWash() {
			isWashGiven = false;
			isWashBack = true;
		}

		public int getWashesGiven() {
			return washesGiven;
		}

		private void recvWash() {
			washes[washesGiven].setDateRecvd(LocalDate.now());
		}

		private boolean getIsWashGiven() {
			return isWashGiven;
		}

		private boolean getIsWashBack() {
			return isWashBack;
		}

		private boolean getIsFinished() {
			if (washesGiven == numWashes) {
				isFinished = true;
			}
			return isFinished;
		}

		private boolean getIsIron() {
			return isIron;
		}

		private double getCostPerWash() {
			return costPerWash;
		}

		private double getPlanCost() {
			return costPerWash * numWashes;
		}

		private Wash getLatestWash() {
			return washes[washesGiven];
		}
	}

	public static final int WEIGHT_LIMIT = 6;
	public final String userName;
	public Hostel hostel;
	private ArrayList<Plan> plans = new ArrayList<Plan>();
	private String fullName;
	private String password;
	private String secretWord;

	public Student(String userName, String fullName, String password, String secretWord, Hostel hostel) {
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.secretWord = secretWord;
		this.hostel = hostel;
	}
	
	
//I have updated this method
	public static void register(String userName, String fullName, String password, String secretWord,String ID,String phoneNumber, Hostel hostel,WashPlan washPlan)throws IOException {
		Writer out = null;
		Writer out2=null;
		try {
			String string_data=userName+","+fullName+","+password+","+secretWord+","+ID+","+phoneNumber+","+hostel+","+washPlan+",Balance:"+(washPlan.numWashes)*washPlan.costPerWash;
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
		String fileName="C:\\Bits pilani\\OOP-Laundromat-Management-main\\"+userName+"_"+password+".txt";
		
		try {
			File myObj = new File(fileName);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        
				out2=new FileWriter(fileName,true);
		        out2.write("Washes given:0");
		        
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }finally {
				out2.close();
				}	
	}
	
	
	

	public void dropWash() {
		String ID=Swing_classes.create_gui("Enter ID");
		String weight=Swing_classes.create_gui("Enter weight of laundry");
		String date=Swing_classes.create_gui("Enter date");
	}
	
	public ArrayList<Plan> getPlans() {
		return plans;
	}

	public void setPlans(boolean isIron, int numWashes, double costPerWash) {
		plans.set(plans.size() - 1, new Plan(isIron, numWashes, costPerWash)); 
	}

	public void newPlan(WashPlan plan) {
		plans.add(new Plan(plan.isIron, plan.numWashes, plan.costPerWash));
	}

	private void newPlan(boolean isIron, int numWashes, double cost) {
		plans.add(new Plan(isIron, numWashes, cost));
	}

	public void giveWash(double weight) {
		Plan activePlan = plans.get(plans.size() - 1);
		if (activePlan.getIsFinished()) {
			System.out
					.println("Plan has been exceeded. 50Rs penalty will be charged. Get new plan to avoid in future.");
			newPlan(activePlan.getIsIron(), 1, activePlan.getCostPerWash() + 50);
			activePlan = plans.get(plans.size() - 1);
		}
		if (activePlan.getIsWashGiven()) {
			System.out.println("Wash already given");
			return;
		}
		while (weight > 0) {
			activePlan.addWash(new Wash(weight));
			weight = weight - WEIGHT_LIMIT;
			if (weight < 0) {
				weight = 0;
			}
		}
	}

	public void giveWash(double weight, LocalDate date) {
		Plan activePlan = plans.get(plans.size() - 1);
		if (activePlan.getIsFinished()) {
			System.out
					.println("Plan has been exceeded. 50Rs penalty will be charged. Get new plan to avoid in future.");
			newPlan(activePlan.getIsIron(), 1, activePlan.getCostPerWash() + 50);
			activePlan = plans.get(plans.size() - 1);
		}
		if (activePlan.getIsWashGiven()) {
			System.out.println("Wash already given");
			return;
		}
		while (weight > 0) {
			activePlan.addWash(new Wash(weight, date));
			weight = weight - WEIGHT_LIMIT;
			if (weight < 0) {
				weight = 0;
			}
		}
	}

	public void recvWash() {
		Plan activePlan = plans.get(plans.size() - 1);
		if (!activePlan.getIsWashGiven()) {
			System.out.println("No wash given");
			return;
		}
		activePlan.recvWash();
	}

	public double cost() {
		double totalCost = 0;
		for (Plan plan : plans) {
			totalCost += plan.getPlanCost();
		}
		return totalCost;
	}

	public Wash getLastWash() {
		return plans.get(plans.size() - 1).getLatestWash();
	}
}
