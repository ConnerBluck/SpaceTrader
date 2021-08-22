package spacetrader;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.Label;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// final screen

public class Confirmation extends Controller {

    //protected JPanel finalPanel = new JPanel();
    private JButton continueButton;

    /**
     * constructor
     *
     * @param one pilot points
     * @param two fighter points
     * @param three merchant points
     * @param four engineer points
     * @param five starting credits
     * @param username username
     */
    Confirmation(int one, int two, int three, int four,
                 int five, String username) {
        super(username, five, null, one, two, three, four);
        pilotPoints = one;
        fighterPoints = two;
        merchantPoints = three;
        engineerPoints = four;
        startingCredits = five;
        this.username = username;
        status();
    }

    /**
     * sets up the the information on the confirmation screen
     */
    private void status() {
        mainFrame.setVisible(true);
        mainFrame.setTitle("Confirmation");
        JPanel endPanel = new JPanel();

        Label nameLabel = new Label("Name: " + username);
        // textArea.setEditable(false);
        Label pilotLabel = new Label("Pilot Skills: " + pilotPoints);
        Label fighterLabel = new Label("\nFighter Skills: " + fighterPoints);
        Label merchantLabel = new Label("\nMerchant Skills: " + merchantPoints);
        Label engineerLabel = new Label("\nEngineer Skills: " + engineerPoints);
        Label creditLabel = new Label("\nStarting Credits: " + startingCredits);

        nameLabel.setAlignment(Label.CENTER);
        pilotLabel.setAlignment(Label.CENTER);
        fighterLabel.setAlignment(Label.CENTER);
        merchantLabel.setAlignment(Label.CENTER);
        engineerLabel.setAlignment(Label.CENTER);
        creditLabel.setAlignment(Label.CENTER);

        //button
        continueButton = new JButton();
        continueButton.setText("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setPreferredSize(new Dimension(256, 50));

        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(endPanel, BoxLayout.Y_AXIS);
        endPanel.setLayout(box);

        endPanel.add(nameLabel);
        endPanel.add(pilotLabel);
        endPanel.add(fighterLabel);
        endPanel.add(merchantLabel);
        endPanel.add(engineerLabel);
        endPanel.add(creditLabel);
        endPanel.add(continueButton);

        endPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        finalPanel.add(endPanel);

        mainFrame.setContentPane(finalPanel);
        mainFrame.setContentPane(finalPanel);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeScreen();
            }
        });
    }

    /**
     * changes the screen
     */
    private void changeScreen() {
        finalPanel.setVisible(false);
        new InRegion();
        mainFrame.dispose();
    };
}
