package com.eKazouFormations.online.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle implements Serializable {

	private static final long serialVersionUID = -3739384185423943011L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int numberPlace;
	@OneToMany(mappedBy = "salle")
	private Collection<Place> places;
	@OneToMany(mappedBy = "salle")
	private Collection<Seance> seances;
	@ManyToOne
	private Formation formation;
}
