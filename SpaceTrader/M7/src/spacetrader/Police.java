package spacetrader;

class Police extends NPC {

    /**
     * Creates a new Police officer.
     */
    Police() {
        super("Police Officer");
    }

    @Override
    void makeDisplay() {
        getInteraction().policeDisplay();
    }
}
