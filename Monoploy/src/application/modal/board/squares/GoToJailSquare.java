package application.modal.board.squares;

import application.modal.player.Player;

//GoToJailSqure: player will directly send to JailSquare
public class GoToJailSquare extends Squares{
	private final int jailPosition;
	
	public GoToJailSquare(int position, int jailPosition) {
		super("Go To Jail", position);
		this.jailPosition = jailPosition;
	}
	
	public void sendToJail(Player player) {
		player.move(jailPosition);
		player.setJail(true);
	}
}
