import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;

public class LaundryStatusGUI extends JInternalFrame{
    private Container c;
    private JTextField bitsId;
	private JLabel bitsIdLabel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField Date;
    private JLabel DateLabel;
    private JButton submitButton;
    
    public LaundryStatusGUI() {

        setTitle("Check Laundry Status");
		c = getContentPane();
		c.setLayout(null);
		c.setSize(630, 350);
		setPreferredSize(new Dimension(630, 350));
		setFont(new Font("Arial", Font.PLAIN, 20));
        
    bitsIdLabel = new JLabel("BITS ID");
		bitsIdLabel.setSize(300, 20);
		bitsIdLabel.setLocation(10, 100);
		c.add(bitsIdLabel);

		bitsId = new JTextField();
		bitsId.setSize(300, 20);
		bitsId.setLocation(320, 100);
		c.add(bitsId);

        DateLabel = new JLabel("Date");
        DateLabel.setSize(300, 20);
        DateLabel.setLocation(10, 140);
        c.add(DateLabel);

        Date = new JTextField();
        Date.setSize(300, 20);
        Date.setLocation(320, 140);
        c.add(Date);

        submitButton = new JButton();
		submitButton.setText("Check Status");
		submitButton.setSize(200, 25);
		submitButton.setLocation(265, 250);
		c.add(submitButton);

        setVisible(true);
    }

    }
