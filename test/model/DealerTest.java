package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {

	private Deck decks;
	private Dealer dealer;
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
		dealer = new Dealer(decks);
	}

	@Test
	public void drawTest() {

		dealer.draw(decks);

		String expectedSuite = "♠";
		int expectedNumber = 3;

		String actualSuite = dealer.getHand().getHand().get(2).getSuite();
		int actualNumber = dealer.getHand().getHand().get(2).getNumber();

		assertThat(actualSuite, is(expectedSuite));
		assertThat(actualNumber, is(expectedNumber));

	}

	//
	//	@Test
	//	public void bustJudgeTest() {
	//
	//		decks.setDeck(decks);
	//		decks = dealer.draw(decks);
	//
	//		dealer.bustJudge();
	//
	//		boolean expected = false;
	//
	//		boolean actual = dealer.getBust();
	//
	//		assertThat(actual, is(expected));
	//
	//	}

}
