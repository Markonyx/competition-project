package jwd.festival;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.festival.model.Festival;
import jwd.festival.model.Mesto;
import jwd.festival.model.Takmicenje;
import jwd.festival.model.Ucesnik;
import jwd.festival.service.FestivalService;
import jwd.festival.service.MestoService;
import jwd.festival.service.TakmicenjeService;
import jwd.festival.service.UcesnikService;

@Component
public class TestData {
	@Autowired
	private MestoService mestoService;
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private TakmicenjeService takmicenjeService;
	@Autowired
	private UcesnikService ucesnikService;

	@PostConstruct
	public void init() {
		
		Mesto subotica = new Mesto();
		subotica.setDrzava("Srbija");
		subotica.setNaziv("Subotica");
		subotica.setPostanskiKod(24000);
		mestoService.save(subotica);
		
		Festival festival1 = new Festival();
		festival1.setNaziv("Summer3p");
		festival1.setCenaKarte(1230.99);
		festival1.setKolicina(99);
		festival1.setDatumPocetka("11.7.2018.");
		festival1.setOrganizator("Grad Subotica");
		festival1.setMesto(subotica);
		festivalService.save(festival1);

		Mesto noviSad = new Mesto();
		noviSad.setDrzava("Srbija");
		noviSad.setNaziv("Novi Sad");
		noviSad.setPostanskiKod(21000);
		mestoService.save(noviSad);
		
		Festival festival2 = new Festival();
		festival2.setNaziv("EXIT");
		festival2.setCenaKarte(6000.0);
		festival2.setKolicina(99);
		festival2.setDatumPocetka("10.7.2018.");
		festival2.setOrganizator("Exit fondacija");
		festival2.setMesto(noviSad);
		festivalService.save(festival2);
		
		Festival festival3 = new Festival();
		festival3.setNaziv("Festival vina");
		festival3.setCenaKarte(200.0);
		festival3.setKolicina(99);
		festival3.setDatumPocetka("11.7.2018.");
		festival3.setOrganizator("Grad Novi Sad");
		festival3.setMesto(noviSad);
		festivalService.save(festival3);
		
		Takmicenje t1 = new Takmicenje();
		t1.setNaziv("Super liga");
		takmicenjeService.save(t1);
		
		
		Ucesnik u1 = new Ucesnik();
		u1.setNaziv("Spartak");
		u1.setMesto("Subotica");
		u1.setEmail("spartak@gmail.com");
		u1.setTakmicenje(t1);
		u1.setOdigrano(11);
		u1.setBrBodova(33);
		ucesnikService.save(u1);
		
		Ucesnik u2 = new Ucesnik();
		u2.setNaziv("Crvena zvezda");
		u2.setMesto("Beograd");
		u2.setEmail("czv@gmail.com");
		u2.setTakmicenje(t1);
		u2.setOdigrano(10);
		u2.setBrBodova(28);
		ucesnikService.save(u2);
		
		Ucesnik u3 = new Ucesnik();
		u3.setNaziv("Vojvodina");
		u3.setMesto("Novi Sad");
		u3.setEmail("vosa@gmail.com");
		u3.setTakmicenje(t1);
		u3.setOdigrano(11);
		u3.setBrBodova(29);
		ucesnikService.save(u3);
		
		Ucesnik u4 = new Ucesnik();
		u4.setNaziv("Partizan");
		u4.setMesto("Beograd");
		u4.setEmail("partizan@gmail.com");
		u4.setTakmicenje(t1);
		u4.setOdigrano(11);
		u4.setBrBodova(28);
		ucesnikService.save(u4);
		
		Ucesnik u5 = new Ucesnik();
		u5.setNaziv("Radnicki");
		u5.setMesto("Nis");
		u5.setEmail("rn@gmail.com");
		u5.setTakmicenje(t1);
		u5.setOdigrano(10);
		u5.setBrBodova(27);
		ucesnikService.save(u5);
		
		t1.addUcesnik(u1);
		t1.addUcesnik(u2);
		t1.addUcesnik(u3);
		t1.addUcesnik(u4);
		t1.addUcesnik(u5);
		takmicenjeService.save(t1);
		
		


	}
}