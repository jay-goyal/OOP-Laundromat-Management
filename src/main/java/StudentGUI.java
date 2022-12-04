import GUI_Templates.SwingSingleInput_GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

 class DropData {
    double weight;
    String date;

     public DropData(double weight, String date) {
         this.weight = weight;
         this.date = date;
     }
 }

public class StudentGUI implements Runnable {
    protected final Thread t;
    private JInternalFrame internalFrame;
    private final JFrame frame;
    private String typeOfFrame;
    private boolean isClosingFlag;
    private boolean shouldRun;
    private RegData regData;
    private DropData dropData;
    private Student student = null;
    private static StudentFileWriter studentFileWriter;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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

    private void setInternalFrame() {
        switch (typeOfFrame) {
            case "Reg":
                this.internalFrame = new StudentGUIReg(this);
                frame.setTitle(internalFrame.getTitle());
                frame.setSize(internalFrame.getPreferredSize());
                internalFrame.setTitle("");
                frame.add(internalFrame);
                break;
            case "Drop":
                this.internalFrame = new DropWashGUI(this);
                frame.setTitle(internalFrame.getTitle());
                frame.setSize(internalFrame.getPreferredSize());
                internalFrame.setTitle("");
                frame.add(internalFrame);
                break;
            case "Check":
                this.internalFrame = new SwingSingleInput_GUI("Enter your ID", "Submit", this::getIDForCheck);
                frame.setTitle("Get Laundry Status");
                frame.setSize(internalFrame.getPreferredSize());
                internalFrame.setTitle("");
                frame.add(internalFrame);
                break;
            case "AllCheck":
                this.internalFrame = new SwingSingleInput_GUI("Enter your ID", "Submit", this::getIDForCheck);
                frame.setTitle("Get Laundry Status");
                frame.setSize(internalFrame.getPreferredSize());
                internalFrame.setTitle("");
                frame.add(internalFrame);
                break;
            default:
                t.interrupt();
        }
    }

    public void run() {
        System.out.println("RUNNING");
        if (shouldRun) {
            if (typeOfFrame.equals("Reg")) {
                regSuccess(regData.userName, regData.fullName, regData.password, regData.secretWord, regData.bitsId, regData.phoneNumber, regData.hostel);
            }
            if (typeOfFrame.equals("Drop")) {
                student.dropWash(dropData.weight, dropData.date);
            }
            if (typeOfFrame.equals("Check")) {
                String status = student.checkStatus(student.getLastWash());
                Swing_classes.show_message(status);
            }
            if (typeOfFrame.equals("AllCheck")) {
                String status = student.checkAllStatus();
                Swing_classes.show_message(status);
            }
            shouldRun = false;
        }
    }

    public Void getIDForCheck(String ID) {
        this.communicateCheckData(ID);
        return null;
    }

    public void communicateCheckData(String ID) {
        synchronized (studentFileWriter.writeLock) {
            student = (Student) studentFileWriter.readStudentFromFile(ID);
            Student.studentFileWriter = studentFileWriter;
        }
        shouldRun = true;
        t.start();
    }

    public void communicateRegData(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
        if (studentFileWriter.checkUserExists(userName, bitsId)) {
            Swing_classes.show_message("User with same user name already exists");
            Swing_classes.close_gui();
            return;
        }
        regData = new RegData(userName, fullName, password, secretWord, bitsId, phoneNumber, hostel);
        shouldRun = true;
        t.start();
    }

    public void communicateDropData(String ID, double weight, String today) {
        synchronized (studentFileWriter.writeLock) {
            student = (Student) studentFileWriter.readStudentFromFile(ID);
            Student.studentFileWriter = studentFileWriter;
        }
        dropData = new DropData(weight, today);
        shouldRun = true;
        t.start();
    }

    private void regSuccess(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
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

    public void setTypeOfFrame(String typeOfFrame) {
        this.typeOfFrame = typeOfFrame;
        setInternalFrame();
    }

    public JFrame getFrame() {
        return frame;
    }
}
