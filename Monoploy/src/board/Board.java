package board;

import java.util.ArrayList;
import java.util.List;


public class Board {
	private static Board instance;
	private List<Square>squares;
	
	public Board() {
		squares = new ArrayList<>();
	}
	
	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}
}