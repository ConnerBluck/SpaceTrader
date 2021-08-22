package spacetrader;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.OverlayLayout;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WelcomeMenuController extends Controller {
    private JButton startButton;
    private JLabel welcomeBanner;
    private JPanel welcomeScreen;
    private JPanel mainPanel = new JPanel();
    private JPanel imageHolder;


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
     *
     */
    public WelcomeMenuController() {
        prepareGUI();
        mainFrame.setVisible(true);
        welcomeScreen.setVisible(true);
    }

    /**
     * creates the welcome screen
     *
     */
    private void prepareGUI() {
        mainFrame.setTitle("Space Trader");
        mainFrame.setMinimumSize(new Dimension(600, 400));

        startButton = new JButton();
        startButton.setText("Click here to start a new game.");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(256, 50));
        startButton.setBounds(250, 500, 300, 50);

        welcomeBanner = new JLabel();
        welcomeBanner.setBackground(Color.black);
        welcomeBanner.setText("Welcome to Space Trader!");
        welcomeBanner.setForeground(java.awt.Color.white);
        welcomeBanner.setFont(new Font("Helvetica", Font.BOLD, 48));
        welcomeBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeBanner.setBounds(100, 250, 800, 150);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        welcomeScreen = new JPanel();
        welcomeScreen.setLayout(null);
        welcomeScreen.setOpaque(false);

        try {
            imageHolder = new JPanel();
            imageHolder.setLayout(null);
            JLabel backgroundPicture = new JLabel();
            backgroundPicture.setBounds(0, 0, 800, 800);
            URL url = new URL("https://i.imgur.com/r4DAYVh.jpg");
            BufferedImage image = ImageIO.read(url);
            ImageIcon picture = new ImageIcon(image);
            backgroundPicture.setIcon(picture);

            imageHolder.add(backgroundPicture);
            imageHolder.setVisible(true);
        } catch (IOException i) {
            System.out.println("Nope, sorry about that but no pictures.");
            i.printStackTrace();
        }

        welcomeScreen.add(welcomeBanner);
        welcomeScreen.add(startButton);

        try {
            JPanel overlap = new JPanel();
            overlap.setLayout(new OverlayLayout(overlap));
            overlap.add(welcomeScreen, BorderLayout.CENTER);
            overlap.add(imageHolder, BorderLayout.CENTER);

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(overlap);
        } catch (Exception e) {
            mainPanel.add(welcomeScreen);
            e.printStackTrace();
        }
        mainFrame.add(mainPanel);
        mainFrame.setContentPane(mainPanel);

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
        mainPanel.setVisible(false);
        Combination combo = new Combination();
        //mainFrame.dispose();
        mainFrame.add(combo.mainPanel);
        combo.mainPanel.setVisible(true);
    }
}