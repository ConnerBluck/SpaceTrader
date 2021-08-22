package spacetrader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Inventory extends Controller {

    private ArrayList<Item> itemList = new ArrayList<>(11);
    private JOptionPane warning = new JOptionPane();

    /**
     * Creates a new inventory with nothing in it.
     */
    protected Inventory() {
        toList();

        for (Item item : itemList) {
            item.setCount(0);
            item.setBuyable(true);
            item.setSellable(true);
        }

        itemList.get(0).setSellable(false);
    }

    /**
     * Fills in the backing list for the Inventory.
     * @return the backing list for the Inventory
     */
    private ArrayList<Item> toList() {
        itemList.add(new Item("Fuel"));
        itemList.add(new Item("Food"));
        itemList.add(new Item("Adornment"));
        itemList.add(new Item("Games"));
        itemList.add(new Item("Medicine"));
        itemList.add(new Item("StarShine"));
        itemList.add(new Item("Artwork"));
        itemList.add(new Item("Pets"));
        itemList.add(new Item("Water"));
        itemList.add(new Item("Furniture"));
        itemList.add(new Item("Weapons"));

        return itemList;
    }

    /**
     * Gets the current list of items in the inventory.
     * @return the list of items in the inventory
     */
    protected ArrayList<Item> getList() {
        return this.itemList;
    }

    /**
     * Buys a certain amount of an Item, given the player has enough funds.
     * @param amount the amount of the Item to buy
     * @param item the Item to buy
     */
    protected void buyGood(int amount, Item item) {
        double price = item.getPrice();
        buyGood(amount, item, price);
    }

    /**
     * Buys a certain amount of an item at a specified price, given the player
     * has enough funds.
     * @param amount the amount of the Item to buy
     * @param item the Item to buy
     * @param price the price to buy the Item at
     */
    protected void buyGood(int amount, Item item, double price) {
        if (item.isBuyable() && ((price * amount)
                <= Game.playerOne.getCredits())) {
            item.changeCount(-amount);
            Game.playerOne.setCredits(Game.playerOne.getCredits()
                    - (price * amount));
            if (item.getName().equals("Fuel")) {
                if (Game.playerOne.getShip().getFuel() + (amount * 100)
                        < Game.playerOne.getShip().getMaxFuel()) {
                    Game.playerOne.getShip().setFuel(
                            Game.playerOne.getShip().getFuel()
                                    + (amount * 100));
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "You do not have enough fuel capacity.",
                            "Not enough fuel capacity!",
                            JOptionPane.ERROR_MESSAGE);
                    Game.playerOne.setCredits(Game.playerOne.getCredits()
                            + (price * amount));
                    item.changeCount(+amount);
                }
            } else {
                Game.playerOne.getShip().addOrRemoveCargo(amount);
            }
            for (Item it
                    : Game.playerOne.getPlayerInventory().getList()) {
                if (item.equals(it)) {
                    it.changeCount(amount);
                    break;
                }
            }
        } else {
            DecimalFormat df = new DecimalFormat(".##");
            throw new InsufficientResourcesException("You only have "
                    + df.format(Game.playerOne.getCredits())
                    + " right now. You can't afford " + amount
                    + " " + item + "(s) right now.");
        }
    }

    /**
     * Gets a random item from the player's inventory.
     * @return the item
     */
    protected Item getRandomItem() {
        Random rand = new Random();
        int index = rand.nextInt(10) + 1;
        return itemList.get(index);
    }

    /**
     * Sells a certain amount of an item and removes it from the inventory.
     * @param amount the amount of the good to sell
     * @param item the Item the player wants to sell
     * @param price the price the item goes for
     * @throws UnsupportedOperationException if the Item is not sellable or the
     *                                       player does not have enough of the
     *                                       item
     */
    protected void sellGood(int amount, Item item, double price) {
        if (item.isSellable() && (item.getCount() >= amount)) {
            item.changeCount(-1 * amount);
            Game.playerOne.setCredits(
                    Game.playerOne.getCredits() + (amount * price));
            Game.playerOne.getShip().addOrRemoveCargo(-1 * amount);
            for (Item i : Game.playerOne.getCurrentLocation().getInventory()) {
                if (item.equals(i)) {
                    i.changeCount(amount);
                    break;
                }
            }
        } else {
            throw new UnsupportedOperationException("You can't sell " + item
                    + "s here!");
        }
    }

    /**
     * Checks if the Inventory has any items with a count of more than 0.
     * @return if the Inventory has any items
     */
    boolean hasItems() {
        for (Item i : itemList) {
            if (i.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a random, non-zero item from the Inventory.
     * @return the random, non-zero item
     */
    Item getRandomNonZeroItem() {
        Item random = getRandomItem();
        while (random.getCount() == 0) {
            random = Game.playerOne.getPlayerInventory().getRandomItem();
        }
        return random;
    }
}
