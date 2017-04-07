package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir_000 on 07-Apr-17.
 */
public class LoginScreen extends JPanel {

    JLabel usernameLabel, passwordLabel, registerLabel;
    JTextField usernameTF;
    JPasswordField passwordTF;
    JButton loginButton, registerButton;

    public LoginScreen() {
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

        // login
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.green);
        this.add(loginButton);

        // register
        registerLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        this.add(registerLabel);
        registerButton = new JButton("Register");
        this.add(registerButton);
    }

    public void addLoginAction(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public void addRegisterAction(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }
}
