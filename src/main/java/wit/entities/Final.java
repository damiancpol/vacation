package wit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="final")
public class Final {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
String CardNumber;
String Scanner;
String wejscie;
String wyjscie;
@Column(name="OgolnaData")
String og;
String imie;
String nazwisko;
String title;
String rok;
String miesiac;
String dzien;
String grupa;
@Column(name="Name")
String name;
String d1;
String d2;
String roznica;
public String getRoznica() {
	return roznica;
}
public void setRoznica(String roznica) {
	this.roznica = roznica;
}
public String getD1() {
	return d1;
}
public void setD1(String d1) {
	this.d1 = d1;
}
public String getD2() {
	return d2;
}
public void setD2(String d2) {
	this.d2 = d2;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCardNumber() {
	return CardNumber;
}
public void setCardNumber(String cardNumber) {
	CardNumber = cardNumber;
}
public String getScanner() {
	return Scanner;
}
public void setScanner(String scanner) {
	Scanner = scanner;
}
public String getWejscie() {
	return wejscie;
}
public void setWejscie(String wejscie) {
	this.wejscie = wejscie;
}
public String getWyjscie() {
	return wyjscie;
}
public void setWyjscie(String wyjscie) {
	this.wyjscie = wyjscie;
}
public String getOg() {
	return og;
}
public void setOg(String og) {
	this.og = og;
}
public String getImie() {
	return imie;
}
public void setImie(String imie) {
	this.imie = imie;
}
public String getNazwisko() {
	return nazwisko;
}
public void setNazwisko(String nazwisko) {
	this.nazwisko = nazwisko;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getRok() {
	return rok;
}
public void setRok(String rok) {
	this.rok = rok;
}
public String getMiesiac() {
	return miesiac;
}
public void setMiesiac(String miesiac) {
	this.miesiac = miesiac;
}
public String getDzien() {
	return dzien;
}
public void setDzien(String dzien) {
	this.dzien = dzien;
}
public String getGrupa() {
	return grupa;
}
public void setGrupa(String grupa) {
	this.grupa = grupa;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

	
}
