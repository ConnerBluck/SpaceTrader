package spacetrader;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Component;

public class WelcomeMenuController extends Controller {
    private JButton startButton;
    private JLabel welcomeBanner;
    protected JPanel welcomeScreen;

    /**
     * main method
     *
     * @param args args
     */
    public static void main(String[] args) {
        WelcomeMenuController menu = new WelcomeMenuController();
    }

    /**
     * constructor
     */
    public WelcomeMenuController() {
        prepareGUI();
        mainFrame.setVisible(true);
        welcomeScreen.setVisible(true);
    }

    /**
     * creates the welcome screen
     */
    private void prepareGUI() {
        mainFrame.setTitle("Space Trader");
        mainFrame.setMinimumSize(new Dimension(600, 400));

        startButton = new JButton();
        startButton.setText("Click here to start a new game.");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(256, 50));

        welcomeBanner = new JLabel();
        welcomeBanner.setText("Welcome to Space Trader!");
        welcomeBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        welcomeScreen = new JPanel();
        BoxLayout layout = new BoxLayout(
                welcomeScreen, BoxLayout.PAGE_AXIS);
        welcomeScreen.setLayout(layout);

        welcomeScreen.add(Box.createRigidArea(new Dimension(0, 30)));
        welcomeScreen.add(welcomeBanner);
        welcomeScreen.add(Box.createRigidArea(new Dimension(0, 100)));
        welcomeScreen.add(startButton);
        mainFrame.add(welcomeScreen);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToConfigurationMenu();
            }
        });
    }

    /**
     * changes screen to combination screen
     */
    private void changeToConfigurationMenu() {
        welcomeScreen.setVisible(false);
        Combination combo = new Combination();
        mainFrame.dispose();
        mainFrame.add(combo.mainPanel);
        combo.mainPanel.setVisible(true);
    }
}