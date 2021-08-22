import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WelcomeMenuController {
    protected JFrame mainFrame;
    private JButton startButton;
    private JLabel welcomeBanner;
    protected JPanel welcomeScreen;
    protected JPanel mainPanel = new JPanel();

    public static void main(String[] args) {
        WelcomeMenuController menu = new WelcomeMenuController();
    }

    public WelcomeMenuController() {
        prepareGUI();
        mainFrame.setVisible(true);
        welcomeScreen.setVisible(true);
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Space Trader");
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
        // mainFrame.setLayout(layout);
        // mainFrame.add(Box.createRigidArea(new Dimension(0, 30)));
        // mainFrame.add(welcomeBanner);
        // mainFrame.add(Box.createRigidArea(new Dimension(0, 100)));
        // mainFrame.add(startButton);

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

    // TO DO: add code to reset the screen and create the configurations menu
    private void changeToConfigurationMenu() {
        welcomeScreen.setVisible(false);
        Combination combo = new Combination();
        mainFrame.dispose();
        mainFrame.add(combo.mainPanel);
        combo.mainPanel.setVisible(true);
    };
}