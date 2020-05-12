package wit.controllers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.Http11InputBuffer;
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
import wit.service.LoginServiceInterface;

@Controller
public class KadryControler {
	@Autowired
	LoginServiceInterface ls;
	Set<String> bz = new HashSet<String>();
	@GetMapping("kadry")
	public String KadryWelcome(Model model,HttpSession session) throws ParseException {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Urlop> allUrlop = ls.getAllUsersWithDate();
		model.addAttribute("allUrlop", allUrlop);
        List<Urlop> allUrlopAccepted = ls.getAllUrlopAccepted();
        model.addAttribute("allUrlopAccepted",allUrlopAccepted);
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
		
		int size = allUrlopAccepted.size();
         long round = Math.round(((size/5)+0.5));
		model.addAttribute("size", round);
		
		
		
		
		
		}
		
		
		
		
		
		return "kadry";
	}
	
	@GetMapping("kadry1")
	public String KadryWelcome1(Model model,HttpSession session,@RequestParam("liczba") int liczba) throws ParseException {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		List<Urlop> allUrlop = ls.getAllUsersWithDate();
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
		//List<Urlop> liczba2 = ls.getLiczba(liczba);
		   List<Urlop> allUrlopAccepted = ls.getAllUrlopAccepted();
		List<Urlop> liczba2 = ls.getLiczbaAccepted(liczba);
		int size = allUrlopAccepted.size();
         long round = Math.round(((size/5)+0.5));
		model.addAttribute("size", round);
		model.addAttribute("liczba2", liczba2);
		model.addAttribute("ll", liczba);
		
		
		
		
		
		
		
		}
		
		
		
		return "kadry1";
	}
	
	
	
	@GetMapping("dodajpracownika")
	public String dodajpracownika(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Uusers uusers=new Uusers();
		
		model.addAttribute("ur", uusers);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		List<Uusers> kierownik = ls.getKierownik();
		model.addAttribute("kierownik",kierownik);
		}
		return "dodajpracownika";
	}
	
	
	@PostMapping("dodanoUzytkownika")
public String dodanouzytkownika(@ModelAttribute("ur")Uusers ur,@ModelAttribute("ur")Uusers rr,Model model) {
		ls.dodajUzytkownika(ur);
		List<Uusers> kierownik = ls.getKierownik();
		model.addAttribute("kierownik",kierownik);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		model.addAttribute("ur", rr);
		
		
		return "dodajpracownika";
	}	
	
	
	@GetMapping("dodajdzial")
	public String dodajdzial(Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Dzial dzial=new Dzial();
		model.addAttribute("dzial", dzial);
		List<Dzial> allDzial = ls.getAllDzial();
		model.addAttribute("allDzial", allDzial);
		}
		return "dodajdzial";
	}
	@PostMapping("dodanodzial")
	public String dodanodzial(@ModelAttribute("dzial")Dzial dzial) {
		
		ls.dodajDzial(dzial);
		return "redirect:dodajdzial";
	}
	
	@GetMapping("usunDzial")
public String usundzial(@RequestParam("usundzial")int id,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		ls.deleteDzial(id);
		}
		return "redirect:dodajdzial";
	}
	@GetMapping("edytujDzial")
	public String edytujDzial(@RequestParam("edytujDzial")int id,Model model,HttpSession session) {
		Uusers sw=(Uusers)session.getAttribute("uss");
		if(sw==null) { return "redirect:/"; }else {
		Dzial dzial = ls.getDzial(id);
		model.addAttribute("dzial", dzial);
		}
		return "edytujDzial";
	}
	
	@PostMapping("updateDzial")
	public String updateDzial(@ModelAttribute("dzial")Dzial dd) {
		ls.saveorUpdateDzial(dd);
		
		return "redirect:dodajdzial";
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
