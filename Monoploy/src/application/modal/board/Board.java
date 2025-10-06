package application.modal.board;

import application.modal.board.squares.*;
import application.modal.decks.ChanceDeck;
import application.modal.decks.CommunityChestDeck;
import application.modal.decks.cards.*;

public class Board {
	private Board instance;
    private final Squares[] boardSquares;
    private final ChanceDeck chanceDeck;
    private final CommunityChestDeck communityChestDeck;
    
    public Board getInstance() {
    	if (instance == null)
    		return new Board();
    	return instance;
    }
    
    public Board() {
        boardSquares = createBoard();
        chanceDeck = new ChanceDeck();
        communityChestDeck = new CommunityChestDeck();

        chanceDeck.shuffle();
        communityChestDeck.shuffle();
    }

    private Squares[] createBoard() {
        return new Squares[]{
            new GoSquare(0, 200),
            new PropertySquares("Mediterranean Avenue", 1, PropertyGroup.BROWN, 2, 60),
            new CardSquare("Community Chest", 2),
            new PropertySquares("Baltic Avenue", 3, PropertyGroup.BROWN, 4, 60),
            new TaxSquare("Income Tax", 4, 200),
            new RailroadSquare("Reading Railroad", 5, 25),
            new PropertySquares("Oriental Avenue", 6, PropertyGroup.LIGHTBLUE, 6, 100),
            new CardSquare("Chance", 7),
            new PropertySquares("Vermont Avenue", 8, PropertyGroup.LIGHTBLUE, 6, 100),
            new PropertySquares("Connecticut Avenue", 9, PropertyGroup.LIGHTBLUE, 8, 120),
            new JailSquare("Jail", 10),
            new PropertySquares("St. Charles Place", 11, PropertyGroup.PINK, 10, 140),
            new UtilitySquare("Electric Company", 12),
            new PropertySquares("States Avenue", 13, PropertyGroup.PINK, 10, 140),
            new PropertySquares("Virginia Avenue", 14, PropertyGroup.PINK, 12, 160),
            new RailroadSquare("Pennsylvania Railroad", 15, 25),
            new PropertySquares("St. James Place", 16, PropertyGroup.ORANGE, 14, 180),
            new CardSquare("Community Chest", 17),
            new PropertySquares("Tennessee Avenue", 18, PropertyGroup.ORANGE, 14, 180),
            new PropertySquares("New York Avenue", 19, PropertyGroup.ORANGE, 16, 200),
            new FreeParkingSquare(20),
            new PropertySquares("Kentucky Avenue", 21, PropertyGroup.RED, 18, 220),
            new CardSquare("Chance", 22),
            new PropertySquares("Indiana Avenue", 23, PropertyGroup.RED, 18, 220),
            new PropertySquares("Illinois Avenue", 24, PropertyGroup.RED, 20, 240),
            new RailroadSquare("B. & O. Railroad", 25, 25),
            new PropertySquares("Atlantic Avenue", 26, PropertyGroup.YELLOW, 22, 260),
            new PropertySquares("Ventnor Avenue", 27, PropertyGroup.YELLOW, 22, 260),
            new UtilitySquare("Water Works", 28),
            new PropertySquares("Marvin Gardens", 29, PropertyGroup.YELLOW, 24, 280),
            new GoToJailSquare(30, 10),
            new PropertySquares("Pacific Avenue", 31, PropertyGroup.GREEN, 26, 300),
            new PropertySquares("North Carolina Avenue", 32, PropertyGroup.GREEN, 26, 300),
            new CardSquare("Community Chest", 33),
            new PropertySquares("Pennsylvania Avenue", 34, PropertyGroup.GREEN, 28, 320),
            new RailroadSquare("Short Line", 35, 25),
            new CardSquare("Chance", 36),
            new PropertySquares("Park Place", 37, PropertyGroup.BLUE, 35, 350),
            new TaxSquare("Luxury Tax", 38, 100),
            new PropertySquares("Boardwalk", 39, PropertyGroup.BLUE, 50, 400)
        };
    }

    public Squares[] getBoardSquares() {
        return boardSquares;
    }

    public ChanceDeck getChanceDeck() {
        return chanceDeck;
    }

    public CommunityChestDeck getCommunityChestDeck() {
        return communityChestDeck;
    }


    public Squares getNearestSquareOfType(int startPosition, String className) {
        int max = boardSquares.length;
        for (int i = startPosition; i < (max + startPosition - 1); i++) {
            try {
                if (Class.forName(className).isInstance(boardSquares[i % max])) return boardSquares[i % max];
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}