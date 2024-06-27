package com.springBoot.EwdOlymp;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


import jakarta.servlet.http.HttpServletRequest;

import repository.WedstrijdRepository;
import service.TicketService;
import service.UserService;
import service.WedstrijdService;

@Controller
public class nieuweTicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	WedstrijdRepository wedstrijdRepository;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	WedstrijdService wedstrijdService;

	//PATHVARIABLE GEBRUIKEN
	//SERVICE AANROEPEN
	@GetMapping("/nieuweTicket")
	public String nieuweWedstrijd(@RequestParam int idWedstrijd, Model model, Principal principal) {

		model.addAttribute("wedstrijd", wedstrijdRepository.findById(idWedstrijd).get());
		model.addAttribute("maxPlaatsen", wedstrijdService.maxAantalPerWedstrijd(idWedstrijd, principal.getName()));
		model.addAttribute("idWedstrijd", wedstrijdRepository.findById(idWedstrijd).get().getIdWedstrijd());
		model.addAttribute("gekochteTicketten",
				userService.getAantalTicketsPerIdWedstrijdEnUsername(idWedstrijd, principal.getName()));

		return "TickettenKopen";
	}

	@PostMapping("/nieuweTicket")
	public String nieuweTicket(@RequestParam int idWedstrijd,
			@RequestParam int aantalTickets, Model model, RedirectAttributes redirectAttributes, Principal principal,
			HttpServletRequest request) {

		ticketService.addAantalTicketenToWedstrijd(idWedstrijd, aantalTickets, principal.getName());

		Locale currentLocale = RequestContextUtils.getLocale(request);
		String successMessage = messageSource.getMessage("successMessage", new Object[] { aantalTickets },
				currentLocale);

		redirectAttributes.addFlashAttribute("successMessage", successMessage);

		redirectAttributes.addAttribute("sport", wedstrijdRepository.findById(idWedstrijd).get().getSport().getNaam());

		return "redirect:/wedstrijd";

	}
}
