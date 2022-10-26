import java.util.ArrayList;
import java.util.Scanner;

public class Student implements User {
	class Wash {
		private final double weight;
		private boolean isRecvd;

		public Wash(double weight) {
			this.weight = weight;
			this.isRecvd = false;
		}
	}
	
	class Plan {
		private final boolean isIron;
		private final int numWashes;
		private Wash[] washes;

		public Plan(boolean isIron, int numWashes) {
			this.isIron = isIron;
			this.numWashes = numWashes;
			this.washes = new Wash[numWashes];
		}
	}
	
	public final String userName;
	private ArrayList<Plan> plans = new ArrayList<Plan>();
	private String fullName;
	private String password;
	private String secretWord;

	public Student(String userName, String fullName, String password, String secretWord) {
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.secretWord = secretWord;
	}

	public boolean checkLogin(String testPass) {
		if (testPass == password) {
			System.out.println(fullName + " has logged in.");
			return true;
		} else {
			return false;
		}
	}

	public void resetPassword(String testWord) {
		Scanner sc = new Scanner(System.in);
		if (testWord.equals(secretWord)) {
			password = sc.nextLine();
			System.out.println("Password Reset");
		}
		sc.close();
	}
}
