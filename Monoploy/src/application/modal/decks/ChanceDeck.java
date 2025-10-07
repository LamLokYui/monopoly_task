package application.modal.decks;


import application.modal.cardeffect.AddAmountEffect;
import application.modal.cardeffect.MoveToPositionEffect;
import application.modal.cardeffect.MoveToNearestRailroadEffect;
import application.modal.cardeffect.MoveToNearestUtilityEffect;
import application.modal.cardeffect.GetOutOfJailEffect;
import application.modal.cardeffect.GoToJailEffect;
import application.modal.decks.cards.Card;
import application.modal.decks.cards.ChanceCard;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

//a list of Chance cards initialization 
public class ChanceDeck implements DeckBehavior {
    private Random seed = new Random(42);

    @Override
    public void initializeCards(Stack<Card> cards) {
        cards.push(new ChanceCard("Advance to Go", new MoveToPositionEffect(0)));
        cards.push(new ChanceCard("Speeding fine $15", new MoveToPositionEffect(15)));
        cards.push(new ChanceCard("Bank pays you dividend of $50.", new AddAmountEffect(50)));
        cards.push(new ChanceCard("Advance token to nearest Utility.", new MoveToNearestUtilityEffect()));
        cards.push(new ChanceCard("Advance to the nearest Railroad.", new MoveToNearestRailroadEffect()));
        cards.push(new ChanceCard("Go to Jail", new GoToJailEffect()));
        cards.push(new ChanceCard("Get Out of Jail Free", new GetOutOfJailEffect()));
    }

    @Override
    public Card draw(Stack<Card> cards) {
        if (cards.isEmpty()) {
            initializeCards(cards);
            shuffle(cards);
        }
        return cards.pop();
    }

    @Override
    public void shuffle(Stack<Card> cards) {
        Collections.shuffle(cards, seed);
    }
}
