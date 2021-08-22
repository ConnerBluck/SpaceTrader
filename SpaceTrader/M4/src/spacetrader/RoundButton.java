package spacetrader;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Graphics;

public class RoundButton extends JButton {
    private int xCord;
    private int yCord;

    /**
     * Creates a new RoundButton, given a label and coordinates
     * @param label the label for this RoundButton
     * @param xCord the x-coordinate of this RoundButton
     * @param yCord the y-coordinate of this RoundButton
     */
    public RoundButton(String label, int xCord, int yCord) {

        super(label);
        setBackground(Color.WHITE);
        setFocusable(false);

        Dimension size = getPreferredSize();
        size.width = 75;
        size.height = 75;

        setContentAreaFilled(false);

        this.xCord = xCord;
        this.yCord = yCord;
    }

    /**
     * Creates a new RoundButton, given only a label
     * @param label the label for this RoundButton
     */
    public RoundButton(String label) {
        super(label);
        setBackground(Color.BLUE);
        setFocusable(false);

        Dimension size = getPreferredSize();
        size.width = 75;
        size.height = 75;

        setContentAreaFilled(false);
    }

    /**
     * Makes the button a circle (or it's supposed to at least?)
     * @param g a Graphics object
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(this.xCord, this.yCord, getSize().width - 1,
                getSize().height - 1);

        super.paintComponent(g);
    }

    /**
     * sets the x-coordinate for the RoundButton object
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.xCord = x;
    }

    /**
     * sets the y-coordinate for the RoundButton object
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.yCord = y;
    }


}
