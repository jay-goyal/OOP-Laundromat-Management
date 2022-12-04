import java.awt.*;
import java.text.*;
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

public class ReceiveWashGUI extends JInternalFrame {
    private StudentGUI studentGUI;
    private Container c;
    private JTextField ID;
    private JLabel IDLabel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField Date;
    ;
    private JLabel DateLabel;
    private JButton ReceiveButton;
    private static StudentFileWriter studentFileWriter;

    public ReceiveWashGUI(StudentGUI studentGUI) {
        this.studentGUI = studentGUI;
        setTitle("Receive Laundry");
        c = getContentPane();
        c.setLayout(null);
        c.setSize(630, 350);
        setPreferredSize(new Dimension(630, 350));
        setFont(new Font("Arial", Font.PLAIN, 20));

        IDLabel = new JLabel("ID");
        IDLabel.setSize(300, 20);
        IDLabel.setLocation(10, 20);
        c.add(IDLabel);

        ID = new JTextField();
        ID.setSize(300, 20);
        ID.setLocation(320, 20);
        c.add(ID);

        DateLabel = new JLabel("Date(dd-mm-yyyy)");
        DateLabel.setSize(300, 20);
        DateLabel.setLocation(10, 100);
        c.add(DateLabel);

        Date = new JTextField();
        Date.setSize(300, 20);
        Date.setLocation(320, 100);
        c.add(Date);

        ReceiveButton = new JButton();
        ReceiveButton.setText("Receive");
        ReceiveButton.setSize(200, 25);
        ReceiveButton.setLocation(265, 250);
        c.add(ReceiveButton);

        ReceiveButton.addActionListener(e -> {
            try {
                actionListener(e);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        setVisible(true);
    }

    public void actionListener(ActionEvent e) throws ParseException {
        String Id = ID.getText();
        Date date = dateFormat.parse(Date.getText());
        dateFormat.applyPattern("EEE, d MMM yyyy");
        String today = dateFormat.format(date);
        studentGUI.communicateReceiveData(Id, today);
    }

}
	