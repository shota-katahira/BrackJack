package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SplitTest {

	private Player player;
	private Deck decks;
	private LinkedList<Card> deck = new LinkedList<Card>();
	private GameInf gi;

	@InjectMocks
	private Split split = new Split();

	@Mock
	private Stand stand;

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);

		decks = new Deck();

		for (int j = 1; j <= 13; j++) {

			for (int i = 0; i < 4; i++) {

				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);
		player = new Player(100, 1, decks);
		gi = new GameInf(player, null, decks);

	}

	@Test
	public void doSplitTest() {

		doReturn(gi).when(stand).doStand(anyObject());

		gi = split.doSplit(gi);

		int expected = 2;

		int actual = gi.getPlayer().getHandList().size();

		assertThat(actual, is(expected));

	}

}
