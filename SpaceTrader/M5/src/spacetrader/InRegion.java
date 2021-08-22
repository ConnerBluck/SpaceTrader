package spacetrader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class InRegion extends Controller {

    private JPanel newGame = new JPanel();
    private JPanel mapPanel = new JPanel();
    private Region initialRegion;
    private Region curr;
    private JLabel xInfo;
    private JLabel yInfo;
    private JLabel fuelInfo;
    private JLabel healthInfo;
    private JLabel shipInfo;
    private JTextArea travelInfo;
    private ArrayList<Region> regions;
    private JPanel backPanel = new JPanel();
    private int count;
    private double distance;
    private double fuelCost;
    private Game game;
    private JPanel buttonPanel = new JPanel();

    /**
     * Makes a screen for when the Player is in a region.
     *
     * @param one pilot points
     */
    protected InRegion(int one) {
        pilotPoints = one;
        mainFrame.setVisible(true);
        mainFrame.setTitle("Regions");
        this.game = new Game();
        regions = game.universe.getRegions();
        initialRegion = regions.get(0);
        Game.playerOne.setCurrentLocation(initialRegion);
        screenSetup();

    }

    /**
     * Makes a screen for when the Player is in a region, taking in a Game,
     * an initialRegion, and a list of all the Regions.
     * @param initialRegion the first region the player should be in
     * @param game the game the player is in
     * @param regions the list of all the regions in this game
     * @param one pilot points
     */
    protected InRegion(Region initialRegion, Game game,
                       ArrayList<Region> regions, int one) {
        pilotPoints = one;
        mainFrame.setVisible(true);
        mainFrame.setTitle("Regions");
        this.initialRegion = initialRegion;
        Game.playerOne.setCurrentLocation(initialRegion);
        this.game = game;
        this.regions = regions;
        screenSetup();
    }

    /**
     * Sets up the screen for a Region.
     */
    private void screenSetup() {
        DecimalFormat df = new DecimalFormat("0.00");
        //where we will display region names, current location, etc
        // if want to make a map should go in here as well
        JLabel regionInfo = new JLabel();
        regionInfo.setText("Current Region: " + initialRegion.getName());
        regionInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        xInfo = new JLabel();
        xInfo.setText("X-Coordinates: " + initialRegion.getX());
        xInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        yInfo = new JLabel();
        yInfo.setText("Y-Coordinates: " + initialRegion.getY());
        yInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        fuelInfo = new JLabel();
        fuelInfo.setText("Total Fuel: " + df.format(
                Game.playerOne.getShip().getFuel()));
        fuelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        healthInfo = new JLabel();
        healthInfo.setText("Current health: "
                + Game.playerOne.getShip().getHealth());
        healthInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        shipInfo = new JLabel();
        shipInfo.setText("Current ship: "
                + Game.playerOne.getShip().getShipType());
        shipInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton travelButton = new JButton("Travel");
        travelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGame.setLayout(new BoxLayout(newGame, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(newGame, BoxLayout.Y_AXIS);
        newGame.setLayout(box);

        newGame.add(regionInfo);
        newGame.add(xInfo);
        newGame.add(yInfo);
        newGame.add(fuelInfo);
        newGame.add(healthInfo);
        newGame.add(shipInfo);
        newGame.add(travelButton);
        marketplaceSetup();
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);


        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        BoxLayout box2 = new BoxLayout(newGame, BoxLayout.Y_AXIS);
        newGame.setLayout(box2);

        backPanel.add(newGame);

        backPanel.setVisible(true);

        mainFrame.setContentPane(backPanel);
        mainFrame.setContentPane(backPanel);

        travelButton.addActionListener((ActionEvent event) -> {
            newGame.setVisible(false);
            regionSetup();
        });

    }

    /**
     * Sets up the marketplace area.
     */
    private void marketplaceSetup() {
        DecimalFormat df = new DecimalFormat("0.00");
        Item[] tempArray1 = new Item[11];
        Item[] tempArray2 = new Item[11];

        Item[] regionList = Game.playerOne.
                getCurrentLocation().getBuyableInventory().toArray(tempArray1);
        Item[] playerList = Game.playerOne.
                getPlayerInventory().getList().toArray(tempArray2);

        JLabel creditDisplay = new JLabel("Current credits: "
                + df.format(Game.playerOne.getCredits()));
        JLabel cargoDisplay = new JLabel("Cargo: "
                + Game.playerOne.getShip().getCargo() + "/"
                + Game.playerOne.getShip().getCargoSize());

        JTabbedPane tabbedPane = new JTabbedPane();
        JList<Item> marketplaceList = new JList<>(regionList);
        marketplaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        marketplaceList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    promptToBuy(marketplaceList.getSelectedValue(),
                            creditDisplay, cargoDisplay);
                }
            }
        });

        JList<Item> playerInventoryList = new JList<>(playerList);
        playerInventoryList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        playerInventoryList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    promptToSell(playerInventoryList.getSelectedValue(),
                            creditDisplay, cargoDisplay);
                }
            }
        });

        tabbedPane.addTab(Game.playerOne.getCurrentLocation().getName()
                + "'s Inventory", marketplaceList);
        tabbedPane.addTab(Game.playerOne.getName() + "'s Inventory",
                playerInventoryList);
        tabbedPane.setPreferredSize(new Dimension(600, 300));
        tabbedPane.setMaximumSize(new Dimension(600, 300));

        newGame.add(tabbedPane);
        newGame.add(creditDisplay);
        newGame.add(cargoDisplay);
    }

    /**
     * Creates a Dialog which asks for buying information
     * @param item the item the Player wants to buy
     * @param creditDisplay the JLabel that shows the Player's credits
     * @param cargoDisplay the JLabel that shows the Player's cargo
     */
    private void promptToBuy(Item item, JLabel creditDisplay,
                             JLabel cargoDisplay) {
        JDialog buyingDialog = new JDialog(mainFrame, "Buying Options");
        buyingDialog.setMinimumSize(new Dimension(400, 275));
        buyingDialog.setMaximumSize(new Dimension(400, 275));
        buyingDialog.setLocationRelativeTo(mainFrame);
        buyingDialog.getRootPane().setLayout(new BoxLayout(
                buyingDialog.getRootPane(), BoxLayout.Y_AXIS));

        DecimalFormat df = new DecimalFormat(".##");

        // a cancel button that closes the dialog if it is pressed
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> {
            buyingDialog.dispose();
        });

        // asks how much you want to buy
        JLabel explanation = new JLabel("How many would you like to buy?");
        JSlider howManyToBuy = new JSlider(JSlider.HORIZONTAL,
                0, item.getCount(), 0);

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
                        * item.getPrice())
                        + " credits"));
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener((ActionEvent e) -> {
            try {
                Game.playerOne.getPlayerInventory().buyGood(
                        howManyToBuy.getValue(), item);
                buyingDialog.dispose();
                if (item.getName().equals("Fuel")) {
                    double newFuel = Game.playerOne.getShip().getFuel();
                    if (newFuel <= Game.playerOne.getShip().getMaxFuel()) {
                        fuelInfo.setText("Total Fuel: " + df.format(
                                Game.playerOne.getShip().getFuel()));
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You got "
                            + howManyToBuy.getValue()
                            + " new things! That's pretty neat.");
                }
                creditDisplay.setText("Current credits: "
                        + df.format(Game.playerOne.getCredits()));
                cargoDisplay.setText("Cargo: "
                        + Game.playerOne.getShip().getCargo()
                        + "/" + Game.playerOne.getShip().getCargoSize());
            } catch (InsufficientResourcesException ex) {
                buyingDialog.dispose();
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(),
                        "Insufficient Funds", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                buyingDialog.dispose();
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(),
                        "No More Room", JOptionPane.WARNING_MESSAGE);
            }

        });
        JPanel temp1 = new JPanel();
        temp1.add(explanation);
        JPanel temp2 = new JPanel();
        temp2.add(howManyToBuy);
        JPanel temp3 = new JPanel();
        temp3.add(cost);
        buyingDialog.getRootPane().add(temp1);
        buyingDialog.getRootPane().add(howManyToBuy);
        buyingDialog.getRootPane().add(temp3);

        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.X_AXIS));
        buttonHolder.add(confirmButton);
        buttonHolder.add(cancelButton);
        buyingDialog.getRootPane().add(buttonHolder);

        buyingDialog.setVisible(true);

    }

    /**
     * Creates a Dialog which asks for selling information
     * @param item the item you want to sell
     * @param creditDisplay the JLabel that displays the player's credits
     * @param cargoDisplay the JLabel that shows the Player's cargo
     */
    private void promptToSell(Item item, JLabel creditDisplay,
                              JLabel cargoDisplay) {
        if (!item.canSell()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "You can't sell that item right now",
                    "Selling Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Item regionsItem = new Item("not an item");
            for (Item it : Game.playerOne.getCurrentLocation()
                    .getInventory()) {
                if (it.equals(item)) {
                    regionsItem = it;
                }
            }
            final double itemPrice = regionsItem.getPrice();
            JDialog sellingDialog = new JDialog(mainFrame,
                    "Selling Options");
            sellingDialog.setMinimumSize(new Dimension(400, 275));
            sellingDialog.getRootPane().setLayout(new BoxLayout(
                    sellingDialog.getRootPane(), BoxLayout.Y_AXIS));
            sellingDialog.setLocationRelativeTo(mainFrame);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener((ActionEvent e) -> {
                sellingDialog.dispose();
            });

            JLabel explanation = new JLabel(
                    "How many would you like to sell?");
            JSlider howManyToSell = new JSlider(JSlider.HORIZONTAL,
                    0, item.getCount(), 0);

            howManyToSell.setMajorTickSpacing(5);
            howManyToSell.setMajorTickSpacing(1);
            howManyToSell.setPaintTicks(true);
            howManyToSell.setPaintLabels(true);
            howManyToSell.setSnapToTicks(true);

            JLabel earnings = new JLabel("Earnings: 0.00 credits");
            DecimalFormat df = new DecimalFormat(".##");

            howManyToSell.addChangeListener((ChangeEvent e) -> {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    earnings.setText("Earnings: " + (df.format(
                            source.getValue()
                            * itemPrice)
                            + " credits"));
                }
            });

            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener((ActionEvent e) -> {
                double profit = (itemPrice * howManyToSell.getValue());
                Game.playerOne.getPlayerInventory().sellGood(
                        howManyToSell.getValue(), item, itemPrice);
                sellingDialog.dispose();
                JOptionPane.showMessageDialog(mainFrame, "Congrats, you've "
                        + "earned " + df.format(profit) + " credits from your s"
                        + "ale! Maybe you should switch to a business degree?");
                creditDisplay.setText("Current credits: "
                        + df.format(Game.playerOne.getCredits()));
                cargoDisplay.setText("Cargo: "
                        + Game.playerOne.getShip().getCargo()
                        + "/" + Game.playerOne.getShip().getCargoSize());

            });
            JPanel temp1 = new JPanel();
            temp1.add(explanation);
            JPanel temp2 = new JPanel();
            temp2.add(howManyToSell);
            JPanel temp3 = new JPanel();
            temp3.add(earnings);
            sellingDialog.getRootPane().add(temp1);
            sellingDialog.getRootPane().add(howManyToSell);
            sellingDialog.getRootPane().add(temp3);

            JPanel buttonHolder = new JPanel();
            buttonHolder.setLayout(new BoxLayout(
                    buttonHolder, BoxLayout.X_AXIS));
            buttonHolder.add(confirmButton);
            buttonHolder.add(cancelButton);
            sellingDialog.getRootPane().add(buttonHolder);

            sellingDialog.setVisible(true);
        }
    }

    /**
     * Sets up the information from the Region and also creates the Region
     * select section.
     */
    private void regionSetup() {

        ArrayList<Region> currentList = regions;



        count = 0;

        JPanel info = new JPanel();
        JPanel returnPanel = new JPanel();
        travelInfo = new JTextArea();
        travelInfo.setEditable(false);
        travelInfo.setMaximumSize(new Dimension(200, 200));
        travelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        JButton back = new JButton("Return");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton goTo = new JButton("Travel Here");
        goTo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton map = new JButton("Map");
        map.setAlignmentX(Component.CENTER_ALIGNMENT);


        curr = currentList.get(0);

        displayRegion(curr);


        info.add(prev);
        info.add(travelInfo);
        info.add(next);
        info.add(back);
        info.add(goTo);
        //info.add(map);
        info.setVisible(true);

        returnPanel.add(back);
        returnPanel.add(map);
        returnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnPanel.setVisible(true);

        //BoxLayout box3 = new BoxLayout(info, BoxLayout.Y_AXIS);
        //info.setLayout(box3);
        BoxLayout box4 = new BoxLayout(returnPanel, BoxLayout.Y_AXIS);
        returnPanel.setLayout(box4);

        backPanel.add(info);
        backPanel.add(returnPanel);


        prev.setEnabled(false);
        next.setEnabled(true);

        next.addActionListener((ActionEvent event) -> {
            if (count >= 9) {
                next.setEnabled(false);
            } else {
                count++;
                if (count == 9) {
                    next.setEnabled(false);
                }
                curr = regions.get(count);
                displayRegion(curr);
            }
            if (count > 0) {
                prev.setEnabled(true);
            } else {
                prev.setEnabled(false);
            }

        });

        prev.addActionListener((ActionEvent event) -> {
            if (count <= 0) {
                prev.setEnabled(false);
            } else {
                count--;
                if (count == 0) {
                    prev.setEnabled(false);
                }
                curr = regions.get(count);
                displayRegion(curr);
            }
            if (count >= 9) {
                next.setEnabled(false);
            } else {
                next.setEnabled(true);
            }
        });

        back.addActionListener((ActionEvent event) -> {
            info.setVisible(false);
            returnPanel.setVisible(false);
            newGame.setVisible(true);
        });

        goTo.addActionListener((ActionEvent event) -> {
            if (canTravel()) {
                Game.playerOne.getShip().setFuel(
                        Game.playerOne.getShip().getFuel() - fuelCost);
            } else {
                JOptionPane.showMessageDialog(info,
                        "You do not have enough fuel to travel here.",
                        "Not enough fuel!",
                        JOptionPane.ERROR_MESSAGE);
            }
            info.setVisible(false);
            returnPanel.setVisible(false);
            newGame.setVisible(false);
            new InRegion(curr, game, currentList, pilotPoints);
            mainFrame.dispose();
        });

        map.addActionListener((ActionEvent event) -> {
            info.setVisible(false);
            returnPanel.setVisible(false);
            newGame.setVisible(false);
            //mapPanel.setVisible(true);
            //buttonPanel.setVisible(true);
            mapSetup();
        });

    }

    /**
     * Sets up the map.
     */
    private void mapSetup() {

        ArrayList<Region> currentList = regions;

        int adjustX = xFrame / 2;
        int adjustY = yFrame / 2;
        count = 0;
        curr = currentList.get(0);
        mapPanel = new JPanel();
        buttonPanel = new JPanel();
        mapPanel.setLayout(null);
        mapPanel.setMinimumSize(new Dimension(800, 600));


        JButton back = new JButton("Return");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea cInfo = new JTextArea();
        cInfo.setEditable(false);
        cInfo.setMinimumSize(new Dimension(200, 75));
        cInfo.setMaximumSize(new Dimension(200, 75));
        cInfo.setText(curr.getName());
        cInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        mapPanel.setBackground(Color.BLUE);
        mapPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton button0 = new RoundButton(currentList.get(0).getName(),
                currentList.get(0).getX(), currentList.get(0).getY());
        mapPanel.add(button0);
        button0.setBounds(currentList.get(0).getX() + adjustX, adjustY
                - currentList.get(0).getY(), 40, 40);

        RoundButton button1 = new RoundButton(currentList.get(1).getName(),
                currentList.get(1).getX(), currentList.get(1).getY());
        mapPanel.add(button1);
        button1.setBounds((currentList.get(1).getX() + adjustX), adjustY
                - currentList.get(1).getY(), 40, 40);

        RoundButton button2 = new RoundButton(currentList.get(2).getName(),
                currentList.get(2).getX(), currentList.get(0).getY());
        mapPanel.add(button2);
        button2.setBounds(currentList.get(2).getX() + adjustX, adjustY
                - currentList.get(2).getY(), 40, 40);

        RoundButton button3 = new RoundButton(currentList.get(3).getName(),
                currentList.get(3).getX(), currentList.get(0).getY());
        mapPanel.add(button3);
        button3.setBounds(currentList.get(3).getX() + adjustX, adjustY
                - currentList.get(3).getY(), 40, 40);

        RoundButton button4 = new RoundButton(currentList.get(4).getName(),
                currentList.get(4).getX(), currentList.get(0).getY());
        mapPanel.add(button4);
        button4.setBounds(currentList.get(4).getX() + adjustX, adjustY
                - currentList.get(4).getY(), 40, 40);

        RoundButton button5 = new RoundButton(currentList.get(5).getName(),
                currentList.get(5).getX(), currentList.get(0).getY());
        mapPanel.add(button5);
        button5.setBounds(currentList.get(5).getX() + adjustX, adjustY
                - currentList.get(5).getY(), 40, 40);

        RoundButton button6 = new RoundButton(currentList.get(6).getName(),
                currentList.get(6).getX(), currentList.get(6).getY());
        mapPanel.add(button6);
        button6.setBounds(currentList.get(6).getX() + adjustX, adjustY
                - currentList.get(6).getY(), 40, 40);

        RoundButton button7 = new RoundButton(currentList.get(7).getName(),
                currentList.get(7).getX(), currentList.get(7).getY());
        mapPanel.add(button7);
        button7.setBounds(currentList.get(7).getX() + adjustX, adjustY
                - currentList.get(7).getY(), 40, 40);

        RoundButton button8 = new RoundButton(currentList.get(8).getName(),
                currentList.get(8).getX(), currentList.get(8).getY());
        mapPanel.add(button8);
        button8.setBounds(currentList.get(8).getX() + adjustX, adjustY
                - currentList.get(8).getY(), 40, 40);

        RoundButton button9 = new RoundButton(currentList.get(9).getName(),
                currentList.get(9).getX(), currentList.get(9).getY());
        mapPanel.add(button9);
        button9.setBounds(currentList.get(9).getX() + adjustX, adjustY
                - currentList.get(9).getY(), 40, 40);


        buttonPanel.add(cInfo);
        buttonPanel.add(back);
        buttonPanel.setMinimumSize(new Dimension(xFrame, 200));

        buttonPanel.setVisible(true);
        mapPanel.setVisible(true);

        BoxLayout box5 = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(box5);

        backPanel.add(buttonPanel);
        backPanel.add(mapPanel);
        backPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        back.addActionListener((ActionEvent event) -> {
            mapPanel.setVisible(false);
            buttonPanel.setVisible(false);
            newGame.setVisible(true);
        });


        button0.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(0).getName());
        });

        button1.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(1).getName());
        });

        button2.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(2).getName());
        });

        button3.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(3).getName());
        });

        button4.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(4).getName());
        });

        button5.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(5).getName());
        });

        button6.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(6).getName());
        });

        button7.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(7).getName());
        });

        button8.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(8).getName());
        });

        button9.addActionListener((ActionEvent event) -> {
            cInfo.setText(currentList.get(9).getName());
        });

    }

    /**
     * determines whether the player has enough fuel to travel
     * @return true if the player has enough fuel to travel, otherwise false
     */
    private boolean canTravel() {
        return fuelCost < Game.playerOne.getShip().getFuel();
    }

    /**
     * Finds the distance of the current Region to the next possible Region.
     * @param curr the Region the player is currently in
     */
    private void displayRegion(Region curr) {
        DecimalFormat df = new DecimalFormat("0.00");
        distance = Math.sqrt(Math.pow((1.0 * (curr.getX()
                - initialRegion.getX())), 2.0)
                + (Math.pow(1.0 * (curr.getY() - initialRegion.getY()), 2.0)));
        fuelCost = distance / (pilotPoints + 1);
        travelInfo.setText("Region: " + curr.getName()
                + "\nX-Coordinates: " + curr.getX()
                + "\nY-Coordinates: " + curr.getY()
                + "\nTech Level: " + curr.getLevel()
                + "\nDistance from current location: " + df.format(distance)
                + "\nFuel Cost: " + df.format(fuelCost));
    }
}
