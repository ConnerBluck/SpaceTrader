package spacetrader;

import java.util.ArrayList;

/**
 * Represents a Game, which has a Player and a Universe within it.
 */
public class Game extends Controller {

    /**
     * Keeps track of which Region names have already been used.
     */
    private static ArrayList<String> tracker = new ArrayList<>();

    /**
     * The list of all possible names for a Region.
     */
    private static String[] names = {"Vulcon", "Cardessia Derivative",
        "Klingoff", "Alphazed", "Reeza", "Bayor", "Arth",
        "Feralignar", "Tursus Four", "Funder's Homeplanet"};

    /**
     * The number of x-coordinates that have been assigned to Regions so far.
     */
    private static int xcount = 0;

    /**
     * The number of y-coordinates that have been assigned to Regions so far.
     */
    private static int ycount = 0;

    /**
     * The Universe for this Game.
     */
    protected static Universe universe;

    /**
     * The Difficulty of this Game.
     */
    protected static GameDifficulty difficulty;

    /**
     * The Player of this Game.
     */
    protected static Player playerOne;

    /**
     * Creates a new Game object, including a universe and a list of regions.
     */
    protected Game() {
        tracker.clear();
        xCord.clear();
        yCord.clear();
        xcount = 0;
        ycount = 0;

        startGame();
    }

    /**
     * Generates a list of possible x and y coordinates for the regions in the
     * universe, and adds all the regions in the Game to the Universe's list of
     * regions.
     */
    private void startGame() {
        gen(xCord);
        gen(yCord);
        universe = Universe.getUniverse();
        for (int i = 0; i < 10; i++) {
            universe.addRegion(regionGenerator());
        }

    }

    private static ArrayList<Integer> xCord = new ArrayList<>();
    private static ArrayList<Integer> yCord = new ArrayList<>();

    /**
     * Generates an integer between 0 and 200, ensures it's not a duplicate in
     * the passed in list, and then adds it to the list.
     * @param cord the list of cords
     */
    private static void gen(ArrayList<Integer> cord) {
        boolean done = false;
        while (!done) {
            boolean canAdd = true;
            int x = (int) (Math.random() * 401) + -200;

            if (!cord.contains(x)) {
                for (int coord: cord) {
                    if (Math.abs(coord - x) <= 5) {
                        canAdd = false;
                        break;
                    }
                }
                if (canAdd) {
                    cord.add(x);
                }
            }

            if (cord.size() == 10) {
                done = true;
            }
        }
    }

    /**
     * Chooses a name at random from the list of possible region names.
     *
     * @return the random name
     */
    private static String assignRegionName() {
        int r = (int) (Math.random() * 10);
        String name = names[r];
        if (!tracker.contains(name)) {
            tracker.add(name);
            return name;
        } else {
            return assignRegionName();
        }
    }

    /**
     * Gets an x-coordinate from the randomly generated list.
     * @return an x-coordinate
     */
    private static int xCord() {
        int cord = xCord.get(xcount);
        xcount++;
        return cord;
    }

    /**
     * Gets a y-coordinate from the randomly generated list.
     * @return a y-coordinate
     */
    private static int yCord() {
        int cord = yCord.get(ycount);
        ycount++;
        return cord;
    }

    /**
     * Generates a region.
     * @return a Region with random attributes
     */
    protected static Region regionGenerator() {
        int x = xCord();
        int y = yCord();
        String name = assignRegionName();

        return new Region(name, x, y, playerOne.getMerchantPoints());
    }

}