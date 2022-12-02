import GUI_Templates.SwingSingleInput_GUI;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class Main {

    public static StudentFileWriter studentFileWriter;

    //this method is for the initial operations like register,drop laundry,check balance.
    //right now only register functionality is available on pressing S
    public static void action(String check) {
        if (check.equals("S")) {
            StudentGUI stdGui = new StudentGUI(studentFileWriter);
            stdGui.setTypeOfFrame("Reg");
        }
    }

    public static void main(String[] args) throws IOException {
        Path relFilesPath = Paths.get("files/");
        Path absFilesPath = relFilesPath.toAbsolutePath();
        Files.createDirectories(absFilesPath);
        studentFileWriter = new StudentFileWriter();
        SwingSingleInput_GUI inputCheck = new SwingSingleInput_GUI("Enter S to Register and L to Login", "Submit", Main::action, WindowConstants.EXIT_ON_CLOSE);
        inputCheck.setVisible(true);
        //Admin.adminRegister();
        //Admin.adminPrintDetails();
        //Swing_classes.multi_input();
        //Admin.adminScheduleDelivery();
    }
}
