import javax.swing.*;
import java.awt.Component;
import java.awt.Label;
import java.awt.*;
// final screen

public class Confirmation extends Combination {

    private JPanel finalPanel = new JPanel();

    Confirmation(int one, int two, int three, int four,
                 int five, String username) {
        this.points1 = one;
        this.points2 = two;
        this.points3 = three;
        this.points4 = four;
        this.startingCredits = five;
        this.username = username;
        status();
    }

    private void status() {
        JPanel endPanel = new JPanel();

        Label nameLabel = new Label("Name: " + username);
        // textArea.setEditable(false);
        Label pilotLabel = new Label("Pilot Skills: " + points1);
        Label fighterLabel = new Label("\nFighter Skills: " + points2);
        Label merchantLabel = new Label("\nMerchant Skills: " + points3);
        Label engineerLabel = new Label("\nEngineer Skills: " + points4);
        Label creditLabel = new Label("\nStarting Credits: " + startingCredits);

        nameLabel.setAlignment(Label.CENTER);
        pilotLabel.setAlignment(Label.CENTER);
        fighterLabel.setAlignment(Label.CENTER);
        merchantLabel.setAlignment(Label.CENTER);
        engineerLabel.setAlignment(Label.CENTER);
        creditLabel.setAlignment(Label.CENTER);

        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(endPanel, BoxLayout.Y_AXIS);
        endPanel.setLayout(box);

        endPanel.add(nameLabel);
        endPanel.add(pilotLabel);
        endPanel.add(fighterLabel);
        endPanel.add(merchantLabel);
        endPanel.add(engineerLabel);
        endPanel.add(creditLabel);

        endPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        finalPanel.add(endPanel);

        mainFrame.setContentPane(finalPanel);
        mainFrame.setContentPane(finalPanel);
        mainFrame.setVisible(true);
    }
}
