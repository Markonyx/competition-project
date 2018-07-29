package project.competition.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.competition.model.Takmicenje;
import project.competition.repository.TakmicenjeRepository;
import project.competition.service.TakmicenjeService;

@Service
@Transactional
public class JpaTakmicenjeService implements TakmicenjeService {
	
	@Autowired
	private TakmicenjeRepository takmicenjeRepository;

	@Override
	public List<Takmicenje> findAll() {
		return takmicenjeRepository.findAll();
	}

	@Override
	public Takmicenje findOne(Long id) {
		return takmicenjeRepository.findOne(id);
	}

	@Override
	public void save(Takmicenje takmicenje) {
		takmicenjeRepository.save(takmicenje);
	}

	@Override
	public void delete(Long id) {
		takmicenjeRepository.delete(id);
	}

}
