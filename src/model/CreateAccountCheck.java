package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbmodel.UserDB;

public class CreateAccountCheck {

	private static final Pattern ID_PATTERN = Pattern.compile("^[0-9A-Za-z!-/:-@^_]{1,20}$");
	private static final Pattern PASS_PATTERN = Pattern.compile("^[0-9A-Za-z!-/:-@^_]{6,20}$");
	private UserDB udb = new UserDB();

	public String check(User user, String password2) {

		String id = user.getId();
		String password1 = user.getPassword();
		String name = user.getName();
		String match = "アカウントを作成しました";
		String mismatch = "アカウントを作成できませんでした";

		//パスワード一致判定
		if (!password1.equals(password2)) {
			return mismatch;
		}

		//空文字判定、ID重複判定
		if (id.equals("") || password1.equals("") || name.equals("")) {
			return mismatch;
		} else if (udb.getUser(id)) {
			return mismatch;
		}

		//文字コード判定
		Matcher m1 = ID_PATTERN.matcher(id);
		Matcher m2 = PASS_PATTERN.matcher(password1);

		if (m1.matches() && m2.matches()) {
			udb.insertUser(user);
			return match;
		}

		return mismatch;
	}

}
