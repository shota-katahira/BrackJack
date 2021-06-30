package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

	private Deck decks;
	private Player player;
	private int chip = 100;
	private int betChip = 2;
	private int index = 0;
	private LinkedList<Card> deck = new LinkedList<Card>();

	@BeforeEach
	public void setup() {

		decks = new Deck();

		for (int i = 0; i < 4; i++) {

			for (int j = 1; j < 14; j++) {
				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);
		player = new Player(chip, betChip, decks);
		player.draw(decks, index);

	}

	//drawテスト
	@Test
	public void drawTest() {

		String expectedSuite = "♠";
		int expectedNumber = 1;

		String actualSuite = player.getHandList().get(index).getHand().get(0).getSuite();
		int actualNumber = player.getHandList().get(index).getHand().get(0).getNumber();

		assertThat(actualSuite, is(expectedSuite));
		assertThat(actualNumber, is(expectedNumber));

	}

	//permitSplitテスト
	@Test
	public void permitSplitTest() {

		player.draw(decks, index);

		boolean expected = false;

		boolean actual = player.permitSplit();

		assertThat(actual, is(expected));

	}

	@Test
	public void permitSplitTestFalse() {

		player.draw(decks, index);
		player.getHandList().add(new Hand(chip));

		boolean expected = false;

		boolean actual = player.permitSplit();

		assertThat(actual, is(expected));

	}

	@Test
	public void splitHandTest() {

		player.splitHand();

		int expectedChip = betChip;
		String expectedSuite = "♠";
		int expectedNumber = 2;

		int actualChip = player.getHandList().get(1).getChip();
		String actualSuite = player.getHandList().get(0).getHand().get(0).getSuite();
		int actualNumber = player.getHandList().get(0).getHand().get(0).getNumber();

		assertThat(actualChip, is(expectedChip));
		assertThat(actualSuite, is(expectedSuite));
		assertThat(actualNumber, is(expectedNumber));

	}

	@Test
	public void calcChipTest() {

		Dealer dealer = new Dealer(decks);
		for (int i = 0; i < 3; i++) {
			dealer.draw(decks);
		}

		player.getHandList().get(0).compareToDealer(dealer.getHand(), false);

		int expected = 102;
		int actual = player.calcChip();

		assertThat(actual, is(expected));

	}

	@Test
	public void getGetChipTest() {

		Dealer dealer = new Dealer(decks);
		for (int i = 0; i < 3; i++) {
			dealer.draw(decks);
		}

		player.getHandList().get(0).compareToDealer(dealer.getHand(), false);

		int expected = 2;
		int actual = player.getGetChip();

		assertThat(actual, is(expected));

	}

}
