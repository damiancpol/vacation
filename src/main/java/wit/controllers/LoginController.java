package wit.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.BadBinaryOpValueExpException;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wit.entities.Final;
import wit.entities.Czaspracy;
import wit.entities.Dzial;
import wit.entities.Groups;
import wit.entities.Kalendarz;
import wit.entities.Pracownik;
import wit.entities.Urlop;
import wit.entities.Uusers;
import wit.service.EmailService;
import wit.service.LoginServiceInterface;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
@Controller
public class LoginController {
	@Autowired
	LoginServiceInterface ls;
	@Autowired
	EmailService emailService;
	Set<String> bz = new HashSet<String>();
	Set<String> bz1 = new HashSet<String>();
	Set<String> bz2 = new HashSet<String>();
	@Autowired
	KierownikControler kr;


	
	//Android---------------------------------------
	
	@GetMapping("alll")
	@ResponseBody
	public List<Uusers> getAlll(){
		List<Uusers> uu=ls.getAllUsers();
	
		
		return uu;
	}
	
	 @PostMapping("updateUser")
	   public void updateUser(@RequestBody Uusers us) {
		 
		 ls.saveOrUpdateUser(us);
		 ls.setKierownik(us);
		 System.out.println(us.getImie()+"-"+us.getNazwisko()+"-"+us.getId()+"-"+us.getZastepstwo()+"-"+us.getLogin());
		 
	 }
	
	
	@ResponseBody
	@PostMapping("konf")
	public Uusers konfiguracja(@RequestBody Uusers uu) {
		
	
		Uusers up = ls.getUser(uu.getId());
	

		return up;
	}

	
	
	@PostMapping("saveurlop")
	public void saveurlop (@RequestBody Urlop up) throws ParseException {
		Uusers user = ls.getUser(up.getId());		
		up.setColor("grey");
		up.setDzial(user.getDzial());
			ls.saveUrlop(up);
              
			
			List<Uusers> iht = ls.getAllUsers();
			for (Uusers hg : iht) {
				if (hg.getPowiadomienie().equals("Tak")) {

					String admin = "Cyjanopex";
					String haslo = "5";
					String encodeBase64 = Base64.encodeBase64URLSafeString(admin.getBytes());
					String encodeBase642 = Base64.encodeBase64URLSafeString(haslo.getBytes());
					String encodeBase643 = Base64.encodeBase64URLSafeString(String.valueOf(up.getIdurlopu()).getBytes());
					String url = "http://localhost:8080/status?lg=" + encodeBase64 + "&bl=" + encodeBase642 + "&lg1="
							+ encodeBase643;
					String url1 = "http://localhost:8080/statusn?lg=" + encodeBase64 + "&bl=" + encodeBase642 + "&lg1="
							+ encodeBase643;

					emailService.sendSimpleMessage(hg.getEmail(), "Urlop witraż system",
							up.getTitle() + " , \nDział:" + up.getDzial() + "\nProsi u udzielenie urlopu od :"
									+ up.getStart() + " do " + up.getEnd() + ",liczba dni wolnych:"
									+ up.getLiczbadniurlopu() + "\nRodzaj wniosku:" + up.getRodzaj() + "\nKomentarz: "
									+ up.getDescription() + "\nZastępstwo: " + up.getZastepstwo()
									+ "\nAby zakceptować kliknij na link " + url

									+ "\nAby odrzucić kliknij na link:" + url1);

			
			
			
			
				};}
	}

	@GetMapping("all")
	@ResponseBody
	public List<String> getAll(){
		List<Uusers> uu=ls.getAllUsers();
		List<String> aa=new ArrayList<>();
		for(Uusers i:uu) {
			
			aa.add(i.getImie()+" "+i.getNazwisko()+"");
			
		}
		
		return aa;
	}
	@PostMapping("checklog")
	@ResponseBody
	public Uusers checklogin(@RequestBody Uusers up) {
		Uusers checkUser = ls.checkUser(up);
		System.out.println(checkUser.getLogin()+"To te imie"+checkUser.getImie());
	
		return checkUser;
		
		
		
	}
	//-----------------------------------------------
	@GetMapping("/")
	public String login(Model model,HttpSession session) {
		
	
		Uusers us = new Uusers();
		model.addAttribute("z", us);
	
		return "tt";
	}

