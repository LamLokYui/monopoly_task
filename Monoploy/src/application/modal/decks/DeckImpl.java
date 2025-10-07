package application.modal.decks;

import application.modal.decks.cards.Card;
import application.modal.decks.ChanceDeck;
import application.modal.decks.CommunityChestDeck;
import java.util.Stack;

public class DeckImpl implements Deck {
    private static DeckImpl chanceInstance;
    private static DeckImpl communityChestInstance;
    private Stack<Card> cards = new Stack<>();
    private DeckBehavior behavior;

    private DeckImpl(DeckBehavior behavior) {
        this.behavior = behavior;
        behavior.initializeCards(cards);
        shuffle();
    }

    public static DeckImpl getChanceInstance() {
        if (chanceInstance == null) {
            chanceInstance = new DeckImpl(new ChanceDeck());
        }
        return chanceInstance;
    }

    public static DeckImpl getCommunityChestInstance() {
        if (communityChestInstance == null) {
            communityChestInstance = new DeckImpl(new CommunityChestDeck());
        }
        return communityChestInstance;
    }

    @Override
    public Card draw() {
        return behavior.draw(cards);
    }

    @Override
    public void shuffle() {
        behavior.shuffle(cards);
    }
}