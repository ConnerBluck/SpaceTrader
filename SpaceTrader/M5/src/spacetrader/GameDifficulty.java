package spacetrader;

public enum GameDifficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int difficulty;

    /**
     * constructor
     *
     * @param difficulty the difficulty level
     */
    GameDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * gets the difficulty level
     *
     * @return the difficulty level
     */
    public int getDifficulty() {
        return difficulty;
    }


}
