package jwd.festival.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.festival.model.Ucesnik;
import jwd.festival.repository.UcesnikRepository;
import jwd.festival.service.UcesnikService;

@Service
@Transactional
public class JpaUcesnikService implements UcesnikService {
	
	@Autowired
	private UcesnikRepository ucesnikRepository;

	@Override
	public Page<Ucesnik> findAll(int pageNum) {
		return ucesnikRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Ucesnik findOne(Long id) {
		return ucesnikRepository.findOne(id);
	}

	@Override
	public void save(Ucesnik ucesnik) {
		ucesnikRepository.save(ucesnik);
	}

	@Override
	public void delete(Long id) {
		ucesnikRepository.delete(id);
	}

	@Override
	public Page<Ucesnik> findByTakmicenjeId(int pageNum, Long takmicenjeId) {
		return ucesnikRepository.findByTakmicenjeId(takmicenjeId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Ucesnik> pretraga(String naziv, Long takmicenjeId, int page) {
		if (naziv != null) {
			naziv = "%" + naziv + "%";
		}
		return ucesnikRepository.pretraga(naziv, takmicenjeId, new PageRequest(page, 5));
	}

}
