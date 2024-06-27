package service;

import org.springframework.stereotype.Service;


@Service
public interface TicketService {

    public void addAantalTicketenToWedstrijd(int idWedstrijd, int aantalTickets, String userName);
    
}
