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

	<h3>現在のカートの中身（JSP版）</h3>
	<table border="1">
		<tr>
			<th>商品番号</th>
			<th>商品名</th>
			<th>単価（税込）</th>
			<th>個数</th>
			<th>小計</th>
			<th>削除</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Javaの基本</td>
			<td>2500円</td>
			<td>2</td>
			<td>5000円</td>
			<td>
				<form action="" method="post">
					<input type="submit" value="削除" />
				</form>
			</td>
		</tr>
		<tr>
			<td>2</td>
			<td>MLB Funs</td>
			<td>980円</td>
			<td>4</td>
			<td>3920円</td>
			<td>
				<form action="" method="post">
					<input type="submit" value="削除" />
				</form>
			</td>
		</tr>
		<tr>
			<td>4</td>
			<td>なつかしのアニメシリーズ</td>
			<td>2000円</td>
			<td>3</td>
			<td>6000円</td>
			<td>
				<form action="" method="post">
					<input type="submit" value="削除" />
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="6" style="text-align:right">総計：14920円</td>
		</tr>
	</table>
	<form action="customerInfo.html" method="post">
		<input type="submit" value="注文する" />
	</form>

</body>
</html>