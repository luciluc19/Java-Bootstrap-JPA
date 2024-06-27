package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springBoot.EwdOlymp.NieuweWedstrijdValidator;

import domain.Wedstrijd;


@Service
public interface WedstrijdService {

	public List<Wedstrijd> getWedstrijdenBySportNaam(String sportName);

	public Wedstrijd findWedstrijdById(int idWedstrijd);

	public Wedstrijd createDummyWedstrijd();

	public void addWedstrijdByUsernameEnWedstrijdIdEnWedstrijdObjectEnSportnaam(int aantal, String username, int wedstrijdID, NieuweWedstrijdValidator nieuweWedstrijd, String sport);

	public int maxAantalPerWedstrijd(int idWedstrijd, String username);

}
