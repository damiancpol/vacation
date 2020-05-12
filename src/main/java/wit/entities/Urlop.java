package wit.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "urlop")
public class Urlop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int idurlopu;
	@Column(name = "name")
	String title;
	String nazwisko;
	String liczbadniurlopu;
	String liczbagodzinurlopu;
	String zastepstwo;
	@Column(name = "description")
	String description;
	String rodzaj;
	public String getKierownik1() {
		return kierownik1;
	}

	public void setKierownik1(String kierownik1) {
		this.kierownik1 = kierownik1;
	}

	@Column(name = "iduzytkownika")
	int id;
	@Column(name = "startDate")
	String start;
	@Column(name = "endDate")
	String end;
	int status;
	String color;

	String dzial;
	String kierownik;
	String kierownik1;
	

	public String getKierownik() {
		return kierownik;
	}

	public void setKierownik(String kierownik) {
		this.kierownik = kierownik;
	}

	public String getDzial() {
		return dzial;
	}

	public void setDzial(String dzial) {
		this.dzial = dzial;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdurlopu() {
		return idurlopu;
	}

	public void setIdurlopu(int idurlopu) {
		this.idurlopu = idurlopu;
	}

	public String getLiczbadniurlopu() {
		return liczbadniurlopu;
	}

	public void setLiczbadniurlopu(String liczbadniurlopu) {
		this.liczbadniurlopu = liczbadniurlopu;
	}

	public String getLiczbagodzinurlopu() {
		return liczbagodzinurlopu;
	}

	public void setLiczbagodzinurlopu(String liczbagodzinurlopu) {
		this.liczbagodzinurlopu = liczbagodzinurlopu;
	}

	public String getZastepstwo() {
		return zastepstwo;
	}

	public void setZastepstwo(String zastepstwo) {
		this.zastepstwo = zastepstwo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}

}
