package application.modal.decks;

import application.modal.decks.cards.Card;
import java.util.Stack;

public interface DeckBehavior {
    void initializeCards(Stack<Card> cards);
    Card draw(Stack<Card> cards);
    void shuffle(Stack<Card> cards);
}