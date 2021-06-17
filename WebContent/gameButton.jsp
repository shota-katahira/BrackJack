<%@page import="model.GameInf"%>
<%@page import="model.Player"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% GameInf gi = (GameInf)session.getAttribute("gameInf");
   Player player = gi.getPlayer();
   boolean judgeEnd = false; %>

<% for(int i = 0; i < player.getHandList().size(); i++) { %>
	<% if(player.getHand(i).getResult() != null) { %>
		<% judgeEnd = true; %>
	<% } else { %>
<!--  		<form action="GameServlet" method="post">
			<button class="command" type='submit' name='command' value=<%= i %>>hit</button>
		</form> -->
		<% judgeEnd = false; %>
	<% } %>
<% } %>

<% if(judgeEnd) { %>
		<form action="StartGameServlet" method="post">
			<input class="command" type="submit" value="再戦">
			<jsp:include page="betChip.jsp"></jsp:include>
		</form>

<% } else { %>
	<form action="GameServlet" method="post">
		<button class="command" type='submit' name='command' value='2'>stand</button>
	</form>
<% } %>