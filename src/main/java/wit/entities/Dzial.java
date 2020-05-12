package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dzial")
public class Dzial {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
String nazwa;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNazwa() {
	return nazwa;
}
public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}
}
