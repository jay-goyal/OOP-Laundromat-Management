import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;

public class Admin extends User {

    private boolean isLoggedIn;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
    public static StudentFileWriter studentFileWriter = Main.studentFileWriter;

    public Admin(String userName, String fullName, String password, String secretWord) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.secretWord = secretWord;
        isLoggedIn = false;
    }

    public boolean login(String username, String password) {
        isLoggedIn = super.checkLogin(username, password);
        if (isLoggedIn) {
            Swing_classes.show_message("Logged In Successfully");
        } else {
            Swing_classes.show_message("Invalid username or password");
        }
        return isLoggedIn;
    }

    public void getLaundryStud(String ID, String date) {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }
        Student student;
        synchronized (studentFileWriter.writeLock) {
            student = studentFileWriter.readStudentFromFile(ID);
            studentFileWriter.writeLock.notify();
        }
        if (student != null) {
            ArrayList<Wash> washes = student.getPlan().getWashList();
            StringBuilder status = new StringBuilder();
            for (Wash wash : washes) {
                if (wash.getDateGiven().equals(date)) {
                    status.append(student.checkStatus(wash)).append("\n");
                }
            }
            Swing_classes.show_message(status.toString());
        } else {
            Swing_classes.show_message("Student does not exist");
        }
    }

    public void updateLaundry(String ID, String date, String status) {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }
        Student student;
        synchronized (studentFileWriter.writeLock) {
            student = studentFileWriter.readStudentFromFile(ID);
            studentFileWriter.writeLock.notify();
        }
        if (student != null) {
            ArrayList<Wash> washes = student.getPlan().getWashList();
            for (Wash wash : washes) {
                if (wash.getDateGiven().equals(date)) {
                    wash.setStatus(status);
                }
            }
            synchronized (studentFileWriter.writeLock) {
                try {
                    studentFileWriter.writeStudentToFile(student, true);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    studentFileWriter.writeLock.notify();
                }
            }
            Swing_classes.show_message("Status of all washes given updated");
        } else {
            Swing_classes.show_message("Student does not exist");
        }
    }

    public void getHostelRev() {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }
        StringBuilder revString = new StringBuilder();
        for (Hostel hostel : Hostel.values()) {
            ArrayList<String> IDs;
            synchronized (studentFileWriter.writeLock) {
                IDs = studentFileWriter.getAllIDs(hostel);
            }
            double hostelRevenue = 0;
            for (String ID : IDs) {
                Student student;
                synchronized (studentFileWriter.writeLock) {
                    student = studentFileWriter.readStudentFromFile(ID);
                    studentFileWriter.writeLock.notify();
                }
                if (student != null) {
                    hostelRevenue += student.getPlan().getExpense();
                }
            }
            revString.append(hostel.toString()).append(" revenue is ").append(hostelRevenue).append("\n");
        }
        Swing_classes.show_message(revString.toString());
    }

    public void logout() {
        if (isLoggedIn) {
            isLoggedIn = false;
            Swing_classes.show_message("Admin logged out successfully");
        } else {
            Swing_classes.show_message("Admin not logged in");
        }
    }

    //following are the new classes added
    public void adminPrintDetails() {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }

        String s = "The Student details are as follows-";
        Path relPath = Paths.get("files/Student_data.txt");
        Path absPath = relPath.toAbsolutePath();
        File file = new File(absPath.toUri());

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] student_data = line.split(",");
                if (student_data.length == 1) {
                    continue;
                }
                s += "\n Name:" + student_data[1] + ",  Hostel:" + student_data[4] + ",  Phone:" + student_data[5];

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Swing_classes.show_message(s);
    }

    public void adminScheduleDelivery(String hostel, String day, String time) {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }

        DeliverySchedule.hostelDay.put(Hostel.valueOf(hostel), day);
        DeliverySchedule.hostelTime.put(Hostel.valueOf(hostel), time);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
	