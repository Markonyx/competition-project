package project.competition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import project.competition.model.Ucesnik;

public interface UcesnikService {
	
	Page<Ucesnik> findAll(int pageNum);
	Ucesnik findOne(Long id);
	void save (Ucesnik ucesnik);
	void delete(Long id);
	Page<Ucesnik> findByTakmicenjeId(int pageNum, Long takmicenjeId);
	Page<Ucesnik> pretraga(
			@Param("naziv") String naziv,
			@Param("takmicenjeId") Long takmicenjeId,
			int page);

}
