package project.competition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.competition.model.Format;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long> {

}
