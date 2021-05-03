<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>
	<h3>ユーザ登録</h3>
	<form action="/shopping/UserServlet?action=signup" method="post">
	<table>
		<tr>
			<th>お名前</th>
			<td>
				${signup.name}
			</td>
		</tr>
		<tr>
			<th>住所</th>
			<td>
				${signup.address}
			</td>
		</tr>
		<tr>
			<th>電話番号</th>
			<td>
				${signup.tel}
			</td>
		</tr>
		<tr>
			<th>電子メール</th>
			<td>
				${signup.email}
			</td>
		</tr>
		<tr>
			<th>表示名</th>
			<td>
				<input type="text" name="uid" value="${auth.uid}" />
			</td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td>
				<input type="password" name="password" value="${auth.password}" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="登録" />&nbsp;&nbsp
				<button formaction="/shopping/UserServlet?action=entry">入力画面に戻る</button>
			</td>
		</tr>
	</table>
</body>
</html>