package ui.employee;

import ui.utils.CustomButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by achir on 07-May-17.
 */
public class ReservationsScreen extends JPanel {


    private JList<String> resultList;
    private JPanel buttonsPanel;
    private CustomButton confirmButton, backButton;

    String[] examples = {"reservation 1", "reservation 2", "reservation 3", "reservation 4"};

    private static final String VIEWRESULTPANEL = "view result screen";

    public ReservationsScreen() {

        this.setLayout(new GridLayout(2, 1));

        setMinimumSize(new Dimension(600, 400));
        resultList = new JList(examples);
        resultList.setCellRenderer(new ui.employee.ReservationsScreen.ResultCellRenderer());
        resultList.setVisibleRowCount(2);
        resultList.setSelectedIndex(0);

        this.add(new JScrollPane(resultList));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));

        // view room
        confirmButton = new CustomButton("CONFIRM RESERVATION");
        buttonsPanel.add(confirmButton);

        // go back
        backButton = new CustomButton("BACK");
        buttonsPanel.add(backButton);

        this.add(buttonsPanel);
    }

    public void addBackAction(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void addConfirmAction(ActionListener actionListener) {
        confirmButton.addActionListener(actionListener);
    }

    public String getSelectedValue() {
        return resultList.getSelectedValue();
    }

    // custom list item
    private class ResultCellRenderer extends JLabel implements ListCellRenderer<String> {

        private Border border;

        public ResultCellRenderer() {
            setIconTextGap(10);
            setOpaque(true);

            border = BorderFactory.createLineBorder(Color.blue, 1);
        }
        @Override
        public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);

            ImageIcon iconImage = new ImageIcon(getClass().getResource("/res/star_test.png"));
            setIcon(iconImage);

            if (isSelected) {
                setBackground((list.getSelectionBackground()));
                setForeground(list.getSelectionForeground());
            } else {
                setBackground((list.getBackground()));
                setForeground(list.getForeground());
            }

            setFont(list.getFont());
            setEnabled(list.isEnabled());

            if (isSelected && cellHasFocus) {
                setBorder(border);
            } else {
                setBorder(null);
            }

            return this;
        }
    }

}
