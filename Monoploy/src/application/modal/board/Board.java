package application.modal.board;

import application.modal.board.squares.*;
import application.modal.decks.DeckState;
import application.modal.decks.ChanceDeckImpl;
import application.modal.decks.CommunityChestDeckImpl;
import application.modal.decks.cards.Card;
import application.modal.player.Player;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private static Board instance;
	private List<Squares> boardSquares;
    private final ChanceDeckImpl chanceDeck;
    private final CommunityChestDeckImpl communityChestDeck;

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance; 
    }

    private Board() {
        boardSquares = createBoard();
        chanceDeck = ChanceDeckImpl.getInstance();
        communityChestDeck = CommunityChestDeckImpl.getInstance();
    }

    protected Board(List<Squares> squares) {
        this.boardSquares = squares;
		this.chanceDeck = null;
		this.communityChestDeck = null;
    }
    
    private List<Squares> createBoard() {
		List<Squares> squares = new ArrayList<>();
            squares.add(new GoSquare(0, 200));
            squares.add(new PropertySquares("Mediterranean Avenue", 1, PropertyGroup.BROWN, 2, 60));
            squares.add(new CommunityChestSquare("Community Chest", 2));
            squares.add(new PropertySquares("Baltic Avenue", 3, PropertyGroup.BROWN, 4, 60));
            squares.add(new TaxSquare("Income Tax", 4, 200));
            squares.add(new RailroadSquare("Reading Railroad", 5, 25));
            squares.add(new PropertySquares("Oriental Avenue", 6, PropertyGroup.LIGHTBLUE, 6, 100));
            squares.add(new ChanceSquare("Chance", 7));
            squares.add(new PropertySquares("Vermont Avenue", 8, PropertyGroup.LIGHTBLUE, 6, 100));
            squares.add(new PropertySquares("Connecticut Avenue", 9, PropertyGroup.LIGHTBLUE, 8, 120));
            squares.add(new JailSquare("Jail", 10));
            squares.add(new PropertySquares("St. Charles Place", 11, PropertyGroup.PINK, 10, 140));
            squares.add(new UtilitySquare("Electric Company", 12));
            squares.add(new PropertySquares("States Avenue", 13, PropertyGroup.PINK, 10, 140));
            squares.add(new PropertySquares("Virginia Avenue", 14, PropertyGroup.PINK, 12, 160));
            squares.add(new RailroadSquare("Pennsylvania Railroad", 15, 25));
            squares.add(new PropertySquares("St. James Place", 16, PropertyGroup.ORANGE, 14, 180));
            squares.add(new CommunityChestSquare("Community Chest", 17));
            squares.add(new PropertySquares("Tennessee Avenue", 18, PropertyGroup.ORANGE, 14, 180));
            squares.add(new PropertySquares("New York Avenue", 19, PropertyGroup.ORANGE, 16, 200));
            squares.add(new FreeParkingSquare(20));
            squares.add(new PropertySquares("Kentucky Avenue", 21, PropertyGroup.RED, 18, 220));
            squares.add(new CommunityChestSquare("Chance", 22));
            squares.add(new PropertySquares("Indiana Avenue", 23, PropertyGroup.RED, 18, 220));
            squares.add(new PropertySquares("Illinois Avenue", 24, PropertyGroup.RED, 20, 240));
            squares.add(new RailroadSquare("B. & O. Railroad", 25, 25));
            squares.add(new PropertySquares("Atlantic Avenue", 26, PropertyGroup.YELLOW, 22, 260));
            squares.add(new PropertySquares("Ventnor Avenue", 27, PropertyGroup.YELLOW, 22, 260));
            squares.add(new UtilitySquare("Water Works", 28));
            squares.add(new PropertySquares("Marvin Gardens", 29, PropertyGroup.YELLOW, 24, 280));
            squares.add(new GoToJailSquare(30, 10));
            squares.add(new PropertySquares("Pacific Avenue", 31, PropertyGroup.GREEN, 26, 300));
            squares.add(new PropertySquares("North Carolina Avenue", 32, PropertyGroup.GREEN, 26, 300));
            squares.add(new CommunityChestSquare("Community Chest", 33));
            squares.add(new PropertySquares("Pennsylvania Avenue", 34, PropertyGroup.GREEN, 28, 320));
            squares.add(new RailroadSquare("Short Line", 35, 25));
            squares.add(new CommunityChestSquare("Chance", 36));
            squares.add(new PropertySquares("Park Place", 37, PropertyGroup.BLUE, 35, 350));
            squares.add(new TaxSquare("Luxury Tax", 38, 100));
            squares.add(new PropertySquares("Boardwalk", 39, PropertyGroup.BLUE, 50, 400));
            
            return squares;
    }

    public List<Squares> getBoardSquares() {
        return boardSquares;
    }

    public Squares getSquare(int position) {
        return boardSquares.get(position%40);
    }

    public ChanceDeckImpl getChanceDeck() {
        return chanceDeck;
    }

    public CommunityChestDeckImpl getCommunityChestDeck() {
        return communityChestDeck;
    }

    public void landOnSquare(Player player, int position) {
        Squares square = getSquare(position);
        if (square instanceof GoSquare) {
            ((GoSquare) square).awardCapital(player);
        } else if (square instanceof ChanceSquare) {
        	Card card = chanceDeck.drawCard();
            card.apply(player);
        } else if (square instanceof CommunityChestSquare) {
            Card card = communityChestDeck.drawCard();
            card.apply(player);
        }else if (square instanceof OwnableSquare ownableSquare && ownableSquare.getOwned() && ownableSquare.getOwner() != player) {
            player.addMoney(-ownableSquare.getRent());
            ownableSquare.getOwner().addMoney(ownableSquare.getRent());
        }
    }
}