import java.util.Scanner;

public abstract class User {
	public final String userName = "";
	private String fullName = "";
	private String password = "";
	private String secretWord = "";

	public boolean checkLogin(String testPass) {
		if (testPass.equals(password)) {
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
