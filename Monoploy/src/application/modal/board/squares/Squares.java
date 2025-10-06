package application.modal.board.squares;

public class Squares {
	private String name;
	private int position;
	
	public Squares (String name, int position) {
		this.name = name;
		this.position = position;
	}
	
	public int getPosition () {
		return position;
	}
	
	public String getName() {
		return name;
	}
	
}
