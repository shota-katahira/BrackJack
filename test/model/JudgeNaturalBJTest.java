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

		player = new Player(100);
		dealer = new Dealer();
		decks = new Deck();

		Card card = new Card(0, 8);
		deck.add(card);
		card = new Card(0, 1);
		deck.add(card);
		card = new Card(0, 13);
		deck.add(card);
		card = new Card(1, 8);
		deck.add(card);
		card = new Card(1, 1);
		deck.add(card);
		card = new Card(1, 13);
		deck.add(card);

		decks.setDeck(deck);

		gi = new GameInf(player, dealer, decks);
		jnbj = new JudgeNaturalBJ();
	}

	@Test
	public void naturalBJdrawTest() {

		decks.getDeck().poll();
		player.firstDraw(decks, 1);
		decks.getDeck().poll();
		dealer.firstDraw(decks);

		gi = jnbj.judge(gi);

		String expected = "Draw";
		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void naturalBJloseTest() {

		player.firstDraw(decks, 1);
		decks.getDeck().poll();
		decks.getDeck().poll();
		dealer.firstDraw(decks);

		gi = jnbj.judge(gi);

		String expected = "Lose";
		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void naturalBJwinTest() {

		decks.getDeck().poll();
		player.firstDraw(decks, 1);
		dealer.firstDraw(decks);

		gi = jnbj.judge(gi);

		String expected = "Win";
		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void nonNaturalBJTest() {

		player.firstDraw(decks, 1);
		decks.getDeck().poll();
		dealer.firstDraw(decks);

		gi = jnbj.judge(gi);

		String expected = null;
		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

}
