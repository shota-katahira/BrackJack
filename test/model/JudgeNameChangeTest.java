package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dbmodel.UserDB;

public class JudgeNameChangeTest {

	@InjectMocks
	private JudgeNameChange jnc = new JudgeNameChange();

	@Mock
	private UserDB udb;

	String name = "test";
	String newName = "newTest";
	User user = new User();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user.setName(name);
	}

	@Test
	public void judgeTestTrue() {

		doNothing().when(udb).updateUserName(user);

		String expected = "ニックネームを変更しました";
		String actual = jnc.judge(newName, user);

		assertThat(actual, is(expected));

	}

	@Test
	public void judgeTestFalse() {

		doNothing().when(udb).updateUserName(user);

		String expected = "ニックネームを変更出来ませんでした";
		String actual = jnc.judge(name, user);

		assertThat(actual, is(expected));

	}

	@Test
	public void judgeTestFalse2() {

		doNothing().when(udb).updateUserName(user);

		String expected = "ニックネームを変更出来ませんでした";
		String actual = jnc.judge("", user);

		assertThat(actual, is(expected));

	}

}
