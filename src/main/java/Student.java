import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Student implements User {
	class Wash {
		private final double weight;
		private LocalDate dateGiven;
		private boolean isReady;
		private LocalDate dateRecvd;

		public Wash(double weight) {
			this.weight = weight;
			this.dateGiven = LocalDate.now();
			this.isReady = false;
			this.dateRecvd = null;
		}

		public Wash(double weight, LocalDate dateGiven) {
			this.weight = weight;
			this.dateGiven = dateGiven;
			this.isReady = false;
			this.dateRecvd = null;
		}
	}

	class Plan {
		private final boolean isIron;
		private final int numWashes;
		private final double costPerWash;
		private int washesGiven;
		private boolean isFinished;
		private boolean isWashGiven;
		private Wash[] washes;

		public Plan(boolean isIron, int numWashes) {
			this.isIron = isIron;
			this.numWashes = numWashes;
			this.washesGiven = 0;
			this.washes = new Wash[numWashes];
			this.isFinished = false;
			this.costPerWash = 220;
		}

		public Plan(boolean isIron, int numWashes, double costPerWash) {
			this.isIron = isIron;
			this.numWashes = numWashes;
			this.washesGiven = 0;
			this.washes = new Wash[numWashes];
			this.isFinished = false;
			this.costPerWash = costPerWash;
		}

		public void addWash(Wash wash) {
			if (washesGiven == numWashes) {
				return;
			}
			washes[washesGiven] = wash;
			washesGiven++;
		}

		public void returnWash() {
			isWashGiven = false;
		}

		public boolean getIsWashGiven() {
			return isWashGiven;
		}

		public boolean getIsFinished() {
			if (washesGiven == numWashes) {
				isFinished = true;
			}
			return isFinished;
		}

		public boolean getIsIron() {
			return isIron;
		}

		public double getPlanCost() {
			return costPerWash * numWashes;
		}
	}

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

	public boolean checkLogin(String testPass) {
		if (testPass == password) {
			System.out.println(fullName + " has logged in.");
			return true;
		} else {
			return false;
		}
	}

	public static Student register(String userName, String fullName, String password, String secretWord,
			Hostel hostel) {
		return new Student(
				userName,
				fullName,
				password,
				secretWord,
				hostel);
	}

	public void resetPassword(String testWord) {
		Scanner sc = new Scanner(System.in);
		if (testWord.equals(secretWord)) {
			password = sc.nextLine();
			System.out.println("Password Reset");
		}
		sc.close();
	}

	public void newPlan(boolean isIron, int numWashes) {
		plans.add(new Plan(isIron, numWashes));
	}

	public void newPlan(boolean isIron, int numWashes, double cost) {
		plans.add(new Plan(isIron, numWashes, cost));
	}

	public void giveWash(double weight) {
		Plan activePlan = plans.get(plans.size() - 1);
		if (activePlan.getIsWashGiven()) {
			System.out.println("Wash already given");
			return;
		}
		plans.get(plans.size() - 1).addWash(new Wash(weight));
	}

	public void giveWash(double weight, LocalDate date) {
		Plan activePlan = plans.get(plans.size() - 1);
		if (activePlan.getIsFinished()) {
			System.out
					.println("Plan has been exceeded. 50Rs penalty will be charged. Get new plan to avoid in future.");
			newPlan(activePlan.getIsIron(), 1, 270);
			activePlan = plans.get(plans.size() - 1);
		}
		if (activePlan.getIsWashGiven()) {
			System.out.println("Wash already given");
			return;
		}
		activePlan.addWash(new Wash(weight, date));
	}

	public double cost() {
		double totalCost = 0;
		for (Plan plan : plans) {
			totalCost += plan.getPlanCost();
		}
		return totalCost;
	}
}
