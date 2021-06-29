package controller;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.Card;
import model.Deck;
import model.GameInf;
import model.Player;
import model.SetGameData;
import model.Split;
import model.User;

public class SplitServletTest {

	private Deck decks;
	private LinkedList<Card> deck = new LinkedList<Card>();

	@InjectMocks
	private SplitServlet ss = new SplitServlet();

	@Mock
	private SetGameData sgd;

	@Mock
	private Split split;

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);

		decks = new Deck();

		for (int j = 1; j < 14; j++) {

			for (int i = 0; i < 4; i++) {

				Card card = new Card(i, j);
				deck.add(card);
			}

		}

		decks.setDeck(deck);

	}

	@Test
	public void doPostTest() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		HttpSession session = request.getSession();
		Player player = new Player(100, 1, decks);
		GameInf gi = new GameInf(player, null, decks);
		User user = new User();

		session.setAttribute("gameInf", gi);

		doReturn(gi).when(split).doSplit(anyObject());
		doReturn(user).when(sgd).setData(anyObject(), anyObject());

		ss.doPost(request, response);

		session = request.getSession();

		boolean expected = true;
		boolean actual = (boolean) session.getAttribute("split");

		assertThat(actual, is(expected));

	}

}
