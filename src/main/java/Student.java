import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class Student extends User {

    public Hostel hostel;
    private String bitsID;
    Plan plan;
    String s = "";
    public static StudentFileWriter studentFileWriter;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

    public Student(String userName, String fullName, String password, String secretWord, Hostel hostel, String bitsID) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.bitsID = bitsID;
        this.secretWord = secretWord;
        this.hostel = hostel;

        Student.studentFileWriter = Main.studentFileWriter;
    }


    //I have updated this method
    public static void register(String userName, String fullName, String password, String secretWord, String ID, String phoneNumber, Hostel hostel, WashPlan washPlan) throws IOException {
        try {
            synchronized (studentFileWriter.writeLock) {
                studentFileWriter.regUserToFile(userName, fullName, password, secretWord, ID, phoneNumber, hostel);
                studentFileWriter.writeLock.notify();
                studentFileWriter.writeLock.notify();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            Student student = new Student(userName, fullName, password, secretWord, hostel, ID);
            student.setPlan(new Plan(washPlan, washPlan.costPerWash * washPlan.numWashes, washPlan.numWashes));
            synchronized (studentFileWriter.writeLock) {
                studentFileWriter.writeStudentToFile(student, false);
                studentFileWriter.writeLock.notify();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropWash(double Weight, String today) {
        String allotedDay = hostel.getDay();
        double washExtras = 0;

        System.out.println(today);
        if (!today.split(",")[0].equals(allotedDay)) {
            System.out.println(allotedDay);
            Swing_classes.show_message("You can only drop laundry on your alloted day. Drop is cancelled");
            return;
        }
        WashPlan washplan = plan.getWashPlan();

        if (plan.getNumWashGiven() >= washplan.numWashes) {
            plan.incrementNumWashGiven();
            washExtras += washplan.costPerWash;
            plan.incrementExtraCharge(washplan.costPerWash);
            Swing_classes.show_message("You have been charged the cost of one extra wash as you have exhausted the number of washes available in your plan");
            plan.setExpense(plan.getExpense() + plan.getExtraCharge());
        }

        if (washplan.weightPerWash < Weight) {
            washExtras += (Weight - washplan.weightPerWash) * 20;
            plan.incrementExtraCharge((Weight - washplan.weightPerWash) * 20);
            Swing_classes.show_message("You have been charged Rs " + Double.toString((Weight - washplan.weightPerWash) * 20) + "extra");
            plan.setExpense(plan.getExpense() + plan.getExtraCharge());
        }
        plan.addWash(new Wash(today, "Dropped", plan.getWashPlan().costPerWash + washExtras));
        String deliveryDate = null;
        try {
            LocalDate todayAsLocal = Instant.ofEpochMilli(simpleDateFormat.parse(today).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            deliveryDate = todayAsLocal.plusDays(2).toString();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        synchronized (studentFileWriter.writeLock) {
            try {
                studentFileWriter.writeStudentToFile(this, true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                studentFileWriter.notify();
            }
        }

        Swing_classes.show_message("Laundry dropped. It will be delivered on " + deliveryDate);
    }

    public Wash getLastWash() {
        ArrayList<Wash> washes = plan.getWashList();
        System.out.println(washes);
        return washes.get(washes.size() - 1);
    }

    public String checkStatus(Wash wash) {
        return wash.toString();
    }

    public String checkAllStatus() {
        ArrayList<Wash> washes = plan.getWashList();
        StringBuilder status = new StringBuilder();
        for (Wash wash : washes) {
            status.append(wash.toString()).append("\n");
        }
        return status.toString();
    }

    public void checkExpense() {
        for (Wash wash : plan.getWashList()) {
            s += "\n" + wash.toString();
        }
        s += "\n" + plan.getExpense();
        Swing_classes.show_message(s);
    }

    public String getID() {
        return this.bitsID;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}


