package com.eKazouFormations.online.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eKazouFormations.online.dao.Cours;
import com.eKazouFormations.online.dao.Reservation;
import com.eKazouFormations.online.repository.CoursRepository;
import com.eKazouFormations.online.repository.ReservationRepository;

import lombok.Data;

@RestController
@RequestMapping("/Formations")
public class FormationsController {

	@Autowired
	private CoursRepository coursRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping(path = "/getImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageFormations(@PathVariable(name="id") Long id) throws IOException {
		
		Cours cours = coursRepository.findById(id).get();
		String imageName = cours.getImagePath();
		File file = new File(System.getProperty("user.home")+"/EkazouFormations/images/"+imageName);
		Path pathImage = Paths.get(file.toURI());
		
		return Files.readAllBytes(pathImage);
	}
	
	@PostMapping(path = "/payFormations")
	@Transactional
	public List<Reservation> payFormations (@RequestBody ReservationForm reservationForm) {
		List<Reservation> listReservation = new ArrayList<Reservation>();
		reservationForm.getListTicket().forEach(ticket -> {
			Reservation reservation = reservationRepository.findById(ticket).get();
			reservation.setNameUser(reservationForm.getName());
			reservation.setCodePayment(reservationForm.getCodePayment());
			reservation.setReserved(true);
			reservationRepository.save(reservation);
			listReservation.add(reservation);
		});
		return listReservation;
	}
}

@Data
class ReservationForm {
	private String name;
	private int codePayment;
	private List<Long> listTicket;
}
