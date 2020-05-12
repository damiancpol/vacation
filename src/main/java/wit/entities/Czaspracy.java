package wit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="czaspracy")
public class Czaspracy {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int CardNumber;
	int Employee;
	int Scanner;
	String wejscie;
	String wyjscie;
	@Column(name="OgolnaData")
	String og;
	
	public String getOg() {
		return og;
	}
	public void setOg(String og) {
		this.og = og;
	}
	String title;
	String Name;
	String Surname;
	String Rok;
	String Miesiac;
	String Dzien;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(int cardNumber) {
		CardNumber = cardNumber;
	}
	public int getEmployee() {
		return Employee;
	}
	public void setEmployee(int employee) {
		Employee = employee;
	}
	public int getScanner() {
		return Scanner;
	}
	public void setScanner(int scanner) {
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
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getRok() {
		return Rok;
	}
	public void setRok(String rok) {
		Rok = rok;
	}
	public String getMiesiac() {
		return Miesiac;
	}
	public void setMiesiac(String miesiac) {
		Miesiac = miesiac;
	}
	public String getDzien() {
		return Dzien;
	}
	public void setDzien(String dzien) {
		Dzien = dzien;
	}

	
}
