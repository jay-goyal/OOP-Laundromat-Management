import java.util.Scanner;

public class Admin implements User {
	public final String userName;
	private String fullName;
	private String password;
	private String secretWord;

	public Admin(String userName, String fullName, String password, String secretWord) {
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
