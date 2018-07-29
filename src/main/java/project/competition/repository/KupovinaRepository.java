package project.competition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.competition.model.Kupovina;

@Repository
public interface KupovinaRepository 
extends JpaRepository<Kupovina, Long> {

}
