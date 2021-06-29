<%@page import="model.GameInf"%>
<%@page import="model.Card"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Player"%>
<%@page import="model.Dealer"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<title>メニュー</title>
</head>

<body>

	<% User user = (User)session.getAttribute("user");
	   GameInf gi = (GameInf)session.getAttribute("gameInf");
	   boolean split = (boolean)session.getAttribute("split");
	   Player player = gi.getPlayer();
	   Dealer dealer = gi.getDealer(); %>

	<header>
		<h1>BlackJack</h1>

		<h3 class="user"><%= user.getName() %>でログイン中</h3>
		<h3>所持チップ：<%= user.getChip() %></h3>

	</header>

	<main>
	<% if(gi.getPlayer() != null) { %>

		<article>
			<h2>Dealer</h2>
			<jsp:include page="dealerInf.jsp"></jsp:include>
		</article>

		<article>
			<h2>Player</h2>
			<jsp:include page="playerInf.jsp"></jsp:include>
		</article>

		<section>
			<jsp:include page="gameButton.jsp"></jsp:include>
		</section>

		<%
			if(split && player.getHandList().get(0).resultIsNull()){
		%>
			<section>
				<form action="SplitServlet" method="post">
					<button class="command" name="split">split</button>
				</form>
			</section>
		<% } %>

	<% } else { %>
		<section>
			<form action="StartGameServlet" method="post">
				<input type="submit" value="start" class="command">
				<jsp:include page="betChip.jsp"></jsp:include>
			</form>
		</section>
	<% } %>
	</main>

	<aside>
		<jsp:include page="menu.jsp"></jsp:include>
	</aside>

</body>

</html>