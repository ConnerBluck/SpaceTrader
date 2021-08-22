package spacetrader;

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
     * Creates a region.
     * @param name the name of the region
     * @param xCord the x-coordinate at which the region is located
     * @param yCord the y-coordinate at whic the region is located
     */
    protected Region(String name, int xCord, int yCord) {
        this.name = name;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    /**
     * Gets the name of the region
     * @return the region's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the x-coordinate of the region
     * @return the region's x-coordinate
     */
    public int getX() {
        return this.xCord;
    }

    /**
     * Gets the y-coordinate of the region
     * @return the region's y-coordinate
     */
    public int getY() {
        return this.yCord;
    }

    /**
     * Gets the tech level
     * @return the tech level
     */
    public TechLevel getLevel() {
        return this.tech;
    }
}