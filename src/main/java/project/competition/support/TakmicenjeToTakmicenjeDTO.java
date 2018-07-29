package project.competition.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.competition.model.Takmicenje;
import project.competition.web.dto.TakmicenjeDTO;

@Component
public class TakmicenjeToTakmicenjeDTO implements Converter<Takmicenje, TakmicenjeDTO> {

	@Override
	public TakmicenjeDTO convert(Takmicenje takmicenje) {
		TakmicenjeDTO dto = new TakmicenjeDTO();
		dto.setId(takmicenje.getId());
		dto.setNaziv(takmicenje.getNaziv());
		return dto;
	}
	
	public List<TakmicenjeDTO> convert(List<Takmicenje> takmicenja) {
		List<TakmicenjeDTO> ret = new ArrayList<>();
		
		for (Takmicenje t : takmicenja) {
			ret.add(convert(t));
		}
		
		return ret;
	}

}
