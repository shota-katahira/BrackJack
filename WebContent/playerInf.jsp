<%@page import="model.Hand"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.GameInf"%>
<%@page import="model.Player"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% GameInf gi = (GameInf)session.getAttribute("gameInf");
   Player player = gi.getPlayer();
   ArrayList<Hand> handList = player.getHandList();%>

<% for(int i = 0; i < player.getHandList().size(); i++) { %>
	<% Hand hand = handList.get(i); %>

	<% for(int j = 0; j < hand.getHand().size(); j++) { %>
		<div>
			<h3 id="suite"><%= hand.getHand().get(j).getSuite() %></h3>
			<h3 id="number"><%= hand.getHand().get(j).getFaceCard() %></h3>
		</div>
	<% } %>

	<%
		if(hand.resultIsNull()) {
	%>

		<%
			if(hand.getAscore() > hand.getScore()) {
		%>
			<h3 class="score">スコア：<%=hand.getScore()%> or <%=hand.getAscore()%></h3>
		<%
			} else {
		%>
			<h3 class="score">スコア：<%=hand.getScore()%></h3>
		<%
			}
		%>
		<h3>ベット：<%=hand.getChip()%></h3>

		<form action="HitServlet" method="post">
			<button class="hit" type='submit' name='index' value=<%=i%>>hit</button>
		</form>

	<%
		} else {
	%>

		<h1><%=hand.getResult()%></h1>

		<% if(hand.getAscore() > hand.getScore()) { %>
			<h3 class="score">スコア：<%= hand.getAscore() %></h3>
		<% } else { %>
			<h3 class="score">スコア：<%= hand.getScore() %></h3>
		<% } %>

	<% } %>

<% } %>