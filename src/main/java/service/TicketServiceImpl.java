package service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Ticket;
import domain.Wedstrijd;

import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import repository.WedstrijdRepository;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StadiumRepository stadiumRepository;

    @Autowired
    WedstrijdRepository wedstrijdRepository;

    @Override
    public void addAantalTicketenToWedstrijd(int idWedstrijd, int aantalTickets, String username) {

        Wedstrijd wedstrijd = wedstrijdRepository.findById(idWedstrijd).get();
        wedstrijd.setBezettePlaatsen(wedstrijd.getBezettePlaatsen() + aantalTickets);

        for (int i = 0; i < aantalTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setWedstrijd(wedstrijd);
            ticket.setUser(userRepository.findByUsername(username));
            ticketRepository.save(ticket);
        }
    }

}
