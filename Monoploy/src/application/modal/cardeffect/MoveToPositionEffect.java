package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for moving a player to a specific position.
 * Uses Player.setPosition to directly update position without triggering "Pass Go" logic.
 */
public class MoveToPositionEffect implements CardEffect {
    private final int position;

    public MoveToPositionEffect(int position) {
        this.position = position;
    }

    @Override
    public void execute(Player player) {
        player.setPosition(position);
    }

    // For testability: Getter for position
    public int getPosition() {
        return position;
    }
}