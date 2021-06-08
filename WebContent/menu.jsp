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
		<link rel="stylesheet" type="text/css" href="login.css">
		<title>メニュー</title>
	</head>

	<body>
		<h1>BrackJack</h1>
		<% User user = (User)session.getAttribute("user");
		   GameInf gi = (GameInf)session.getAttribute("gameInf");%>
		<h1><%= user.getName() %>でログイン中</h1>

		<% if(gi.getMessage() != null) {%>
			<h1><%= gi.getMessage() %></h1>
		<% } %>

		<% if(gi.getPlayer() != null) {
	 		ArrayList<Card> playerHand = gi.getPlayer().getHand();
			ArrayList<Card> dealerHand = gi.getDealer().getHand();%>

			<h2>Dealer</h2>
			<% if(gi.getMessage() != null) { %>
				<% for(int i = 0; i < dealerHand.size(); i++) {%>
					<h3><%= dealerHand.get(i).getSuite() %>-<%= dealerHand.get(i).getNumber() %></h3>
				<% } %>

				<h3>スコア：<%= gi.getDealer().getScore() %></h3>
			<% } else { %>
				<h3><%= dealerHand.get(0).getSuite() %>-<%= dealerHand.get(0).getNumber() %></h3>
			<% } %>

			<h2>Player</h2>
			<% for(int i = 0; i < playerHand.size(); i++) {%>
				<h3><%= playerHand.get(i).getSuite() %>-<%= playerHand.get(i).getNumber() %></h3>
			<% } %>
			<h3>スコア：<%= gi.getPlayer().getScore() %></h3>

			<% if(gi.getPlayer().getAscore() > gi.getPlayer().getScore()) { %>
				<h3>or</h3>
				<h3><%= gi.getPlayer().getAscore() %></h3>
			<% } %>

			<% if(gi.getMessage() == null) { %>
				<form action="GameServlet" method="post">
					<button type='submit' name='command' value='0'>hit</button>
	  				<button type='submit' name='command' value='1'>stand</button>
				</form>
			<% } else { %>
				<form action="StartGameServlet" method="post">
					<input type="submit" value="再戦">
				</form>
			<% } %>

		<% } else { %>
			<form action="StartGameServlet" method="post">
				<input type="submit" value="start">
			</form>
		<% } %>

		<a href="GameLogServlet">戦績表示</a>
		<a href="RankingServlet">ランキング表示</a>
		<a href="accountmanagement.jsp">ニックネーム変更</a>

		<form action="LogoutServlet" method="post">
			<input type="submit" value="ログアウト">
		</form>

	</body>

</html>