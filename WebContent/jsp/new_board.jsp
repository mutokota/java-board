<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新しい投稿ページ</title>
</head>
<body>
	<h1>新規投稿</h1>
	<form action="${pageContext.request.contextPath}/new_board" method="post">
		<p>
			name:<input type="text" name="name">
		</p>
		<p>
			age:<input type="text" name="age">
		</p>
		<textarea id="message" name="message" required></textarea>
		<br>
		<br>
		<button type="submit">送信</button>
	</form>
</body>
</html>