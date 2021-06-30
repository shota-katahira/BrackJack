package model;

import dbmodel.UserDB;

public class JudgeNameChange {

	private UserDB udb = new UserDB();

	public String judge(String newName, User user) {

		if (!newName.equals(user.getName()) && !newName.equals("")) {
			user.setName(newName);
			udb.updateUserName(user);

			return "ニックネームを変更しました";
		}

		return "ニックネームを変更出来ませんでした";
	}

}
