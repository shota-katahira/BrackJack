package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dbmodel.GameDB;
import dbmodel.UserDB;

public class SetGameDataTest {

	private User user;
	private Player player;
	private Game game;
	private Deck decks;
	private LinkedList<Card> deck = new LinkedList<Card>();
	private GameInf gi;
	private Hit hit;

	@InjectMocks
	private SetGameData sgd = new SetGameData();

	@Mock
	private GameDB gdb;

	@Mock
	private UserDB udb;

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setId("1");
		user.setPassword("p");
		user.setName("n");
		user.setPlay(0);
		user.setChip(10);
		game = new Game("1", 1, null);
		decks = new Deck();
		player = new Player(100, 1, decks);
		hit = new Hit();

		for (int j = 1; j <= 13; j++) {

			for (int i = 0; i < 4; i++) {

				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);
		gi = new GameInf(player, null, decks);

	}

	@Test
	public void setDateTestNull() {

		user = sgd.setData(user, player);

		int expectedPlay = 0;
		int expectedChip = 10;

		int actualPlay = user.getPlay();
		int actualChip = user.getChip();

		assertThat(actualPlay, is(expectedPlay));
		assertThat(actualChip, is(expectedChip));

	}

	@Test
	public void setDateTest() {

		for (int i = 0; i < 11; i++) {
			gi = hit.doHit(gi, 0);
		}

		doNothing().when(gdb).insertGame(game);
		doNothing().when(udb).updateUserRecord(user);

		user = sgd.setData(user, player);

		int expectedPlay = 1;
		int expectedChip = -1;

		int actualPlay = user.getPlay();
		int actualChip = player.getGetChip();

		assertThat(actualPlay, is(expectedPlay));
		assertThat(actualChip, is(expectedChip));

	}

}
