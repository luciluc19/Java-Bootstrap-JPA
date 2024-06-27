package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.EwdOlymp.NieuweWedstrijdValidator;

import domain.Discipline;
import domain.Sport;
import domain.Stadium;
import domain.Wedstrijd;
import exception.SportNietGevondenException;
import exception.WedstrijdIdNietGevondenException;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import repository.WedstrijdRepository;

@Service("wedstrijdService")
public class WedstrijdServiceImpl implements WedstrijdService {

	@Autowired
	SportRepository sportRepository;

	@Autowired
	WedstrijdRepository wedstrijdRepository;

	@Autowired
	DisciplineRepository disciplineRepository;

	@Autowired
	StadiumRepository stadiumRepository;

	@Autowired
	UserService userService;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Wedstrijd> getWedstrijdenBySportNaam(String sportName) {
		Sport sport = sportRepository.findByNaam(sportName);
		if (sport == null) {
			throw new SportNietGevondenException(sportName);
		}
		return wedstrijdRepository.findAllBySport(sport);
	}

	@Override
	public Wedstrijd findWedstrijdById(int idWedstrijd) {
		Optional<Wedstrijd> w = wedstrijdRepository.findById(idWedstrijd);
		if (w.isEmpty()) {
			throw new WedstrijdIdNietGevondenException(idWedstrijd);
		}
		Wedstrijd wedstrijd = w.get();
		return wedstrijd;
	}

	@Override
	public Wedstrijd createDummyWedstrijd() {
		Sport sport = sportRepository.findByNaam("Tennis");
		Wedstrijd wed = new Wedstrijd(9999, LocalDate.of(2024, 7, 8), LocalTime.of(20, 0), 10.0, sport, 10,
				new HashSet<Discipline>(), 12345, 23456, 10);
		wedstrijdRepository.save(wed);
		return wed;
	}

	@Override
	public void addWedstrijdByUsernameEnWedstrijdIdEnWedstrijdObjectEnSportnaam(int aantal, String username,
			int wedstrijdID,
			NieuweWedstrijdValidator nieuweWedstrijd, String sport) {

		Wedstrijd wedstrijd = new Wedstrijd();
		wedstrijd.setAanvangUur(nieuweWedstrijd.getAanvangsuur());
		wedstrijd.setDatum(nieuweWedstrijd.getDatum());

		if (nieuweWedstrijd.getDiscipline1() != null && !nieuweWedstrijd.getDiscipline1().isEmpty()) {
			Discipline discipline = new Discipline();
			discipline.setNaam(nieuweWedstrijd.getDiscipline1());
			disciplineRepository.save(discipline);
			wedstrijd.addDiscipline(discipline);
		}
		if (nieuweWedstrijd.getDiscipline2() != null && !nieuweWedstrijd.getDiscipline2().equals("")) {
			Discipline discipline = new Discipline();
			discipline.setNaam(nieuweWedstrijd.getDiscipline2());
			disciplineRepository.save(discipline);
			wedstrijd.addDiscipline(discipline);
		}

		wedstrijd.setOlympischNummer1(Integer.parseInt(nieuweWedstrijd.getOlympischNummer1()));
		wedstrijd.setOlympischNummer2(Integer.parseInt(nieuweWedstrijd.getOlympischNummer2()));
		wedstrijd.setTicketPrijs(nieuweWedstrijd.getTicketPrijs());
		wedstrijd.setCapaciteit(nieuweWedstrijd.getAantalPlaatsen());
		Optional<Stadium> optionalstadium = stadiumRepository.findById(nieuweWedstrijd.getIdStadium());
		wedstrijd.setStadium(optionalstadium.get());
		wedstrijd.setSport(sportRepository.findByNaam(sport));

		wedstrijdRepository.save(wedstrijd);

	}

	@Override
	public int maxAantalPerWedstrijd(int idWedstrijd, String username) {
		Wedstrijd wedstrijd = wedstrijdRepository.findById(idWedstrijd).get();

		wedstrijd.getCapaciteit();
		wedstrijd.getBezettePlaatsen();

		int openPlaatsen = wedstrijd.getCapaciteit() - wedstrijd.getBezettePlaatsen();
		int maxPlaatsen = 20;
		int hierTicketten = userService.getAantalTicketsPerIdWedstrijdEnUsername(idWedstrijd, username);
		int aantalTickettenTotaal = ticketRepository.findByUser(userRepository.findByUsername(username))
				.size();
		int minAantal = 0;
		boolean isMaxBijna = false;
		if (aantalTickettenTotaal > 80) {
			maxPlaatsen = 0;
			isMaxBijna = true;
			minAantal = 100 - aantalTickettenTotaal;
		}

		if (openPlaatsen < 20) {
			maxPlaatsen = openPlaatsen;
		}

		if (maxPlaatsen > 20 - hierTicketten) {
			maxPlaatsen = 20 - hierTicketten;
		}

		maxPlaatsen = maxPlaatsen - minAantal;

		maxPlaatsen = Math.abs(maxPlaatsen);
		if (isMaxBijna) {
			if (aantalTickettenTotaal + maxPlaatsen >= 100) {
				int tijdelijk = (aantalTickettenTotaal + maxPlaatsen) - 100;
				maxPlaatsen = Math.abs(maxPlaatsen - tijdelijk);

			}
		}

		return maxPlaatsen;

	}
}
