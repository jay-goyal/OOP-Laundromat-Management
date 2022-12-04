package GUI_Templates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingSingleInput_GUI extends JInternalFrame {
    private Container c;
    private final JLabel inputLabel;
    private final JTextField input;
    private final JButton submitButton;
    private String inputVal;
    private Actionable action;

    public SwingSingleInput_GUI(String labelMessage, String buttonText, Actionable action, int frameBehaviour) {
        c = getContentPane();
        c.setLayout(null);
        setBounds(50, 50, 350, 200);
        setPreferredSize(new Dimension(350, 200));
        setFont(new Font("Arial", Font.PLAIN, 20));

        inputLabel = new JLabel(labelMessage);
        inputLabel.setSize(300, 20);
        inputLabel.setLocation(50, 20);
        c.add(inputLabel);

        input = new JTextField();
        input.setSize(300, 20);
        input.setLocation(30, 50);
        c.add(input);

        submitButton = new JButton();
        submitButton.setText(buttonText);
        submitButton.setLocation(125, 100);
        submitButton.setSize(100, 40);
        c.add(submitButton);

        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputVal = input.getText();
                assert action != null;
                action.action(inputVal);
            }
        });
        setDefaultCloseOperation(frameBehaviour);
    }

    public SwingSingleInput_GUI(String labelMessage, String buttonText, Actionable action) {
        c = getContentPane();
        c.setLayout(null);
        setBounds(50, 50, 350, 200);
        setFont(new Font("Arial", Font.PLAIN, 20));

        inputLabel = new JLabel(labelMessage);
        inputLabel.setSize(300, 20);
        inputLabel.setLocation(50, 20);
        c.add(inputLabel);

        input = new JTextField();
        input.setSize(300, 20);
        input.setLocation(30, 50);
        c.add(input);

        submitButton = new JButton();
        submitButton.setText(buttonText);
        submitButton.setLocation(125, 100);
        submitButton.setSize(100, 40);
        c.add(submitButton);

        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputVal = input.getText();
                assert action != null;
                action.action(inputVal);
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
};
