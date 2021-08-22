import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class combines the skill allocation and naming aspect combined
//used multiple methods in order to avoid checkstyle error

public class Combination extends WelcomeMenuController {
    protected int skills;
    protected int points1;
    protected int points2;
    protected int points3;
    protected int points4;
    private JButton pilot = new JButton("Pilot: " + points1);
    private JButton fighter = new JButton("Fighter: " + points2);
    private JButton merchant = new JButton("Merchant: " + points3);
    private JButton engineer = new JButton("Engineer: " + points4);

    private JButton reset = new JButton("Reset Skills");
    private ButtonGroup difficulty = new ButtonGroup();
    private JRadioButton easy = new JRadioButton("Easy");
    private JRadioButton medium = new JRadioButton("Medium");
    private JRadioButton hard = new JRadioButton("Hard");

    private JLabel label = new JLabel("Enter your name:", JLabel.CENTER);
    private JTextArea name = new JTextArea("name");
    protected String username;

    protected int startingCredits;
    protected JPanel setupPanel = new JPanel();
    protected JButton confirmButton = new JButton("Confirm Inputs");
    //protected JPanel confirmPanel = new JPanel();


    protected Combination() {
        setup();
        actions();
    }

    private void setup() {
        // JFrame frame = new JFrame();
        // frame.setMinimumSize(new Dimension(400, 400));
        // mainPanel = new JPanel();
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
        mainFrame.setVisible(true);

    }


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
                points1++;
            }
            pilot.setText("Pilot " + points1);
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
                points2++;
            }
            fighter.setText("Fighter " + points2);
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
                points3++;
            }
            merchant.setText("Merchant " + points3);
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
                points4++;
            }
            engineer.setText("Engineer " + points4);
            if (skills > 0) {
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
            }
        });
        //actions for reset button
        reset.addActionListener((ActionEvent event)
            -> {
            points1 = 0;
            points2 = 0;
            points3 = 0;
            points4 = 0;
            pilot.setEnabled(true);
            fighter.setEnabled(true);
            merchant.setEnabled(true);
            engineer.setEnabled(true);
            pilot.setText("Pilot " + points1);
            fighter.setText("Fighter " + points2);
            merchant.setText("Merchant " + points3);
            engineer.setText("Engineer " + points4);
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
            confirmButton.setEnabled(false);
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = name.getText();
                changeToConfirmation();
            }
        });

    }


    private void changeToConfirmation() {
        setupPanel.setVisible(false);
        Confirmation confir = new Confirmation(points1, points2, points3,
                points4, startingCredits, username);
        mainFrame.dispose();
        mainFrame.add(confir.setupPanel);
        confir.setupPanel.setVisible(true);
    }

}
