package deck;

import java.util.Collections;
import java.util.Stack;

import card.Card;
import card.ChanceCard;

import java.util.Random;

import cardeffect.MoveToPositionEffect;
import cardeffect.AddAmountEffect;

public class ChanceDeck implements Deck{
	private static ChanceDeck instance;
	private Stack<Card> cards = new Stack<>();
	private Random seed = new Random(42);
	
	private void initializeCards() {
		cards.push(new ChanceCard("Advance to Go", new MoveToPositionEffect(0)));
		cards.push(new ChanceCard("Speeding fine $15", new MoveToPositionEffect(15)));
		cards.push(new ChanceCard("Bank pays you dividend of $50.", new AddAmountEffect(50)));
	}
	
	
	private ChanceDeck() {
		initializeCards();
		shuffle();
	}
	
	public static ChanceDeck getInstance() {
		if (instance == null) {
			instance = new ChanceDeck();
		}
		return instance;
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
