<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<script type="text/javascript" src="/jss/jquery-1.7.2.min.js"></script>
  <script src="/jss/jquery-1.10.2.js"></script>
  <script src="/jss/jquery-ui-1.11.0/jquery-ui.js"></script>
  <link rel="stylesheet" href="/jss/jquery-ui-1.11.0/jquery-ui.min.css">
     <script src="/jss/script.js"></script>
   <script src="/jss/script2.js"></script>
   <link rel="stylesheet" href="/css/style.css">

<script>
	function myFunction() {
		var x = document.getElementById("myDIV");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
</script>
<script>
	$(window).load(function() {
		// selects all the divs of class='sample',hides them, finds the first, and shows it
		$('div.sample').hide().first().show();
		// binds a click event-handler to a elements whose class='display'
		$('a.display').on('click', function(e) {
			// prevents the default action of the link
			e.preventDefault();
			// assigns the currently visible div.sample element to a variable
			var that = $('div.sample:visible'),
			// assigns the text of the clicked-link to a variable for comparison purposes
			t = $(this).text();
			// checks if it was the 'next' link, and ensures there's a div to show after the currently-shown one
			if (t == 'następny' && that.next('div.sample').length > 0) {
				// hides all the div.sample elements
				$('div.sample').hide();
				// shows the 'next'
				that.next('div.sample').show()
			}
			// exactly the same as above, but checking that it's the 'prev' link
			// and that there's a div 'before' the currently-shown element.
			else if (t == 'poprzedni' && that.prev('div.sample').length > 0) {
				$('div.sample').hide();
				that.hide().prev('div.sample').show()
			}
		});
	});
</script>



<script>

$(document).ready(function() {
///////
var startDate;
var endDate;
 $( "#datepicker" ).datepicker({
dateFormat: 'dd-mm-yy'
})
///////
///////
 $( "#datepicker1" ).datepicker({
dateFormat: 'dd-mm-yy'
});
///////
$('#datepicker').change(function() {
startDate = $(this).datepicker('getDate');
$("#datepicker1").datepicker("option", "minDate", startDate );
})

///////
$('#datepicker1').change(function() {
endDate = $(this).datepicker('getDate');
$("#datepicker").datepicker("option", "maxDate", endDate );
////////////////
var t1=$('#datepicker').val();
t1=t1.split('-');
dt_t1=new Date(t1[2],t1[1]-1,t1[0]); // YYYY,mm,dd format to create date object
dt_t1_tm=dt_t1.getTime(); // time in milliseconds for day 1
//alert(dt_t1_tm);
var t2=$('#datepicker1').val();
t2=t2.split('-');
dt_t2=new Date(t2[2],t2[1]-1,t2[0]); // YYYY,mm,dd format to create date object
dt_t2_tm=dt_t2.getTime(); // time in milliseconds for day 2
/////////////////
var one_day = 24*60*60*1000; // hours*minutes*seconds*milliseconds
var diff_days=Math.abs((dt_t2_tm-dt_t1_tm)/one_day) // difference in days
$("#result").html("Liczba dni urlopu:" + diff_days + "");
$("#result").show();
})

////////////////
})
</script>
<style>
#datepicker {position: relative; indeks z: 1000; }
.ui-widget-content {
	border: 1px solid #dddddd;
	background: #eeeeee url("jss/jquery-ui-1.11.0/images/ui-bg_highlight-soft_100_eeeeee_1x100.png") 50% top repeat-x;
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
</style>
<title>Insert title here</title>
</head>
<body  bgcolor="#F5F5F5">
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
	<div
		style="color: black; font-family: Andale Mono; float: left; margin-left: 60px; margin-top: 130px;border: 1px solid silver;width:800px;background: white">
	<div style="margin-left:300px;color:#15062c;font-size: 25px;font-family: Andale Mono;;margin-right:auto">Nowy Wniosek</div><br><br>
			
				<div style="background: #EEEEEE;width: full">
				
				
				<div style="font-weight: bold;margin-left:150px;font-size:14px">	Imię i nazwisko	<font   color="blue">${uss.imie} ${uss.nazwisko}</font></div>
				
				
				<br>
				
			
			<div style="font-weight: bold;margin-left:150px;font-size:14px">		dzial/Dział	<font color="blue">${uss.dzial}</font></div>
				
				
				<br>
		</div>
		<tr>
<div style="margin-left:150px;">	<tr>	<span style="font-size:14px;font-weight: bold;">	Rodzaj wniosku:</span>
		${urlop.rodzaj }
		</tr>
		</div>
		 <br>
	
<div class="container">

<div class="main">
<div style="font-weight: bold;margin-left:140px;font-size:14px">
<table>

<tr><td><span style="font-weight: bold;font-size:14px">Urlop od :  ${urlop.start } do :  ${urlop.end }</span></span></td>

</tr>
</table>
</div>
</div><br>
	
		<div class="col-md-3"><p class="bg-info" id="result"><span style="font-weight: bold;font-size:14px;margin-left:140px;">Liczba dni :${urlop.liczbadniurlopu} </span><br></p></div>
	<div class="col-md-3"><p class="bg-info" id="result"><span style="font-weight: bold;font-size:14px;margin-left:140px;">Liczba godzin urlopu:${urlop.liczbagodzinurlopu } </span><br></div>
		<span style="font-weight: bold;font-size:14px;margin-left:140px;">	<td>Zastępstwo:${urlop.zastepstwo}</td></span>
	
		</tr>
		<br> <br>
		<tr><td><span style="font-weight: bold;font-size:14px;margin-left:140px;">Komentarz:</span></td>${urlop.description}</tr><br>
		
	</div>
	</div>
</body>
</html>