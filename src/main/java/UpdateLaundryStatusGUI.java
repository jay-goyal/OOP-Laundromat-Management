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

public class UpdateLaundryStatusGUI extends JInternalFrame {
    private AdminGUI adminGui;
    private Container c;
    private JTextField bitsId;
    private JLabel bitsIdLabel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField Date;
    private JLabel DateLabel;
    private JLabel StatusLabel;
    private JComboBox UpdateStatus;
    private String[] StatusArray = {"Washing", "Iron", "Drying", "On Delivery"};
    private JButton submitButton;
    private String currentStatus;

    public UpdateLaundryStatusGUI(AdminGUI adminGui) {

        setTitle("Update Laundry Status");
        c = getContentPane();
        c.setLayout(null);
        c.setSize(700, 350);
        setPreferredSize(new Dimension(700, 350));
        setFont(new Font("Arial", Font.PLAIN, 20));

        bitsIdLabel = new JLabel("BITS ID");
        bitsIdLabel.setSize(300, 20);
        bitsIdLabel.setLocation(10, 60);
        c.add(bitsIdLabel);

        bitsId = new JTextField();
        bitsId.setSize(300, 20);
        bitsId.setLocation(320, 60);
        c.add(bitsId);

        DateLabel = new JLabel("Date");
        DateLabel.setSize(300, 20);
        DateLabel.setLocation(10, 100);
        c.add(DateLabel);

        Date = new JTextField();
        Date.setSize(300, 20);
        Date.setLocation(320, 100);
        c.add(Date);

        StatusLabel = new JLabel("Select Status");
        StatusLabel.setSize(300, 20);
        StatusLabel.setLocation(10, 140);
        c.add(StatusLabel);

        UpdateStatus = new JComboBox();
        UpdateStatus.addItem(StatusArray[0]);
        UpdateStatus.addItem(StatusArray[1]);
        UpdateStatus.addItem(StatusArray[2]);
        UpdateStatus.addItem(StatusArray[3]);
        UpdateStatus.setSize(300, 20);
        UpdateStatus.setLocation(320, 140);
        c.add(UpdateStatus);

        submitButton = new JButton();
        submitButton.setText("Update Status");
        submitButton.setSize(200, 25);
        submitButton.setLocation(265, 250);
        c.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                actionListener(e);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        this.adminGui = adminGui;

        setVisible(true);
    }

    public String getCurrentStatus() {
        currentStatus = String.valueOf(UpdateStatus.getSelectedItem());
        return currentStatus;
    }

    public void actionListener(ActionEvent e) throws ParseException {
        String status = getCurrentStatus();
        String Id = bitsId.getText();
        Date date = dateFormat.parse(Date.getText());
        dateFormat.applyPattern("EEE, d MMM yyyy");
        String today = dateFormat.format(date);
        adminGui.communicateUpdateData(Id, today, status);
    }
}
