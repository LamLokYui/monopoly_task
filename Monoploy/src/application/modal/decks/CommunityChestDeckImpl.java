package application.modal.decks;

import application.modal.decks.cards.Card;
import java.util.Stack;

public class CommunityChestDeckImpl {
	private static CommunityChestDeckImpl instance;
    private Stack<Card> cards = new Stack<>();
    private DeckState deck;
    
    public static CommunityChestDeckImpl getInstance() {
    	if (instance == null) {
    		instance = new CommunityChestDeckImpl();
    	}
    	return instance;
    }
    
    public CommunityChestDeckImpl () {
    	this.deck = new CommunityChestDeckState();
    	deck.initializeCards(cards);
    }
    
    public void setDeck(DeckState newdeck) {
    	this.deck = newdeck;
    	cards.clear();
    	deck.initializeCards(cards);
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
