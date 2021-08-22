package spacetrader;

class Bandit extends NPC {

    /**
     * Creates a new Bandit.
     */
    Bandit() {
        super("Bandit");
        //getInteraction().banditDisplay();
    }

    @Override
    void makeDisplay() {
        getInteraction().banditDisplay();
    }
}
