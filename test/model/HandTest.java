package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandTest {

	private Deck decks;
	private Hand hand;
	private Hand dealerHand;
	private int betChip = 5;
	private LinkedList<Card> deck = new LinkedList<Card>();

	@BeforeEach
	private void setup() {

		decks = new Deck();
		hand = new Hand(betChip);
		dealerHand = new Hand(0);

		for (int i = 0; i < 4; i++) {

			for (int j = 1; j <= 13; j++) {
				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);
	}

	//drawBaseテスト
	@Test
	public void drawBaseTest() {

		hand.drawBase(decks);

		String expectedSuite = "♠";
		int expectedNumber = 1;

		String actualSuite = hand.getHand().get(0).getSuite();
		int actualNumber = hand.getHand().get(0).getNumber();

		assertThat(actualSuite, is(expectedSuite));
		assertThat(actualNumber, is(expectedNumber));

	}

	//judgeSplitテスト
	@Test
	public void judgeSplitTestTrue() {

		hand.drawBase(decks);
		hand.getHand().add(new Card(1, 1));

		boolean expected = true;
		boolean actual = hand.judgeSplit();

		assertThat(actual, is(expected));

	}

	@Test
	public void judgeSplitTestFalse() {

		hand.drawBase(decks);
		hand.drawBase(decks);

		boolean expected = false;

		boolean actual = hand.judgeSplit();

		assertThat(actual, is(expected));

	}

	@Test
	public void getScoreTest() {

		hand.drawBase(decks);
		hand.drawBase(decks);

		int expected = 3;
		int actual = hand.getScore();

		assertThat(actual, is(expected));

	}

	@Test
	public void getAscoreTest() {

		hand.drawBase(decks);
		hand.drawBase(decks);

		int expected = 13;
		int actual = hand.getAscore();

		assertThat(actual, is(expected));

	}

	@Test
	public void getHighScoreTest() {

		hand.drawBase(decks);
		hand.drawBase(decks);

		int expected = 13;
		int actual = hand.getHighScore();

		assertThat(actual, is(expected));

	}

	//getBustテスト
	@Test
	public void getBustTest() throws Exception {

		for (int i = 0; i < 7; i++) {
			hand.drawBase(decks);
		}

		boolean expected = true;

		boolean actual = hand.getBust();

		assertThat(actual, is(expected));

	}

	@Test
	public void compareToDealerTestThroughAndLose() {

		hand.drawBase(decks);
		for (int i = 0; i < 4; i++) {
			dealerHand.drawBase(decks);
		}

		hand.compareToDealer(dealerHand, false);

		String expected = "Lose";
		String actual = hand.getResult();

		assertThat(actual, is(expected));

		hand.drawBase(decks);
		hand.compareToDealer(dealerHand, false);

		actual = hand.getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void compareToDealerTestNB_Win() {

		hand.drawBase(decks);
		dealerHand.drawBase(decks);

		hand.compareToDealer(dealerHand, true);

		String expected = "Win(NB)";
		String actual = hand.getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void compareToDealerTestWin() {

		hand.drawBase(decks);
		dealerHand.drawBase(decks);

		hand.compareToDealer(dealerHand, false);

		String expected = "Win";
		String actual = hand.getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void compareToDealerTestDraw() {

		hand.drawBase(decks);
		dealerHand.getHand().add(new Card(1, 1));

		hand.compareToDealer(dealerHand, false);

		String expected = "Draw";
		String actual = hand.getResult();

		assertThat(actual, is(expected));

	}

	@Test
	public void comparisonHandTestTrue() {

		hand.drawBase(decks);
		hand.getHand().add(new Card(1, 1));

		boolean expected = true;
		boolean actual = hand.comparisonHand();

		assertThat(actual, is(expected));

	}

	@Test
	public void comparisonHandTestFalse() {

		hand.drawBase(decks);
		hand.drawBase(decks);

		boolean expected = false;
		boolean actual = hand.comparisonHand();

		assertThat(actual, is(expected));

	}

}
