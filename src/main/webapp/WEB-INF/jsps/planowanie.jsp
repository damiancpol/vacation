<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body bgcolor="#F5F5F5">
<div style="float:right">
<div style="font-size:14px;font-family: Verdana">${uss.login} <a href="sessionDestroy" style="text-decoration: none;margin-left:30px">  |  Wyloguj</a></div><br>
<div style="font-size:14px;font-family: Verdana;color:#007fff;">(${uss.dzial })</div>
</div>
<br>
<div style="width:full;border-bottom:1px solid silver;margin:0;height:50px;left:0;top:0">
<span style="font-size: 30px;font-weight: bold;font-family: Verdana;color:#3f374b; float:left">Witraż</span>

<a href="dodajWniosek"><div style="background: #3f374b;color:white;font-family: Verdana;float:left;margin-left: 110px;margin-top:10px;height: 30px"> + DODAJ WNIOSEK</div></a>
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
<div>
<f:form modelAttribute="urlop" action="miesiace">
<f:select path="planowanyurlopdo" >
<f:option value="Styczeń"></f:option>
<f:option value="Luty"></f:option>
<f:option value="Marzec"></f:option>
<f:option value="Kwiecień"></f:option>
<f:option value="Maj"></f:option>
<f:option value="Czerwiec"></f:option>
<f:option value="Lipiec"></f:option>
<f:option value="Sierpień"></f:option>
<f:option value="Wrzesień"></f:option>
<f:option value="Pazdziernik"></f:option>
<f:option value="Listopad"></f:option>
<f:option value="Grudzień"></f:option>
</f:select>

<input type="submit" value="Zatwierdz">
</f:form>
</div>

</body>
</html>