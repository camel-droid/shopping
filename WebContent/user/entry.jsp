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
	<p><a href="/shopping">ユーザ認証ページに戻る</a></p>
	<form action="/shopping/UserServlet?action=confirm" method="post">
	<table>
		<tr>
			<th>お名前</th>
			<td>
				<input type="text" name="name" value="${signup.name}" />
			</td>
		</tr>
		<tr>
			<th>住所</th>
			<td>
				<input type="text" name="address" value="${signup.address}" />
			</td>
		</tr>
		<tr>
			<th>電話番号</th>
			<td>
				<input type="text" name="tel" value="${signup.tel}" />
			</td>
		</tr>
		<tr>
			<th>電子メール</th>
			<td>
				<input type="text" name="email" value="${signup.email}" />
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
				<input type="text" name="password" value="${auth.password}" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="確認画面へ" /
			</td>
		</tr>
	</table>
</body>
</html>