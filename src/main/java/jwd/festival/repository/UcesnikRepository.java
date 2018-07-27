package jwd.festival.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.festival.model.Ucesnik;

@Repository
public interface UcesnikRepository extends JpaRepository<Ucesnik, Long> {
	
	Page<Ucesnik> findByTakmicenjeId(Long takmicenjeId, Pageable pageRequest);
	
	@Query("SELECT u FROM Ucesnik u WHERE "
			+ "(:naziv IS NULL or u.naziv like :naziv ) AND"
			+ "(:takmicenjeId IS NULL or u.takmicenje.id = :takmicenjeId )")
	Page<Ucesnik> pretraga(
			@Param("naziv") String naziv,
			@Param("takmicenjeId") Long takmicenjeId,
			Pageable pageRequest);
	

}
