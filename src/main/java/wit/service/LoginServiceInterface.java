package wit.service;

import java.util.List;

import wit.entities.Final;
import wit.entities.Czaspracy;
import wit.entities.Dzial;
import wit.entities.Groups;
import wit.entities.Kalendarz;
import wit.entities.Pracownik;
import wit.entities.Urlop;
import wit.entities.Uusers;

public interface LoginServiceInterface {

	List<Uusers> getAllUsers();

	Uusers checkUser(Uusers us);

	void saveDateUser(String data, Uusers sss);

	List<Kalendarz> getAllFreeDays(Uusers ssj);

	void saveUrlop(Urlop up);

	Urlop getUrlop(int iduzytkownika);

	void deleteWniosek(Urlop up);

	List<Urlop> getAllUrlop(int iduzytkownika);

	Urlop getUrlopbyIdUrlopu(int idurlopu);

	void updUrlop(Urlop up);

	List<Urlop> getAllUsersWithDate();

	List<Urlop> getUsers(String dzial);

	void saveOrUpdateUser(Uusers ut);

	Uusers getUser(int id);

	void dodajUzytkownika(Uusers ur);

	List<Dzial> getAllDzial();

	void dodajDzial(Dzial dzial);

	void deleteDzial(int id);

	Dzial getDzial(int id);

	

	void saveorUpdateDzial(Dzial dd);

	List<Urlop> getLiczba(int liczba,String kierownik2);

	List<Urlop> getLiczba1(int liczba);

	

	List<Urlop> getAllUrlopWithLiczba(int liczba, int id);

	List<Urlop> getliczba3(int liczba, int id);

	List<Urlop> getLiczba4(int liczba, int id);

	List<Urlop> getAllUrlopAccepted();

	List<Urlop> getLiczbaAccepted( int liczba);

	List<Czaspracy> getAllCzaspracy();

	List<Czaspracy> getCzasPracyUsers();

	List<Pracownik> getPracownik();

	List<Czaspracy> getPracownikGodziny(String rok, String miesiac, String title);

	List<Groups> getAllGroups();

	List<Final> getBB(Final czas);
List<Final> getFinal(Final czas);

List<Uusers> getKierownik();

Uusers getEmail( String kierownik);

List<Urlop> getallUrlopbyKieronik(String kierownik);
void setKierownik(Uusers us);





	

	



	

}
