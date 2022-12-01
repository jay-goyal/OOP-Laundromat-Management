import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {
	public String userName;
	protected String fullName;
	protected String password;
	protected String secretWord;

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

	public String getFullName() {
		return fullName;
	}
}
