package project.competition.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.competition.model.Festival;
import project.competition.service.FestivalService;
import project.competition.service.MestoService;
import project.competition.web.dto.FestivalDTO;

@Component
public class FestivalDTOToFestival 
	implements Converter<FestivalDTO, Festival>{
	
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private MestoService mestoService;
	
	
	@Override
	public Festival convert(FestivalDTO source) {
		Festival festival;
		if(source.getId()==null){
			festival = new Festival();
			festival.setMesto(
					mestoService.findOne(
							source.getMestoId()));
		}else{
			festival = festivalService.findOne(source.getId());
		}
		festival.setNaziv(source.getNaziv());
		festival.setOrganizator(source.getOrganizator());
		festival.setDatumPocetka(source.getDatumPocetka());
		festival.setCenaKarte(source.getCenaKarte());
		festival.setKolicina(source.getKolicina());
		
		return festival;
	}

}
