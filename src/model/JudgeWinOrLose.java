package model;

import java.util.ArrayList;

public class JudgeWinOrLose {

	public void judgeScore(Player player, Dealer dealer) {

		ArrayList<Hand> handList = player.getHandList();

		for (int i = 0; i < handList.size(); i++) {

			Hand playerHand = handList.get(i);

			if (playerHand.resultIsNull()) {

				playerHand.compareToDealer(dealer.getHand());
			}

		}

	}

}
