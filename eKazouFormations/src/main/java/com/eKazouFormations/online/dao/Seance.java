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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance implements Serializable {

	private static final long serialVersionUID = 4864446406864469118L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double prix;
	@Temporal(TemporalType.TIME)
	private Date dateSeance;
	@ManyToOne
	private Salle salle;
	@ManyToOne
	private Cours cours;
	@OneToMany(mappedBy = "seance")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Reservation> reservations;
}
