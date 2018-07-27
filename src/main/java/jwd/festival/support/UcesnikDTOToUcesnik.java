package jwd.festival.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.festival.model.Ucesnik;
import jwd.festival.service.TakmicenjeService;
import jwd.festival.service.UcesnikService;
import jwd.festival.web.dto.UcesnikDTO;

@Component
public class UcesnikDTOToUcesnik implements Converter<UcesnikDTO, Ucesnik> {
	
	@Autowired
	private TakmicenjeService takmicenjeService;
	@Autowired
	private UcesnikService ucesnikService;

	@Override
	public Ucesnik convert(UcesnikDTO source) {
		Ucesnik ucesnik;
		
		if(source.getId() == null) {
			ucesnik = new Ucesnik();
			ucesnik.setTakmicenje(takmicenjeService.findOne(source.getTakmicenjeId()));
		} else {
			ucesnik = ucesnikService.findOne(source.getId());
		}
		
		ucesnik.setNaziv(source.getNaziv());
		ucesnik.setMesto(source.getMesto());
		ucesnik.setEmail(source.getEmail());
		ucesnik.setOdigrano(source.getOdigrano());
		ucesnik.setBrBodova(source.getBrBodova());
		
		return ucesnik;
	}

}
