package model;

import java.util.ArrayList;

public class Stand {

	public GameInf doStand(GameInf gi) {

		Player player = gi.getPlayer();
		Dealer dealer = gi.getDealer();
		Deck deck = gi.getDeck();

		dealer.draw(deck);

		ArrayList<Hand> handList = player.getHandList();

		for (int i = 0; i < handList.size(); i++) {

			Hand playerHand = handList.get(i);
			Hand dealerHand = dealer.getHand();
			playerHand.compareToDealer(dealerHand, false);
		}

		return gi;
	}

}
