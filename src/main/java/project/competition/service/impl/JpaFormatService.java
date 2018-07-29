package project.competition.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.competition.model.Format;
import project.competition.repository.FormatRepository;
import project.competition.service.FormatService;

@Service
@Transactional
public class JpaFormatService implements FormatService {
	
	@Autowired
	private FormatRepository formatRepository;

	@Override
	public void save(Format format) {
		formatRepository.save(format);
	}

	@Override
	public void delete(Long id) {
		formatRepository.delete(id);
	}

}
