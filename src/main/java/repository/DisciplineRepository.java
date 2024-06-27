package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer>{

	Discipline findByNaam(String naam);

}
