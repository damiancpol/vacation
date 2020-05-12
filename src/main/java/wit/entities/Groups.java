package wit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class Groups {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
	@Column(name="Name")
	String nm;
	@Column(name="Parent")
	String pr;
	public int getId() {
		return id;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public void setId(int id) {
		this.id = id;
	}

}
