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
import model.JudgeNaturalBJ;
import model.Player;
import model.SetGameData;
import model.User;

public class StartGameServletTest {

	@InjectMocks
	private StartGameServlet sgs = new StartGameServlet();

	@Mock
	private JudgeNaturalBJ gm;

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
		request.setParameter("betChip", "1");
		User user = new User();
		user.setId("id");
		session.setAttribute("user", user);
		Deck deck = new Deck();
		Player player = new Player(0, 0, deck);
		GameInf gi = new GameInf(player, null, null);

		doReturn(gi).when(gm).judge(anyObject());
		doReturn(user).when(sgd).setData(anyObject(), anyObject());

		sgs.doPost(request, response);

		String expectedId = "id";
		session = request.getSession();
		User actualUser = (User) session.getAttribute("user");
		String actualId = actualUser.getId();

		assertThat(actualId, is(expectedId));

	}

}
