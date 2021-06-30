package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JudgeNaturalBJTest {

	private Player player;
	private Dealer dealer;
	private Deck decks;
	private LinkedList<Card> deck = new LinkedList<Card>();
	private GameInf gi;
	private JudgeNaturalBJ jnbj;

	@BeforeEach
	public void setup() {

		decks = new Deck();

		Card card = new Card(0, 1);
		deck.add(card);
		card = new Card(0, 13);
		deck.add(card);
		card = new Card(1, 8);
		deck.add(card);
		card = new Card(1, 1);
		deck.add(card);
		card = new Card(1, 7);
		deck.add(card);

		decks.setDeck(deck);

		jnbj = new JudgeNaturalBJ();
	}

	@Test
	public void naturalBJTest() {

		player = new Player(100, 0, decks);
		dealer = new Dealer(decks);
		gi = new GameInf(player, dealer, decks);

		gi = jnbj.judge(gi);

		String expected = "Win(NB)";
		String actual = gi.getPlayer().getHandList().get(0).getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void nonNaturalBJTest() {

		deck.poll();
		player = new Player(100, 0, decks);
		dealer = new Dealer(decks);
		gi = new GameInf(player, dealer, decks);

		gi = jnbj.judge(gi);

		String expected = null;
		String actual = gi.getPlayer().getHandList().get(0).getResult();

		assertThat(actual, is(expected));

	}

}
