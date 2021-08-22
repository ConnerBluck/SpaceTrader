package spacetrader;

public enum ShipType {
    STARSHIP(20, 50),
    BFR(50, 100);

    private int cargoCapacity;
    private int health;

    /**
     * constructor
     *
     * @param cargoCapacity the capacity of the ship
     * @param health the health of the ship
     */
    ShipType(int cargoCapacity, int health) {
        this.cargoCapacity = cargoCapacity;
        this.health = health;
    }

    /**
     * getter for cargo capacity
     *
     * @return the cargo capacity
     */
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * getter for ship health
     *
     * @return the ship health
     */
    public int getHealth() {
        return health;
    }

}
