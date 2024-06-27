package com.springBoot.EwdOlymp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domain.Discipline;
import domain.Sport;
import domain.Stadium;
import domain.Ticket;
import domain.User;
import domain.Wedstrijd;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import repository.WedstrijdRepository;;

@Component
public class InitDataConfig implements CommandLineRunner {
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserRepository userRepository;

	@Autowired
	WedstrijdRepository wedstrijdRepository;

	@Autowired
	SportRepository sportRepository;
	
	@Autowired
	StadiumRepository stadiumRepository;

	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	DisciplineRepository disciplineRepository;
	

	@Override
	public void run(String... args) {

		User user1 = new User(1, "gebruiker1", passwordEncoder.encode("123"), true,
				Collections.singletonList("ROLE_USER"));

		User user2 = new User(2, "admin1", passwordEncoder.encode("123"), true,
				Collections.singletonList("ROLE_ADMIN"));
		
		User user3 = new User(3, "gebruiker2", passwordEncoder.encode("123"), true,
				Collections.singletonList("ROLE_USER"));

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		



//

		Sport voetbal = new Sport(1, "Voetbal",
				"https://oneftbl-cms.imgix.net/https%3A%2F%2Ffootballtoday.com%2Fwp-content%2Fuploads%2F2023%2F03%2Fssc-napoli-v-atalanta-bc-serie-a-2-scaled.jpg?auto=format%2Ccompress&crop=faces&dpr=2&fit=crop&h=210&q=25&w=280&s=91dc2d6e4a69c53f8e086210d94bcc39",
				new ArrayList<>());

		Sport tennis = new Sport(2, "Tennis",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpCIMCSHGPo3PA2A7TO5uoGl-T4_j9UU8xig&usqp=CAU",
				new ArrayList<>());

		Sport armworstelen = new Sport(3, "Armworstelen", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcST4Ii7b4fuKdKgn4tiSuGwfu9c7O78B2_CjMtMf5j5eQ&s",
				new ArrayList<>());

		Sport worstelen = new Sport(4, "Worstelen",
				"https://64.media.tumblr.com/ed8f210364dc70e5f742525953133d4c/tumblr_p425bqXmj81wb3ayqo6_1280.jpg",
				new ArrayList<>());

		Sport basketbal = new Sport(5, "Basketbal",
				"https://www.basketbal.vlaanderen/files/_1200x590_crop_center-center_none/SPURSDONZA.jpg",
				new ArrayList<>());
		
		sportRepository.save(voetbal);
		sportRepository.save(tennis);
		sportRepository.save(armworstelen);
		sportRepository.save(worstelen);
		sportRepository.save(basketbal);
		
        
		
		Wedstrijd wedstrijd1 = new Wedstrijd(1, LocalDate.of(2024, 7, 8), LocalTime.of(20, 0), 10.0, voetbal,10, new HashSet<Discipline>(), 12345, 23456, 10 );
		Wedstrijd wedstrijd2 = new Wedstrijd(2, LocalDate.of(2024, 8, 8), LocalTime.of(11, 0), 21.0, voetbal, 0, new HashSet<Discipline>(), 34567, 45678, 21);
		Wedstrijd wedstrijd3 = new Wedstrijd(3, LocalDate.of(2024, 9, 8), LocalTime.of(12, 0), 12.0, tennis, 0, new HashSet<Discipline>(), 56789, 67890, 12);
		Wedstrijd wedstrijd4 = new Wedstrijd(4, LocalDate.of(2024, 10, 8), LocalTime.of(13, 0), 13.0, tennis, 0, new HashSet<Discipline>(), 67890, 78901, 13);
		Wedstrijd wedstrijd5 = new Wedstrijd(5, LocalDate.of(2024, 11, 8), LocalTime.of(14, 0), 14.0, armworstelen, 0, new HashSet<Discipline>(), 78901, 89012, 14);
		Wedstrijd wedstrijd6 = new Wedstrijd(6, LocalDate.of(2024, 12, 8), LocalTime.of(15, 0), 15.0, armworstelen, 0, new HashSet<Discipline>(), 89012, 90123, 15);
		Wedstrijd wedstrijd7 = new Wedstrijd(7, LocalDate.of(2024, 1, 8), LocalTime.of(16, 0), 16.0, worstelen, 0, new HashSet<Discipline>(), 90123, 12345, 16);
		Wedstrijd wedstrijd8 = new Wedstrijd(8, LocalDate.of(2024, 2, 8), LocalTime.of(17, 0), 17.0, worstelen, 0, new HashSet<Discipline>(), 23456, 34567, 17);
		Wedstrijd wedstrijd9 = new Wedstrijd(9, LocalDate.of(2024, 3, 8), LocalTime.of(18, 0), 18.0, basketbal, 0, new HashSet<Discipline>(), 45678, 56789, 18);
		Wedstrijd wedstrijd10 = new Wedstrijd(10, LocalDate.of(2024, 4, 8), LocalTime.of(19, 0), 19.0, basketbal, 0, new HashSet<Discipline>(), 67890, 78901, 19);
		

		wedstrijdRepository.save(wedstrijd1);
		wedstrijdRepository.save(wedstrijd2);
		wedstrijdRepository.save(wedstrijd3);
		wedstrijdRepository.save(wedstrijd4);
		wedstrijdRepository.save(wedstrijd5);
		wedstrijdRepository.save(wedstrijd6);
		wedstrijdRepository.save(wedstrijd7);
		wedstrijdRepository.save(wedstrijd8);
		wedstrijdRepository.save(wedstrijd9);
		wedstrijdRepository.save(wedstrijd10);
			
		Stadium stadium1 = new Stadium(1, "Stadium1", new ArrayList<>());
		Stadium stadium2 = new Stadium(2, "Stadium2", new ArrayList<>());
		Stadium stadium3 = new Stadium(3, "Stadium3", new ArrayList<>());
		Stadium stadium4 = new Stadium(4, "Stadium4", new ArrayList<>());
		Stadium stadium5 = new Stadium(5, "Stadium5", new ArrayList<>());
		

		
		stadium1.addWedstrijd(wedstrijd1);
		stadium1.addWedstrijd(wedstrijd2);
		stadium2.addWedstrijd(wedstrijd3);
		stadium2.addWedstrijd(wedstrijd4);
		stadium3.addWedstrijd(wedstrijd5);
		stadium3.addWedstrijd(wedstrijd6);
		stadium4.addWedstrijd(wedstrijd7);
		stadium4.addWedstrijd(wedstrijd8);
		stadium5.addWedstrijd(wedstrijd9);
		stadium5.addWedstrijd(wedstrijd10);
		
		stadiumRepository.save(stadium1);
		stadiumRepository.save(stadium2);
		stadiumRepository.save(stadium3);
		stadiumRepository.save(stadium4);
		stadiumRepository.save(stadium5);
		
		wedstrijdRepository.save(wedstrijd1);
		wedstrijdRepository.save(wedstrijd2);
		wedstrijdRepository.save(wedstrijd3);
		wedstrijdRepository.save(wedstrijd4);
		wedstrijdRepository.save(wedstrijd5);
		wedstrijdRepository.save(wedstrijd6);
		wedstrijdRepository.save(wedstrijd7);
		wedstrijdRepository.save(wedstrijd8);
		wedstrijdRepository.save(wedstrijd9);
		wedstrijdRepository.save(wedstrijd10);
		
		
		Ticket ticket1 = new Ticket(1, user1, wedstrijd1);
		Ticket ticket2 = new Ticket(2, user1, wedstrijd2);
		Ticket ticket3 = new Ticket(3, user1, wedstrijd3);
		Ticket ticket4 = new Ticket(4, user1, wedstrijd4);
		Ticket ticket5 = new Ticket(5, user1, wedstrijd5);
		Ticket ticket6 = new Ticket(6, user1, wedstrijd6);
		Ticket ticket7 = new Ticket(7, user1, wedstrijd7);
		Ticket ticket8 = new Ticket(8, user1, wedstrijd8);
		Ticket ticket9 = new Ticket(9, user1, wedstrijd9);
		Ticket ticket10 = new Ticket(10, user1, wedstrijd10);
		
		
		
		ticketRepository.save(ticket1);
		ticketRepository.save(ticket2);
		ticketRepository.save(ticket3);
		ticketRepository.save(ticket4);
		ticketRepository.save(ticket5);
		ticketRepository.save(ticket6);
		ticketRepository.save(ticket7);
		ticketRepository.save(ticket8);
		ticketRepository.save(ticket9);
		ticketRepository.save(ticket10);
		
		
		Discipline discipline1 = new Discipline(1, "Zand voetbal");
		Discipline discipline2 = new Discipline(2, "UEFA voetbal");
		Discipline discipline3 = new Discipline(3, "Roland Garros");
		Discipline discipline4 = new Discipline(4, "Wimbledon");
		Discipline discipline5 = new Discipline(5, "Anything goes");
		Discipline discipline6 = new Discipline(6, "Official rules");
		Discipline discipline7 = new Discipline(7, "Freestyle");
		Discipline discipline8 = new Discipline(8, "Greko");
		Discipline discipline9 = new Discipline(9, "Streetball");
		Discipline discipline10 = new Discipline(10, "NBA");
		Discipline discipline11 = new Discipline(11, "Straat voetbal");
		
		disciplineRepository.save(discipline1);
		disciplineRepository.save(discipline2);
		disciplineRepository.save(discipline3);
		disciplineRepository.save(discipline4);
		disciplineRepository.save(discipline5);
		disciplineRepository.save(discipline6);
		disciplineRepository.save(discipline7);
		disciplineRepository.save(discipline8);
		disciplineRepository.save(discipline9);
		disciplineRepository.save(discipline10);
		disciplineRepository.save(discipline11);
		
		wedstrijd1.addDiscipline(discipline1);
		wedstrijd1.addDiscipline(discipline11);
		wedstrijd2.addDiscipline(discipline2);
		wedstrijd3.addDiscipline(discipline3);
		wedstrijd4.addDiscipline(discipline4);
		wedstrijd5.addDiscipline(discipline5);
		wedstrijd6.addDiscipline(discipline6);
		wedstrijd7.addDiscipline(discipline7);
		wedstrijd8.addDiscipline(discipline8);
		wedstrijd9.addDiscipline(discipline9);
		wedstrijd10.addDiscipline(discipline10);
		
		
		wedstrijdRepository.save(wedstrijd1);
		wedstrijdRepository.save(wedstrijd2);
		wedstrijdRepository.save(wedstrijd3);
		wedstrijdRepository.save(wedstrijd4);
		wedstrijdRepository.save(wedstrijd5);
		wedstrijdRepository.save(wedstrijd6);
		wedstrijdRepository.save(wedstrijd7);
		wedstrijdRepository.save(wedstrijd8);
		wedstrijdRepository.save(wedstrijd9);
		wedstrijdRepository.save(wedstrijd10);

	}

}
