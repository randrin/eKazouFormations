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
import com.eKazouFormations.online.dao.ProjectionCours;
import com.eKazouFormations.online.dao.Reservation;
import com.eKazouFormations.online.dao.Salle;
import com.eKazouFormations.online.dao.Seance;
import com.eKazouFormations.online.dao.Ville;
import com.eKazouFormations.online.repository.CategorieRepository;
import com.eKazouFormations.online.repository.CoursRepository;
import com.eKazouFormations.online.repository.FormationRepository;
import com.eKazouFormations.online.repository.PlaceRepository;
import com.eKazouFormations.online.repository.ProjectionCoursRepository;
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
	private ProjectionCoursRepository projectionCoursRepository;
	
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
		String[][] coursList = {
				{"Angular", "Front End Web Application Angular"},
				{"ReactJS", "Front End JavaScript ReactJS"},
				{"Java-J2ee", "Back End Application Java-J2ee"},
				{"Spring Boot", "Back End Framework Applications Spring Boot"},
				{"NodeJs", "Back End Serveur pour Front End Applications"},
				{"Aws", "Awesome Cloud pour applications"},
				{"Oracle SQL", "Stockage des données pour applicarions"},
				{"SalesForce", "Développement des Applications sur le Cloud Web"},
				{"SAP Hybris", "Développement pour les applications e-Commerce"}
		};
		String[][] professors = {
				{"Randrin Nzeukang", "https://kazoucoin.com/assets/img/avatars/user/1554246047.jpeg"},
				{"Boclair Temgoua", "https://kazoucoin.com/assets/img/avatars/user/1556213392.jpeg"},
				{"Darry Noubissi", "https://kazoucoin.com//assets/img/avatars/user/261b7af5e26acbcad82ec906cf713ba8a04e07de.jpeg"}
		};
		double [] duration =  {1.00, 1.30, 2.00, 2.30, 3.00, 3.30};
		List<Categorie> listCategories = categorieRepository.findAll();
		Stream.of(coursList)
		.forEach(crs -> {
			Cours cours = new Cours();
			cours.setName(crs[0]);
			cours.setDescription(crs[1]);
			cours.setProfessor("Randrin Nzeukang");
			cours.setProfessorImage("https://kazoucoin.com/assets/img/avatars/user/1554246047.jpeg");
			//cours.setProfessor(professors[0][new Random().nextInt(professors.length)]);
			//cours.setProfessorImage(professors[0][new Random().nextInt(professors.length)]);
			cours.setDuration(duration[new Random().nextInt(duration.length)]);
			cours.setImagePath(crs[0].replaceAll(" ", "")+".jpg");
			cours.setCategorie(listCategories.get(new Random().nextInt(listCategories.size())));
			coursRepository.save(cours);
		});
		
	}

	@Override
	public void initFormations() {
		String[][] formations = {
				{"Web Developpement", "tim-icons icon-components"},
				{"DataBase Management", "fa fa-database"},
				{"RH & Gestion", "tim-icons icon-single-copy-04"},
				{"Système Rèseaux", "tim-icons icon-settings-gear-63"},
				{"Marketing Comunication", "tim-icons icon-chart-bar-32"},
				{"Gestion Projet", "fa fa-users"},
				{"Business Management", "fa fa-handshake"}
		};
		villerepository.findAll().forEach(ville -> {
			Stream.of(formations)
			.forEach(formation -> {
				Formation fr = new Formation();
				fr.setName(formation[0]);
				fr.setIconFormation(formation[1]);
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
		projectionCoursRepository.findAll().forEach(projection -> {
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
		Stream.of("09:00", "13:00", "15:00", "18:00", "21:00").forEach(sea -> {
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
		String[][] villes = {
		                     {"Bonapriso - Douala", "https://r-cf.bstatic.com/images/hotel/max500/166/166045620.jpg"},
		                     {"Akwa - Douala", "https://photos.wikimapia.org/p/00/04/68/95/75_full.jpg"},
		                     {"Bonadjo - Douala", "https://www.journalducameroun.com/en/wp-content/uploads/2019/12/bonanjo-780x440.jpg"},
		                     {"Ngousso - Yaoundé", "https://agence.complexesantalucia.com/wp-content/uploads/sites/15/2017/01/ngousso.png"},
		                     {"Cité Verte - Yaoundé", "https://sic.cm/images/Features/gallery-etetak-01.jpg"}
							};
		for (int ville = 0; ville < villes.length; ville++) {	
			String[] villeSelected = villes[ville];
			Ville v = new Ville();
			v.setName(villeSelected[0]);
			v.setImageVille(villeSelected[1]);
			villerepository.save(v);
		}
	}

	@Override
	public void initProjections() {
		double [] prix = {30.000, 45.000, 55.000, 80.000, 100.000};
		List<Cours> listCours = coursRepository.findAll();
		villerepository.findAll().forEach(ville -> {
			ville.getFormations().forEach(formation -> {
				formation.getSalles().forEach(salle -> {
					int index = new Random().nextInt(listCours.size());
					Cours cours = listCours.get(index);
					seanceRepository.findAll().forEach(seance -> {
						ProjectionCours projection = new ProjectionCours();
						projection.setDateProjection(new Date());
						projection.setCours(cours);
						projection.setSalle(salle);
						projection.setPrix(prix[new Random().nextInt(prix.length)]);
						projection.setSeance(seance);
						projectionCoursRepository.save(projection);
					});
				});
			});
		});
	}
}
