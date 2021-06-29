package model;

public class JudgeGameEnd {

	public static int judge(Player player) {

		int count = 0; //勝敗判定済Hand数

		for (int i = 0; i < player.getHandList().size(); i++) {
			if (!player.getHandList().get(i).resultIsNull()) {
				count++;
			}
		}

		return count;
	}

}
