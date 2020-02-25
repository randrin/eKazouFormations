package com.eKazouFormations.online.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.internal.build.AllowPrintStacktrace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formation implements Serializable {

	private static final long serialVersionUID = 292332067187476519L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double longitude;
	private double latitude;
	private double altitude;
	private int numberSalle;
	@OneToMany(mappedBy = "formation")
	private Collection<Salle> salles;
	@ManyToOne
	private Ville ville;
	
}
