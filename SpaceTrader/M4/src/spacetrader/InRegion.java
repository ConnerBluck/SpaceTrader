package spacetrader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class InRegion extends Controller {

    private JPanel newGame = new JPanel();
    private JPanel mapPanel = new JPanel();
    private Region initialRegion;
    private Region curr;
    private JLabel xInfo;
    private JLabel yInfo;
    private JTextArea travelInfo;
    private ArrayList<Region> regions;
    private JPanel backPanel = new JPanel();
    private int count;
    private double distance;
    private Game game;
    private JPanel buttonPanel = new JPanel();

    /**
     * Makes a screen for when the Player is in a region.
     */
    protected InRegion() {
        mainFrame.setVisible(true);
        mainFrame.setTitle("Regions");
        this.game = new Game();
        regions = game.universe.getRegions();
        initialRegion = regions.get(0);
        screenSetup();

    }

    /**
     * Makes a screen for when the Player is in a region, taking in a Game,
     * an initialRegion, and a list of all the Regions.
     * @param initialRegion the first region the player should be in
     * @param game the game the player is in
     * @param regions the list of all the regions in this game
     */
    protected InRegion(Region initialRegion, Game game,
                       ArrayList<Region> regions) {
        mainFrame.setVisible(true);
        mainFrame.setTitle("Regions");
        this.initialRegion = initialRegion;
        this.game = game;
        this.regions = regions;
        screenSetup();
    }

    /**
     * Sets up the screen for a Region.
     */
    private void screenSetup() {
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

        JButton travelButton = new JButton("Travel");
        travelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGame.setLayout(new BoxLayout(newGame, BoxLayout.Y_AXIS));
        BoxLayout box = new BoxLayout(newGame, BoxLayout.Y_AXIS);
        newGame.setLayout(box);

        newGame.add(regionInfo);
        newGame.add(xInfo);
        newGame.add(yInfo);
        newGame.add(travelButton);
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
            info.setVisible(false);
            returnPanel.setVisible(false);
            newGame.setVisible(false);
            new InRegion(curr, game, currentList);
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
     * Finds the distance of the current Region to the next possible Region.
     * @param curr the Region the player is currently in
     */
    private void displayRegion(Region curr) {
        DecimalFormat df = new DecimalFormat("0.00");
        distance = Math.sqrt(Math.pow((1.0 * (curr.getX()
                - initialRegion.getX())), 2.0)
                + (Math.pow(1.0 * (curr.getY() - initialRegion.getY()), 2.0)));
        travelInfo.setText("Region: " + curr.getName()
                + "\nX-Coordinates: " + curr.getX()
                + "\nY-Coordinates: " + curr.getY()
                + "\nTech Level: " + curr.getLevel()
                + "\nDistance from current location: " + df.format(distance));
    }
}
