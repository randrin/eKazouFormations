package com.eKazouFormations.online.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projection implements Serializable {

	private static final long serialVersionUID = 536533415232468969L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateProjection;
	private double prix;
	@ManyToOne
	private Salle salle;
	@ManyToOne
	private Cours cours;
	@OneToMany(mappedBy = "projection")
	private Collection<Reservation> reservations;
	@ManyToOne
	private Seance seance;
}
