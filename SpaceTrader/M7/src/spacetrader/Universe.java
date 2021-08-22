package spacetrader;

import java.util.ArrayList;

/**
 * A singleton class representing a Universe object.
 * Each Universe has a list of the Regions within it.
 */
class Universe {

    /**
     * The Universe.
     */
    private static Universe universe = null;

    /**
     * The Regions within the Universe.
     */
    private ArrayList<Region> regions;

    /**
     * Creates a new Universe and initializes an empty array for the Regions
     * within the Universe.
     */
    private Universe() {
        regions = new ArrayList<>(10);
    }

    /**
     * If the Universe does not already exists, this method creates it and
     * returns it. Otherwise, it just returns the Universe that's already been
     * created.
     *
     * @return the Universe object
     */
    static Universe getUniverse() {
        if (universe == null) {
            universe = new Universe();
        }

        return universe;

    }

    /**
     * Gets the list of Regions within this Universe.
     *
     * @return the list of Regions within the Universe
     */
    ArrayList<Region> getRegions() {
        return regions;
    }

    /**
     * Adds a Region to the list of Regions within the Universe.
     *
     * @param r the Region to be added
     */
    void addRegion(Region r) {
        regions.add(r);
    }

    /**
     * Destroys the universe & sets it to null. For restarting game.
     */
    void destroyUniverse() {
        universe = null;
    }

}