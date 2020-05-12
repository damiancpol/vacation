
package wit.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wit.Hibernateutil;
import wit.entities.Final;
import wit.entities.Czaspracy;
import wit.entities.Dzial;
import wit.entities.Groups;
import wit.entities.Kalendarz;
import wit.entities.Pracownik;
import wit.entities.Urlop;
import wit.entities.Uusers;

@Repository
public class RepositoryLogin implements RepositoryLoginInterface {

	Hibernateutil ss;

	@Override
	public List<Uusers> getAllUsers() {
		Session openSession = ss.session().openSession();
		openSession.beginTransaction();
		List<Uusers> list = openSession.createQuery("from Uusers").list();
		openSession.getTransaction().commit();
		openSession.close();

		return list;
	}

	@Override
	public Uusers checkuser(Uusers us) {
		Session openSession = ss.session().openSession();
		openSession.beginTransaction();
		Uusers ss = (Uusers) openSession.createQuery("from Uusers where login=:login and haslo=:haslo")
				.setParameter("login", us.getLogin()).setParameter("haslo", us.getHaslo()).uniqueResult();
		openSession.getTransaction().commit();
		openSession.close();

		return ss;
	}

	@Override
	public void saveDateUser(String data, Uusers sss) {

		Kalendarz kk = new Kalendarz();
		kk.setData(data);
		kk.setLogin(sss.getLogin());

		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(kk);
		tr.commit();
		openSession.close();

	}

