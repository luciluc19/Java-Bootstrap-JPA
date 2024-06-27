package com.springBoot.EwdOlymp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import domain.Wedstrijd;
import jakarta.validation.Valid;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.WedstrijdRepository;
import service.WedstrijdService;
import validator.NieuweWedstrijdValidation;

@Controller
@RequestMapping("/nieuweWedstrijd")
public class NieuweWedstrijdController {
	
	@Autowired
	StadiumRepository stadiumRepository;
	
	@Autowired
	NieuweWedstrijdValidation nieuweWedstrijdValidation;
	
	@Autowired
	DisciplineRepository disciplineRepository;
	
	@Autowired
	SportRepository sportRepository;
	
	@Autowired
	WedstrijdRepository wedstrijdRepository;

	@Autowired
	WedstrijdService wedstrijdService;
	

	

    @GetMapping
    public String nieuweWedstrijd(@RequestParam String sport,Model model) {
    	

        model.addAttribute("wedstrijd", new Wedstrijd());        
        model.addAttribute("stadiums", stadiumRepository.findAll());
        model.addAttribute("nieuweWedstrijd", new NieuweWedstrijdValidator());
        model.addAttribute("sportNaam", sport);
       
        return "nieuweWedstrijdForm";
    }
    
    @PostMapping
    public String verwerkNieuweWedstrijd(@Valid @ModelAttribute("nieuweWedstrijd") NieuweWedstrijdValidator nieuweWedstrijd, BindingResult bindingResult, 
    		Model model, @RequestParam String sport, RedirectAttributes redirectAttributes) {
    		nieuweWedstrijdValidation.validate(nieuweWedstrijd, bindingResult);
    	if (bindingResult.hasErrors()) {
            nieuweWedstrijd.setStadiums(stadiumRepository.findAll());
            model.addAttribute("stadiums", stadiumRepository.findAll());
            model.addAttribute("sportNaam", sport);
            return "nieuweWedstrijdForm";
        }

		wedstrijdService.addWedstrijdByUsernameEnWedstrijdIdEnWedstrijdObjectEnSportnaam(nieuweWedstrijd.getAantalPlaatsen(), "admin", nieuweWedstrijd.getIdStadium(), nieuweWedstrijd, sport);
		
        redirectAttributes.addAttribute("sport", sport);
        return "redirect:/wedstrijd";
    }
}
