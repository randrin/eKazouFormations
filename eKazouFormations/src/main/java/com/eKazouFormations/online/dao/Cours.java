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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cours implements Serializable {

	private static final long serialVersionUID = 2130829244144705121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 25)
	private String name;
	@Column(length = 100)
	private String description;
	private double duration;
	@Column(length = 100)
	private String professor;
	private String imagePath;
	private Date initClasse;
	@OneToMany(mappedBy = "cours")
	private Collection<Seance> seances;
	@ManyToOne
	private Categorie categorie;
	@OneToMany(mappedBy = "cours")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<ProjectionCours> projections;
}
