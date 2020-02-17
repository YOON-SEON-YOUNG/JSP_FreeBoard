<%@page import="ch13.board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="boardVo" class="ch13.board.BoardVo">
	<jsp:setProperty property="*" name="boardVo"/>
</jsp:useBean>
<%
	BoardDao boardDao = BoardDao.getInstance();
	boardDao.insertArticle(boardVo);
%>