	@ResponseBody
	@GetMapping("test")
	public String Test(String login, String haslo) {
		String imie = login + "" + haslo;

		return imie;
	}

	@PostMapping("checkLogin")

	public String checklogin(@ModelAttribute("z") Uusers us, Model model, HttpSession session)
			throws ParseException, NoSuchAlgorithmException {
		Uusers checkUser = ls.checkUser(us);
		int iduzytkownika = checkUser.getId();
		List<Urlop> allUrlop = ls.getAllUrlop(iduzytkownika);
		bz.clear();
		if (checkUser != null && checkUser.getLogin().equals("Kadry")) {
			model.addAttribute("imie", checkUser.getImie());
			session.setAttribute("uss", checkUser);

			return "redirect:kadry";
		}

		else if (checkUser != null && checkUser.getDzial().equals("Kierownik")) {
			model.addAttribute("imie", checkUser.getImie());
			session.setAttribute("uss", checkUser);
			return "redirect:admin";
		}

		else if (checkUser != null) {
			model.addAttribute("imie", checkUser.getImie());
			session.setAttribute("uss", checkUser);
			model.addAttribute("allUrlop", allUrlop);
			Date dd = new Date();
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = sFormat.format(dd);

			List<Urlop> allUsersWithDate = ls.getAllUsersWithDate();

			for (Urlop o : allUsersWithDate) {
				Date ss = new SimpleDateFormat("yyyy-MM-dd").parse(o.getStart());
				Date ss1 = new SimpleDateFormat("yyyy-MM-dd").parse(o.getEnd());
				List<LocalDate> datesInPeriod = getDatesInPeriod(ss, ss1);

				String string = datesInPeriod.toString();
				model.addAttribute("title", o.getTitle());

				model.addAttribute("string", string);
				model.addAttribute("datesInPeriod", datesInPeriod);
				for (LocalDate su : datesInPeriod) {

					if (su.toString().equals(format)) {

						ArrayList<String> strings = new ArrayList<>();
						strings.add(o.getTitle().toString());

						model.addAttribute("strings", o.getTitle().toString() + "" + su);
						model.addAttribute("su", o.getTitle().toString());
						for (String vv : bz) {

							System.out.println(vv);
						}

						System.out.println(o.getTitle().toString() + "" + su);

						bz.add(o.getTitle() + " -  " + o.getDzial());

						model.addAttribute("bz", bz);
					}
					model.addAttribute("bz", bz);
					session.setAttribute("bzz", bz);
				}

			}
			model.addAttribute("format", format);
			model.addAttribute("allUsersWithDate", allUsersWithDate);
			List<Urlop> allUrlop2 = ls.getAllUrlop(checkUser.getId());
			int size = allUrlop2.size();
			long round = Math.round(((size / 5) + 0.5));
			model.addAttribute("size", round);
			model.addAttribute("allUsersWithDate", allUsersWithDate);

			return "welcome";
		} else {

			model.addAttribute("np", "Nieprawidlowy login lub haslo");
			return "tt";
		}

	}

	@GetMapping("checkLogin1")

