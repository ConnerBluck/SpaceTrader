package spacetrader;

import java.util.ArrayList;

/**
 * Represents a Region object.
 * Each Region has an economic level--a TechLevel, a name, and an (x, y)
 * location.
 */
public class Region {

    /**
     * The x-coordinate of the Region's location.
     */
    private int xCord;

    /**
     * The y-coordinate of the Region's location.
     */
    private int yCord;

    /**
     * The region's TechLevel--what kind of economy it has.
     */
    private TechLevel tech = TechLevel.random();

    /**
     * The name of the Region.
     */
    private String name;

    /**
     * The Inventory of the Region's marketplace.
     */
    private Inventory inventory;

    /**
     * Creates a region.
     * @param name the name of the region
     * @param xCord the x-coordinate at which the region is located
     * @param yCord the y-coordinate at whic the region is located
     * @param merchantSkill the merchant skill of the player
     */
    protected Region(String name, int xCord, int yCord, int merchantSkill) {
        this.name = name;
        this.xCord = xCord;
        this.yCord = yCord;

        inventory = new Inventory();

        setInventory(this.tech.getLevel(), merchantSkill);
    }

    /**
     * A method to set the marketplace inventory of the Region.
     * @param level the TechLevel of the region
     * @param merchantSkill the merchant skill of the player
     */
    void setInventory(int level, int merchantSkill) {
        for (int i = 0; i < 11; i++) {
            if (i != level) {
                int amount = (int) (Math.random() * 20 + 1);
                inventory.getList().get(i).setCount(amount);

                double price = priceCalculator(amount, merchantSkill);

                inventory.getList().get(i).setPrice(price);
            } else {
                inventory.getList().get(i).setCount(0);
                inventory.getList().get(i).setBuyable(false);
                double price = priceCalculator(10, merchantSkill);
                inventory.getList().get(i).setPrice(price);
                //we should make a label that addresses that is is not buyable
            }
        }
    }

    /**
     * Returns the current inventory of the region's marketplace
     * @return the region's current inventory
     */
    ArrayList<Item> getInventory() {
        return inventory.getList();
    }

    /**
     * Returns the current buyable inventory of the region
     * @return the region's buyable inventory
     */
    ArrayList<Item> getBuyableInventory() {
        ArrayList<Item> list = new ArrayList<>();
        for (Item i : getInventory()) {
            if (i.isBuyable()) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Gets the name of the region
     * @return the region's name
     */
    String getName() {
        return this.name;
    }

    /**
     * Gets the x-coordinate of the region
     * @return the region's x-coordinate
     */
    int getX() {
        return this.xCord;
    }

    /**
     * Gets the y-coordinate of the region
     * @return the region's y-coordinate
     */
    int getY() {
        return this.yCord;
    }

    /**
     * Gets the tech level
     * @return the tech level
     */
    TechLevel getLevel() {
        return this.tech;
    }

    /**
     * Calculates the price of an item for a player with a given
     * merchant skill.
     * @param amountOfItem the amount of the item there is
     * @param merchantSkill the merchant skill of the player
     * @return the item's price
     */
    private double priceCalculator(int amountOfItem, int merchantSkill) {
        double price = (Math.random() * 750 + 50);
        price /= this.tech.getLevel();
        price /= (amountOfItem);
        price *= (1 - (merchantSkill * .01));
        return price;
    }
}