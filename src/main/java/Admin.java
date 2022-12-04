import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
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

    public static Admin getAdminFromFile() {
        Path relPathOut = Paths.get("files/admin.txt");
        Path absPathOut = relPathOut.toAbsolutePath();

        if (!new File(absPathOut.toString()).isFile()) {
            return new Admin("admin", "Admin User", "admin123", "admin");
        }

        try {

            FileInputStream fileIn = new FileInputStream(absPathOut.toString());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object ob = objectIn.readObject();
            System.out.println(ob);
            Admin obj = (Admin) ob;

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Admin("admin", "Admin User", "admin123", "admin");
        }
    }

    public void writeAdminToFile() {
        String fileName = "files/admin.txt";
        Path relPathStud = Paths.get(fileName);
        Path absPathStud = relPathStud.toAbsolutePath();
        try {
            File myObj = new File(absPathStud.toUri());
            System.out.println("File created: " + myObj.getName());
            FileOutputStream fos = new FileOutputStream(absPathStud.toString(), true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void changeStudentPlan(Student student, boolean isIron, int numWashes, double costPerWash) {
        Plan activePlan = student.getPlan();
        Plan newPlan;
        if (activePlan.getNumWashGiven() >= numWashes) {
            newPlan = new Plan(activePlan.getWashPlan(), (activePlan.getNumWashGiven() + 1) * activePlan.getWashPlan().costPerWash, activePlan.getNumWashGiven() + 1);
        } else {
            newPlan = new Plan(activePlan.getWashPlan(), (activePlan.getNumWashGiven() + 1) * activePlan.getWashPlan().costPerWash, numWashes);
        }
        newPlan.addWash(activePlan.getWashList());
        student.setPlan(newPlan);
    }

    public void login(String username, String password) {
        isLoggedIn = super.checkLogin(username, password);
    }

    public void getLaundryStud(String ID, Date date) {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }
        Student student;
        synchronized (studentFileWriter.writeLock) {
            student = studentFileWriter.readStudentFromFile(ID);
            studentFileWriter.notify();
        }
        if (student != null) {
            ArrayList<Wash> washes = student.getPlan().getWashList();
            StringBuilder status = new StringBuilder();
            String dateAsStr = simpleDateFormat.format(date);
            for (Wash wash : washes) {
                if (wash.getDateGiven().equals(dateAsStr)) {
                    status.append(student.checkStatus(wash)).append("\n");
                }
            }
            Swing_classes.show_message(status.toString());
        }
    }

    public void updateLaundry(String ID, Date date, String status) {
        if (!isLoggedIn) {
            Swing_classes.show_message("Admin not logged in");
            return;
        }
        Student student;
        synchronized (studentFileWriter.writeLock) {
            student = studentFileWriter.readStudentFromFile(ID);
            studentFileWriter.notify();
        }
        if (student != null) {
            ArrayList<Wash> washes = student.getPlan().getWashList();
            String dateAsStr = simpleDateFormat.format(date);
            for (Wash wash : washes) {
                if (wash.getDateGiven().equals(dateAsStr)) {
                    wash.setStatus(status);
                }
            }
            Swing_classes.show_message("Status of all washes given updated");
        }
    }

    public void getWeekLaundry() {
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
//                if (ChronoUnit.DAYS.between(LocalDate.parse(student.getLastWash().getDateGiven()), LocalDate.now()) < 7) {
//
//                }

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
                s += "\n Name:" + student_data[1] + ",  Hostel:" + student_data[4] + ",  Plan:" + student_data[5];

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Swing_classes.show_message(s);
    }

    public static void adminScheduleDelivery() throws IOException {
        String s = Swing_classes.multi_input();
        String[] data = s.split(",");
        String hostel = data[0];
        String day = data[1];
        String time = data[2];
        //System.out.println(s);

        Writer out = null;
        Path relPath = Paths.get("files/Hostel_data.txt");
        Path absPath = relPath.toAbsolutePath();
        FileIO.removeLineFromFile(absPath.toString(), hostel);
        File fileName = new File(absPath.toUri());
        try {
            out = new FileWriter(fileName, true);
            String hostelData = hostel + "," + day + "," + time;
            out.write(hostelData);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            out.close();
        }

    }
}
	