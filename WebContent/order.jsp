<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcom shopping!</title>
</head>
<body>
	<!-- メニュー -->
	<jsp:include page="/menu.jsp" />

	<h3>ご注文ありがとうございました。</h3>
	<p>お客様の注文番号は</p>
	<h3>${orderNumber}</h3>
	<p>になります。</p>

</body>
</html>