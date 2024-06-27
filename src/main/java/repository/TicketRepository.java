package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Ticket;
import domain.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	List<Ticket> findByUser(User user);
	
}
