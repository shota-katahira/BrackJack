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
	   String message = (String)session.getAttribute("message");
	   Player splitPlayer = (Player)session.getAttribute("splitPlayer");
	   boolean split = (boolean)session.getAttribute("split"); %>

	<header>
		<h1>BlackJack</h1>

		<h1><%= user.getName() %>でログイン中</h1>

		<% if(message != null) { %>
			<h1><%= message %></h1>
		<% } %>
	</header>

	<main>
	<% if(gi.getPlayer() != null) { %>

		<article>
			<h2>Dealer</h2>
			<% request.setAttribute("dealer", gi.getDealer());
			   request.setAttribute("message", message); %>
			<jsp:include page="dealerInf.jsp"></jsp:include>
		</article>

		<article>
			<h2>Player</h2>
			<% request.setAttribute("player", gi.getPlayer());
			   request.setAttribute("message", message); %>
			<jsp:include page="playerInf.jsp"></jsp:include>
		</article>

		<% if(splitPlayer != null) { %>
			<% request.setAttribute("splitPlayer", splitPlayer);
			   request.setAttribute("message", message); %>
			<jsp:include page="splitPlayerInf.jsp"></jsp:include>
			<jsp:include page="gameButton.jsp"></jsp:include>
		<% } %>

		<section>
			<jsp:include page="gameButton.jsp"></jsp:include>
		</section>

		<% if(split) { %>
			<section>
				<form action="SplitServlet" method="post">
					<button name="split">split</button>
				</form>
			</section>
		<% } %>

	<% } else { %>
		<section>
			<form action="StartGameServlet" method="post">
				<input type="submit" value="start">
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