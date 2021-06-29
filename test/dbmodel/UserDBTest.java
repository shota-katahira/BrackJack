package dbmodel;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.User;

public class UserDBTest {

	private UserDB udb = new UserDB();
	private User user = new User();
	private String id = "test";
	private String password = "test";
	private String name = "test";

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
	public void getUserTest1() throws SQLException {

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

		boolean expected = true;

		boolean actual = udb.getUser(id);

		assertThat(actual, is(expected));

	}

	//getRankingテスト
	@Test
	public void getRankingTest() {

		double expected = 100;

		ArrayList<User> ranking = udb.getRanking();

		double actual = ranking.get(0).getChip();

		assertThat(actual, is(expected));

	}

	//getMyRankingテスト
	@Test
	public void getMyRankingTest() {

		int expected = 1;

		int actual = udb.getMyRanking(id);

		assertThat(actual, is(expected));

	}

	//updateUserテスト
	@Test
	public void updateUserTest() throws SQLException {

		User setUser = new User();
		setUser.setId(id);
		setUser.setPassword(password);
		setUser.setName("name2");

		udb.updateUserName(setUser);
		User getUser = udb.getUser(id, password);

		String expected = "name2";
		String actual = getUser.getName();

		assertThat(actual, is(expected));

	}

}
