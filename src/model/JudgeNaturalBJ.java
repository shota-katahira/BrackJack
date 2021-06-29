package model;

public class JudgeNaturalBJ {

	public GameInf judge(GameInf gi) {

		Player player = gi.getPlayer();
		Dealer dealer = gi.getDealer();
		Hand hand = player.getHandList().get(0);

		if (dealer.getHighScore() == 21 || hand.getAscore() == 21) {

			if (dealer.getHighScore() == 21 && hand.getScore() == 21) {
				hand.gameEndProcess(Result.DRAW);
			} else if (dealer.getHighScore() == 21) {
				hand.gameEndProcess(Result.LOSE);
			} else {
				hand.gameEndProcess(Result.NB_WIN);
			}

		}

		return gi;
	}

}