	public String checklogin1(Model model, HttpSession session, @RequestParam("liczba") int liczba)
			throws ParseException, NoSuchAlgorithmException {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Urlop> allUrlop = ls.getAllUsersWithDate();
		Uusers ut = (Uusers) session.getAttribute("uss");
		int id = ut.getId();
		// List<Urlop> liczba2 = ls.getAllUrlopWithLiczba(liczba,id);
		List<Urlop> getliczba3 = ls.getliczba3(liczba, id);
		List<Urlop> allUrlop2 = ls.getAllUrlop(ut.getId());
		int size = allUrlop2.size();
		long round = Math.round(((size / 5) + 0.5));
		model.addAttribute("size", round);
		model.addAttribute("liczba2", getliczba3);
		model.addAttribute("ll", liczba);
		}
		return "welcome1";

	}

	@GetMapping("dzien")
	public String selectDay(@RequestParam("liczba") String data, HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers ss = (Uusers) session.getAttribute("uss");
		ls.saveDateUser(data, ss);
		}
		return "redirect:dodajWniosek";
	}

	@GetMapping("kalendarz")
	public String kalendarz(HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		
		Uusers ssj = (Uusers) session.getAttribute("uss");
		List<Kalendarz> allFreeDays = ls.getAllFreeDays(ssj);
		model.addAttribute("allFreeDays", allFreeDays);
		Urlop us = new Urlop();
		model.addAttribute("us", us);
		}
		return "kalendarz";
	}

	@GetMapping("dodajWniosek")
	public String dodajWniosek(Model model, HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		String message = (String) session.getAttribute("ll");
		model.addAttribute("message", message);
		session.removeAttribute("ll");
		Urlop up = new Urlop();
		model.addAttribute("up", up);
		List<Uusers> allUsers = ls.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		Uusers ssj = (Uusers) session.getAttribute("uss");
		List<Kalendarz> allFreeDays = ls.getAllFreeDays(ssj);
		model.addAttribute("allFreeDays", allFreeDays);
		Urlop us = new Urlop();
		model.addAttribute("us", us);
		}
		return "dodajwniosek";
	}

	@PostMapping("dodajWniosek")
	public String dodajWniosek1(Model model, HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Urlop up = new Urlop();
		model.addAttribute("up", up);
		List<Uusers> allUsers = ls.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		Uusers ssj = (Uusers) session.getAttribute("uss");
		List<Kalendarz> allFreeDays = ls.getAllFreeDays(ssj);
		model.addAttribute("allFreeDays", allFreeDays);
		Urlop us = new Urlop();
		model.addAttribute("us", us);
		}
		return "dodajwniosek";
	}

	@PostMapping("saveWniosek")
	public String saveWniosek(@ModelAttribute("up") Urlop up, HttpSession session, Model model) throws ParseException {
		String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(format);
		Date parse2 = new SimpleDateFormat("yyyy-MM-dd").parse(up.getStart());
		Date parse3 = new SimpleDateFormat("yyyy-MM-dd").parse(up.getEnd());
		Uusers ssi = (Uusers) session.getAttribute("uss");
		up.setKierownik(ssi.getKierownik());
		up.setId(ssi.getId());
		up.setDzial(ssi.getDzial());
		String nazwa = ssi.getImie() + " " + ssi.getNazwisko();
		up.setTitle(nazwa);
		if (parse2.before(parse) || parse3.before(parse)) {
			model.addAttribute("dataP", "Wybrana data nie może być datą przeszłą");

			return "dodajwniosek";
		} else {
			up.setColor("grey");
			ls.saveUrlop(up);

		}

		Uusers uusers = (Uusers) session.getAttribute("uss");
		String kierownik = uusers.getKierownik();
		session.setAttribute("kier", kierownik);
		Uusers email = ls.getEmail(kierownik);
		String email2 = email.getEmail();
			session.setAttribute("email2", email2);
		

				String admin = "Cyjanopex";
				String haslo = "5";
				String encodeBase64 = Base64.encodeBase64URLSafeString(admin.getBytes());
				String encodeBase642 = Base64.encodeBase64URLSafeString(haslo.getBytes());
				String encodeBase643 = Base64.encodeBase64URLSafeString(String.valueOf(up.getIdurlopu()).getBytes());
				String url = "http://192.168.1.254:8080/status?lg=" + encodeBase64 + "&bl=" + encodeBase642 + "&lg1="
						+ encodeBase643;
				String url1 = "http://192.168.1.254:8080/statusn?lg=" + encodeBase64 + "&bl=" + encodeBase642 + "&lg1="
						+ encodeBase643;

				emailService.sendSimpleMessage(email2, up.getTitle()+" prosi o udzielenie urlopu",
						up.getTitle() + " , \nDział:" + up.getDzial() + "\nProsi u udzielenie urlopu od :"
								+ up.getStart() + " do " + up.getEnd() + ",liczba dni wolnych:"
								+ up.getLiczbadniurlopu() +"\nLiczba godzin urlopu: "+up.getLiczbagodzinurlopu()+ "\nRodzaj wniosku:" + up.getRodzaj() + "\nKomentarz: "
								+ up.getDescription() + "\nZastępstwo: " + up.getZastepstwo()
								+ "\nAby zakceptować kliknij na link " + url

								+ "\nAby odrzucić kliknij na link:" + url1);
			
		bz2.clear();

		return "redirect:edit";

	}

	@GetMapping("edit")
	public String editUrop(HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers ss = (Uusers) session.getAttribute("uss");
		int iduzytkownika = ss.getId();
		Urlop urlop = ls.getUrlop(iduzytkownika);
		model.addAttribute("urlop", urlop);
		}
		return "edytujwniosek";
	}

	@GetMapping("edytowanie")
	public String edytowanieWniosku(HttpSession session, Model model) {
		
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {Uusers ss = (Uusers) session.getAttribute("uss");
		int id = ss.getId();
		Urlop up = ls.getUrlop(id);
		List<Uusers> allUsers = ls.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("up", up);
		}
		return "edytowanie";
	}

	@GetMapping("usuwanieWniosku")
	public String usuwanieWniosku(HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers ss = (Uusers) session.getAttribute("uss");
		int id = ss.getId();
		Urlop up = ls.getUrlop(id);
		ls.deleteWniosek(up);
		bz.remove(up.getTitle() + " -  " + up.getDzial() + " wolne od " + up.getStart() + " do " + up.getEnd());
		}
		return "wniosekusunieto";
	}

	@GetMapping("sessionDestroy")
	public String sessionDestroy(HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {	session.removeAttribute("uss");
		Uusers us = new Uusers();
		model.addAttribute("z", us);
		}
		return "tt";
	}

	@GetMapping("zobacz")
	public String zobacz(@RequestParam("zob") int idurlopu, Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Urlop urlopbyIdUrlopu = ls.getUrlopbyIdUrlopu(idurlopu);
		model.addAttribute("urlop", urlopbyIdUrlopu);
		}
		return "zobacz";
	}

	@GetMapping("stronaG")
	public String stronaGlowna(HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers uusers = (Uusers) session.getAttribute("uss");
		int iduzytkownika = uusers.getId();
		List<Urlop> allUrlop = ls.getAllUrlop(iduzytkownika);
		Set<String> strings = (Set<String>) session.getAttribute("bzz");
		model.addAttribute("allUrlop", allUrlop);
		model.addAttribute("bz", strings);

		int size = allUrlop.size();
		long round = Math.round(((size / 5) + 0.5));
		model.addAttribute("size", round);
		}
		return "welcome";
	}

	@PostMapping("WniosekUpdate")
	public String WniosekUpdate(@ModelAttribute("up") Urlop up, HttpSession session) {
		
		Uusers uusers = (Uusers) session.getAttribute("uss");
		up.setId(uusers.getId());
		String nazwa = uusers.getImie() + " " + uusers.getNazwisko();
		up.setTitle(nazwa);

		if (nazwa.equals("Damian Cichy")) {
			up.setColor("green");
			ls.updUrlop(up);
		} else if (nazwa.equals("Piotr Staworko")) {

			up.setColor("red");
			ls.updUrlop(up);

		} else if (nazwa.equals("Łukasz Urwanowicz")) {

			up.setColor("yellow");
			ls.updUrlop(up);

		}

		return "redirect:stronaG";
	}

	@GetMapping("planowanie")
	public String nCalendar(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {	Urlop urlop = new Urlop();
		model.addAttribute("urlop", urlop);
		}
		return "planowanie";

	}

	@GetMapping("miesiace")
	public String miesiace(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Urlop ut = new Urlop();
		model.addAttribute("ut", ut);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		}
		return "miesiace";
	}

	@GetMapping("allUsers")
	@ResponseBody
	public List<Urlop> geTallUsers(Model model) {

		List<Urlop> allUsersWithDate = ls.getAllUsersWithDate();

		return allUsersWithDate;

	}

	@PostMapping("getUsers")

	public String getUsers(@ModelAttribute("ut") Urlop uti, HttpSession session, Model model) {
		String dzial = uti.getDzial();
		List<Urlop> users = ls.getUsers(dzial);
		session.setAttribute("users", users);
		Urlop ut = new Urlop();
		model.addAttribute("ut", ut);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		return "miesiace";
	}

	@GetMapping("getUsers")
	@ResponseBody
	public List<Urlop> getUsers1(HttpSession session, Model model) {

		List<Urlop> users = (List<Urlop>) session.getAttribute("users");
		Urlop ut = new Urlop();
		model.addAttribute("ut", ut);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		return users;
	}

	public static LocalDate toLocalDate(Date date) {
		Date lDate = new Date(date.getTime());
		return lDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static List<LocalDate> getDatesInPeriod(Date startDate, Date endDate) {
		List<LocalDate> dates = new ArrayList<>();
		LocalDate start = toLocalDate(startDate);
		LocalDate end = toLocalDate(endDate);
		while (!start.equals(end)) {
			dates.add(start);
			start = start.plusDays(1);
		}
		return dates;
	}

	@GetMapping("listawnioskow")
	public String listawnioskow(HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers ps = (Uusers) session.getAttribute("uss");
		List<Urlop> allUrlop = ls.getAllUrlop(ps.getId());
		model.addAttribute("allUrlop", allUrlop);

		int size = allUrlop.size();
		long round = Math.round(((size / 10) + 0.5));
		model.addAttribute("size", round);
		}
		return "listawnioskow";

	}

	@GetMapping("listawnioskow1")
	public String listawnioskow1(@RequestParam("liczba") int liczba, HttpSession session, Model model) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers ps = (Uusers) session.getAttribute("uss");
		int id = ps.getId();
		List<Urlop> allUrlop = ls.getAllUrlop(id);

		List<Urlop> liczba2 = ls.getLiczba4(liczba, id);
		int size = allUrlop.size();
		long round = Math.round(((size / 10) + 0.5));
		model.addAttribute("size", round);
		model.addAttribute("liczba2", liczba2);
		model.addAttribute("ll", liczba);
		}
		return "listawnioskow1";

	}

	@GetMapping("status")
	public String status(@RequestParam("lg") String login, @RequestParam("bl") String haslo,
			@RequestParam("lg1") String idurlopu, Model model, HttpSession session) throws NumberFormatException, InterruptedException {
String email=(String)session.getAttribute("email2");
session.setAttribute("email", email);
		byte[] decodeBase64 = Base64.decodeBase64(login);
		byte[] decodeBase642 = Base64.decodeBase64(haslo);
		byte[] decodeBase643 = Base64.decodeBase64(idurlopu);

		String string = new String(decodeBase64);
		String string2 = new String(decodeBase642);
		String string3 = new String(decodeBase643);
		Uusers us = (Uusers) session.getAttribute("uss");
		Urlop up = ls.getUrlopbyIdUrlopu(Integer.valueOf(string3));
		int id = up.getId();
		Uusers user = ls.getUser(id);
		if (string.equals("Cyjanopex") && string2.equals("5")) {
		
			
			
			
			
			kr.zatwierdz(Integer.valueOf(string3));
			
			session.setAttribute("h", "Wniosek został zaakceptowany");
			return "status";
		}

		return "status";

	}

	@GetMapping("statusn")
	public String statusn(@RequestParam("lg") String login, @RequestParam("bl") String haslo,
			@RequestParam("lg1") String idurlopu, Model model, HttpSession session) {

		byte[] decodeBase64 = Base64.decodeBase64(login);
		byte[] decodeBase642 = Base64.decodeBase64(haslo);
		byte[] decodeBase643 = Base64.decodeBase64(idurlopu);

		String string = new String(decodeBase64);
		String string2 = new String(decodeBase642);
		String string3 = new String(decodeBase643);
		Uusers us = (Uusers) session.getAttribute("uss");
		Urlop up = ls.getUrlopbyIdUrlopu(Integer.valueOf(string3));
		int id = up.getId();
		Uusers user = ls.getUser(id);
		if (string.equals("Cyjanopex") && string2.equals("5")) {




			kr.odrzuc(Integer.valueOf(string3));
			session.setAttribute("h", "Wniosek został odrzucony");
			return "status";
		}

		return "status";

	}

	@GetMapping("czaspracy")
	public String czaspracy(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Czaspracy> allCzaspracy = ls.getAllCzaspracy();
		model.addAttribute("allCzaspracy", allCzaspracy);
		List<Pracownik> pracownik = ls.getPracownik();
		model.addAttribute("pracownik", pracownik);
		Czaspracy czaspracy = new Czaspracy();
		model.addAttribute("czaspracy", czaspracy);
		}
		return "czaspracy";
	}

	@PostMapping("wyswietlPracownika")
	public String wyswietlCzaspracy(@ModelAttribute("czaspracy") Czaspracy czas, Model model) {
		if (czas.getMiesiac().equals("Styczeń")) {

			czas.setMiesiac("1");

		} else if (czas.getMiesiac().equals("Luty")) {

			czas.setMiesiac("2");

		}

		else if (czas.getMiesiac().equals("Marzec")) {

			czas.setMiesiac("3");

		}

		else if (czas.getMiesiac().equals("Kwiecień")) {

			czas.setMiesiac("4");

		}

		else if (czas.getMiesiac().equals("Maj")) {

			czas.setMiesiac("5");

		}

		else if (czas.getMiesiac().equals("Czerwiec")) {

			czas.setMiesiac("6");

		}

		else if (czas.getMiesiac().equals("Lipiec")) {

			czas.setMiesiac("7");

		}

		else if (czas.getMiesiac().equals("Sierpień")) {

			czas.setMiesiac("8");

		}

		else if (czas.getMiesiac().equals("Wrzesień")) {

			czas.setMiesiac("9");

		}

		else if (czas.getMiesiac().equals("Pazdziernik")) {

			czas.setMiesiac("10");

		}

		else if (czas.getMiesiac().equals("Listopad")) {

			czas.setMiesiac("11");

		} else if (czas.getMiesiac().equals("Grudzień")) {

			czas.setMiesiac("12");

		}

		String rok = czas.getRok();
		String miesiac = czas.getMiesiac();
		String title = czas.getTitle();
		List<Czaspracy> pracownikGodziny = ls.getPracownikGodziny(rok, miesiac, title);

		model.addAttribute("pracownikGodziny", pracownikGodziny);
		model.addAttribute("title", title);

		return "czaspracy1";

	}

	@GetMapping("Grupa")
	public String Grupa(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {Final bb = new Final();
		model.addAttribute("BB", bb);
		}
		return "czaspracyWszyscy";
	}

	@PostMapping("wybranyDzial")
	public String wybranyDzial(@ModelAttribute("BB") Final czas,Model model,HttpSession session) throws ParseException {
		if (czas.getMiesiac().equals("Styczeń")) {

			czas.setMiesiac("1");

		} else if (czas.getMiesiac().equals("Luty")) {

			czas.setMiesiac("2");

		}

		else if (czas.getMiesiac().equals("Marzec")) {

			czas.setMiesiac("3");

		}

		else if (czas.getMiesiac().equals("Kwiecień")) {

			czas.setMiesiac("4");

		}

		else if (czas.getMiesiac().equals("Maj")) {

			czas.setMiesiac("5");

		}

		else if (czas.getMiesiac().equals("Czerwiec")) {

			czas.setMiesiac("6");

		}

		else if (czas.getMiesiac().equals("Lipiec")) {

			czas.setMiesiac("7");

		}

		else if (czas.getMiesiac().equals("Sierpień")) {

			czas.setMiesiac("8");

		}

		else if (czas.getMiesiac().equals("Wrzesień")) {

			czas.setMiesiac("9");

		}

		else if (czas.getMiesiac().equals("Pazdziernik")) {

			czas.setMiesiac("10");

		}

		else if (czas.getMiesiac().equals("Listopad")) {

			czas.setMiesiac("11");

		} else if (czas.getMiesiac().equals("Grudzień")) {

			czas.setMiesiac("12");

		}
		

		List<Final> bbo = ls.getFinal(czas);
		for(Final ff:bbo) {
			
			
			String d1 = ff.getD1();
			String d2 = ff.getD2();
			SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			Date parse = sFormat.parse(d1);
			Date parse2 = sFormat.parse(d2);
			
			DateTime dt1 = new DateTime(parse);
			DateTime dt2 = new DateTime(parse2);
			
			System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			ff.setRoznica(Hours.hoursBetween(dt1, dt2).getHours() % 24 + ":"+Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 );
			model.addAttribute("zas", bbo);
		}
		

		return "czaspracyWszyscy1";

	}

}
