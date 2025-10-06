package application.modal.board.squares;

//GoToJailSqure: player will directly send to JailSquare
public class GoToJailSquare extends Squares{
	private final int jailPosition;
	
	public GoToJailSquare(int position, int jailPosition) {
		super("Go To Jail", position);
		this.jailPosition = jailPosition;
	}
}
