<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
<h1>글 수정</h1>
<form action="updatePro.jsp" method="post">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="writer"/></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"/></textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"/></td>
		</tr>
		<tr>
			<td>
				<input type="submit" 	value="작성완료"/>
				<input type="reset" 	value="다시작성"/>
				<input type="button" 	value="목록보기"	id="btnList"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>