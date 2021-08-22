package spacetrader;

import javax.swing.JDialog;
import java.util.Random;

public class EncounterGenerator {

    /**
     * A method to determine if an encounter will happen during travel.
     * @param difficulty the difficulty of the game
     * @param player the player for this game
     * @return the NPC that the player encountered; or null for no encounter
     */
    static JDialog determineEncounter(
            GameDifficulty difficulty, Player player) {
        Random rand = new Random();
        NPC npc = null;
        int encounterNum = rand.nextInt(30);
        if (difficulty == GameDifficulty.EASY) {
            if (encounterNum == 0
                    && player.getPlayerInventory().hasItems()) {
                npc = new Police();
            } else if (encounterNum == 10) {
                npc = new Bandit();
            } else if (encounterNum == 20) {
                npc = new Trader();
            }
        } else if (difficulty == GameDifficulty.MEDIUM) {
            if ((encounterNum == 0 || encounterNum == 1)
                    && player.getPlayerInventory().hasItems()) {
                npc = new Police();
            } else if (encounterNum == 10 || encounterNum == 11) {
                npc = new Bandit();
            } else if (encounterNum == 20) {
                npc = new Trader();
            }
        } else if (difficulty == GameDifficulty.HARD) {
            if ((encounterNum == 0 || encounterNum == 1 || encounterNum == 2)
                    && player.getPlayerInventory().hasItems()) {
                npc = new Police();
            } else if (encounterNum == 10
                    || encounterNum == 11 || encounterNum == 12) {
                npc = new Bandit();
            } else if (encounterNum == 20 || encounterNum == 21) {
                npc = new Trader();
            }
        }
        if (npc == null) {
            return null;
        } else {
            npc.makeDisplay();
            return npc.getJDialog();
        }
    }
}
