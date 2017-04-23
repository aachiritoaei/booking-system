package ui;

import ui.utils.CustomButton;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Alexandru-Adrian Achiritoaei on 07-Apr-17.
 */
public class RegisterScreen extends JPanel {

    private JLabel usernameLabel, passwordLabel, mailLabel, phoneLabel;
    private JTextField usernameTF, mailTF, phoneTF;
    private JPasswordField passwordTF;
    private CustomButton registerButton, backButton;

    public RegisterScreen() {
        this.setLayout(new GridLayout(10, 1));

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

        // phone
        phoneLabel = new JLabel("Insert phone:", SwingConstants.CENTER);
        this.add(phoneLabel);
        phoneTF = new JTextField();
        phoneTF.setHorizontalAlignment(JTextField.CENTER);
        phoneTF.setDocument(customTFDocument());
        this.add(phoneTF);

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

    private PlainDocument customTFDocument() {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });

        return doc;
    }
}
