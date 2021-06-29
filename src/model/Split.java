package model;

public class Split {

	private Stand stand = new Stand();

	public GameInf doSplit(GameInf gi) {

		Player player = gi.getPlayer();

		player.splitHand();
		player.draw(gi.getDeck(), 0);
		player.draw(gi.getDeck(), 1);

		if (player.getHandList().get(0).getHand().get(0).getNumber() == 1) {
			gi = stand.doStand(gi);
		}

		return gi;
	}

}
