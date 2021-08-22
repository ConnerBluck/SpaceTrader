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
        NPC police = new Police();
        NPC bandit = new Bandit();
        NPC trader = new Trader();
        int encounterNum = rand.nextInt(30);
        if (difficulty == GameDifficulty.EASY) {
            if (encounterNum == 0
                    && player.getPlayerInventory().hasItems()) {
                police.getInteraction().policeDisplay();
                return police.getJDialog();
            } else if (encounterNum == 10) {
                bandit.getInteraction().banditDisplay();
                return bandit.getJDialog();
            } else if (encounterNum == 20) {
                trader.getInteraction().traderDisplay(
                        Game.playerOne.getPlayerInventory().getRandomItem());
                return trader.getJDialog();
            }
        } else if (difficulty == GameDifficulty.MEDIUM) {
            if ((encounterNum == 0 || encounterNum == 1)
                    && player.getPlayerInventory().hasItems()) {
                police.getInteraction().policeDisplay();
                return police.getJDialog();
            } else if (encounterNum == 10 || encounterNum == 11) {
                bandit.getInteraction().banditDisplay();
                return bandit.getJDialog();
            } else if (encounterNum == 20) {
                trader.getInteraction().traderDisplay(
                        Game.playerOne.getPlayerInventory().getRandomItem());
                return trader.getJDialog();
            }
        } else if (difficulty == GameDifficulty.HARD) {
            if ((encounterNum == 0 || encounterNum == 1 || encounterNum == 2)
                    && player.getPlayerInventory().hasItems()) {
                police.getInteraction().policeDisplay();
                return police.getJDialog();
            } else if (encounterNum == 10
                    || encounterNum == 11 || encounterNum == 12) {
                bandit.getInteraction().banditDisplay();
                return bandit.getJDialog();
            } else if (encounterNum == 20 || encounterNum == 21) {
                trader.getInteraction().traderDisplay(
                        Game.playerOne.getPlayerInventory().getRandomItem());
                return trader.getJDialog();
            }
        }
        return null;
    }
}
