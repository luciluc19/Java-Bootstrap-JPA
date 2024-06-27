package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer>{

	public Sport findByNaam(String naam);
}
