package ui;

import ui.utils.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir_000 on 23-Apr-17.
 */
public class ViewResultScreen extends JPanel {

    private JLabel imageLabel, titleLabel, descriptionLabel, ratingLabel;
    private CustomButton bookButton, backButton;

    public ViewResultScreen() {
        this.setLayout(new GridLayout(6,1));

        imageLabel = new JLabel("Image here");
        this.add(imageLabel);

        titleLabel = new JLabel("Title here");
        this.add(titleLabel);

        descriptionLabel = new JLabel("Description here");
        this.add(new JScrollPane(descriptionLabel));

        ratingLabel = new JLabel("Rating here");
        this.add(ratingLabel);

        bookButton = new CustomButton("BOOK");
        this.add(bookButton);

        backButton = new CustomButton("BACK");
        this.add(backButton);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
