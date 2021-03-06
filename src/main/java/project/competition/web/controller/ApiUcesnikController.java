package project.competition.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import project.competition.model.Ucesnik;
import project.competition.service.UcesnikService;
import project.competition.support.UcesnikDTOToUcesnik;
import project.competition.support.UcesnikToUcesnikDTO;
import project.competition.web.dto.UcesnikDTO;

@RestController
@RequestMapping(value="/api/ucesnici")
public class ApiUcesnikController {
	
	@Autowired
	private UcesnikService ucesnikService;
	@Autowired
	private UcesnikToUcesnikDTO toDTO;
	@Autowired
	private UcesnikDTOToUcesnik toUcesnik;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UcesnikDTO>> getAll(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String mesto,
			@RequestParam(required=false) Long takmicenjeId,
			@RequestParam(defaultValue="0") int pageNum) {
		
		Page<Ucesnik> ucesnici;
		
		if (naziv != null || mesto != null || takmicenjeId != null) {
			ucesnici = ucesnikService.pretraga(naziv, mesto, takmicenjeId, pageNum);
		} else {
			ucesnici = ucesnikService.findAll(pageNum);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(ucesnici.getTotalPages()));
		
		return new ResponseEntity<>(toDTO.convert(ucesnici.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<UcesnikDTO> findById(@PathVariable Long id) {
		Ucesnik ucesnik = ucesnikService.findOne(id);
		
		if(ucesnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDTO.convert(ucesnik), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UcesnikDTO> addUcesnik(
			@Validated @RequestBody UcesnikDTO noviUcesnik) {
		
		Ucesnik ucesnik = toUcesnik.convert(noviUcesnik);
		int maxUcesnika = ucesnik.getTakmicenje().getFormat().getBrUcesnika();
		int brUcesnika = ucesnik.getTakmicenje().getUcesnici().size();
		if (brUcesnika < maxUcesnika) {
			ucesnikService.save(ucesnik);	
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDTO.convert(ucesnik), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<UcesnikDTO> editUcesnik (
			@PathVariable Long id,
			@Validated @RequestBody UcesnikDTO izmenjen) {
		
		if(!id.equals(izmenjen.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Ucesnik ucesnik = toUcesnik.convert(izmenjen);
		ucesnikService.save(ucesnik);
		
		return new ResponseEntity<>(toDTO.convert(ucesnik), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<UcesnikDTO> deleteUcesnik(@PathVariable Long id) {
		
		Ucesnik ucesnik = ucesnikService.findOne(id);
		if (ucesnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ucesnikService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{u1}/{u2}/{ishod}")
	public ResponseEntity<UcesnikDTO> odigraj(@PathVariable Long u1,
			@PathVariable Long u2, @PathVariable Long ishod) {
		
		ucesnikService.odigrajMec(u1, u2, ishod);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
			DataIntegrityViolationException e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
