<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<h3>Witraż System</h3>
<img src="/images/witraz.jpg" style="height: 100px;width: 300px">
<f:form modelAttribute="z" action="checkLogin">

<tr><td>Login</td><td><f:input path="login"/></td></tr>
<tr><td>Haslo</td><td><f:password path="haslo"/></td></tr>
<input type="submit" value="Zaloguj">
<tr><td><span style="color:red">${np}</span></td></tr>
</f:form>


</body>
</html>