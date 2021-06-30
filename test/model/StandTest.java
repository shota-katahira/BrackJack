package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StandTest {

	Player player;
	Dealer dealer;
	Deck decks;
	GameInf gi;
	Stand stand;
	Hit hit;
	private LinkedList<Card> deck = new LinkedList<Card>();

	@BeforeEach
	public void setup() {

		for (int i = 0; i < 4; i++) {

			for (int j = 1; j <= 13; j++) {
				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks = new Deck();
		decks.setDeck(deck);
		player = new Player(100, 1, decks);
		dealer = new Dealer(decks);
		gi = new GameInf(player, dealer, decks);
		stand = new Stand();
		hit = new Hit();

	}

	@Test
	public void doStandTest() {

		gi = stand.doStand(gi);

		String expected = "Lose";

		String actual = gi.getPlayer().getHandList().get(0).getResult();

		assertThat(actual, is(expected));

	}

}
