package jwd.festival.service;

import java.util.List;

import jwd.festival.model.Takmicenje;

public interface TakmicenjeService {
	
	List<Takmicenje> findAll();
	Takmicenje findOne(Long id);
	void save(Takmicenje takmicenje);
	void delete(Long id);

}
