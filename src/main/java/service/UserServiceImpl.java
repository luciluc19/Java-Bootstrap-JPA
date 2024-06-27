package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Ticket;
import domain.User;
import repository.TicketRepository;
import repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketRepository ticketRepository;
	





	@Override
	public int getAantalTicketsPerIdWedstrijdEnUsername(int idWedstrijd, String username) {
		User user = userRepository.findByUsername(username);

		List<Ticket> t = ticketRepository.findAll();
		int teller = 0;
		for (Ticket ticket : t) {
			if (ticket.getWedstrijd().getIdWedstrijd() == idWedstrijd && ticket.getUser().equals(user)) {
				teller++;
			}
		}

		return teller;
	}

}
