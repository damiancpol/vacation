<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
							<c:when test="${uss.dzial eq 'Kierownik'}">
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
	
	<div style="float:left;margin-left:50px"><img src="/images/pracownik.png" style="width: 70px;height: 70px;margin-left:50px"><a href="dodajdzial"><img src="/images/ddn.png"></a><img src="/images/pracownik.png" style="width: 70px;height: 70px;margin-left:50px"><a href="dodajpracownika"><img src="/images/ddp.png"></a>
	<img src="/images/lista.png" style="width: 100px;height: 100px;margin-left:50px"><a href="klistawnioskow"><img src="/images/lw.png"></a>
	</div>

<br><br><br><br><br><br><br>

<div style="float:left;width: 600px;height:50px;border:1px solid silver;margin-left:50px;height: auto;font-family: Verdana;font-size: 20px;background: white">Zmiany statusów


<div style="margin-left:auto;margin-right:auto; width:450px;font-size: 12px;font-family: Verdana" >



<c:url value="zobacz" var="zzi">
<c:param name="zob" value=""></c:param>
</c:url>


<c:forEach items="${allUrlopAccepted}" var="i" begin="0" end="4">
<table style="border-bottom: 1px solid silver"><tr>
<c:choose>
<c:when test="${i.status==0 }">
<img src="/images/czeka.png"><b>Czeka na akceptację przełożonego</b>
</c:when>
<c:when test="${i.status==2 }">
<img src="/images/odrzucony.png"><b>Odrzucone przez przełożonego</b>
</c:when>
<c:otherwise>

<img src="/images/zakceptowany.png"><b>Zatwierdzony przez przełożonego</b>
</c:otherwise>
</c:choose> 

<c:url value="kzobacz" var="zzi">
<c:param name="zob" value="${i.idurlopu}"></c:param>
</c:url>

<td>Od ${i.start} do ${i.end}</td>
<td><span style="color:green;font-size:12px;margin-left:30px"><a href="${zzi}" style="text-decoration: none;color:green;font-weight: bold;">Zobacz</a></span></td>
<td > <br><span style="margin-left:20px">${i.idurlopu}  </span><span style="font-size:10px;font-weight: bold;margin-left:10px">${i.title}</span></td>
</tr>
</table>

</c:forEach>

<div style="margin:auto"> 


<c:forEach begin="1" end="${size}" var="i">
<c:url value="kadry1" var="tt">
<c:param name="liczba"  value="${i}"></c:param>
 
</c:url>
<c:choose>
<c:when test="${i eq ll }">

<td style="background:  #FFCC33"><a href="${tt}"  ><span style="color:red;font-size: 25px;text-decoration: none">${i}</span></a></td>
</c:when>
<c:otherwise>
<td><a href="${tt}"><span style="color:black;font-size: 20px;text-decoration: none">${i}</span></a></td>
</c:otherwise>
</c:choose>
</c:forEach>

</div>
</div>

</div>


<div style="float:left; margin-left:20px;">


<div style="width:550px;background: white">
<img src="/images/nieobecni.png" style="border: 1px solid silver;width:550px;height:auto">

<c:forEach items="${bz}" var="i">
<img src="/images/ur.png" style="margin-left:40px;margin-top:10px"><span style="font-weight: bold;font-style: Verdana;margin-left: 10px;margin-top:-5px;font-size:12px">${i }</span>
<br>
</c:forEach>
</div>


<div >

<img src="/images/obieg.png" style="width:550px;height:auto;border: 1px solid silver;"/>


</div>
</div>
</body>
</html>