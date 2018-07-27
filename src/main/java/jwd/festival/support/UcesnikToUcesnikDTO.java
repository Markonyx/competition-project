package jwd.festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.festival.model.Ucesnik;
import jwd.festival.web.dto.UcesnikDTO;

@Component
public class UcesnikToUcesnikDTO implements Converter<Ucesnik, UcesnikDTO> {

	@Override
	public UcesnikDTO convert(Ucesnik ucesnik) {
		UcesnikDTO dto = new UcesnikDTO();
		dto.setId(ucesnik.getId());
		dto.setNaziv(ucesnik.getNaziv());
		dto.setMesto(ucesnik.getMesto());
		dto.setEmail(ucesnik.getEmail());
		dto.setOdigrano(ucesnik.getOdigrano());
		dto.setBrBodova(ucesnik.getBrBodova());
		dto.setTakmicenjeId(ucesnik.getTakmicenje().getId());
		dto.setTakmicenjeNaziv(ucesnik.getTakmicenje().getNaziv());
		return dto;
	}
	
	public List<UcesnikDTO> convert(List<Ucesnik> ucesnici) {
		List<UcesnikDTO> ret = new ArrayList<>();
		
		for (Ucesnik u : ucesnici) {
			ret.add(convert(u));
		}
		
		return ret;
	}

}
