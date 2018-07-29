package project.competition;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.competition.model.Takmicenje;
import project.competition.model.Ucesnik;
import project.competition.service.TakmicenjeService;
import project.competition.service.UcesnikService;

@Component
public class TestData {
	
	@Autowired
	private TakmicenjeService takmicenjeService;
	@Autowired
	private UcesnikService ucesnikService;

	@PostConstruct
	public void init() {
		
		Takmicenje t1 = new Takmicenje();
		t1.setNaziv("Super liga");
		takmicenjeService.save(t1);
		
		Takmicenje t2 = new Takmicenje();
		t2.setNaziv("Basket liga");
		takmicenjeService.save(t2);
		
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
		
		Ucesnik u6 = new Ucesnik();
		u6.setNaziv("Lakers");
		u6.setMesto("Los Angeles");
		u6.setEmail("lakers@gmail.com");
		u6.setTakmicenje(t2);
		u6.setOdigrano(5);
		u6.setBrBodova(26);
		ucesnikService.save(u6);
		
		Ucesnik u7 = new Ucesnik();
		u7.setNaziv("Atlanta Hawks");
		u7.setMesto("Atlanta");
		u7.setEmail("hawks@gmail.com");
		u7.setTakmicenje(t2);
		u7.setOdigrano(3);
		u7.setBrBodova(8);
		ucesnikService.save(u7);
		
		t1.addUcesnik(u1);
		t1.addUcesnik(u2);
		t1.addUcesnik(u3);
		t1.addUcesnik(u4);
		t1.addUcesnik(u5);
		t2.addUcesnik(u6);
		t2.addUcesnik(u7);
		takmicenjeService.save(t1);
		
		


	}
}