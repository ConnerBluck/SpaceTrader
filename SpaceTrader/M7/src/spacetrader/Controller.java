//java -jar checkstyle-8.24-all.jar -c cs2340_checks.xml *.java

package spacetrader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;

public class Controller {

    protected static JFrame mainFrame = new JFrame();

    protected JPanel setupPanel = new JPanel();
    protected JPanel mainPanel = new JPanel();
    protected JPanel finalPanel = new JPanel();

    protected static GameOver gameOverScreen = new GameOver();

    protected int pilotPoints;
    protected int fighterPoints;
    protected int merchantPoints;
    protected int engineerPoints;
    protected int startingCredits;
    protected static int skills;
    protected String username;
    protected Region currentLoc;
    protected int xFrame;
    protected int yFrame;

    protected static JPanel iconHolder = new JPanel();

    protected JLabel cargoDisplay = new JLabel();

    /**
     * constructor
     */
    public Controller() {
        xFrame = 800;
        yFrame = 800;
        mainFrame.setMinimumSize(new Dimension(xFrame, yFrame));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * constructor
     *
     * @param username        name of the user
     * @param startingCredits starting balance of the user
     * @param currentLoc      current location of user
     * @param pilotPoints     number of pilot points
     * @param fighterPoints   number of fighter points
     * @param merchantPoints  number of merchant points
     * @param engineerPoints  number of engineer points
     */
    public Controller(String username, int startingCredits, Region currentLoc,
                      int pilotPoints, int fighterPoints, int merchantPoints,
                      int engineerPoints) {
        this();
        this.username = username;
        this.startingCredits = startingCredits;
        this.currentLoc = currentLoc;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.merchantPoints = merchantPoints;
        this.engineerPoints = engineerPoints;

        Game.playerOne = new Player(username, startingCredits, currentLoc,
                pilotPoints, fighterPoints, merchantPoints, engineerPoints);
    }
}
