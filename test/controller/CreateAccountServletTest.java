package controller;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.CreateAccountCheck;

public class CreateAccountServletTest {

	String id = "test";
	String password = "test";
	String name = "test";

	@InjectMocks
	private CreateAccountServlet cas = new CreateAccountServlet();

	@Mock
	private CreateAccountCheck cac;

	@Mock
	private MockHttpServletRequest request;

	@Mock
	private RequestDispatcher rd;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	//アカウント作成成功
	@Test
	public void createSuccessTest() throws Exception {

		MockHttpServletResponse response = new MockHttpServletResponse();

		doNothing().when(request).setCharacterEncoding(anyString());
		doReturn(id).when(request).getParameter("id");
		doReturn(password).when(request).getParameter("password1");
		doReturn(password).when(request).getParameter("password2");
		doReturn(name).when(request).getParameter("naem");
		doReturn("アカウントを作成しました").when(cac).check(anyObject(), anyString());
		doReturn(rd).when(request).getRequestDispatcher("createaccount.jsp");
		doNothing().when(rd).forward(anyObject(), anyObject());

		cas.doPost(request, response);

		verify(request, times(1)).setAttribute("message", "アカウントを作成しました");
		verify(request, times(1)).getRequestDispatcher("createaccount.jsp");

	}

	//アカウント作成失敗
	@Test
	public void createFalseTest() throws Exception {

		MockHttpServletResponse response = new MockHttpServletResponse();

		String password2 = "tes";

		doNothing().when(request).setCharacterEncoding(anyString());
		doReturn(id).when(request).getParameter("id");
		doReturn(password).when(request).getParameter("password1");
		doReturn(password2).when(request).getParameter("password2");
		doReturn(name).when(request).getParameter("naem");
		doReturn("アカウントを作成できませんでした").when(cac).check(anyObject(), anyString());
		doReturn(rd).when(request).getRequestDispatcher("createaccount.jsp");
		doNothing().when(rd).forward(anyObject(), anyObject());

		cas.doPost(request, response);

		verify(request, times(1)).setAttribute("message", "アカウントを作成できませんでした");
		verify(request, times(1)).getRequestDispatcher("createaccount.jsp");

	}

}
