<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>
	<h3>ユーザ認証</h3>
	<form action="/shopping/AuthServlet?action=signin" method="post">
	<p>ユーザ登録は <a href="/shopping/UserServlet?action=entry">こちら</a> から</p>
	<table>
		<tr>
			<th>ユーザID</th>
			<td><input type="text" name="uid" /></td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="text" name="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="ログイン" /></td>
		</tr>
	</table>
	</form>
</body>
</html>