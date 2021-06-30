package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

public class CardTest {

	@Test
	public void getSuiteTest() {

		Card spadeCard = new Card(0, 1);
		Card clubCard = new Card(1, 1);
		Card diamondCard = new Card(2, 1);
		Card heartCard = new Card(3, 1);

		String expectedSpade = "♠";
		String expectedClub = "♣";
		String expectedDiamond = "♢";
		String expectedHeart = "♡";

		String actualSpade = spadeCard.getSuite();
		String actualClab = clubCard.getSuite();
		String actualDiamond = diamondCard.getSuite();
		String actualHeart = heartCard.getSuite();

		assertThat(actualSpade, is(expectedSpade));
		assertThat(actualClab, is(expectedClub));
		assertThat(actualDiamond, is(expectedDiamond));
		assertThat(actualHeart, is(expectedHeart));

	}

	@Test
	public void getFaceCardTest() {

		Card cardA = new Card(0, 1);
		Card cardJ = new Card(0, 11);
		Card cardQ = new Card(0, 12);
		Card cardK = new Card(0, 13);
		Card cardNum = new Card(0, 2);

		String expectedA = "A";
		String expectedJ = "J";
		String expectedQ = "Q";
		String expectedK = "K";
		String expectedNum = "2";

		String actualA = cardA.getFaceCard();
		String actualJ = cardJ.getFaceCard();
		String actualQ = cardQ.getFaceCard();
		String actualK = cardK.getFaceCard();
		String actualNum = cardNum.getFaceCard();

		assertThat(actualA, is(expectedA));
		assertThat(actualJ, is(expectedJ));
		assertThat(actualQ, is(expectedQ));
		assertThat(actualK, is(expectedK));
		assertThat(actualNum, is(expectedNum));

	}

	@Test
	public void getCardScoreTest() {

		Card cardA = new Card(0, 1);
		Card cardJ = new Card(0, 11);

		int expectedA = 1;
		int expectedJ = 10;

		int actualA = cardA.getCardScore();
		int actualJ = cardJ.getCardScore();

		assertThat(actualA, is(expectedA));
		assertThat(actualJ, is(expectedJ));

	}

	@Test
	public void getAcardScoreTest() {

		Card cardA = new Card(0, 1);
		Card card2 = new Card(0, 2);

		int expectedA = 11;
		int expected2 = 2;

		int actualA = cardA.getAcardScore();
		int actual2 = card2.getAcardScore();

		assertThat(actualA, is(expectedA));
		assertThat(actual2, is(expected2));

	}

}
