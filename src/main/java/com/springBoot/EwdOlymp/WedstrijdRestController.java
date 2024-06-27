package com.springBoot.EwdOlymp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Wedstrijd;
import service.WedstrijdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rest")
public class WedstrijdRestController {

    @Autowired
    WedstrijdService wedstrijdService;


    // http://localhost:8080/rest/wedstrijdenBySport/Voetbal

    @GetMapping("/wedstrijdenBySport/{sportnaam}")
    public List<Wedstrijd> getWedstrijdBySport(@PathVariable("sportnaam") String sportnaam) {
        
        return wedstrijdService.getWedstrijdenBySportNaam(sportnaam);

    }

    // http://localhost:8080/rest/AantalPlaatsenByWedstrijdId/1

    @GetMapping("/AantalPlaatsenByWedstrijdId/{idWedstrijd}")
    public int getBeschikbarePlaatsenVanWedstrijd(@PathVariable("idWedstrijd") int idWedstrijd) {
        Wedstrijd w = wedstrijdService.findWedstrijdById(idWedstrijd);
        return w.getCapaciteit() - w.getBezettePlaatsen();

    }
}