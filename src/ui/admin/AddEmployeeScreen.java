package ui.admin;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir on 07-May-17.
 */
public class AddEmployeeScreen extends JPanel {

    private JLabel usernameLabel, passwordLabel;
    private JTextField  usernameTF, passwordTF;
    private CustomButton addButton, backButton;

    public AddEmployeeScreen() {
        this.setLayout(new GridLayout(6, 1));

        // username
        usernameLabel = new JLabel("Insert username:", SwingConstants.CENTER);
        this.add(usernameLabel);
        usernameTF = new JTextField();
        usernameTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(usernameTF);

        // password
        passwordLabel = new JLabel("Insert password:", SwingConstants.CENTER);
        this.add(passwordLabel);
        passwordTF = new JPasswordField();
        passwordTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordTF);

        // add button
        addButton = new CustomButton("Add employee");
        this.add(addButton);

        // back button
        backButton = new CustomButton("BACK");
        this.add(backButton);
    }

    public void addEmployeeAction(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }


}
