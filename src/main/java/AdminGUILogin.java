import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class AdminGUILogin extends JInternalFrame {
	private AdminGUI adminGUI;
    private Container c;
	private JTextField userName;
	private JLabel userNameLabel;
	private JPasswordField password;
	private JLabel passwordLabel;
    private JButton submitButton;

    public AdminGUILogin(AdminGUI adminGUI){
        setTitle("Admin Login");
		c = getContentPane();
		c.setLayout(null);
		c.setSize(630, 350);
		setPreferredSize(new Dimension(630, 350));
		setFont(new Font("Arial", Font.PLAIN, 20));

		userNameLabel = new JLabel("User Name");
		userNameLabel.setSize(300, 20);
		userNameLabel.setLocation(10, 100);
		c.add(userNameLabel);

		userName = new JTextField();
		userName.setSize(300, 20);
		userName.setLocation(320, 100);
		c.add(userName);

		passwordLabel = new JLabel("Password");
		passwordLabel.setSize(300, 20);
		passwordLabel.setLocation(10, 140);
		c.add(passwordLabel);

        password = new JPasswordField();
		password.setSize(300, 20);
		password.setLocation(320, 140);
		c.add(password);

        submitButton = new JButton();
		submitButton.setText("Login");
		submitButton.setSize(200, 25);
		submitButton.setLocation(265, 250);
		c.add(submitButton);

		submitButton.addActionListener(this::actionListener);

        setVisible(true);
		this.adminGUI = adminGUI;
    }

	public void actionListener(ActionEvent e) {
		String uname = userName.getText();
		String passwd = new String(password.getPassword());
		adminGUI.communicateLoginData(uname, passwd);
	}
}