package com.eKazouFormations.online.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eKazouFormations.online.dao.Categorie;
import com.eKazouFormations.online.dao.Cours;
import com.eKazouFormations.online.dao.Formation;
import com.eKazouFormations.online.dao.Place;
import com.eKazouFormations.online.dao.Projection;
import com.eKazouFormations.online.dao.Reservation;
import com.eKazouFormations.online.dao.Salle;
import com.eKazouFormations.online.dao.Seance;
import com.eKazouFormations.online.dao.Ville;
import com.eKazouFormations.online.repository.CategorieRepository;
import com.eKazouFormations.online.repository.CoursRepository;
import com.eKazouFormations.online.repository.FormationRepository;
import com.eKazouFormations.online.repository.PlaceRepository;
import com.eKazouFormations.online.repository.ProjectionRepository;
import com.eKazouFormations.online.repository.ReservationRepository;
import com.eKazouFormations.online.repository.SalleRepository;
import com.eKazouFormations.online.repository.SeanceRepository;
import com.eKazouFormations.online.repository.VilleRepository;

@Service
@Transactional
public class EkazouFormationsServiceImpl implements EkazouFormationsService{

	private static final Logger logger = LoggerFactory.getLogger(EkazouFormationsServiceImpl.class);
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private CoursRepository coursRepository;
	
	@Autowired
	private FormationRepository formationRepository;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private SalleRepository salleRepository;
	
	@Autowired
	private SeanceRepository seanceRepository;
	
	@Autowired
	private VilleRepository villerepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public void initCategories() {
		Stream.of("Front End", "Back End", "DataBase", "Gestion Projet", "Sistemiste", "Gestion Client")
		.forEach(cat -> {
			Categorie categorie = new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
	}

	@Override
	public void initCours() {
		double [] duration =  {1.00, 1.30, 2.00, 2.30, 3.00, 3.30};
		List<Categorie> listCategories = categorieRepository.findAll();
		Stream.of("Angular", "ReactJS", "VueJS", "Java-J2ee", "Spring Boot", "NodeJs", "Aws", "Oracle SQL", "SalesForce", "SAP Hybris")
		.forEach(crs -> {
			Cours cours = new Cours();
			cours.setName(crs);
			cours.setDuration(duration[new Random().nextInt(duration.length)]);
			cours.setImagePath(crs.replaceAll(" ", "")+".jpg");
			cours.setCategorie(listCategories.get(new Random().nextInt(listCategories.size())));
			coursRepository.save(cours);
		});
		
	}

	@Override
	public void initFormations() {
		villerepository.findAll().forEach(ville -> {
			Stream.of("Web Developpement", "DataBase Management", "RH & Gestion", "Système Rèseaux", "Marketing Comunication", "Gestion Projet", "Business Management")
			.forEach(formation -> {
				Formation fr = new Formation();
				fr.setName(formation);
				fr.setNumberSalle(3 + (int)(Math.random()*7));
				fr.setVille(ville);
				formationRepository.save(fr);
			});
		});
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle -> {
			for (int i = 0; i < salle.getNumberPlace(); i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
	}

	@Override
	public void initReservations() {
		projectionRepository.findAll().forEach(projection -> {
			projection.getSalle().getPlaces().forEach(place -> {
				Reservation reservation = new Reservation();
				reservation.setPlace(place);
				reservation.setPrix(projection.getPrix());
				reservation.setProjection(projection);
				reservation.setReserved(false);
				reservationRepository.save(reservation);
			});
		});
	}

	@Override
	public void initSalles() {
		formationRepository.findAll().forEach(formation -> {
			for (int i = 0; i < formation.getNumberSalle(); i++) {
				Salle salle = new Salle();
				salle.setName("Salle " +(i+1));
				salle.setNumberPlace(5 + (int)(Math.random()*15));
				salle.setFormation(formation);
				salleRepository.save(salle);
			}
		});
	}

	@Override
	public void initSeances() {
		DateFormat dtf = new SimpleDateFormat("HH:mm");
		Stream.of("09:00", "13;00", "15:00", "18:00", "21:00").forEach(sea -> {
			Seance seance = new Seance();
			try {
				seance.setDateSeance(dtf.parse(sea));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				logger.info("Message: " + e.getMessage() + " Cause: " + e.getCause());
			}
		});
	}

	@Override
	public void initVilles() {
		Stream.of("Bonapriso - Douala", "Bonadjo - Douala", "Akwa - Douala", "Ngousso - Yaoundé", "Cité Verte - Yaoundé").forEach(ville -> {
			Ville v = new Ville();
			v.setName(ville);
			villerepository.save(v);
		});
		
	}

	@Override
	public void initProjections() {
		double [] prix = {30.000, 45.000, 55.000, 80.000, 100.000};
		villerepository.findAll().forEach(ville -> {
			ville.getFormations().forEach(formation -> {
				formation.getSalles().forEach(salle -> {
					coursRepository.findAll().forEach(cours -> {
						seanceRepository.findAll().forEach(seance -> {
							Projection projection = new Projection();
							projection.setDateProjection(new Date());
							projection.setCours(cours);
							projection.setSalle(salle);
							projection.setPrix(prix[new Random().nextInt(prix.length)]);
							projection.setSeance(seance);
							projectionRepository.save(projection);
						});
					});
				});
			});
		});
	}
}
