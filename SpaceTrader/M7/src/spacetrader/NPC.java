package spacetrader;

import javax.swing.JDialog;
import java.util.Random;

abstract class NPC {
    private String name;
    private NPCInteraction interaction;

    /**
     * Creates a new NPC.
     * @param name the name of the NPC
     */
    NPC(String name) {
        this.name = name;
        interaction  = new NPCInteraction();
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks the player's fighter skill.
     * @param player the player whose skill we need to check
     * @return if the skill check was passed
     */
    static boolean fighterCheck(Player player) {
        Random ran = new Random();
        int checkNum = ran.nextInt(10) + 4;
        return (player.getFighterPoints() >= checkNum);
    }

    /**
     * Checks the player's pilot skill.
     * @param player the player whose skill we need to check
     * @return if the skill check was passed
     */
    static boolean pilotCheck(Player player) {
        Random ran = new Random();
        int checkNum = ran.nextInt(10) + 4;
        return (player.getPilotPoints() >= checkNum);
    }

    /**
     * Checks the player's merchant skill.
     * @param player the player whose skill we need to check
     * @return if the skill check was passed
     */
    static boolean merchantCheck(Player player) {
        Random ran = new Random();
        int checkNum = ran.nextInt(10) + 4;
        return (player.getMerchantPoints() >= checkNum);
    }

    /**
     * gets the NPC interaction
     * @return the NPC interaction
     */
    NPCInteraction getInteraction() {
        return interaction;
    }

    /**
     * gets the JDialog from this interaction
     * @return the jdialog
     */
    JDialog getJDialog() {
        return interaction.getJDialog();
    }

    /**
     * Creates the display for this class
     */
    abstract void makeDisplay();
}
