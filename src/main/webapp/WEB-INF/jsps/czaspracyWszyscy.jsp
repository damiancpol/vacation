<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<link href="/jss/jquery-ui-1.12.1.custom/jquery-ui.css" rel="stylesheet">
<script src="jss/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
<script src="/jss/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#tt')
								.click(
										function() {
											var d1 = $('#datepicker-12').val();
											var d2 = $('#datepicker-123').val();

											document.getElementById('dif').value = workingDaysBetweenDates(
													d1, d2);
										});
					});

	function workingDaysBetweenDates(d0, d1) {
		var holidays = [ '2016-05-03', '2016-05-05' ];
		var startDate = parseDate(d0);
		var endDate = parseDate(d1);
		// Validate input
		if (endDate < startDate) {
			return 0;
		}
		// Calculate days between dates
		var millisecondsPerDay = 86400 * 1000; // Day in milliseconds
		startDate.setHours(0, 0, 0, 1); // Start just after midnight
		endDate.setHours(23, 59, 59, 999); // End just before midnight
		var diff = endDate - startDate; // Milliseconds between datetime objects    
		var days = Math.ceil(diff / millisecondsPerDay);

		// Subtract two weekend days for every week in between
		var weeks = Math.floor(days / 7);
		days -= weeks * 2;

		// Handle special cases
		var startDay = startDate.getDay();
		var endDay = endDate.getDay();

		// Remove weekend not previously removed.   
		if (startDay - endDay > 1) {
			days -= 2;
		}
		// Remove start day if span starts on Sunday but ends before Saturday
		if (startDay == 0 && endDay != 6) {
			days--;
		}
		// Remove end day if span ends on Saturday but starts after Sunday
		if (endDay == 6 && startDay != 0) {
			days--;
		}
		/* Here is the code */
		for ( var i in holidays) {
			if ((holidays[i] >= d0) && (holidays[i] <= d1)) {
				days--;
			}
		}
		return days;
	}

	function parseDate(input) {
		// Transform date from text to date
		var parts = input.match(/(\d+)/g);
		// new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
		return new Date(parts[0], parts[1] - 1, parts[2]); // months are 0-based
	}
</script>




<script>
	$(function() {
		$("#datepicker-12").datepicker();
		
	});
</script>

<script>
	$(function() {
		$("#datepicker-123").datepicker();
		
	});
</script>

<script>
	$("#datepicker-12").datepicker({
		dateFormat : "yy-mm-dd"
	});
</script>

<script>
	$("#datepicker-123").datepicker({
		dateFormat : "yy-mm-dd"
	});
</script>



<style>
.ui-widget-content {
	border: 1px solid #dddddd;
	background: #eeeeee
		url("jss/jquery-ui-1.11.0/images/ui-bg_highlight-soft_100_eeeeee_1x100.png")
		50% top repeat-x;
	color: #333333;
}

.ui-widget-content a {
	color: #333333;
	text-decoration: none;
	font-family: Verdana;
}

#myDIV {
	display: none;
}

.content {
	display: none;
}

button {
	margin-top: 30px;
}

.back {
	display: none;
}

.next {
	margin-left: 50px;
}

.end {
	display: none;
}

td {
width:100px;
border-bottom: 1px solid black;
text-align: left;
	
}
th{
border-bottom: 1px solid black;
width:200px;
}

table {
	
}

a:hover {
	color: pink;
}

.dz {
	text-decoration: none;
	color: red;
}

.kl {
	border: 1px solid blue;
}

.sst {
	display: none;
}

spsan:hover+.st {
	display: block;
}

input {
	font-family: monospace;
}
</style>
<title>Insert title here</title>
</head>
<body bgcolor="#F5F5F5">



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
		style="width: full; border-bottom: 1px solid silver; margin: 0; height: 50px; left: 0; top: 0;">
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
	<div id="tt"
		style="color: black; font-family: Andale Mono; float: left; margin-left: 60px; margin-top: 130px; border: 1px solid silver; width: 1200px; background: white">
<table>
<tr>
<tr>
<f:form modelAttribute="BB" action="wybranyDzial" >
<f:select path="Name">
<f:option value="Wybierz dział" select="selected"></f:option>
<f:option value="Drewno"></f:option>
<f:option value="Kierowcy"></f:option>
<f:option value="Załadunek"></f:option>
<f:option value="Biuro drewno"></f:option>
<f:option value="Biuro PCV"></f:option>
<f:option value="Drewno uz"></f:option>
</f:select>

<f:select path="rok">
<f:option value="Wybierz rok"></f:option>
<f:option value="2014"></f:option>
<f:option value="2015"></f:option>
<f:option value="2016"></f:option>
<f:option value="2017"></f:option>
<f:option value="2018"></f:option>
<f:option value="2019"></f:option>
<f:option value="2020"></f:option>
<f:option value="2021"></f:option>
<f:option value="2022"></f:option>
<f:option value="2023"></f:option>
<f:option value="2024"></f:option>
<f:option value="2025"></f:option>
<f:option value="2026"></f:option>
<f:option value="2027"></f:option>
<f:option value="2028"></f:option>
<f:option value="2029"></f:option>
<f:option value="2030"></f:option>
<f:option value="2031"></f:option>
<f:option value="2032"></f:option>
<f:option value="2033"></f:option>
<f:option value="2034"></f:option>
<f:option value="2035"></f:option>
<f:option value="2036"></f:option>
<f:option value="2037"></f:option>
<f:option value="2038"></f:option>


</f:select>
<f:select path="miesiac">
<f:option value="Wybierz miesiac" select="selected"></f:option>
<f:option value="Styczeń"></f:option>
<f:option value="Luty"></f:option>
<f:option value="Marzec"></f:option>
<f:option value="Kwiecień"></f:option>
<f:option value="Maj"></f:option>
<f:option value="Czerwiec"></f:option>
<f:option value="Lipiec"></f:option>
<f:option value="Sierpień"></f:option>
<f:option value="Wrzesień" ></f:option>
<f:option value="Pazdziernik"></f:option>
<f:option value="Listopad"></f:option>
<f:option value="Grudzień"></f:option>


</f:select>
<f:select path="dzien">
<f:option value="Wybierz dzień" select="Selected"></f:option>
<f:option value="1"></f:option>
<f:option value="2"></f:option>
<f:option value="3"></f:option>
<f:option value="4"></f:option>
<f:option value="5"></f:option>
<f:option value="6"></f:option>
<f:option value="7"></f:option>
<f:option value="8"></f:option>
<f:option value="9"></f:option>
<f:option value="10"></f:option>
<f:option value="11"></f:option>
<f:option value="12"></f:option>
<f:option value="13"></f:option>
<f:option value="14"></f:option>
<f:option value="15"></f:option>
<f:option value="16"></f:option>
<f:option value="17"></f:option>
<f:option value="18"></f:option>
<f:option value="19"></f:option>
<f:option value="20"></f:option>
<f:option value="21"></f:option>
<f:option value="22"></f:option>
<f:option value="23"></f:option>
<f:option value="24"></f:option>
<f:option value="25"></f:option>
<f:option value="26"></f:option>
<f:option value="27"></f:option>
<f:option value="28"></f:option>
<f:option value="29"></f:option>
<f:option value="30"></f:option>
<f:option value="31"></f:option>

</f:select>
</tr>
<tr><input type="submit" value="Potwierdz"></tr>
</f:form>

</table>

		
	</div>
	
</body>
</html>