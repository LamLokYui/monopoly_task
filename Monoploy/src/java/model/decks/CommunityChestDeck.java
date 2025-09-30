package java.model.decks;

import java.model.cardeffect.AddAmountEffect;
import java.model.cardeffect.MoveToPositionEffect;
import java.model.cardeffect.PayAmountEffect;
import java.model.decks.cards.Card;
import java.model.decks.cards.CommunityChestCard;
import java.util.Collections;
import java.util.Stack;
import java.util.Random;

//a list of Chance cards initialization 
public class CommunityChestDeck implements Deck{
	private static CommunityChestDeck instance;
	private Random seed = new Random(42);
	private Stack<Card> cards = new Stack<>();
	
	
	public static CommunityChestDeck getInstance() {
		if (instance == null) {
			instance = new CommunityChestDeck();
		}
		return instance;
	}
	
	private void initializeCards() {
		cards.push(new CommunityChestCard("Advance to Go", new MoveToPositionEffect(0)));
		cards.push(new CommunityChestCard("Bank error in your favor. Collect $200", new AddAmountEffect(200)));
		cards.push(new CommunityChestCard("Doctor’s fee. Pay $50", new PayAmountEffect(50)));
	}
	
	public CommunityChestDeck() {
		initializeCards();
		shuffle();
	}
	
	@Override
	public Card draw() {
		if (cards.isEmpty()) {
			initializeCards();
			shuffle();
		}
		return cards.pop();
	}
	
	@Override
	public void shuffle() {
		Collections.shuffle(cards, seed);
	}
}
