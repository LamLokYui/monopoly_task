package application.modal.decks;

import application.modal.cardeffect.AddAmountEffect;
import application.modal.cardeffect.MoveToPositionEffect;
import application.modal.cardeffect.PayAmountEffect;
import application.modal.cardeffect.GetOutOfJailEffect;
import application.modal.decks.cards.Card;
import application.modal.decks.cards.CommunityChestCard;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CommunityChestDeckState implements DeckState {
    private Random seed = new Random(42);

    @Override
    public void initializeCards(Stack<Card> cards) {
        cards.push(new CommunityChestCard("Advance to Go", new MoveToPositionEffect(0)));
        cards.push(new CommunityChestCard("Bank error in your favor. Collect $200", new AddAmountEffect(200)));
        cards.push(new CommunityChestCard("Doctor’s fee. Pay $50", new PayAmountEffect(50)));
        cards.push(new CommunityChestCard("Get Out of Jail Free", new GetOutOfJailEffect()));
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