import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class clicker extends JFrame {
    private static int skills = 0;
    private static int points1 = 0;
    private static int points2 = 0;
    private static int points3 = 0;
    private static int points4 = 0;

    public clicker() {

        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        JPanel difficultyPanel = new JPanel();
        JPanel skillsPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(skillsPanel, BoxLayout.X_AXIS);
        skillsPanel.setLayout(box);
        //skills buttons
        JButton pilot = new JButton("Pilot " + points1);
        JButton fighter = new JButton("Fighter " + points2);
        JButton merchant = new JButton("Merchant " + points3);
        JButton engineer = new JButton("Engineer " + points4);
        JButton reset = new JButton("Reset Skills");
        //difficulty buttons
        ButtonGroup difficulty = new ButtonGroup();
        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton medium = new JRadioButton("Medium");
        JRadioButton hard = new JRadioButton("Hard");
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);

        mainPanel.add(skillsPanel);
        mainPanel.add(difficultyPanel);
        //adds buttons to panels
        skillsPanel.add(pilot);
        skillsPanel.add(fighter);
        skillsPanel.add(merchant);
        skillsPanel.add(engineer);
        skillsPanel.add(reset);
        difficultyPanel.add(easy);
        difficultyPanel.add(medium);
        difficultyPanel.add(hard);

        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);



        //actions for easy button
        easy.addActionListener((ActionEvent event)
                -> {
            skills = 16;
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
            }
            if(skills >= 0){
                points1++;
            }
            pilot.setText("Pilot " + points1);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);
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
            }
            if(skills >= 0){
                points2++;
            }
            fighter.setText("Fighter " + points2);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);
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
            }
            if(skills >= 0){
                points3++;
            }
            merchant.setText("Merchant " + points3);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);

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
            }
            if(skills >= 0){
                points4++;
            }
            engineer.setText("Engineer " + points4);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);
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
        });

    }

    public static void main(String[] args) {
        new clicker();



    }

}




