package spacetrader;

class KarmaController {

    /**
     * Handles a Bad Action occurring.
     */
    static void badAction() {
        Game.playerOne.lowerKarma();
    }

    /**
     * Handles a Good Action occurring.
     */
    static void goodAction() {
        Game.playerOne.increaseKarma();
    }

    /**
     * Checks if the Player has good Karma.
     * @return if the Player's karma is at least 5
     */
    static boolean hasGoodKarma() {
        return (Game.playerOne.getKarma() >= 5);
    }
}
