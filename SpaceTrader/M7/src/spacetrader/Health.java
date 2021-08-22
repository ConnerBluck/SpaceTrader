package spacetrader;

import java.util.Observable;

class Health extends Observable {

    private int health;

    /**
     * Creates a new health object
     * @param health starting health
     */
    Health(int health) {
        this.health = health;
    }

    /**
     * Sets the health of the Health.
     * @param health the health
     */
    void setHealth(int health) {
        this.health = health;
        if (this.health == 0) {
            setChanged();
            notifyObservers(0);
        }
    }

    /**
     * Damages the health
     * @param damage damage taken to health
     */
    void damageHealth(int damage) {
        if (this.health - damage <= 0) {
            this.health = 0;
            setChanged();
            notifyObservers(0);
        } else {
            this.health -= damage;
        }
    }
}
