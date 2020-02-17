<%@page import="ch13.board.BoardDao"%>
<%@page import="ch13.board.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strNum = request.getParameter("num");
	int num = Integer.parseInt(strNum);
	
	BoardDao boardDao = BoardDao.getInstance();
	BoardVo boardVo = boardDao.insertArticle(boardVo);
%>
<%@ include file="include/header.jsp" %>

<form>
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%=boardVo.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=boardVo.getSubject() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=boardVo.getEmail() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=boardVo.getContent() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="답글달기" id="btnReply"/>
				<input type="button" value="수정하기" id="btnUpdate"/>
				<input type="button" value="삭제하기" id="btnDelete"/>
				<input type="button" value="목록보기" id="btnList"/>
			</td>
		</tr>
	</table>
</form>
<%@ include file="include/footer.jsp" %>