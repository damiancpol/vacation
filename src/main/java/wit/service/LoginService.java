package wit.service;

import static org.mockito.Mockito.doCallRealMethod;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.spi.cache.LRUCache;

import wit.dao.RepositoryLoginInterface;
import wit.entities.Final;
import wit.entities.Czaspracy;
import wit.entities.Dzial;
import wit.entities.Groups;
import wit.entities.Kalendarz;
import wit.entities.Pracownik;
import wit.entities.Urlop;
import wit.entities.Uusers;
@Service
public class LoginService implements LoginServiceInterface {
@Autowired
RepositoryLoginInterface rl;
	@Transactional
	public List<Uusers> getAllUsers() {
		// TODO Auto-generated method stub
		return rl.getAllUsers();
	}
	@Transactional
	public Uusers checkUser(Uusers us) {
		// TODO Auto-generated method stub
		return rl.checkuser(us);
	}
	@Transactional
	public void saveDateUser(String data, Uusers sss) {
		rl.saveDateUser(data,sss);
		
	}
	@Transactional
	public List<Kalendarz> getAllFreeDays(Uusers ssj) {
		// TODO Auto-generated method stub
		return rl.getAllFreeDays(ssj);
	}
	
	
	@Transactional
	public void saveUrlop(Urlop up) {
		// TODO Auto-generated method stub
		rl.saveUrlop(up);
	}
	@Transactional
	public Urlop getUrlop(int iduzytkownika) {
		// TODO Auto-generated method stub
		return rl.getUrlop(iduzytkownika);
	}
	@Transactional
	public void deleteWniosek(Urlop up) {
		rl.deleteWniosek(up);
		
	}
	@Transactional
	public List<Urlop> getAllUrlop(int iduzytkownika) {
		// TODO Auto-generated method stub
		return rl.getallUrlop(iduzytkownika);
	}
	@Transactional
	public Urlop getUrlopbyIdUrlopu(int idurlopu) {
		// TODO Auto-generated method stub
		return rl.getUrlopbyIdUrlopu(idurlopu);
	}
	@Transactional
	public void updUrlop(Urlop up) {
		// TODO Auto-generated method stub
		rl.updUrlop(up);
	}
	@Transactional
	public List<Urlop> getAllUsersWithDate() {
		// TODO Auto-generated method stub
		return rl.getAllUsersWithDate();
	}
	@Transactional
	public List<Urlop> getUsers(String dzial) {
		// TODO Auto-generated method stub
		return rl.getUsers(dzial);
	}
	@Transactional
	public void saveOrUpdateUser(Uusers ut) {
		// TODO Auto-generated method stub
		rl.saveOrUpdateUser(ut);
		
	}
	@Transactional
	public Uusers getUser(int id) {
		// TODO Auto-generated method stub
		return rl.getUser(id);
	}
	@Transactional
	public void dodajUzytkownika(Uusers ur) {
		
	rl.dodajUzytkownika(ur);
	}
	@Transactional
	public List<Dzial> getAllDzial() {
		// TODO Auto-generated method stub
		return rl.getAllDzial();
	}
	@Transactional
	public void dodajDzial(Dzial dzial) {
		// TODO Auto-generated method stub
		rl.dodajDzial(dzial);
	}
	@Transactional
	public void deleteDzial(int id) {
		// TODO Auto-generated method stub
		rl.deleteDzial(id);
	}
	@Transactional
	public Dzial getDzial(int id) {
		return rl.getDzial(id);
		
	}
	@Transactional
	public void saveorUpdateDzial(Dzial dd) {
		rl.saveorupdateDzial(dd);
		
	}
	@Transactional
	public List<Urlop> getLiczba(int liczba,String kierownik2) {
		// TODO Auto-generated method stub
		return rl.getLiczba(liczba,kierownik2);
	}
	@Transactional
	public List<Urlop> getLiczba1(int liczba) {
		// TODO Auto-generated method stub
		return rl.getLiczba1(liczba);
	}
	@Transactional
	public List<Urlop> getAllUrlopWithLiczba(int liczba, int id) {
		// TODO Auto-generated method stub
		return rl.getUlropWithLiczba(liczba, id);
	}
	@Transactional
	public List<Urlop> getliczba3(int liczba, int id) {
		// TODO Auto-generated method stub
		return rl.getLiczba3(liczba,id);
	}
	@Transactional
	public List<Urlop> getLiczba4(int liczba,int id) {
		// TODO Auto-generated method stub
		return  rl.getLiczba4(liczba,id);
	}
	@Transactional
	public List<Urlop> getAllUrlopAccepted() {
		// TODO Auto-generated method stub
		return rl.getAllUrlopAccepted();
	}
	@Transactional
	public List<Urlop> getLiczbaAccepted( int liczba) {
		// TODO Auto-generated method stub
		return rl.getLiczbaAccepted(liczba);
	}
	@Transactional
	public List<Czaspracy> getAllCzaspracy() {
		// TODO Auto-generated method stub
		return rl.getAllCzaspracy();
	}
	@Transactional
	public List<Czaspracy> getCzasPracyUsers() {
		// TODO Auto-generated method stub
		return rl.getCzasPracyUsers();
	}
	@Transactional
	public List<Pracownik> getPracownik() {
		// TODO Auto-generated method stub
		return rl.getPracownik();
	}
	@Transactional
	public List<Czaspracy> getPracownikGodziny(String rok, String miesiac, String title) {
		// TODO Auto-generated method stub
		return rl.getPracownikGodziny(rok,miesiac,title);
	}
	@Transactional
	public List<Groups> getAllGroups() {
		// TODO Auto-generated method stub
		return rl.getAllGroups();
	}
	@Transactional
	public List<Final> getBB(Final czas) {
		// TODO Auto-generated method stub
		return rl.getBB(czas);
	}
	@Transactional
	public List<Final> getFinal(Final czas) {
		// TODO Auto-generated method stub
		return rl.getFinal(czas);
	}
	@Transactional
	public List<Uusers> getKierownik() {
		// TODO Auto-generated method stub
		return rl.getKierownik();
	}
	@Transactional
	public Uusers getEmail(String kierownik) {
		// TODO Auto-generated method stub
		return rl.getEmaill(kierownik);
	}
	@Transactional

	public List<Urlop> getallUrlopbyKieronik(String kierownik) {
		// TODO Auto-generated method stub
		return rl.getallUrlopbyKieronik(kierownik);
	}
	@Transactional
	public void setKierownik(Uusers us) {
		rl.setKierownik(us);
		
	}
	

	
	
	

}
;