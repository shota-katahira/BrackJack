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

		player = new Player(100);
		dealer = new Dealer();
		deck = new Deck();
		gi = new GameInf(player, dealer, deck);
		hit = new Hit();

	}

	@Test
	public void doHitTest() {

		gi = hit.doHit(gi, 0);

		String expected = null;

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

	@Test
	public void doHitTestBust() {

		for (int i = 0; i < 7; i++) {
			player.draw(deck, 0);
		}

		player.getHandList().get(0).setChip(1);

		gi = hit.doHit(gi, 0);

		String expected = "Lose";

		String actual = gi.getPlayer().getHandList().get(0).resultIsNull();

		assertThat(actual, is(expected));

	}

}
