package application.modal.decks;

import application.modal.decks.cards.Card;
import java.util.Stack;

public class ChanceDeckImpl {
	private static ChanceDeckImpl instance;
    private Stack<Card> cards = new Stack<>();
    private DeckState deck;
    
    public static ChanceDeckImpl getInstance() {
    	if (instance == null) {
    		instance = new ChanceDeckImpl();
    	}
    	return instance;
    }
    
    public ChanceDeckImpl () {
    	this.deck = new ChanceDeckState();
    }
    
    public void setDeck(DeckState deck) {
    	this.deck = deck;
    }
    
    public void initializeCards(Stack<Card> cards) {
        deck.initializeCards(cards);
    }
    
    public Card draw(Stack<Card> cards) {
        return deck.draw(cards);
    }
    
    public void shuffle(Stack<Card> cards) {
        deck.shuffle(cards);
    }
    
    public Card drawCard() {
    	return draw(cards);
    }
    
}
