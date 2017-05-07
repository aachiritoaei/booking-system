package ui.admin;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by achir on 07-May-17.
 */
public class AddRoomScreen extends JPanel {

    private JLabel nameLabel, hotelLabel, numberPersonsLabel, priceLabel;
    private JTextField nameTF;
    private JFormattedTextField numberPersonsTF, priceTF;
    private JComboBox hotelCB;
    private CustomButton addButton, backButton;

    private String[] hotelStrings = {"Sheraton", "The Greatest", "Burj-Al-Arab"};

    public AddRoomScreen() {
        this.setLayout(new GridLayout(10, 1));

        // room name
        nameLabel = new JLabel("Insert room number:", SwingConstants.CENTER);
        this.add(nameLabel);
        nameTF = new JTextField();
        nameTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(nameTF);

        // room location
        hotelLabel = new JLabel("Choose hotel:", SwingConstants.CENTER);
        this.add(hotelLabel);
        hotelCB = new JComboBox(hotelStrings);
        this.add(hotelCB);

        // number of persons
        numberPersonsLabel = new JLabel("Insert number of persons:", SwingConstants.CENTER);
        this.add(numberPersonsLabel);
        numberPersonsTF = new JFormattedTextField(NumberFormat.getIntegerInstance());
        numberPersonsTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(numberPersonsTF);

        // price
        priceLabel = new JLabel("Insert price:", SwingConstants.CENTER);
        this.add(priceLabel);
        priceTF = new JFormattedTextField(NumberFormat.getIntegerInstance());
        priceTF.setHorizontalAlignment(JTextField.CENTER);
        this.add(priceTF);

        // add button
        addButton = new CustomButton("Add room");
        this.add(addButton);

        // back button
        backButton = new CustomButton("BACK");
        this.add(backButton);
    }

    public void addRoomAction(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

}
