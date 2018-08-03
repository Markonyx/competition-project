package project.competition.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import project.competition.model.Ucesnik;
import project.competition.repository.UcesnikRepository;
import project.competition.service.UcesnikService;

@Service
@Transactional
public class JpaUcesnikService implements UcesnikService {
	
	@Autowired
	private UcesnikRepository ucesnikRepository;

	@Override
	public Page<Ucesnik> findAll(int pageNum) {
		return ucesnikRepository.findAll(new PageRequest(pageNum, 3));
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
		return ucesnikRepository.findByTakmicenjeId(takmicenjeId, new PageRequest(pageNum, 3));
	}

	@Override
	public Page<Ucesnik> pretraga(String naziv, String mesto, Long takmicenjeId, int page) {
		if (naziv != null) {
			naziv = "%" + naziv + "%";
		}
		if (mesto != null) {
			mesto = "%" + mesto + "%";
		}
		return ucesnikRepository.pretraga(naziv, mesto, takmicenjeId, new PageRequest(page, 3));
	}

	@Override
	public void odigrajMec(Long ucesnik1Id, Long ucesnik2Id, Long ishod) {
		
		Ucesnik ucesnik1 = ucesnikRepository.findOne(ucesnik1Id);
		ucesnik1.setOdigrano(ucesnik1.getOdigrano() + 1);
		ucesnikRepository.save(ucesnik1);
		
		Ucesnik ucesnik2 = ucesnikRepository.findOne(ucesnik2Id);
		ucesnik2.setOdigrano(ucesnik2.getOdigrano() + 1);
		ucesnikRepository.save(ucesnik2);
		
		if (ishod == 1) {
			ucesnik1.setBrBodova(ucesnik1.getBrBodova() + ucesnik1.getTakmicenje().getFormat().getVrPobeda());
			ucesnikRepository.save(ucesnik1);
		}
		if (ishod == 2) {
			ucesnik2.setBrBodova(ucesnik2.getBrBodova() + ucesnik2.getTakmicenje().getFormat().getVrPobeda());
			ucesnikRepository.save(ucesnik2);
		}
		if (ishod == 3) {
			ucesnik1.setBrBodova(ucesnik1.getBrBodova() + ucesnik1.getTakmicenje().getFormat().getVrNereseno());
			ucesnikRepository.save(ucesnik1);
			ucesnik2.setBrBodova(ucesnik2.getBrBodova() + ucesnik2.getTakmicenje().getFormat().getVrNereseno());
			ucesnikRepository.save(ucesnik2);
		}
		
	}
	
}
