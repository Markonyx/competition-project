package project.competition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.competition.model.Takmicenje;

@Repository
public interface TakmicenjeRepository extends JpaRepository<Takmicenje, Long> {

}
