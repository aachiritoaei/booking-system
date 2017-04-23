package ui;

import ui.utils.CustomButton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Alexandru-Adrian Achiritoaei on 07-Apr-17.
 */
public class LoginScreen extends JPanel {

    private JLabel usernameLabel, passwordLabel, registerLabel;
    private JTextField usernameTF;
    private JPasswordField passwordTF;
    private CustomButton loginButton, registerButton;

    public LoginScreen() {
        this.setLayout(new GridLayout(8, 1));

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

        // login
        loginButton = new CustomButton("Login");
        this.add(loginButton);

        // register
        registerLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        this.add(registerLabel);
        registerButton = new CustomButton("Register");
        this.add(registerButton);
    }

    public void addLoginAction(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public void addRegisterAction(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }
}
