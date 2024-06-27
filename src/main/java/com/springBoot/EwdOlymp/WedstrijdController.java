package com.springBoot.EwdOlymp;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Wedstrijd;
import service.UserService;
import service.WedstrijdService;

@Controller
@RequestMapping("/wedstrijd")

public class WedstrijdController {
	

	
	@Autowired
	WedstrijdService wedstrijdService;

	@Autowired
	UserService userService;


	
	@GetMapping
	public String getWedstrijdenPage(@RequestParam("sport") String sportName, Model model, Principal principal ) {
	    List<Wedstrijd> wedstrijden = wedstrijdService.getWedstrijdenBySportNaam(sportName);
	    
	    model.addAttribute("wedstrijden", wedstrijden);
	    model.addAttribute("sportName", sportName);
	    model.addAttribute("userName",  principal.getName());
	    model.addAttribute("isAdministrator", isAdministrator());
	    model.addAttribute("userService", userService);
	    
	    return "wedstrijd";
	}
	
	private boolean isAdministrator() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	        return authentication.getAuthorities().stream()
	                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	    
	}
}
