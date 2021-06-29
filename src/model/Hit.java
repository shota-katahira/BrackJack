package model;

import java.util.ArrayList;

public class Hit {

	public GameInf doHit(GameInf gi, int index) {

		Player player = gi.getPlayer();
		Deck deck = gi.getDeck();
		ArrayList<Hand> handList = player.getHandList();

		player.draw(deck, index);

		if (handList.get(index).getBust()) {
			handList.get(index).gameEndProcess(Result.LOSE);
		}

		return gi;
	}

}
