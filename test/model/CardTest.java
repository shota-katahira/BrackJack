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

}
