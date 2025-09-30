package cardeffect;

import player.Player;

public class MoveToPositionEffect implements CardEffect{
	private int position;
	
	public MoveToPositionEffect(int position) {
		this.position = position;
	}
	
	@Override
	public void execute(Player player) {
		player.move(position);
		System.out.println(player + " moved to position" + position);
	}
}
