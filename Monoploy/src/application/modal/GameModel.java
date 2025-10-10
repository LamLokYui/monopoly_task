package application.modal;

import application.modal.board.Board;
import application.modal.player.Player;
import application.modal.dice.Dice;

import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;

public class GameModel {

    private Board board;
    private Dice dice;
    private ArrayList<Player> players;
    private final SimpleObjectProperty<Player> activePlayer;

    public GameModel() {
        board = Board.getInstance();
        dice = new Dice();
        players = new ArrayList<>();
        activePlayer = new SimpleObjectProperty<>();
    }
    
    public GameModel(ArrayList<Player> players, Player activePlayer, Board board) {
        this.board = board;
        dice = new Dice();
        this.players = players;
        this.activePlayer = new SimpleObjectProperty<>(activePlayer);
    }
    public void startNewGame(int numberOfPlayers) {
        players = configurePlayers(numberOfPlayers);
        board = Board.getInstance();
        dice = new Dice();
    }
    
    public ArrayList<Player> configurePlayers(int numberOfPlayers) {
		ArrayList<Player> players = new ArrayList<>();
		if (numberOfPlayers < 2 || numberOfPlayers > 4) {
			throw new IllegalArgumentException("Number of players must be between 2 and 6.");
		}
		for (int i = 1; i <= numberOfPlayers; i++) {
			players.add(new Player("Player " + i));
		}
		activePlayer.set(players.get(0));
		return players;
	}
}
