<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>
	<!-- メニュー -->
	<jsp:include page="/menu.jsp" />

	<h3>ご注文商品</h3>
	<table border="1">
		<tr>
			<th>商品番号</th>
			<th>商品名</th>
			<th>単価（税込）</th>
			<th>個数</th>
			<th>小計</th>
			<th>削除</th>
		</tr>

		<c:forEach items="${cart.items}" var="item">
		<tr>
			<td>${item.value.code}</td>
			<td>${item.value.name}</td>
			<td>${item.value.price}円</td>
			<td>${item.value.quantity}</td>
			<td>${item.value.price * item.value.quantity}円</td>
			<td>
				<form action="/shopping/CartServlet?action=delete" method="post">
					<input type="hidden" name="item_code" value="${item.value.code}" />
					<input type="submit" value="削除" />
				</form>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" style="text-align:right">総計：${cart.total}円</td>
		</tr>
	</table>

	<h3>お客様情報を入力してください。</h3>

	<table border="1">
		<tr>
			<th>お名前</th>
			<td><input type="text" name="name" value="鈴木一郎" /></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><input type="text" name="address" value="東京都新宿区" /></td>
		</tr>
		<tr>
			<th>電話</th>
			<td><input type="text" name="tel" value="03-3333-3333" /></td>
		</tr>
		<tr>
			<th>e-mail</th>
			<td><input type="text" name="email" value="ichiro@abc.com" /></td>
		</tr>
	</table>

	<form action="confirm.html" method="post">
		<input type="submit" value="確認画面へ" />
	</form>

</body>
</html>