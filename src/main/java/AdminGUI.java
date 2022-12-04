import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class LoginData {
    public String username;
    public String password;

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class WashData {
    public String Id;
    public String date;
    public String status;

    public WashData(String id, String date, String status) {
        Id = id;
        this.date = date;
        this.status = status;
    }
}

class SchdData {
    public String hostel;
    public String day;
    public String time;

    public SchdData(String hostel, String day, String time) {
        this.hostel = hostel;
        this.day = day;
        this.time = time;
    }
}

public class AdminGUI implements Runnable {
    protected final Thread t;
    private JInternalFrame internalFrame;
    private final JFrame frame;
    private String typeOfFrame;
    private boolean shouldRun;
    private LoginData loginData;
    private WashData washData;
    private SchdData schdData;

    public AdminGUI() {
        this.t = new Thread(this);
        this.frame = new JFrame();
        typeOfFrame = "Reg";
        frame.setBounds(50, 50, 400, 150);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void setInternalFrame() {
        switch (typeOfFrame) {
            case "Login" -> {
                if (Main.admin.isLoggedIn()) {
                    Swing_classes.show_message("Admin already logged in!!");
                    frame.dispose();
                    return;
                }
                internalFrame = new AdminGUILogin(this);
                frame.setSize(internalFrame.getPreferredSize());
                frame.setTitle(internalFrame.getTitle());
                internalFrame.setTitle("");
                frame.add(internalFrame);
            }
            case "PrintStud", "Logout", "Rev" -> {
                shouldRun = true;
                frame.setVisible(false);
                t.start();
            }
            case "CheckStat" -> {
                internalFrame = new LaundryStatusGUI(this);
                frame.setSize(internalFrame.getPreferredSize());
                frame.setTitle(internalFrame.getTitle());
                internalFrame.setTitle("");
                frame.add(internalFrame);
            }
            case "Update" -> {
                internalFrame = new UpdateLaundryStatusGUI(this);
                frame.setSize(internalFrame.getPreferredSize());
                frame.setTitle(internalFrame.getTitle());
                internalFrame.setTitle("");
                frame.add(internalFrame);
            }
            case "Schd" -> {
                internalFrame = new ScheduleDeliveryGUI(this);
                frame.setSize(internalFrame.getPreferredSize());
                frame.setTitle(internalFrame.getTitle());
                internalFrame.setTitle("");
                frame.add(internalFrame);
            }
        }
    }

    public void run() {
        System.out.println("RUNNING");
        if (shouldRun) {
            if (typeOfFrame.equals("Login")) {
                boolean isLoggedIn = Main.admin.login(loginData.username, loginData.password);
            } else if (typeOfFrame.equals("PrintStud")) {
                Main.admin.adminPrintDetails();
            } else if (typeOfFrame.equals("CheckStat")) {
                Main.admin.getLaundryStud(washData.Id, washData.date);
            } else if (typeOfFrame.equals("Rev")) {
                Main.admin.getHostelRev();
            } else if (typeOfFrame.equals("Update")) {
                Main.admin.updateLaundry(washData.Id, washData.date, washData.status);
            } else if (typeOfFrame.equals("Schd")){
                Main.admin.adminScheduleDelivery(schdData.hostel, schdData.day, schdData.time);
            } else if (typeOfFrame.equals("Logout")) {
                Main.admin.logout();
            }
            shouldRun = false;
            frame.dispose();
        }
    }

    public void communicateSchdData(String hostel, String day, String time) {
        schdData = new SchdData(hostel, day, time);
        shouldRun = true;
        t.start();
    }

    public void communicateUpdateData(String Id, String date, String status) {
        washData = new WashData(Id, date, status);
        shouldRun = true;
        t.start();
    }

    public void communicateSearchData(String Id, String date) {
        washData = new WashData(Id, date, "");
        shouldRun = true;
        t.start();
    }

    public void communicateLoginData(String username, String password) {
        loginData = new LoginData(username, password);
        shouldRun = true;
        t.start();
    }

    public void setTypeOfFrame(String typeOfFrame) {
        this.typeOfFrame = typeOfFrame;
        setInternalFrame();
    }

    public JFrame getFrame() {
        return frame;
    }
}
