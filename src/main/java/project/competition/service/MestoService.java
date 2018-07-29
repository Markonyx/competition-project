package project.competition.service;

import java.util.List;

import project.competition.model.Mesto;


public interface MestoService {
	List<Mesto> findAll();
	Mesto findOne(Long id);
	void save(Mesto sajam);
	void remove(Long id);

}
