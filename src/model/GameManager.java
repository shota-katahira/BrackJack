package model;

public class GameManager {

	private HitOrStand hos = new HitOrStand();

	public GameInf GameManagement(GameInf gi, int command, int chip) {

		if (command == 0) {
			return hos.doHit(gi, chip);
		} else {
			return hos.doStand(gi, chip);
		}

	}

	public GameInf naturalBJ(GameInf gi, int chip) {

		Player player = gi.getPlayer();
		Dealer dealer = gi.getDealer();
		Deck deckInf = gi.getDeck();

		if (dealer.getAscore() == 21 || player.getAscore() == 21) {

			player.changeAscore();
			dealer.changeAscore();

			if (dealer.getScore() == 21 && player.getScore() == 21) {
				gi = new GameInf(player, dealer, deckInf, 0);
			} else if (dealer.getScore() == 21) {
				gi = new GameInf(player, dealer, deckInf, -chip);
			} else {
				gi = new GameInf(player, dealer, deckInf, (int) (chip * 1.5));
			}

		}

		return gi;
	}

}
