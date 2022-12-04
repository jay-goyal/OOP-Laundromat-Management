import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ScheduleDeliveryGUI extends JInternalFrame{
	private AdminGUI adminGui;
    private Container c;
    private JTextField hostel;
	private JLabel hostelLabel;
	private JButton submitButton;
    private JTextField day;
    private JLabel dayLabel;
    private JTextField time;
    private JLabel timeLabel;


public ScheduleDeliveryGUI(AdminGUI adminGui) {
    setTitle("Schedule Delivery");
		c = getContentPane();
		c.setLayout(null);
		c.setSize(700, 350);
		setPreferredSize(new Dimension(700, 350));
		setFont(new Font("Arial", Font.PLAIN, 20));

		hostelLabel = new JLabel("Hostel");
		hostelLabel.setSize(300, 20);
		hostelLabel.setLocation(10, 100);
		c.add(hostelLabel);

		hostel = new JTextField();
		hostel.setSize(300, 20);
		hostel.setLocation(320, 100);
		c.add(hostel);

		dayLabel = new JLabel("Day");
		dayLabel.setSize(300, 20);
		dayLabel.setLocation(10, 140);
		c.add(dayLabel);

        day = new JTextField();
		day.setSize(300, 20);
		day.setLocation(320, 140);
		c.add(day);

        timeLabel = new JLabel("Time");
		timeLabel.setSize(300, 20);
		timeLabel.setLocation(10, 180);
		c.add(timeLabel);

		time = new JTextField();
		time.setSize(300, 20);
		time.setLocation(320, 180);
		c.add(time);

        submitButton = new JButton();
		submitButton.setText("Schedule");
		submitButton.setSize(200, 25);
		submitButton.setLocation(265, 250);
		c.add(submitButton);

		submitButton.addActionListener(this::actionListener);

		this.adminGui = adminGui;

        setVisible(true);
    }

	public void actionListener(ActionEvent e) {
		String hostelStr = hostel.getText();
		String dayStr = day.getText();
		String timeStr = time.getText();
		adminGui.communicateSchdData(hostelStr, dayStr, timeStr);
	}
}
