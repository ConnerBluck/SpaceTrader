package spacetrader;

public class ShipController {

    private static Ship ship = Game.playerOne.getShip();
    private static ShipType type = ship.getShipType();

    /**
     * Determines if the Ship's health is lower than its max.
     * @return if the ship is damaged or not
     */
    //moved to Ship
    /*
    static boolean isShipDamaged() {
        return (ship.getHealth() < type.getHealth());
    }
    */
    /**
     * Sets the ship's current health to its max health.
     * @param wantsToRepair an int representing if the person wants to repair
     * @param healthInfo the label displaying the health info
     * @param cost the cost of the repair
     * @param creditDisplay the label displaying the credit info
     * @return if the repair was successful
     */
    //moved to Ship
    /*
    static boolean repairShip(int wantsToRepair, JLabel healthInfo,
                           double cost, JLabel creditDisplay) {
        if (wantsToRepair == 0) {
            if (Game.playerOne.getCredits() >= cost) {
                ship.setHealth(type.getHealth());
                healthInfo.setText("Current health : " + ship.getHealth());
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

     */


    /**
     * Gets the ship's type.
     * @return the ship's type
     */
    static ShipType getShipType() {
        return type;
    }

    /**
     * Determines how much the ship will cost to repair.
     * @return the repair cost
     */
    static double determineRepairCost() {
        double startingCost = 250.0;
        int engineeringLevel = Game.playerOne.getEngineerPoints();
        return (startingCost * (1 - (.1 * (engineeringLevel - 1))));
    }


}
