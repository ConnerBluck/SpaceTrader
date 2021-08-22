package spacetrader;

class Trader extends NPC {

    /**
     * Creates a new Trader.
     */
    Trader() {
        super("Trader");
        /*getInteraction().traderDisplay(
                Game.playerOne.getPlayerInventory().getRandomItem());*/

    }

    /**
     * For robbing the player's goods
     * @param amount of money to rob
     * @param item item to rob
     */
    protected static void robGoods(int amount, Item item) {
        Game.playerOne.getShip().addOrRemoveCargo(amount);
        for (Item it
                : Game.playerOne.getPlayerInventory().getList()) {
            if (item.equals(it)) {
                it.changeCount(amount);
                break;
            }
        }

    }

}
