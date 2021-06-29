package model;

import java.sql.Timestamp;

import dbmodel.GameDB;
import dbmodel.UserDB;

public class SetGameData {

	private GameDB gdb = new GameDB();
	private UserDB udb = new UserDB();

	public User setData(User user, Player player) {

		if (JudgeGameEnd.judge(player) == player.getHandList().size()) {

			Timestamp playTime = new Timestamp(System.currentTimeMillis());

			user.setChip(player.calcChip());
			user.setPlay(user.getPlay() + 1);

			Game game = new Game(user.getId(), player.getGetChip(), playTime);

			gdb.insertGame(game);
			udb.updateUserRecord(user);

		}

		return user;

	}

}
