package model;

public class JudgeNaturalBJ {

	public GameInf judge(GameInf gi) {

		Player player = gi.getPlayer();
		Dealer dealer = gi.getDealer();
		Hand hand = player.getHandList().get(0);

		if (dealer.getHighScore() == 21 || hand.getAscore() == 21) {

			hand.compareToDealer(dealer.getHand(), true);

		}

		return gi;
	}

}
