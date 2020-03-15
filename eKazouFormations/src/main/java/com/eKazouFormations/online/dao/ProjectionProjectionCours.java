package com.eKazouFormations.online.dao;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.eKazouFormations.online.dao.Cours;
import com.eKazouFormations.online.dao.ProjectionCours;
import com.eKazouFormations.online.dao.Reservation;
import com.eKazouFormations.online.dao.Salle;
import com.eKazouFormations.online.dao.Seance;

@Projection(name = "coursProjection", types = { ProjectionCours.class })
public interface ProjectionProjectionCours {

	public Long getId();
	public double getPrix();
	public Date getDateProjection();
	public Salle getSalle();
	public Cours getCours();
	public Collection<Reservation> getReservations();
	public Seance getSeance();
	
}
