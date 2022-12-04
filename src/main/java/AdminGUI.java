import GUI_Templates.SwingSingleInput_GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EnumSet;

public class AdminGUI implements Runnable {
    protected final Thread t;
    private JInternalFrame internalFrame;
    private final JFrame frame;
    private String typeOfFrame;
    private boolean shouldRun;

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
            case "PrintStud" -> {
                shouldRun = true;
                frame.setVisible(false);
                t.start();
            }
        }
    }

    public void run() {
        System.out.println("RUNNING");
        if (shouldRun) {
            if (typeOfFrame.equals("PrintStud")) {
                Main.admin.adminPrintDetails();
            }
            shouldRun = false;
        }
    }

    public void setTypeOfFrame(String typeOfFrame) {
        this.typeOfFrame = typeOfFrame;
        setInternalFrame();
    }

    public JFrame getFrame() {
        return frame;
    }
}
