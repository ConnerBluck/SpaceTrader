package spacetrader;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;


/**
 * The selection screen.
 */
public class Combination extends Controller {
    private JButton pilot = new JButton("Pilot: " + pilotPoints);
    private JButton fighter = new JButton("Fighter: " + fighterPoints);
    private JButton merchant = new JButton("Merchant: " + merchantPoints);
    private JButton engineer = new JButton("Engineer: " + engineerPoints);

    private JButton reset = new JButton("Reset Skills");
    private ButtonGroup difficulty = new ButtonGroup();
    private JRadioButton easy = new JRadioButton("Easy");
    private JRadioButton medium = new JRadioButton("Medium");
    private JRadioButton hard = new JRadioButton("Hard");

    private JLabel label = new JLabel("Enter your name:", JLabel.CENTER);
    private JTextArea name = new JTextArea("name");
    protected String username;

    protected JButton confirmButton = new JButton("Confirm Inputs");
  
    /**
     * Constructor for combination class, sets up the screen and allows for
     * the user's actions to adjust their attributes.
     * This class combines the skill allocation and naming aspect combined
     */

    protected Combination() {
        setup();
        actions();
    }

    /**
     * This method sets up the screen so that the user can choose
     * what their attributes are.
     */
    private void setup() {
        mainFrame.setVisible(true);
        mainFrame.setTitle("Inputs");
        JPanel textPanel = new JPanel();
        JPanel difficultyPanel = new JPanel();
        JPanel skillsPanel = new JPanel();
        JPanel confirmPanel = new JPanel();
        setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(skillsPanel, BoxLayout.X_AXIS);
        skillsPanel.setLayout(box);

        textPanel.add(label);
        textPanel.add(name);
        skillsPanel.add(pilot);
        skillsPanel.add(fighter);
        skillsPanel.add(merchant);
        skillsPanel.add(engineer);
        skillsPanel.add(reset);
        difficultyPanel.add(easy);
        difficultyPanel.add(medium);
        difficultyPanel.add(hard);
        confirmPanel.add(confirmButton);
        //adding panels
        setupPanel.add(textPanel);
        setupPanel.add(skillsPanel);
        setupPanel.add(difficultyPanel);
        setupPanel.add(confirmPanel);

        mainFrame.setContentPane(setupPanel);
        mainFrame.setContentPane(setupPanel);

    }

    /**
     * This methods allows for the user's actions (button clicking)
     * to adjust their player attributes
     */
    protected void actions() {
        confirmButton.setEnabled(false);
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        //actions for easy button
        easy.addActionListener((ActionEvent event)
            -> {
            skills = 16;
            startingCredits = 1000;
            pilot.setEnabled(true);
            fighter.setEnabled(true);
            merchant.setEnabled(true);
            engineer.setEnabled(true);
            reset.doClick();
        });
        //actions for medium button
        medium.addActionListener((ActionEvent event)
            -> {
            skills = 12;
            startingCredits = 500;
            pilot.setEnabled(true);
            fighter.setEnabled(true);
            merchant.setEnabled(true);
            engineer.setEnabled(true);
            reset.doClick();
        });
        //actions for hard button
        hard.addActionListener((ActionEvent event)
            -> {
            skills = 8;
            startingCredits = 100;
            pilot.setEnabled(true);
            fighter.setEnabled(true);
            merchant.setEnabled(true);
            engineer.setEnabled(true);
            reset.doClick();
        });


        //actions for pilot button
        pilot.addActionListener((ActionEvent event)
            -> {
            skills--;
            if (skills == 0) {
                pilot.setEnabled(false);
                fighter.setEnabled(false);
                merchant.setEnabled(false);
                engineer.setEnabled(false);
                confirmButton.setEnabled(true);
            }
            if (skills >= 0) {
                pilotPoints++;
            }
            pilot.setText("Pilot " + pilotPoints);
            if (skills > 0) {
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
            }
        });
        //actions for fighter button
        fighter.addActionListener((ActionEvent event)
            -> {
            skills--;
            if (skills == 0) {
                pilot.setEnabled(false);
                fighter.setEnabled(false);
                merchant.setEnabled(false);
                engineer.setEnabled(false);
                confirmButton.setEnabled(true);
            }
            if (skills >= 0) {
                fighterPoints++;
            }
            fighter.setText("Fighter " + fighterPoints);
            if (skills > 0) {
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
            }
        });
        //actions for merchant button
        merchant.addActionListener((ActionEvent event)
            -> {
            skills--;
            if (skills == 0) {
                pilot.setEnabled(false);
                fighter.setEnabled(false);
                merchant.setEnabled(false);
                engineer.setEnabled(false);
                confirmButton.setEnabled(true);
            }
            if (skills >= 0) {
                merchantPoints++;
            }
            merchant.setText("Merchant " + merchantPoints);
            if (skills > 0) {
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
            }

        });
        //actions for engineer button
        engineer.addActionListener((ActionEvent event)
            -> {
            skills--;
            if (skills == 0) {
                pilot.setEnabled(false);
                fighter.setEnabled(false);
                merchant.setEnabled(false);
                engineer.setEnabled(false);
                confirmButton.setEnabled(true);
            }
            if (skills >= 0) {
                engineerPoints++;
            }
            engineer.setText("Engineer " + engineerPoints);
            if (skills > 0) {
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
            }
        });

        resetButton();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = name.getText();
                if (easy.isSelected()) {
                    Game.difficulty = GameDifficulty.EASY;
                } else if (medium.isSelected()) {
                    Game.difficulty = GameDifficulty.MEDIUM;
                } else {
                    Game.difficulty = GameDifficulty.HARD;
                }
                changeToConfirmation();
            }
        });

    }

    /**
     * Sets all the actions for the reset button so actions isn't too long.
     */
    private void resetButton() {
        //actions for reset button
        reset.addActionListener((ActionEvent event) -> {
            pilotPoints = 0;
            fighterPoints = 0;
            merchantPoints = 0;
            engineerPoints = 0;
            pilot.setEnabled(true);
            fighter.setEnabled(true);
            merchant.setEnabled(true);
            engineer.setEnabled(true);
            pilot.setText("Pilot " + pilotPoints);
            fighter.setText("Fighter " + fighterPoints);
            merchant.setText("Merchant " + merchantPoints);
            engineer.setText("Engineer " + engineerPoints);
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
            confirmButton.setEnabled(false);
            if (easy.isSelected()) {
                skills = 16;
            } else if (medium.isSelected()) {
                skills = 12;
            } else if (hard.isSelected()) {
                skills = 8;
            }
        });
    }

    /**
     * This method changes the screen to the "confirmation screen" by making the
     * current panel invisible and calling the Confirmation constructor,
     * which sets up the confirmation screen.
     */
    private void changeToConfirmation() {
        setupPanel.setVisible(false);
        new Confirmation(pilotPoints, fighterPoints, merchantPoints,
                engineerPoints, startingCredits, username);
    }
}
