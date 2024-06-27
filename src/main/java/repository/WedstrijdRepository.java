package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Sport;
import domain.Wedstrijd;

@Repository
public interface WedstrijdRepository extends JpaRepository<Wedstrijd, Integer>{

	public List<Wedstrijd> getWedstrijdenBySportNaam(String sportName);
	
	public List<Wedstrijd> findAllBySport(Sport sport);
	
	public Wedstrijd findByOlympischNummer1(int olympischNummer1);
	
	public Wedstrijd findByOlympischNummer2(int olympischNummer1);

	
	

}
