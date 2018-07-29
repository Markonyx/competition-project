package project.competition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import project.competition.model.Festival;
import project.competition.model.Kupovina;

public interface FestivalService {
	Page<Festival> findAll(int pageNum);
	Festival findOne(Long id);
	void save(Festival festival);
	void remove(Long id);
	Kupovina buy(Long id);
	Page<Festival> findByMestoId(int pageNum, Long mestoId);
	Page<Festival> pretraga(
			@Param("naziv") String naziv, 
			@Param("maxCena") Double maxCena,
			@Param("idMesta") Long idMesta, 
			int page);
}
