package ui.admin;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir on 07-May-17.
 */
public class AdminScreen extends JPanel {

    private CustomButton addEmployeeButton, addHotelButton, addRoomButton;

    public AdminScreen() {
        this.setLayout(new GridLayout(3, 1));

        // add employee button
        addEmployeeButton = new CustomButton("Add new employee");
        this.add(addEmployeeButton);

        // add hotel
        addHotelButton = new CustomButton("Add new hotel");
        this.add(addHotelButton);

        // add room
        addRoomButton = new CustomButton("Add new room");
        this.add(addRoomButton);
    }

    public void addEmployeehAction(ActionListener actionListener) {
        addEmployeeButton.addActionListener(actionListener);
    }

    public void addHotelAction(ActionListener actionListener) {
        addHotelButton.addActionListener(actionListener);
    }

    public void addRoomAction(ActionListener actionListener) {
        addRoomButton.addActionListener(actionListener);
    }
}
