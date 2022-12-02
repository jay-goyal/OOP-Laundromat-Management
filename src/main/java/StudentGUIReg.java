import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class StudentGUIReg extends JInternalFrame {
	private final StudentGUI studentGui;
	private Container c;
	private JTextField userName;
	private JLabel userNameLabel;
	private JPasswordField password;
	private JLabel passwordLabel;
	private JTextField fullName;
	private JLabel fullNameLabel;
	private JPasswordField secretWord;
	private JLabel secretWordLabel;
	private JTextField bitsId;
	private JLabel bitsIdLabel;
	private JTextField phoneNumber;
	private JLabel phoneNumberLabel;
	private JTextField hostel;
	private JLabel hostelLabel;
	private JButton submitButton;

	public StudentGUIReg(StudentGUI studentGui) {
		this.studentGui = studentGui;
		setTitle("Register Student");
		c = getContentPane();
		c.setLayout(null);
		c.setSize(630, 350);
		setPreferredSize(new Dimension(630, 350));
		setFont(new Font("Arial", Font.PLAIN, 20));

		userNameLabel = new JLabel("User Name");
		userNameLabel.setSize(300, 20);
		userNameLabel.setLocation(10, 20);
		c.add(userNameLabel);

		userName = new JTextField();
		userName.setSize(300, 20);
		userName.setLocation(320, 20);
		c.add(userName);

		passwordLabel = new JLabel("Password");
		passwordLabel.setSize(300, 20);
		passwordLabel.setLocation(10, 50);
		c.add(passwordLabel);

		password = new JPasswordField();
		password.setSize(300, 20);
		password.setLocation(320, 50);
		c.add(password);

		fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setSize(300, 20);
		fullNameLabel.setLocation(10, 80);
		c.add(fullNameLabel);

		fullName = new JTextField();
		fullName.setSize(300, 20);
		fullName.setLocation(320, 80);
		c.add(fullName);

		secretWordLabel = new JLabel("Secret Word");
		secretWordLabel.setSize(300, 20);
		secretWordLabel.setLocation(10, 110);
		c.add(secretWordLabel);

		secretWord = new JPasswordField();
		secretWord.setSize(300, 20);
		secretWord.setLocation(320, 110);
		c.add(secretWord);

		bitsIdLabel = new JLabel("BITS ID");
		bitsIdLabel.setSize(300, 20);
		bitsIdLabel.setLocation(10, 140);
		c.add(bitsIdLabel);

		bitsId = new JTextField();
		bitsId.setSize(300, 20);
		bitsId.setLocation(320, 140);
		c.add(bitsId);

		phoneNumberLabel = new JLabel("Phone Number");
		phoneNumberLabel.setSize(300, 20);
		phoneNumberLabel.setLocation(10, 170);
		c.add(phoneNumberLabel);

		phoneNumber = new JTextField();
		phoneNumber.setSize(300, 20);
		phoneNumber.setLocation(320, 170);
		c.add(phoneNumber);

		hostelLabel = new JLabel("Hostel");
		hostelLabel.setSize(300, 20);
		hostelLabel.setLocation(10, 200);
		c.add(hostelLabel);

		hostel = new JTextField();
		hostel.setSize(300, 20);
		hostel.setLocation(320, 200);
		c.add(hostel);

		submitButton = new JButton();
		submitButton.setText("Register");
		submitButton.setSize(200, 25);
		submitButton.setLocation(265, 250);
		c.add(submitButton);

		submitButton.addActionListener(this::actionListener);

		setVisible(true);
	}

	public void actionListener(ActionEvent e) {
			studentGui.communicateRegData(userName.getText(),fullName.getText(),new String(password.getPassword()),new String( secretWord.getPassword()),bitsId.getText(),phoneNumber.getText(), Hostel.valueOf(hostel.getText()));
	}
//	public static int registerInput() throws IOException {
//		JTextField username = new JTextField();
//		JTextField password = new JTextField();
//		JTextField fullname = new JTextField();
//		JTextField secretWord = new JTextField();
//		JTextField ID = new JTextField();
//		JTextField phoneNumber = new JTextField();
//		JTextField hostel = new JTextField();
//
//		Object[] message = {
//			"Username:",username,
//			"Password:",password,
//			"Full name:",fullname,
//			"Secret Word:",secretWord,
//			"ID:",ID,
//			"Phone Number:",phoneNumber,
//		    "Hostel:", hostel,
//		};
//		JOptionPane.showConfirmDialog(null,message);
//
//		Writer out = null;
//
//
//		//Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
//		Path relPath = Paths.get("files/Student_data.txt");
//		Path absPath = relPath.toAbsolutePath();
//		File file = new File(absPath.toUri());
//
//		try {
//		    Scanner scanner = new Scanner(file);
//
//		    //now read the file line by line
//		    while (scanner.hasNextLine()) {
//		        String line = scanner.nextLine();
//		        String[] student_data=line.split(",");
//
//		        if(student_data[0].equals(username.getText())&& student_data[2].equals(password.getText())) {
//		        	Swing_classes.show_message("You have already registered!! ");
//		        	Swing_classes.close_gui();
//		        	return 0;
//		        }
//
//
//		        else if(student_data[0].equals(username.getText())) {
////		        	Swing_classes.show_message("Sorry, choose a different username! ");
////		        	Swing_classes.close_gui();
////		        	username=Swing_classes.create_gui("Enter username");
////		        	password=Swing_classes.create_gui("Enter password");
//		        }
//
//		    }
//		} catch(FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//
//		//We have printing the details of the plans in a new window
//		String s="DETAILS OF WASHPLANS:\n";
//		for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
//				s+="\n"+plan.toString()+" "+"Iron included:"+plan.isIron+" "+"Number of washes in plan:"+plan.numWashes+" "+"Cost of each wash:"+plan.costPerWash;
//			}
//		Swing_classes.show_message(s);
//		String washPlan=Swing_classes.create_gui("WashPlan");
//
//
//
//		//write this data into the file
//		Student.register(username.getText(),fullname.getText(),password.getText(), secretWord.getText(),ID.getText(),phoneNumber.getText(), Hostel.valueOf(hostel.getText()),WashPlan.valueOf(washPlan));
//
//		Swing_classes.close_gui();
//		return 0;
//	}

}

