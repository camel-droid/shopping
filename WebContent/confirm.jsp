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
		</tr>
		<c:forEach items="${cart.items}" var="item">
		<tr>
			<td>${item.value.code}</td>
			<td>${item.value.name}</td>
			<td>${item.value.price}円</td>
			<td>${item.value.quantity}</td>
			<td>${item.value.price * item.value.quantity}円</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" style="text-align:right">総計：${cart.total}円</td>
		</tr>
	</table>

	<h3>お客様情報</h3>

	<table border="1">
		<tr>
			<th>お名前</th>
			<td>${customer.name}</td>
		</tr>
		<tr>
			<th>住所</th>
			<td>${customer.address}</td>
		</tr>
		<tr>
			<th>電話</th>
			<td>${customer.tel}</td>
		</tr>
		<tr>
			<th>e-mail</th>
			<td>${customer.email}</td>
		</tr>
	</table>

	<form action="/shopping/OrderServlet?action=order" method="post">
		<input type="submit" value="この内容で注文" />
	</form>

</body>
</html>