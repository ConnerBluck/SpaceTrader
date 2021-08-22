package spacetrader;

import java.util.Random;

/**
 * An enum representing a TechLevel, which is the type of economy a Region has.
 */
public enum TechLevel {
    PRE_AGRICULTURE(1),
    AGRICULTURE(2),
    MEDIEVAL(3),
    RENAISSANCE(4),
    INDUSTRIAL(5),
    MODERN(6),
    FUTURISTIC(7);

    /**
     * The number associated with each TechLevel.
     */
    private final int level;

    /**
     * constructor
     *
     * @param level the tech level
     */
    TechLevel(int level) {
        this.level = level;
    }

    /**
     * gets the tech level
     *
     * @return the tech level
     */
    public int getLevel() {
        return level;
    }

    /**
     * All the possible TechLevels.
     */
    private static TechLevel[] levels = TechLevel.values();

    /**
     * A Random object, for use in the random() function.
     */
    private static Random random = new Random();

    /**
     * Returns a random TechLevel.
     * @return a random TechLevel
     */
    public static TechLevel random() {
        return levels[random.nextInt(levels.length)];
    }
}