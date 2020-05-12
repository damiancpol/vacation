<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function myFunction() {
  var x = document.getElementById("myInput");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>

<title>Insert title here</title>
</head>
<body bgcolor="#F5F5F5">
	<
	<div style="float: right">
		<div style="font-size: 14px; font-family: Verdana">${uss.login}
			<a href="sessionDestroy"
				style="text-decoration: none; margin-left: 30px"> | Wyloguj</a>
		</div>
		<br>
		<div style="font-size: 14px; font-family: Verdana; color: #007fff;">(${uss.dzial })</div>
	</div>
	<br>
	<div
		style="width: full; border-bottom: 1px solid silver; margin: 0; height: 50px; left: 0; top: 0">
		<span
			style="font-size: 30px; font-weight: bold; font-family: Verdana; color: #3f374b; float: left">Witraż</span>

		<a href="dodajWniosek"><div
				style="background: #3f374b; color: white; font-family: Verdana; float: left; margin-left: 110px; margin-top: 10px; height: 30px">
				+ DODAJ WNIOSEK</div></a>
	</div>
	<br>
	<div style="background: #3f374b; width: 200px; height: 1000px; left: 0; float: left">
		<div style="color: white; font-family: Verdana">
			<br>
			<br>
			<br>
			<br>
			<br>

			<c:choose>
				<c:when test="${uss.dzial eq 'Kierownik' }">
					<a href="admin"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Strona
						główna</a>
					<br>
					<br>
				</c:when>
					<c:when test="${uss.login eq 'Kadry' }">
					<a href="kadry"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Strona
						główna</a>
					<br>
					<br>
				</c:when>
				<c:otherwise>
					<a href="stronaG"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Strona
						główna</a>
					<br>
					<br>

				</c:otherwise>
			</c:choose>


			<span
				style="color: #007fff; font-family: Verdana; font-size: 30px; float: left; margin-left: 30px">Urlopy</span><br>
			<br>
			<br>
			<br> <a href="dodajWniosek"
				style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Dodaj
				wniosek</a><br> <br> <a href="miesiace"
				style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Planowanie</a><br>
			<br>

			<c:choose>
				<c:when test="${uss.dzial eq 'Kierownik' }">
					<a href="klistawnioskow"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Lista
						wniosków</a>
					<br>
					<br>
				</c:when>
				<c:when test="${uss.login eq 'Kadry' }">
					<a href="klistawnioskow"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Lista
						wniosków</a>
					<br>
					<br>
				</c:when>
				<c:otherwise>
					<a href="listawnioskow"
						style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px">Lista
						wniosków</a>
					<br>
					<br>

				</c:otherwise>
			</c:choose>
		<a href="konfiguracja" style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px;margin-top: 70px">Konfiguracja</a><br><br>
<c:choose>
							<c:when test="${uss.dzial eq 'Kierownik' }">
				<a href="Grupa" style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px;margin-top: 70px">Czytniki</a>
					<br>
					<br>
				</c:when>
				<c:when test="${uss.login eq 'Kadry' }">
				<a href="Grupa" style="color: white; font-family: Verdana; text-decoration: none; margin-left: 30px;margin-top: 70px">Czytniki</a>
					<br>
					<br>
				</c:when>
				<c:otherwise>
					<br>
					<br>

				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<div style="float: left;">
	
	<f:form modelAttribute="ur" action="dodanoUzytkownika">
	<table>
	<tr><td>Imie</td><td><f:input path="imie"/></td></tr>
	<tr><td>Nazwisko</td><td><f:input path="nazwisko"/></td></tr>
	<tr><td>Wiek</td><td><f:input path="wiek"/></td></tr>
	<tr><td>Email</td><td><f:input path="email"/></td></tr>
	<tr><td>Liczba dni wolnych</td><td><f:input path="liczbadniwolnych"/></td></tr>
	<tr><td>Dział</td><td>
	<f:select path="dzial">
<f:option select="selected" value="Wybierz dział"></f:option>
<c:forEach items="${allDzial}" var="i">
<f:option value="${i.nazwa }">
${i.nazwa }

</f:option>

</c:forEach>
</f:select>
</td></tr>
<tr><td>Kierownik</td><td>
	<f:select path="kierownik">
<f:option select="selected" value="Przypisz kierownika"></f:option>
<c:forEach items="${kierownik}" var="i">
<f:option value="${i.login}">
${i.login}

</f:option>
	
	</c:forEach>
	</f:select>
	
	</td></tr>
	<tr><td>Login</td><td><f:input path="login"/></td></tr>
	<tr><td>Hasło</td><td><f:input type="password" path="haslo" id="myInput"/><input type="checkbox" onclick="myFunction()">Pokaż hasło</td></tr>
	
	<tr><td><input type="submit"></td></tr>
	</table>
	
	
	</f:form>
	
	
	
	
	
	</div>
</body>
</html>