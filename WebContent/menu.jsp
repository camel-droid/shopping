<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>${signin.uid}さんがサインインしています。</p>

<!-- メニュー -->
<a href="/shopping/ShowItemServlet?action=top">ようこそ</a>|

<c:forEach items="${categories}" var="category">
<a href="/shopping/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>|
</c:forEach>

<a href="/shopping/CartServlet?action=show">カートを見る</a>
&nbsp;&nbsp;
<a href="/shopping/AuthServlet?action=signout">ログアウト</a>

