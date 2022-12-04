import GUI_Templates.SwingSingleInput_GUI;
import com.sun.jdi.VoidValue;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class Main {

    public static StudentFileWriter studentFileWriter;
    public static Admin admin;

    //this method is for the initial operations like register,drop laundry,check balance.
    //right now only register functionality is available on pressing S
    public static Void action(String check) {
        switch (check) {
            case "S" -> {
                StudentGUI stdGui = new StudentGUI(studentFileWriter);
                stdGui.setTypeOfFrame("Reg");
            }
            case "D" -> {
                StudentGUI stdGui = new StudentGUI(studentFileWriter);
                stdGui.setTypeOfFrame("Drop");
            }
            case "C" -> {
                StudentGUI stdGui = new StudentGUI(studentFileWriter);
                stdGui.setTypeOfFrame("Check");
            }
            case "B" -> {
                StudentGUI stdGui = new StudentGUI(studentFileWriter);
                stdGui.setTypeOfFrame("AllCheck");
            }
            case "R" -> {
                StudentGUI stdGui = new StudentGUI(studentFileWriter);
                stdGui.setTypeOfFrame("Receive");
            }
            case "AC" -> {
                AdminGUI adminGui = new AdminGUI();
                adminGui.setTypeOfFrame("PrintStud");
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        Path relFilesPath = Paths.get("files/");
        Path absFilesPath = relFilesPath.toAbsolutePath();
        Files.createDirectories(absFilesPath);
        admin = new Admin("admin", "Admin User", "admin123", "admin");
        studentFileWriter = new StudentFileWriter();
        SwingSingleInput_GUI inputCheck = new SwingSingleInput_GUI("Enter the operation", "Submit", Main::action, WindowConstants.EXIT_ON_CLOSE);
        inputCheck.setVisible(true);
        JFrame frame = new JFrame();
        frame.setTitle("Laundromat Application");
        frame.setSize(inputCheck.getPreferredSize());
        frame.add(inputCheck);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Admin.adminRegister();
        //Admin.adminPrintDetails();
        //Swing_classes.multi_input();
        //Admin.adminScheduleDelivery();
    }
}
