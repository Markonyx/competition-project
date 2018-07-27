package jwd.festival.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.festival.model.Ucesnik;
import jwd.festival.service.TakmicenjeService;
import jwd.festival.service.UcesnikService;
import jwd.festival.support.TakmicenjeToTakmicenjeDTO;
import jwd.festival.support.UcesnikToUcesnikDTO;
import jwd.festival.web.dto.TakmicenjeDTO;
import jwd.festival.web.dto.UcesnikDTO;

@RestController
@RequestMapping(value="api/takmicenja")
public class ApiTakmicenjeController {
	
	@Autowired
	private TakmicenjeService takmicenjeService;
	@Autowired
	private UcesnikService ucesnikService;
	@Autowired
	private TakmicenjeToTakmicenjeDTO toDTO;
	@Autowired
	private UcesnikToUcesnikDTO toUcesnikDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TakmicenjeDTO>> getAll() {
		return new ResponseEntity<>(toDTO.convert(takmicenjeService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{takmicenjeId}/ucesnici")
	public ResponseEntity<List<UcesnikDTO>> getByTakmicenje(
			@PathVariable Long takmicenjeId,
			@RequestParam(defaultValue="0") int pageNum) {
		
		Page<Ucesnik> ucesnici = ucesnikService.findByTakmicenjeId(pageNum, takmicenjeId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(ucesnici.getTotalPages()));
		
		return new ResponseEntity<>(toUcesnikDTO.convert(ucesnici.getContent()), headers, HttpStatus.OK);
	}

}
