package wit.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wit.entities.Dzial;
import wit.entities.Urlop;
import wit.entities.Uusers;
import wit.service.EmailService;
import wit.service.LoginServiceInterface;
import java.util.*;
@Controller
public class KierownikControler {
	@Autowired
	LoginServiceInterface ls;
	Set<String> bz = new HashSet<String>();
	@Autowired
	EmailService emailService;

	@GetMapping("admin")
	public String Kwelcome(Model model, HttpSession session) throws ParseException {

		//List<Urlop> allUrlop = ls.getAllUsersWithDate();
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
	Uusers us=(Uusers)session.getAttribute("uss");
	String kierownik = us.getLogin();
List<Urlop> allUrlop = ls.getallUrlopbyKieronik(kierownik);
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
		int size = allUrlop.size();
		long round = Math.round(((size / 5) + 0.5));
		model.addAttribute("size", round);
		}
		return "kwelcome";
	}

	@GetMapping("admin1")
	public String Kwelcome1(Model model, HttpSession session, @RequestParam("liczba") int liczba)
			throws ParseException {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers us=(Uusers)session.getAttribute("uss");
		String kierownik2 = us.getLogin();
		String kierownik = kierownik2;
		List<Urlop> allUrlop = ls.getallUrlopbyKieronik(kierownik);
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
		us.getKierownik();
		List<Urlop> liczba2 = ls.getLiczba(liczba,kierownik2);
		int size = allUrlop.size();
		long round = Math.round(((size / 5) + 0.5));
		model.addAttribute("size", round);
		model.addAttribute("liczba2", liczba2);
		model.addAttribute("ll", liczba);
		}
		return "kwelcome1";
	}

	@GetMapping("kzobacz")
	public String kzobacz(@RequestParam("zob") int idurlopu, Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Urlop urlopbyIdUrlopu = ls.getUrlopbyIdUrlopu(idurlopu);
		model.addAttribute("urlop", urlopbyIdUrlopu);
		}
		return "kzobacz";

	}

	@GetMapping("kstronaG")
	public String kstrongaG() {

		return "redirect:admin";
	}

	@GetMapping("zatwierdz")
	public String zatwierdz(@RequestParam("zatw") int idurlopu) throws InterruptedException {
		Urlop up = ls.getUrlopbyIdUrlopu(idurlopu);
		Urlop urlopbyIdUrlopu = up;
		urlopbyIdUrlopu.setStatus(3);
		ls.saveUrlop(urlopbyIdUrlopu);
		String nazwa = urlopbyIdUrlopu.getTitle();
		
		if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {
			up.setColor("green");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("blue");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("silver");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("#800000");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("blue");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("silver");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("#800000");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("blue");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("silver");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("#800000");
			ls.saveUrlop(up);
		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);


		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("blue");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("silver");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("red");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("#800000");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("#800000");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("orange");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("brown");
			ls.saveUrlop(up);

		} else if (nazwa.equals("Name and surname for Calendar color in JavaScript ")) {

			up.setColor("pink");
			ls.saveUrlop(up);

		}else {up.setColor("Name and surname for Calendar color in JavaScript ");
		ls.saveUrlop(up);
		
		}
		ls.getUrlopbyIdUrlopu(idurlopu);
Uusers user = ls.getUser(1);
int iduzytkownika=up.getId(); 
Uusers uw=ls.getUser(iduzytkownika);


emailService.sendSimpleMessage(user.getEmail(), "Urlop został zaakceptowany przez "+up.getKierownik(),
			
				up.getTitle() + " , \nDział:" + up.getDzial() + "\nProsi u udzielenie urlopu od :" + up.getStart()
						+ " do " + up.getEnd() + ",liczba dni wolnych:" + up.getLiczbadniurlopu() +"\nLiczba godzin urlopu: "+up.getLiczbagodzinurlopu()+ "\nRodzaj wniosku:"
						+ up.getRodzaj() + "\nKomentarz: " + up.getDescription() + "\nZastępstwo: "
						+ up.getZastepstwo());
