package spacetrader;

import javax.swing.JLabel;
import java.text.DecimalFormat;

public class Ship {

    private ShipType shipType;
    private int cargoSize;
    private int currentCargo;
    private double maxFuel;
    private double currentFuel;
    private int maxHealth;
    private int currentHealth;
    private Health healthObject;
    private static final double STARTING_FUEL = 1000.00;

    /**
     * Creates a ship of a given ship type.
     *
     * @param shipType the type of ship that's being made
     */
    Ship(ShipType shipType) {
        this.shipType = shipType;
        cargoSize = shipType.getCargoCapacity();
        currentCargo = 0;
        maxFuel = STARTING_FUEL;
        currentFuel = maxFuel;
        maxHealth = shipType.getHealth();
        healthObject = new Health(shipType.getHealth());
        healthObject.addObserver(Controller.gameOverScreen);
        currentHealth = maxHealth;
    }

    /**
     * getter for ship current cargo
     *
     * @return the ship current cargo
     */
    public int getCargo() {
        return currentCargo;
    }

    /**
     * getter for ship cargo size
     *
     * @return the ship cargo size
     */
    public int getCargoSize() {
        return cargoSize;
    }

    /**
     * getter for ship type
     *
     * @return the ship type
     */
    public ShipType getShipType() {
        return shipType;
    }

    /**
     * setter for ship type
     *
     * @param ship the new ship type
     */
    void setShipType(ShipType ship) {
        this.shipType = ship;
    }

    /**
     * setter for max cargo size
     *
     * @param cargoSize the new cargo size
     */
    void setCargoSize(int cargoSize) {
        this.cargoSize = cargoSize;
    }


    /**
     * getter for ship fuel
     *
     * @return the ship fuel
     */
    double getFuel() {
        return currentFuel;
    }

    /**
     * getter for ship max fuel
     *
     * @return the ship max fuel
     */
    double getMaxFuel() {
        return maxFuel;
    }


    /**
     * setter for ship fuel
     *
     * @param fuel the new ship fuel amount
     */
    void setFuel(double fuel) {
        this.currentFuel = fuel;
    }

    /**
     * getter for ship health
     *
     * @return the ship health
     */
    int getHealth() {
        return currentHealth;
    }

    /**
     * setter for ship health
     *
     * @param health the new ship health
     */
    void setHealth(int health) {

        this.currentHealth = health;
        this.healthObject.setHealth(health);
    }

    /**
     * Adds or deducts available cargo space from the ship.
     * @param changeInCargo a positive int if adding cargo, a negative int if
     *                      deducting cargo
     */
    void addOrRemoveCargo(int changeInCargo) {
        if (currentCargo + changeInCargo > cargoSize) {
            throw new IllegalArgumentException(
                    "There isn't enough room on the ship to add more cargo.");
        } else {
            currentCargo += changeInCargo;
        }
    }

    /**
     * Damages the ship's health by a given amount.
     * @param damageTaken the amount of health to take away from the ship
     */
    void damageHealth(int damageTaken) {
        currentHealth -= damageTaken;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        healthObject.damageHealth(damageTaken);
    }

    /**
     * Determines if the Ship's health is lower than its max.
     * @return if the ship is damaged or not
     */
    boolean isShipDamaged() {
        System.out.println("ship health: " + currentHealth);
        System.out.println("type health: " + maxHealth);
        return (currentHealth < maxHealth);
    }

    /**
     * Sets the ship's current health to its max health.
     * @param wantsToRepair an int representing if the person wants to repair
     * @param healthInfo the label displaying the health info
     * @param cost the cost of the repair
     * @param creditDisplay the label displaying the credit info
     * @return if the repair was successful
     */
    boolean repairShip(int wantsToRepair, JLabel healthInfo,
                        double cost, JLabel creditDisplay) {
        if (wantsToRepair == 0) {
            if (Game.playerOne.getCredits() >= cost) {
                setHealth(maxHealth);
                healthInfo.setText("Current health : " + currentHealth);
                Game.playerOne.addCredits(-cost);
                creditDisplay.setText("Current credits: "
                        + new DecimalFormat(
                        "0.00").format(
                        Game.playerOne.getCredits()));
                return true;
            }
            return false;
        }
        return true;
    }
}
