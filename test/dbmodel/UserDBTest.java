package dbmodel;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.User;

public class UserDBTest {

	private UserDB udb = new UserDB();
	private User user = new User();
	private String id = "2";
	private String password = "pass";
	private String name = "name";

	@BeforeEach
	public void setup() {

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		udb.insertUser(user);

	}

	@AfterEach
	public void delete() {
		udb.deleteUser(id);
	}

	//getUser(id,password)テスト
	@Test
	public void getUserTest1() {

		String expectedId = user.getId();
		String expectedPassword = user.getPassword();
		String expectedName = user.getName();

		User actualuser = udb.getUser(id, password);
		String actualId = actualuser.getId();
		String actualPassword = actualuser.getPassword();
		String actualName = actualuser.getName();

		assertThat(actualId, is(expectedId));
		assertThat(actualPassword, is(expectedPassword));
		assertThat(actualName, is(expectedName));

	}

	//getUser(id)テスト
	@Test
	public void getUserTest2() {

		String expectedId = user.getId();
		String expectedPassword = user.getPassword();
		String expectedName = user.getName();

		User actualuser = udb.getUser(id);
		String actualId = actualuser.getId();
		String actualPassword = actualuser.getPassword();
		String actualName = actualuser.getName();

		assertThat(actualId, is(expectedId));
		assertThat(actualPassword, is(expectedPassword));
		assertThat(actualName, is(expectedName));

	}

	//getRankingテスト
	@Test
	public void getRankingTest() {

		double expected = 0;

		ArrayList<User> ranking = udb.getRanking();

		double actual = ranking.get(3).getWinRate();

		assertThat(actual, is(expected));

	}

	//getMyRankingテスト
	@Test
	public void getMyRankingTest() {

		int expected = 4;

		int actual = udb.getMyRanking(id);

		assertThat(actual, is(expected));

	}

	//updateUserテスト
	@Test
	public void updateUserTest() {

		User setUser = new User();
		setUser.setId(id);
		setUser.setPassword(password);
		setUser.setName("name2");

		udb.updateUserName(setUser);
		User getUser = udb.getUser(id);

		String expected = "name2";
		String actual = getUser.getName();

		assertThat(actual, is(expected));

	}

}
