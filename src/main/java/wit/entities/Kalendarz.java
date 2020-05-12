package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kalendarz")
public class Kalendarz {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
String login;
String data;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}

}