	@Override
	public List<Kalendarz> getAllFreeDays(Uusers ssj) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Kalendarz> kk = openSession.createQuery(" from Kalendarz where login=:login order by id asc")
				.setParameter("login", ssj.getLogin()).setMaxResults(1).list();
		tr.commit();
		openSession.close();
		return kk;
	}

	@Override
	public void saveUrlop(Urlop up) {
		// TODO Auto-generated method stub
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.saveOrUpdate(up);

		tr.commit();
		openSession.close();
	}

	@Override
	public Urlop getUrlop(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		Urlop urlop = (Urlop) openSession.createQuery("from Urlop where id=:id order by idurlopu desc")
				.setParameter("id", id).setMaxResults(1).uniqueResult();
		tr.commit();
		openSession.close();
		return urlop;
	}

	@Override
	public void deleteWniosek(Urlop up) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.delete(up);
		tr.commit();
		openSession.close();
	}

	@Override
	public List<Urlop> getallUrlop(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Urlop> urlops = (List<Urlop>) openSession.createQuery("from Urlop where id=:id order by idurlopu desc")
				.setParameter("id", id).list();

		tr.commit();
		openSession.close();
		return urlops;
	}

	@Override
	public Urlop getUrlopbyIdUrlopu(int idurlopu) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		Urlop pp = (Urlop) openSession.createQuery("from Urlop where idurlopu=:idurlopu ")
				.setParameter("idurlopu", idurlopu).uniqueResult();
		tr.commit();
		openSession.close();
		return pp;
	}

	@Override
	public void updUrlop(Urlop up) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.update(up);
		tr.commit();
		openSession.close();
	}

	@Override
	public List<Urlop> getAllUsersWithDate() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Urlop> as = (List<Urlop>) openSession.createQuery("from Urlop order by idurlopu desc").list();
		tr.commit();
		openSession.close();
		return as;
	}

	@Override
	public List<Urlop> getUsers(String dzial) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Urlop> as = (List<Urlop>) openSession.createQuery("from Urlop where dzial=:dzial order by idurlopu desc")
				.setParameter("dzial", dzial).list();
		tr.commit();
		openSession.close();

		return as;
	}

	@Override
	public void saveOrUpdateUser(Uusers ut) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.update(ut);
		tr.commit();
		openSession.close();

	}

	@Override
	public Uusers getUser(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		Uusers uusers = (Uusers) openSession.createQuery("from Uusers where id=:id").setParameter("id", id)
				.uniqueResult();
		tr.commit();
		openSession.close();

		return uusers;
	}

	@Override
	public void dodajUzytkownika(Uusers ur) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.save(ur);
		tr.commit();
		openSession.close();

	}

	@Override
	public List<Dzial> getAllDzial() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Dzial> dzial = (List<Dzial>) openSession.createQuery("from Dzial").list();
		tr.commit();
		openSession.close();
		return dzial;
	}

	@Override
	public void dodajDzial(Dzial dzial) {
		// TODO Auto-generated method stub
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.save(dzial);
		tr.commit();
		openSession.close();

	}

	@Override
	public void deleteDzial(int id) {
		// TODO Auto-generated method stub
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		Dzial dd = (Dzial) openSession.createQuery("from Dzial where id=:id").setParameter("id", id).uniqueResult();
		openSession.delete(dd);
		tr.commit();
		openSession.close();

	}

	@Override
	public Dzial getDzial(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		Dzial dd = (Dzial) openSession.createQuery("from Dzial where id=:id").setParameter("id", id).uniqueResult();
		tr.commit();
		openSession.close();

		return dd;
	}

	@Override
	public void saveorupdateDzial(Dzial dd) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.saveOrUpdate(dd);
		tr.commit();
		openSession.close();

	}

	@Override
	public List<Urlop> getLiczba(int liczba,String kierownik) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 5;
		List<Urlop> ls = (List<Urlop>) openSession.createQuery("from Urlop  where kierownik=:kierownik order by idurlopu desc")
				.setParameter("kierownik", kierownik)
				.setFirstResult(l * (liczba - 1)).setMaxResults(l).list();
		tr.commit();
		openSession.close();
		return ls;
	}

	@Override
	public List<Urlop> getLiczba1(int liczba) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 10;
		List<Urlop> ls = (List<Urlop>) openSession.createQuery("from Urlop order by idurlopu desc")
				.setFirstResult(l * (liczba - 1)).setMaxResults(l).list();
		tr.commit();
		openSession.close();
		return ls;
	}

	@Override
	public List<Urlop> getUlropWithLiczba(int liczba, int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 5;
		List<Urlop> pp = (List<Urlop>) openSession.createQuery("from Urlop where id=:id order by idurlopu desc")
				.setParameter("id", id).setFirstResult(l * (liczba - 1)).setMaxResults(l).list();
		tr.commit();
		openSession.close();
		return pp;
	}

	@Override
	public List<Urlop> getLiczba3(int liczba, int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 5;
		List<Urlop> oUrlops = (List<Urlop>) openSession.createQuery("from Urlop where id=:id order by idurlopu desc")
				.setParameter("id", id).setFirstResult(l * (liczba - 1)).setMaxResults(l).list();

		tr.commit();
		openSession.close();
		return oUrlops;
	}

	@Override
	public List<Urlop> getLiczba4(int liczba, int id) {
		// TODO Auto-generated method stub
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 10;
		List<Urlop> oUrlops = (List<Urlop>) openSession.createQuery("from Urlop where id=:id order by idurlopu desc")
				.setParameter("id", id).setFirstResult(l * (liczba - 1)).setMaxResults(l).list();

		tr.commit();
		openSession.close();
		return oUrlops;
	}

	@Override
	public List<Urlop> getAllUrlopAccepted() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 5;
		List<Urlop> oUrlops = (List<Urlop>) openSession.createQuery("from Urlop where status=3 order by idurlopu desc")
				.list();

		tr.commit();
		openSession.close();
		return oUrlops;
	}

	@Override
	public List<Urlop> getLiczbaAccepted(int liczba) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		int l = 5;
		List<Urlop> oUrlops = (List<Urlop>) openSession.createQuery("from Urlop where status=3 order by idurlopu desc")
				.setFirstResult(l * (liczba - 1)).setMaxResults(l).list();

		tr.commit();
		openSession.close();
		return oUrlops;
	}

	@Override
	public List<Czaspracy> getAllCzaspracy() {

		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Czaspracy> cz = (List<Czaspracy>) openSession.createQuery("from Czaspracy order by OgolnaData asc ").list();
		tr.commit();
		openSession.close();

		return cz;
	}

	@Override
	public List<Czaspracy> getCzasPracyUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pracownik> getPracownik() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Pracownik> cz = (List<Pracownik>) openSession.createQuery("from Pracownik order  by title asc ").list();
		tr.commit();
		openSession.close();
		return cz;
	}





	@Override
	public List<Czaspracy> getPracownikGodziny(String rok, String miesiac, String title) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Czaspracy> cza = (List<Czaspracy>) openSession.createQuery("from Czaspracy where Rok=:rok and Miesiac=:miesiac and title=:title  order by title asc ")
		.setParameter("rok", rok)
		.setParameter("miesiac", miesiac)	
		.setParameter("title",title)	
				.list();
		tr.commit();
		openSession.close();		return cza;
	}

	@Override
	public List<Groups> getAllGroups() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Groups> cza = (List<Groups>) openSession.createQuery("from Groups ").list();
	
		tr.commit();
		openSession.close();	
		
		
		return cza;
	}

	@Override
	public List<Final> getBB(Final czas) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Final> cza = (List<Final>) openSession.createQuery("from Final where Name=:name and rok=:rok and miesiac=:miesiac and dzien=:dzien")
		.setParameter("name", czas.getName().replaceAll(":", ""))
		.setParameter("rok", czas.getRok().replaceAll(":", ""))
		.setParameter("miesiac", czas.getMiesiac().replaceAll(":", ""))
		.setParameter("dzien", czas.getDzien().replaceAll(":", ""))
				
				.list();
	
		tr.commit();
		openSession.close();		
		
		return cza;
	}

	@Override
	public List<Final> getFinal(Final czas) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Final> czad = (List<Final>) openSession.createQuery("from Final where name=:name and rok=:rok and miesiac=:miesiac and dzien=:dzien ")
		.setParameter("name", czas.getName().replaceAll(":", ""))		
		.setParameter("rok", czas.getRok().replaceAll(":", ""))		
		.setParameter("miesiac", czas.getMiesiac().replaceAll(":", ""))		
		.setParameter("dzien", czas.getDzien().replaceAll(":", ""))		
				.list();
	
		tr.commit();
		openSession.close();		return czad;
	}

	@Override
	public List<Uusers> getKierownik() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Uusers> cza = (List<Uusers>) openSession.createQuery("from Uusers where dzial='Kierownik' ").list();
	
		tr.commit();
		openSession.close();	
		
		return cza;
	}

	@Override
	public Uusers getEmaill(String kierownik) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
Uusers cza = (Uusers) openSession.createQuery("from Uusers where login=:kierownik ")
.setParameter("kierownik", kierownik)
.uniqueResult();
	
		tr.commit();
		openSession.close();	
		return cza;
	}

	@Override
	public List<Urlop> getallUrlopbyKieronik(String kierownik) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		List<Urlop> cza = (List<Urlop>) openSession.createQuery("from Urlop where kierownik=:kierownik order by idurlopu desc").
				setParameter("kierownik", kierownik).
				list();
	
		tr.commit();
		openSession.close();		return cza;
	}
	

	@Override
	public void setKierownik(Uusers us) {
		Session openSession = ss.session().openSession();
		Transaction tr =openSession.beginTransaction();
		
		List<Urlop> ii=(List<Urlop>)openSession.createQuery("from Urlop where  kierownik1=:kierownik ").
		setParameter("kierownik", us.getLogin()).list();
		
		for(Urlop kk:ii) {
			kk.setKierownik(us.getZastepstwo());
			
			openSession.saveOrUpdate(kk);
		}
		
		
		tr.commit();
		openSession.close();
		
	}
	










}
