package spacetrader;

public class Player {
    private int credits;
    private Region currentLocation;
    private int pilotPoints;
    private int fighterPoints;
    private int merchantPoints;
    private int engineerPoints;
    private String name;

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
    int getCredits() {
        return credits;
    }

    /**
     * Sets the amount of credits the player should have
     * @param credits the new amount of credits the player has
     */
    void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Adds a certain amount of credits to the player's credits
     * @param moreMoney the amount of credits to add
     */
    void addCredits(int moreMoney) {
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
}
