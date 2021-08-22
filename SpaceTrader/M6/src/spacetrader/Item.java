package spacetrader;

public class Item {
    private boolean isSellable;
    private boolean isBuyable;
    private String name;
    private int count;
    private double price;

    /**
     * Creates an item with a name.
     * @param name the name of the item
     */
    protected Item(String name) {

        this.name = name;
    }

    /**
     * Returns the name of the Item
     * @return the item's name
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Returns the number of Items there are
     * @return the number of items there are
     */
    protected int getCount() {
        return this.count;
    }

    /**
     * Adds an additional number of items to the number of Items there are
     * @param change the additional number of items
     */
    protected void changeCount(int change) {
        this.count = count + change;
    }

    /**
     * Sets the count to an entirely new number
     * @param count the new amount of items there are
     */
    protected void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets the price of the item
     * @return the item's price
     */
    protected double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the item
     * @param price the item's new price
     */
    protected void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns true if the item is available to buy; false otherwise
     * @return if the item is buyable or not
     */
    protected boolean isBuyable() {
        return this.isBuyable;
    }

    /**
     * Returns true if the item is available to sell; false otherwise
     * @return if the item is sellable or not
     */
    protected boolean isSellable() {
        return this.isSellable;
    }

    /**
     * Sets if the item is available to buy or not
     * @param status is buyable or not
     */
    protected void setBuyable(boolean status) {
        this.isBuyable = status;
    }

    /**
     * Sets if the item is available to sell or not
     * @param status is sellable or not
     */
    protected void setSellable(boolean status) {
        this.isSellable = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        } else if (!(o instanceof Item)) {
            return false;
        }

        Item i = (Item) o;

        return this.getName().equals(i.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Is the Item able to be sold?
     * @return if the item can be sold
     */
    boolean canSell() {
        return (isSellable && count > 0);
    }
}
