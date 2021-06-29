package controller;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.Deck;
import model.GameInf;
import model.Player;
import model.SetGameData;
import model.Stand;
import model.User;

public class StandServletTest {

	@InjectMocks
	private StandServlet ss = new StandServlet();

	@Mock
	private Stand stand;

	@Mock
	private SetGameData sgd;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doPostTest() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		HttpSession session = request.getSession();
		User user = new User();
		user.setId("id");
		Deck deck = new Deck();
		Player player = new Player(0, 0, deck);
		GameInf gi = new GameInf(player, null, null);
		request.setParameter("command", "0");
		session.setAttribute("user", user);
		session.setAttribute("gameInf", gi);

		doReturn(gi).when(stand).doStand(anyObject());
		doReturn(user).when(sgd).setData(anyObject(), anyObject());

		ss.doPost(request, response);

		String expectedId = "id";
		session = request.getSession();
		User actualUser = (User) session.getAttribute("user");
		String actualId = actualUser.getId();

		assertThat(actualId, is(expectedId));

	}

}
