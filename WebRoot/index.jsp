<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <c:forEach items="${message}" var="mm">
	${mm.defaultMessage}
</c:forEach> --%>
${message}
<form action="tologin.action" method="post">
	<input name="username">
	<input name="pwd" type="password">
	<input value="登录" type="submit">
</form>
</body>
</html>