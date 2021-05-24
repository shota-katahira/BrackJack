package model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateAccountCheckTest {

	private CreateAccountCheck cac;

	@BeforeEach
	public void name() {
		cac = new CreateAccountCheck();
	}

	//確認用パスワードとの不一致
	@Test
	public void passMachTest() {

		boolean expected = false;
		boolean actual = cac.check("2", "p", "a", "n2");

		assertThat(actual, is(expected));

	}

	//ID未入力
	@Test
	public void idVoidTest() {

		boolean expected = false;
		boolean actual = cac.check("", "p", "p", "n2");

		assertThat(actual, is(expected));

	}

	//パスワード未入力
	@Test
	public void passVoidTest() {

		boolean expected = false;
		boolean actual = cac.check("2", "", "", "n2");

		assertThat(actual, is(expected));

	}

	//名前未入力
	@Test
	public void nameVoidTest() {

		boolean expected = false;
		boolean actual = cac.check("2", "p", "p", "");

		assertThat(actual, is(expected));

	}

	//ID重複
	@Test
	public void idDuplicateTest() {

		boolean expected = false;
		boolean actual = cac.check("1", "p", "p", "n2");

		assertThat(actual, is(expected));

	}

	//ID文字コード
	@Test
	public void idCharCodeTest() {

		boolean expected = false;
		boolean actual = cac.check("あ", "p", "p", "n2");

		assertThat(actual, is(expected));

	}

	//パスワード文字コード
	@Test
	public void passwordCharCodeTest() {

		boolean expected = false;
		boolean actual = cac.check("1", "あ", "あ", "n2");

		assertThat(actual, is(expected));

	}

	//アカウント追加成功
	@Test
	public void createSuccessTest() {

		boolean expected = true;
		boolean actual = cac.check("2", "p", "p", "n2");

		assertThat(actual, is(expected));

	}

}
