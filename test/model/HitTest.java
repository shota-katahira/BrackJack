package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HitTest {

	Player player;
	Dealer dealer;
	Deck deck;
	GameInf gi;
	Hit hit;

	@BeforeEach
	public void setup() {

		deck = new Deck();
		player = new Player(100, 1, deck);
		dealer = new Dealer(deck);
		gi = new GameInf(player, dealer, deck);
		hit = new Hit();

	}

	@Test
	public void doHitTest() {

		gi = hit.doHit(gi, 0);

		String expected = null;

		String actual = gi.getPlayer().getHandList().get(0).getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void doHitTestBust() {

		for (int i = 0; i < 7; i++) {
			player.draw(deck, 0);
		}

		gi = hit.doHit(gi, 0);

		String expected = "Lose";

		String actual = gi.getPlayer().getHandList().get(0).getResult();

		assertThat(actual, is(expected));

	}

}
