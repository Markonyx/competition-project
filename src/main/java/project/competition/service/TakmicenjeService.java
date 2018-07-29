package project.competition.service;

import java.util.List;

import project.competition.model.Takmicenje;

public interface TakmicenjeService {
	
	List<Takmicenje> findAll();
	Takmicenje findOne(Long id);
	void save(Takmicenje takmicenje);
	void delete(Long id);

}
