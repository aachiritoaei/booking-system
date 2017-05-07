package ui.admin;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir on 07-May-17.
 */
public class AddHotelScreen extends JPanel {

    private JLabel nameLabel, locationLabel;
    private JTextField nameTF, locationTF;
    private CustomButton addButton, backButton;

    public AddHotelScreen() {
        this.setLayout(new GridLayout(6, 1));

        // hotel name
        nameLabel = new JLabel("Insert hotel name:", SwingConstants.CENTER);
        this.add(nameLabel);
        nameTF = new JTextField();
        nameTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(nameTF);

        // hotel location
        locationLabel = new JLabel("Insert hotel location:", SwingConstants.CENTER);
        this.add(locationLabel);
        locationTF = new JTextField();
        locationTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(locationTF);

        // add button
        addButton = new CustomButton("Add hotel");
        this.add(addButton);

        // back button
        backButton = new CustomButton("BACK");
        this.add(backButton);
    }

    public void addHotelAction(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

}
