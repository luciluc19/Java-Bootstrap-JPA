
package com.springBoot.EwdOlymp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Sport;
import domain.Ticket;
import repository.SportRepository;
import repository.TicketRepository;
import repository.UserRepository;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/sport")
public class SportController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	SportRepository sportRepository;




	


	@GetMapping
	public String getAllSports(Model model, Principal principal) {
		
		List<Sport> sports = sportRepository.findAll();
	    List<Ticket> tickettten = ticketRepository.findByUser(userRepository.findByUsername(principal.getName()));
	    
	    model.addAttribute("hasTickets", !tickettten.isEmpty());
	    model.addAttribute("sports", sports);
	    
		return "sporten";
	}
	
	@GetMapping("/mijnTicketten")
	public String getMijnTicketten(Model model, Principal principal) {
	    List<Ticket> tickettten = ticketRepository.findByUser(userRepository.findByUsername(principal.getName()));
	    List<Ticket> sortedTickets = tickettten.stream()
	            .sorted(Comparator.comparing((Ticket t) -> t.getWedstrijd().getSport().getNaam())
	                .thenComparing(t -> t.getWedstrijd().getDatum()))
	            .collect(Collectors.toList());
	    
	    model.addAttribute("tickettten", sortedTickets);
	    model.addAttribute("aantalTicketsInTotaal", tickettten.size());
	    return "mijnTicketten";
	}
}
