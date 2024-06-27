package com.springBoot.EwdOlymp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Stadium;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import repository.StadiumRepository;


@Getter
@Setter
public class NieuweWedstrijdValidator {
	
	@Autowired
	private StadiumRepository stadiumRepository;

    private LocalTime aanvangsuur;
    
    private LocalDate datum;
    
	@Pattern(regexp = "^(?!0)(?!([0-9])\\d*\\1$)[1-9][0-9]{3}[0-9]$", message = "{Validation.regex}")
	private String olympischNummer1;
    
    private String olympischNummer2;
    
    private String discipline1;
    
    private String discipline2;
    
    @NotNull
    @DecimalMin(value = "0.01", message = "{Validation.TicketMin}")
    @DecimalMax(value = "149.99", message = "{Validation.TicketMax}")
    private double ticketPrijs = 0;
    
    
	@NotNull
	@Min(value = 1, message = "{Validation.plaatsMin}")
	@Max(value = 49, message = "{Validation.plaatsMax}")
	private int aantalPlaatsen = 0;
	
	private String sportNaam;
    
    
    
    
    private List<Stadium> stadiums;
    
    private int idStadium;

}
