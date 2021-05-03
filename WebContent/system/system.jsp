<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Welcom shopping!</title>
</head>
<body>
	<h3>管理者メニュ</h3>
	<ol>
		<li><a href="/shopping/ShowItemServlet?action=top">トップページへ</a></li>
		<li><a href="/shopping/ItemServlet?action=top">商品管理</a></li>
		<li><a href="/shopping/UserSevlet?action=top">利用者管理</a></li>
		<li><a href="/shopping/AuthServlet?action=signout">ログアウト</a></li>
	</ol>
</body>
</html>