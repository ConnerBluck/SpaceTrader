package spacetrader;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class NPCInteraction extends Controller {

    private static boolean goBack = false;
    private JDialog jDialog;
    private int price = 4;

    /**
     * Creates a new NPC interaction
     */
    NPCInteraction() {
    }

    /**
     * Makes a trader display
     * @param item the item that the trader is trying to sell
     */
    void traderDisplay(Item item) {

        iconSetup("trader");
        DecimalFormat df = new DecimalFormat("0.00");


        JDialog traderDialog = new JDialog(mainFrame, "Trader appeared!");
        traderDialog.setMinimumSize(new Dimension(400, 275));
        traderDialog.setMaximumSize(new Dimension(400, 275));
        traderDialog.setLocationRelativeTo(mainFrame);
        traderDialog.getRootPane().setLayout(new BoxLayout(
                traderDialog.getRootPane(), BoxLayout.Y_AXIS));

        JLabel explanation = new JLabel("Would you like to buy some "
                + item + "?");
        JSlider howManyToBuy = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);

        howManyToBuy.setMajorTickSpacing(5);
        howManyToBuy.setMajorTickSpacing(1);
        howManyToBuy.setPaintTicks(true);
        howManyToBuy.setPaintLabels(true);
        howManyToBuy.setSnapToTicks(true);

        JLabel cost = new JLabel("Cost: 0.00 credits");

        howManyToBuy.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                cost.setText("Cost: " + (df.format(source.getValue()
                        * price)
                        + " credits"));

            }
        });

        JButton ignoreButton = new JButton("Ignore");
        ignoreButton.addActionListener((ActionEvent e) -> {
            traderDialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "You decided not"
                    + " to buy anything from the Trader today. He respected you"
                    + "r decision but secretly felt a little sad inside.");
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener((ActionEvent e) -> {
            try {
                Game.playerOne.getPlayerInventory().buyGood(
                        howManyToBuy.getValue(), item, price);
                traderDialog.dispose();

                KarmaController.goodAction();

                JOptionPane.showMessageDialog(mainFrame, "You got "
                        + howManyToBuy.getValue()
                        + " new things! That's pretty neat.");

            } catch (InsufficientResourcesException ex) {
                traderDialog.dispose();
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(),
                        "Insufficient Funds", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(traderDialog, ex.getMessage(),
                        "No More Room", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton robTrader = new JButton("Rob");
        robTrader.addActionListener((ActionEvent e) -> {
            KarmaController.badAction();
            int amount = Game.playerOne.getShip().getCargoSize()
                    - Game.playerOne.getShip().getCargo();
            if (amount > 10) {
                amount = 10;
            }
            try {
                if (Trader.fighterCheck(Game.playerOne)) {
                    Trader.robGoods(amount, item);
                    JOptionPane.showMessageDialog(traderDialog, "You got "
                            + amount
                            + " new things! That's pretty neat.");
                } else {
                    Game.playerOne.getShip().damageHealth(10);
                    JOptionPane.showMessageDialog(traderDialog,
                            "Robbing unsuccessful,"
                                    + " ship took 10 damage");
                }

                traderDialog.dispose();

            }  catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(traderDialog, ex.getMessage(),
                        "No More Room", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton negotiateButton = new JButton("Negotiate");
        negotiateButton.addActionListener((ActionEvent e) -> {
            if (Trader.merchantCheck(Game.playerOne)) {
                JOptionPane.showMessageDialog(traderDialog,
                        "Negotiating successful!"
                                + " Everything is half off!");
                price = 2;
            } else {
                JOptionPane.showMessageDialog(traderDialog,
                        "Negotiating unsuccessful,"
                                + " everything is doubled.");
                price = 8;
            }
            negotiateButton.setEnabled(false);
            NPC trader = new Trader();
            trader.getJDialog();
        });

        if (Game.playerOne.getShip().getCargo()
                >= Game.playerOne.getShip().getCargoSize()) {
            negotiateButton.setEnabled(false);
            robTrader.setEnabled(false);
            confirmButton.setEnabled(false);
        }


        JPanel temp1 = new JPanel();
        temp1.add(explanation);
        JPanel temp2 = new JPanel();
        temp2.add(howManyToBuy);
        JPanel temp3 = new JPanel();
        temp3.add(cost);
        traderDialog.getRootPane().add(temp1);
        traderDialog.getRootPane().add(howManyToBuy);
        traderDialog.getRootPane().add(temp3);

        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.X_AXIS));
        buttonHolder.add(confirmButton);
        buttonHolder.add(ignoreButton);
        buttonHolder.add(robTrader);
        buttonHolder.add(negotiateButton);
        traderDialog.getRootPane().add(buttonHolder);

        jDialog = traderDialog;
    }

    //all front end for npc interaction goes here

    /**
     * Creates the frontend for a bandit encounter
     */
    void banditDisplay() {
        iconSetup("bandit");

        JDialog banditDialog = new JDialog(mainFrame, "Bandit appeared!");
        banditDialog.setMinimumSize(new Dimension(400, 275));
        banditDialog.setMaximumSize(new Dimension(400, 275));
        banditDialog.setLocationRelativeTo(mainFrame);
        banditDialog.getRootPane().setLayout(new BoxLayout(
                banditDialog.getRootPane(), BoxLayout.Y_AXIS));
        Random random = new Random();
        int creditsDemanded = random.nextInt(1000);
        JButton pay = new JButton("Pay Bandit");
        JButton flee = new JButton("Escape");
        JButton fight = new JButton("Fight!");
        JLabel explanation = new JLabel("<html>"
                + "Listen here, pal. This is my "
                + "territory, and if you want to fly through it, you gotta "
                + "make it worth my while. I'm gonna need at least "
                + creditsDemanded + " credits to let you pass through."
                + "</html>");

        pay.addActionListener((ActionEvent e) -> {
            banditDialog.dispose();
            if ((int) Game.playerOne.getCredits() >= creditsDemanded) {
                Game.playerOne.addCredits(-creditsDemanded);
                JOptionPane.showMessageDialog(mainFrame, "The bandit "
                        + "took your money, but at least you got out of there "
                        + "alive.");
            } else if (
                    Game.playerOne.getPlayerInventory().hasItems()) {
                Game.playerOne.getShip().damageHealth(10);
                JOptionPane.showMessageDialog(mainFrame, "You couldn't"
                        + " pay, so the bandit beat your ship up.");
            } else {
                Item sub = Game.playerOne.getPlayerInventory().
                        getRandomNonZeroItem();
                sub.changeCount(-1);
                Game.playerOne.getShip().addOrRemoveCargo(-1);
                JOptionPane.showMessageDialog(mainFrame, "You didn't "
                        + "have enough credits, so the bandit helped himself to"
                        + " a " + sub + " instead.");

            }
        });

        flee.addActionListener((ActionEvent e) -> {
            banditDialog.dispose();
            KarmaController.goodAction();
            if (NPC.pilotCheck(Game.playerOne)) {
                goBack = true;
                JOptionPane.showMessageDialog(mainFrame, "You escaped "
                        + "from the bandit!");
            } else {
                Game.playerOne.setCredits(0);
                Game.playerOne.getShip().damageHealth(3);
                JOptionPane.showMessageDialog(mainFrame, "You tried to "
                        + "escape, but the bandit caught you. He took all your "
                        + "credits and damaged your health by 3!");
            }
        });

        fight.addActionListener((ActionEvent e) -> {
            banditDialog.dispose();
            if (Bandit.fighterCheck(Game.playerOne)) {
                Game.playerOne.addCredits(200);
                JOptionPane.showMessageDialog(mainFrame, "You beat the "
                        + "bandit and took 200 of his credits!");
            } else {
                Game.playerOne.setCredits(0);
                Game.playerOne.getShip().damageHealth(3);
                JOptionPane.showMessageDialog(mainFrame, "You tried to "
                        + "fight, but the bandit bested you. He took all your "
                        + "credits and damaged your health by 3!");
            }
        });

        banditDialog.getRootPane().add(explanation);
        banditDialog.getRootPane().add(pay);
        banditDialog.getRootPane().add(flee);
        banditDialog.getRootPane().add(fight);

        jDialog = banditDialog;
    }

    /**
     * Creates a display for a Police encounter
     */
    void policeDisplay() {
        iconSetup("police");
        JDialog policeDialog = new JDialog(mainFrame, "Police appeared!");
        policeDialog.setMinimumSize(new Dimension(400, 275));
        policeDialog.setMaximumSize(new Dimension(400, 275));
        policeDialog.setLocationRelativeTo(mainFrame);
        policeDialog.getRootPane().setLayout(new BoxLayout(
                policeDialog.getRootPane(), BoxLayout.Y_AXIS));
        Item stolenItem = Game.playerOne
                .getPlayerInventory().getRandomNonZeroItem();
        JButton pay = new JButton("Forfeit items");
        JButton flee = new JButton("Escape");
        JButton fight = new JButton("Fight!");
        JButton letMeOffPlease = new JButton("Ask for leniency");
        JLabel explanation = new JLabel("<html>"
                + "Stop right there, criminal scum! "
                + "You violated the law. Pay the court a fine or serve your sen"
                + "tence. Your stolen goods [" + stolenItem + "] are now forfei"
                + "t." + "</html>");

        letMeOffPlease.addActionListener((ActionEvent e) -> {
            policeDialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "You asked nicely, "
                    + "and since the cop had heard about all your good deeds, "
                    + "he decided to let you off this time.");
        });

        pay.addActionListener((ActionEvent e) -> {
            policeDialog.dispose();
            KarmaController.goodAction();
            stolenItem.changeCount(-1);
            Game.playerOne.getShip().addOrRemoveCargo(-1);
            JOptionPane.showMessageDialog(mainFrame, "You forfeited 1 "
                    + stolenItem + "!");
        });

        flee.addActionListener((ActionEvent e) -> {
            policeDialog.dispose();
            KarmaController.badAction();
            if (Police.pilotCheck(Game.playerOne)) {
                goBack = true;
                JOptionPane.showMessageDialog(mainFrame, "You escaped!"
                        + " Phew, that was close.");
            } else {
                goBack = true;
                int fine = 5 * Game.difficulty.getDifficulty();

                if (fine > Game.playerOne.getCredits()) {
                    Game.playerOne.setCredits(0.00);
                } else {
                    Game.playerOne.addCredits(-fine);
                }

                Game.playerOne.getShip().damageHealth(10);
                Game.playerOne.getShip().addOrRemoveCargo(-1);

                JOptionPane.showMessageDialog(mainFrame, "Oh no!"
                        + " You lost goods and your health was damaged by 10 "
                        + "points. Also, you had to pay a fine of "
                        + fine + " credits.");
            }
        });

        fight.addActionListener((ActionEvent e) -> {
            policeDialog.dispose();
            KarmaController.badAction();
            if (Police.fighterCheck(Game.playerOne)) {
                JOptionPane.showMessageDialog(mainFrame, "You successf"
                        + "ully fought off the cops! Way to stick it to the ma"
                        + "n, dude.");
            } else {
                goBack = true;
                int damage = 10;
                Game.playerOne.getShip().damageHealth(damage);
                stolenItem.changeCount(-1);
                Game.playerOne.getShip().addOrRemoveCargo(-1);
                JOptionPane.showMessageDialog(mainFrame, "You tried to fight"
                        + " off the police, but his ship far outpowered yours. "
                        + "You took " + damage + " damage, and he confiscated "
                        + "your " + stolenItem + " too.");
            }
        });

        policeDialog.getRootPane().add(explanation);
        policeDialog.getRootPane().add(pay);
        policeDialog.getRootPane().add(flee);
        if (KarmaController.hasGoodKarma()) {
            policeDialog.getRootPane().add(letMeOffPlease);
        }
        policeDialog.getRootPane().add(fight);

        jDialog = policeDialog;
    }

    /**
     * Gets what the variable goBack is
     * @return goBack
     */
    static boolean getGoBack() {
        return goBack;
    }

    /**
     * gets the JDialog created by this class
     * @return the JDialog
     */
    JDialog getJDialog() {
        return jDialog;
    }


    /**
     * Sets up an icon for the JDialog.
     * @param s which icon it is
     */
    protected void iconSetup(String s) {
        URL url;

        try {
            JLabel backgroundPicture = new JLabel();
            if (s.equals("bandit")) {
                url = new URL("https://i.imgur.com/CEgHrOc.jpg");
            } else if (s.equals("police")) {
                url = new URL("https://i.imgur.com/Ia5Z56C.jpg");
            } else {
                url = new URL("https://i.imgur.com/eM9r8SB.jpg");
            }
            Image image = ImageIO.read(url);
            ImageIcon picture = new ImageIcon(image);
            backgroundPicture.setIcon(picture);

            iconHolder.add(backgroundPicture);
            iconHolder.setVisible(true);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
