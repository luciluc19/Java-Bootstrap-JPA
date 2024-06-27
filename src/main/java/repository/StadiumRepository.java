package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer>{
	

}
