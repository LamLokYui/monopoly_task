package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for sending a player to jail.
 * Moves the player to the jail position (10) and sets their jail status to true.
 */
public class GoToJailEffect implements CardEffect {
    private static final int JAIL_POSITION = 10;

    @Override
    public void execute(Player player) {
        player.setPosition(JAIL_POSITION);
        player.setJail(true);
    }
}