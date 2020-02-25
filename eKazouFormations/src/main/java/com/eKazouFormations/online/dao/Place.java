package com.eKazouFormations.online.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
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
public class Place implements Serializable {

	private static final long serialVersionUID = -6663772528578752262L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 10)
	private String numero;
	private double longitude;
	private double latitude;
	private double altitude;
	@ManyToOne
	private Salle salle;
	@OneToMany(mappedBy = "place")
	private Collection<Reservation> reservations;

}
