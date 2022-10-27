public interface User {
	final String userName = "";
	String fullName = "";
	String password = "";
	String secretWord = "";

	boolean checkLogin(String testPass);
}
