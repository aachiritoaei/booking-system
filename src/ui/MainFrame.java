package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Alexandru-Adrian Achiritoaei on 07-Apr-17.
 */
public class MainFrame {
    private JFrame mainFrame;
    private JPanel cards;
    private JLabel title;

    private final static String LOGINPANEL = "login panel";
    private final static String REGISTERPANEL = "register panel";
    private final static String SEARCHPANEL = "search panel";
    private final static String LISTRESULTPANEL = "list result panel";
    private final static String VIEWRESULTPANEL = "view result panel";
    private final static String LOGOUTPANEL = "logout panel";

    public MainFrame() {
        prepareGUI();
    }

    private void prepareGUI() {
        try {
            // setTheme(String themeName, String licenseKey, String logoString)
            com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme("Blue");

            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");

            // start the demo application
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /* Main frame */
        mainFrame = new JFrame("hotel booking");
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        mainFrame.setSize(800, 600);
        mainFrame.setMinimumSize(new Dimension(600, 400));

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {//add exit listener
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width / 2 - 400, dim.height / 2 - 300); // center frame
        ImageIcon iconImage = new ImageIcon(getClass().getResource("/res/hotel-icon.png"));
        mainFrame.setIconImage(iconImage.getImage());

        // title
        title = new JLabel();
        title.setIcon(new ImageIcon(getClass().getResource("/res/logo.png")));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        mainFrame.add(title);

        // JPanel
        CardLayout cardLayout = new CardLayout();
        cardLayout.setHgap(10);
        cardLayout.setVgap(10);
        cards = new JPanel(cardLayout);
        cards.setMaximumSize(new Dimension(500, 400));
        mainFrame.add(cards);

        // Login screen
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.addLoginAction(e -> cardLayout.show(cards, SEARCHPANEL));
        loginScreen.addRegisterAction(e -> cardLayout.show(cards, REGISTERPANEL));
        cards.add(loginScreen, LOGINPANEL);

        // Register screen
        RegisterScreen registerScreen = new RegisterScreen();
        registerScreen.addRegisterAction(e -> cardLayout.show(cards, LOGINPANEL));
        registerScreen.addBackAction(e -> cardLayout.show(cards, LOGINPANEL));
        cards.add(registerScreen, REGISTERPANEL);

        // Search screen
        SearchScreen searchScreen = new SearchScreen();
        searchScreen.addSearchAction(e -> cardLayout.show(cards, LISTRESULTPANEL));
        cards.add(searchScreen, SEARCHPANEL);

        // View result screen
        ViewResultScreen viewResultScreen = new ViewResultScreen();
        viewResultScreen.addBackAction(e -> cardLayout.show(cards, LISTRESULTPANEL));
        cards.add(viewResultScreen, VIEWRESULTPANEL);

        // List results screen
        ListResultsScreen listResultsScreen = new ListResultsScreen(viewResultScreen);
        listResultsScreen.addViewAction(e -> {
            viewResultScreen.setTitle(listResultsScreen.getSelectedValue());
            cardLayout.show(cards, VIEWRESULTPANEL);
        });
        listResultsScreen.addBackAction(e -> cardLayout.show(cards, SEARCHPANEL));
        cards.add(listResultsScreen, LISTRESULTPANEL);



        // Logout screen
        LogoutScreen logoutScreen = new LogoutScreen();
        logoutScreen.addLogoutAction(e -> cardLayout.show(cards, LOGINPANEL));
        cards.add(logoutScreen, LOGOUTPANEL);

        // set login panel as first panel
        cardLayout.first(cards);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
