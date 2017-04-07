package ui;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Alexandru-Adrian Achiritoaei on 07-Apr-17.
 */
public class RegisterScreen extends JPanel {

    JLabel usernameLabel, passwordLabel, mailLabel;
    JTextField usernameTF, mailTF;
    JPasswordField passwordTF;
    CustomButton registerButton, backButton;

    public RegisterScreen() {
        this.setLayout(new GridLayout(8, 1));

        // username
        usernameLabel = new JLabel("Insert username:", SwingConstants.CENTER);
        this.add(usernameLabel);
        usernameTF = new JTextField();
        usernameTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(usernameTF);

        // pasword
        passwordLabel = new JLabel("Insert password:", SwingConstants.CENTER);
        this.add(passwordLabel);
        passwordTF = new JPasswordField();
        passwordTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordTF);

        // mail
        mailLabel = new JLabel("Insert email:", SwingConstants.CENTER);
        this.add(mailLabel);
        mailTF = new JTextField();
        mailTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(mailTF);

        // register
        registerButton = new CustomButton("Register");
        this.add(registerButton);

        // back
        backButton = new CustomButton("Back");
        this.add(backButton);
    }

    public void addRegisterAction(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
