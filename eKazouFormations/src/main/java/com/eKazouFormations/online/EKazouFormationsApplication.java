package com.eKazouFormations.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.eKazouFormations.online.dao.Cours;
import com.eKazouFormations.online.service.EkazouFormationsService;

@SpringBootApplication
public class EKazouFormationsApplication implements CommandLineRunner {

	@Autowired
	private EkazouFormationsService ekazouFormationsService;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(EKazouFormationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Cours.class);
		ekazouFormationsService.initVilles();
		ekazouFormationsService.initFormations();
		ekazouFormationsService.initSalles();
		ekazouFormationsService.initPlaces();
		ekazouFormationsService.initSeances();
		ekazouFormationsService.initCategories();
		ekazouFormationsService.initCours();
		ekazouFormationsService.initProjections();
		ekazouFormationsService.initReservations();
	}
}