Uusers user2 = ls.getUser(up.getId());	


		emailService.sendSimpleMessage(user2.getEmail(), "Urlop został zaakceptowany przez "+up.getKierownik(),
				
				up.getTitle() + " , \nDział:" + up.getDzial() + "\nProsi u udzielenie urlopu od :" + up.getStart()
						+ " do " + up.getEnd() + ",liczba dni wolnych:" + up.getLiczbadniurlopu()+"\nLiczba godzin urlopu: "+up.getLiczbagodzinurlopu() + "\nRodzaj wniosku:"
						+ up.getRodzaj() + "\nKomentarz: " + up.getDescription() + "\nZastępstwo: "
						+ up.getZastepstwo());
		
		
	

		return "redirect:admin";
	}

	@GetMapping("odrzuc")
	public String odrzuc(@RequestParam("zatw1") int idurlopu) {
		Urlop urlopbyIdUrlopu = ls.getUrlopbyIdUrlopu(idurlopu);
		urlopbyIdUrlopu.setStatus(2);
		ls.saveUrlop(urlopbyIdUrlopu);
		Uusers user2 = ls.getUser(urlopbyIdUrlopu.getId());
         emailService.sendSimpleMessage(user2.getEmail(), "Wniosek został odrzucony przez "+urlopbyIdUrlopu.getKierownik(),
				
        		 urlopbyIdUrlopu.getTitle() + " , \nDział:" + urlopbyIdUrlopu.getDzial() + "\nProsi u udzielenie urlopu od :" + urlopbyIdUrlopu.getStart()
						+ " do " + urlopbyIdUrlopu.getEnd() + ",liczba dni wolnych:" + urlopbyIdUrlopu.getLiczbadniurlopu()+"\nLiczba godzin urlopu: "+urlopbyIdUrlopu.getLiczbagodzinurlopu() + "\nRodzaj wniosku:"
						+ urlopbyIdUrlopu.getRodzaj() + "\nKomentarz: " + urlopbyIdUrlopu.getDescription() + "\nZastępstwo: "
						+ urlopbyIdUrlopu.getZastepstwo());
		
		return "redirect:admin";
	}

	@GetMapping("klistawnioskow")
	public String klistawnioskow(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Urlop> allUrlop = ls.getAllUsersWithDate();
		model.addAttribute("allUrlop", allUrlop);
		List<Urlop> allUsersWithDate = ls.getAllUsersWithDate();
		int size = allUsersWithDate.size();
		long round = Math.round(((size / 10) + 0.5));
		model.addAttribute("size", round);
		}
		return "klistawnioskow";
	}
	@GetMapping("kklistawnioskow")
	public String kklistawnioskow(Model model,@RequestParam("ido")int id,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
	
		Urlop urlopbyIdUrlopu = ls.getUrlopbyIdUrlopu(id);
		ls.deleteWniosek(urlopbyIdUrlopu);
		}
		return "redirect:klistawnioskow";
	}


	@GetMapping("klistawnioskow1")
	public String klistawnioskow1(Model model, @RequestParam("liczba") int liczba,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Urlop> allUrlop = ls.getAllUsersWithDate();
		model.addAttribute("allUrlop", allUrlop);
		List<Urlop> liczba2 = ls.getLiczba1(liczba);
		int size = allUrlop.size();
		long round = Math.round(((size / 10) + 0.5));
		model.addAttribute("size", round);
		model.addAttribute("liczba2", liczba2);
		model.addAttribute("ll", liczba);
		}
		return "klistawnioskow1";
	}

	@GetMapping("konfiguracja")
	public String konfiguracja(Model model, HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers po = (Uusers) session.getAttribute("uss");
		int id = po.getId();
		Uusers up = ls.getUser(id);
		model.addAttribute("usz", up);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		}
		return "konfiguracja";
	}

	@PostMapping("konfiguracja1")
	public String konfiguracja1(@ModelAttribute("usz") Uusers ut,Model model,HttpSession session) {
		
			ls.saveOrUpdateUser(ut);
			List<Dzial> allDzial = ls.getAllDzial();
			model.addAttribute("allDzial", allDzial);
		
		

		return "konfiguracja";
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

}
