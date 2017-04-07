package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir_000 on 07-Apr-17.
 */
public class LogoutScreen extends JPanel {

    private JButton logoutButton;

    public LogoutScreen() {
        this.setLayout(new GridLayout(3, 1));

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        this.add(logoutButton);
    }

    public void addLogoutAction(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }
}
