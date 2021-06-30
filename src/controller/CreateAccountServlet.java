package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreateAccountCheck;
import model.User;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 6364871625137530566L;

	private CreateAccountCheck cac = new CreateAccountCheck();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");

		User user = new User();
		user.setId(id);
		user.setPassword(password1);
		user.setName(name);

		//アカウント作成可能か判定
		String message = cac.check(user, password2);

		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("createaccount.jsp");
		rd.forward(request, response);

	}

}
