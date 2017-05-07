package ui.user;

import javafx.embed.swing.JFXPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import ui.utils.CustomButton;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Created by achir_000 on 23-Apr-17.
 */
public class SearchScreen extends JPanel {

    private JLabel locationLabel, hotelLabel,roomLabel, numberOfPersonsLabel, startDateLabel, endDateLabel;
    private JComboBox locationCB, hotelCB, roomCB, numberOfPersonsCB;
    private JFormattedTextField startDateTF, endDateTF;

    private String[] locationStrings = {"New York", "Ibiza", "Dubai"};
    private String[] hotelStrings = {"Sheraton", "The Greatest", "Burj-Al-Arab"};
    private String[] roomStrings = {"Simple", "Double", "Family", "Luxurious"};
    private String[] numberOfPersonsStrings = {"1", "2", "3", "4"};

    // date picker
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;

    private CustomButton searchButton;

    public SearchScreen() {
        this.setLayout(new GridLayout(14, 1));

        // location
        locationLabel = new JLabel("Choose location:", SwingConstants.CENTER);
        this.add(locationLabel);
        locationCB = new JComboBox(locationStrings);
        this.add(locationCB);

        // hotel
        hotelLabel = new JLabel("Choose hotel:", SwingConstants.CENTER);
        this.add(hotelLabel);
        hotelCB = new JComboBox(hotelStrings);
        this.add(hotelCB);

        // room
        roomLabel = new JLabel("Choose room:", SwingConstants.CENTER);
        this.add(roomLabel);
        roomCB = new JComboBox(roomStrings);
        this.add(roomCB);

        // location
        numberOfPersonsLabel = new JLabel("Choose number of persons:", SwingConstants.CENTER);
        this.add(numberOfPersonsLabel);
        numberOfPersonsCB = new JComboBox(numberOfPersonsStrings);
        this.add(numberOfPersonsCB);

        // start date
        startDateLabel = new JLabel("Choose start date:", SwingConstants.CENTER);
        this.add(startDateLabel);
        startDateTF = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
        startDateTF.setToolTipText("Date format is dd/mm/yyyy");
        this.add(startDateTF);

        // end date
        endDateLabel = new JLabel("Choose end date:", SwingConstants.CENTER);
        this.add(endDateLabel);
        endDateTF = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
        endDateTF.setToolTipText("Date format is dd/mm/yyyy");
        this.add(endDateTF);

        // search button
        searchButton = new CustomButton("SEARCH");
        this.add(searchButton);
    }

    public void addSearchAction(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }
}
