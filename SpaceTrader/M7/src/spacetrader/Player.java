package spacetrader;


public class Player extends Controller {
    private double credits;
    private Region currentLocation;
    private int pilotPoints;
    private int fighterPoints;
    private int merchantPoints;
    private int engineerPoints;
    private int karma;

    private String name;

    private Ship ship;

    private Inventory playerInventory;

    /**
     * Creates a Player object.
     * @param name the name of the player
     * @param credits the number of credits they should start with
     * @param currentLocation where the player starts
     * @param pilotPoints their pilot points
     * @param fighterPoints their fighter points
     * @param merchantPoints their merchant points
     * @param engineerPoints their engineer points
     */
    Player(String name, int credits, Region currentLocation, int pilotPoints,
           int fighterPoints, int merchantPoints, int engineerPoints) {
        this.name = name;
        this.credits = credits;
        this.currentLocation = currentLocation;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.merchantPoints = merchantPoints;
        this.engineerPoints = engineerPoints;
        this.karma = 0;

        newShip(ShipType.STARSHIP);

        playerInventory = new Inventory();

        if (Game.difficulty.getDifficulty() == 1) {
            this.setStats(1000, 10, 10);
        } else if (Game.difficulty.getDifficulty() == 2) {
            this.setStats(800, 10, 10);
        } else {
            this.setStats(600, 10, 10);
        }
    }

    /**
     * Sets the Player's initial most important Items.
     * @param initialFuel the amount of fuel the player starts with
     * @param initialWater the amount of water the player starts with
     * @param initialFood the amount of food the player starts with
     */
    private void setStats(int initialFuel, int initialWater, int initialFood) {
        playerInventory.getList().get(0).setCount(initialFuel);
        playerInventory.getList().get(1).setCount(initialFood);
        playerInventory.getList().get(8).setCount(initialWater);
        ship.setCargoSize(initialFood + initialWater);
        ship.addOrRemoveCargo(initialFood + initialWater);

    }

    /**
     * Sets the Player's ship to be a new Ship.
     * @param newShip the Player's snazzy new ship
     */
    private void newShip(ShipType newShip) {
        this.ship = new Ship(newShip);
    }

    /**
     * Gets the name of the Player
     * @return the name of the player
     */
    String getName() {
        return this.name;
    }

    /**
     * Gets the credits the Player currently has
     * @return player's credits
     */
    double getCredits() {
        return credits;
    }

    /**
     * Sets the amount of credits the player should have
     * @param credits the new amount of credits the player has
     */
    void setCredits(double credits) {
        this.credits = credits;
    }

    /**
     * Adds a certain amount of credits to the player's credits
     * @param moreMoney the amount of credits to add
     */
    void addCredits(double moreMoney) {
        credits += moreMoney;
    }

    /**
     * Gets the current location of the player
     * @return the player's current location
     */
    Region getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the new current location of the player
     * @param location the player's current location
     */
    void setCurrentLocation(Region location) {
        currentLocation = location;
    }

    /**
     * Gets the player's pilot points
     * @return the player's pilot points
     */
    int getPilotPoints() {
        return pilotPoints;
    }

    /**
     * Sets the player's pilot points
     * @param pilotPoints the player's new pilot points
     */
    void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * Gets the player's fighter points
     * @return the player's fighter points
     */
    int getFighterPoints() {
        return fighterPoints;
    }

    /**
     * Sets the player's fighter points
     * @param fighterPoints the player's new fighter points
     */
    void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * Gets the player's merchant points
     * @return the player's merchant points
     */
    int getMerchantPoints() {
        return merchantPoints;
    }

    /**
     * Sets the player's merchant points
     * @param merchantPoints the player's new merchant points
     */
    void setMerchantPoints(int merchantPoints) {
        this.merchantPoints = merchantPoints;
    }

    /**
     * Gets the player's engineer points
     * @return the player's engineer points
     */
    int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     * Sets the player's engineer points
     * @param engineerPoints the player's new engineer points
     */
    void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    /**
     * Gets the player's inventory
     * @return the player's inventory
     */
    Inventory getPlayerInventory() {
        return playerInventory;
    }

    /**
     * Gets the Player's ship.
     * @return the player's ship
     */
    Ship getShip() {
        return ship;
    }

    /**
     * Lowers the Player's karma by one.
     */
    void lowerKarma() {
        karma--;
    }

    /**
     * Increases the Player's karma by one.
     */
    void increaseKarma() {
        karma++;
    }

    /**
     * Return's the Player's current karma.
     * @return the Player's current karma.
     */
    int getKarma() {
        return karma;
    }
}
