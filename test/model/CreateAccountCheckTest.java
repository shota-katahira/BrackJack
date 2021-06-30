package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dbmodel.UserDB;

public class CreateAccountCheckTest {

	@InjectMocks
	private CreateAccountCheck cac = new CreateAccountCheck();;

	@Mock
	private UserDB udb = new UserDB();

	private User user = new User();
	private String id = "test";
	private String password = "testpass";
	private String name = "test";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
	}

	@AfterEach
	public void delete() {
		udb.deleteUser(id);
	}

	//確認用パスワードとの不一致
	@Test
	public void passMachTest() {

		user.setId(id);
		user.setPassword("testtest");
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//ID未入力
	@Test
	public void idVoidTest() {

		user.setId("");
		user.setPassword(password);
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//パスワード未入力
	@Test
	public void passVoidTest() {

		user.setId(id);
		user.setPassword("");
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//名前未入力
	@Test
	public void nameVoidTest() {

		user.setId(id);
		user.setPassword(password);
		user.setName("");

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//ID重複
	@Test
	public void idDuplicateTest() {

		doReturn(true).when(udb).getUser(anyString());

		user.setId(id);
		user.setPassword(password);
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//ID文字コード
	@Test
	public void idCharCodeTest() {

		user.setId("あ");
		user.setPassword(password);
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

	//パスワード文字コード
	@Test
	public void passwordCharCodeTest() {

		user.setId(id);
		user.setPassword("あ");
		user.setName(name);

		String expected = "アカウントを作成できませんでした";
		String actual = cac.check(user, "あ");

		assertThat(actual, is(expected));

	}

	//アカウント追加成功
	@Test
	public void createSuccessTest() {

		user.setId(id);
		user.setPassword(password);
		user.setName(name);

		String expected = "アカウントを作成しました";
		String actual = cac.check(user, password);

		assertThat(actual, is(expected));

	}

}
