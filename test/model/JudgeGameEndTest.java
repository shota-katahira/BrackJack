package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JudgeGameEndTest {

	private Player player;
	private Deck decks;
	private LinkedList<Card> deck = new LinkedList<Card>();
	private Split split;
	private GameInf gi;
	private Hit hit;

	@BeforeEach
	public void setup() {

		decks = new Deck();
		player = new Player(100, 1, decks);
		hit = new Hit();

		for (int j = 2; j <= 13; j++) {

			for (int i = 0; i < 4; i++) {

				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);
		gi = new GameInf(player, null, decks);
		split = new Split();
		gi = split.doSplit(gi);

		for (int i = 0; i < 8; i++) {
			gi = hit.doHit(gi, 0);
		}

	}

	@Test
	public void judgeTest() {

		int expected = 1;

		int actual = JudgeGameEnd.judge(gi.getPlayer());

		assertThat(actual, is(expected));

	}

}
