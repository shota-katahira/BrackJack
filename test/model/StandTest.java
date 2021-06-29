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

		player = new Player(100);
		player.getHandList().get(0);
		player.getHandList().get(0).setChip(1);
		dealer = new Dealer();
		decks = new Deck();
		decks.setDeck(deck);
		gi = new GameInf(player, dealer, decks);
		stand = new Stand();
		hit = new Hit();

	}

	@Test
	public void doStandTestBust() {

		for (int i = 0; i < 6; i++) {
			gi = hit.doHit(gi, 0);
		}
		gi = stand.doStand(gi);

		String expected = "Win";

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void doStandTestWin() {

		for (int i = 0; i < 6; i++) {
			gi = hit.doHit(gi, 0);
		}
		gi.getDeck().getDeck().poll();
		gi = stand.doStand(gi);

		String expected = "Win";

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void doStandTestDraw() {

		for (int i = 0; i < 6; i++) {
			gi = hit.doHit(gi, 0);
		}
		for (int i = 0; i < 6; i++) {
			gi.getDeck().getDeck().poll();
		}
		gi = stand.doStand(gi);

		String expected = "Draw";

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void doStandTestLose() {

		gi = stand.doStand(gi);

		String expected = "Lose";

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

}
