import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Scanner;

public class StudentGUI implements Runnable {
    protected final Thread t;
    private JInternalFrame internalFrame;
    private final JFrame frame;
    private Student student;
    private String typeOfFrame;
    private boolean isClosingFlag;
    public StudentGUI() {
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
        while (!isClosingFlag) {
            System.out.println("RUNNING");
        }
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void regSuccess(String userName, String fullName, String password, String secretWord, String bitsId, String phoneNumber, Hostel hostel) {
        Writer out = null;


        //Check if the username is already taken. Username is always stored 1st, so it will be at 1st positon of arrray
        Path relPath = Paths.get("files/Student_data.txt");
        Path absPath = relPath.toAbsolutePath();
        File file = new File(absPath.toUri());

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] student_data=line.split(",");

                if(student_data[0].equals(userName)) {
                    Swing_classes.show_message("User with same user name already exists");
                    Swing_classes.close_gui();
                    return;
                }
            }
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        //We have printing the details of the plans in a new window
        String s="DETAILS OF WASHPLANS:\n";
        for (WashPlan plan : EnumSet.allOf(WashPlan.class)) {
            s+="\n"+plan.toString()+" "+"Iron included:"+plan.isIron+" "+"Number of washes in plan:"+plan.numWashes+" "+"Cost of each wash:"+plan.costPerWash;
        }
        Swing_classes.show_message(s);
        String washPlan=Swing_classes.create_gui("WashPlan");

        //write this data into the file
        try {
            Student.register(userName,fullName,password, secretWord,bitsId,phoneNumber, hostel,WashPlan.valueOf(washPlan));
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
