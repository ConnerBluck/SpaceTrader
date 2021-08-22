package spacetrader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Observable;
import java.util.Observer;

public class GameOver extends Controller implements Observer {

    @Override
    public void update(Observable observe, Object o) {
        JFrame tempFrame = new JFrame();
        tempFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                mainFrame.dispose();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // do nothing
            }
        });
        mainFrame.dispose();
        tempFrame.setTitle("You lost!");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel whyYouDied = new JLabel("<html>"
                + "Your ship's health fell to 0, and "
                + "that finally made you realize that you were never going to "
                + "be a great Space Trader. In fact, you might not be cut out "
                + "to be a Space Trader at all. Sadly, you hitchhiked your way"
                + " back to Arth and took an office job at Gloogle, where you "
                + "spend the rest of your time daydreaming of your life in "
                + "space."
                + "</html>");
        whyYouDied.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        JButton startOver = new JButton("Start Over");
        startOver.addActionListener((ActionEvent e) -> {
            Game.universe.destroyUniverse();
            new WelcomeMenuController();
            tempFrame.dispose();
        });
        JButton quit = new JButton("Quit");
        quit.addActionListener((ActionEvent e) -> {
            tempFrame.dispose();
        });
        buttonPane.add(startOver);
        buttonPane.add(quit);

        panel.add(whyYouDied);
        panel.add(buttonPane);
        tempFrame.setContentPane(panel);
        tempFrame.setPreferredSize(new Dimension(800, 800));
        tempFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tempFrame.pack();
        tempFrame.setAlwaysOnTop(true);
        tempFrame.setVisible(true);
    }
}
