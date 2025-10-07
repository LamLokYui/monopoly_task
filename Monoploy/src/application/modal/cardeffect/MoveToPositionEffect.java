package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for moving a player to a specific position.
 * Uses Player.setPosition to directly update position without triggering "Pass Go" logic.
 */
public class MoveToPositionEffect implements CardEffect {
    private final int position;

    public MoveToPositionEffect(int position) {
        if (position < 0 || position >= 40) {
            throw new IllegalArgumentException("Position must be between 0 and 39");
        }
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