import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

class RegData {
    String userName;
    String fullName;
    String password;
    String secretWord;
    String bitsId;
    String phoneNumber;
    Hostel hostel;

    public RegData(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.secretWord = secretWord;
        this.bitsId = bitsId;
        this.phoneNumber = phoneNumber;
        this.hostel = hostel;
    }
}

public class StudentGUI implements Runnable {
    protected final Thread t;
    private JInternalFrame internalFrame;
    private final JFrame frame;
    private String typeOfFrame;
    private boolean isClosingFlag;
    private boolean shouldReg;
    private RegData regData;
    private static StudentFileWriter studentFileWriter;

    public StudentGUI(StudentFileWriter studentFileWriter) {
        this.t = new Thread(this);
        this.frame = new JFrame();
        typeOfFrame = "Reg";
        frame.setBounds(50, 50, 400, 150);
        frame.setVisible(true);
        isClosingFlag = false;
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                isClosingFlag = true;
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        StudentGUI.studentFileWriter = studentFileWriter;
    }

    public void setInternalFrame() {
        switch (typeOfFrame) {
            case "Reg":
                this.internalFrame = new StudentGUIReg(this);
                frame.setTitle(internalFrame.getTitle());
                frame.setSize(internalFrame.getPreferredSize());
                internalFrame.setTitle("");
                frame.add(internalFrame);
                break;
            default:
                t.interrupt();
        }
    }

    public void setTypeOfFrame(String typeOfFrame) {
        this.typeOfFrame = typeOfFrame;
        setInternalFrame();
    }

    public void run() {
        System.out.println("RUNNING");
        if (typeOfFrame.equals("Reg")) {
            if (shouldReg) {
                regSuccess(regData.userName, regData.fullName, regData.password, regData.secretWord, regData.bitsId, regData.phoneNumber, regData.hostel);
                shouldReg = false;
            }
        }
    }

    public void communicateRegData(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
        if (studentFileWriter.checkUserExists(userName, bitsId)) {
            Swing_classes.show_message("User with same user name already exists");
            Swing_classes.close_gui();
            return;
        }
        regData = new RegData(userName, fullName, password, secretWord, bitsId, phoneNumber, hostel);
        shouldReg = true;
        t.start();
    }

    public void regSuccess(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
        //Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
        //We have printing the details of the plans in a new window
        String s = "DETAILS OF WASHPLANS:\n";
        for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
            s += "\n" + plan.toString() + " " + "Iron included:" + plan.isIron + " " + "Number of washes in plan:" + plan.numWashes + " " + "Cost of each wash:" + plan.costPerWash;
        }
        Swing_classes.show_message(s);
        String washPlan = Swing_classes.create_gui("WashPlan");

        //write this data into the file
        try {
            Student.studentFileWriter = studentFileWriter;
            Student.register(userName, fullName, password, secretWord, bitsId, phoneNumber, hostel, WashPlan.valueOf(washPlan));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        frame.remove(internalFrame);
        setTypeOfFrame("Login");
    }

    public JFrame getFrame() {
        return frame;
    }
}
