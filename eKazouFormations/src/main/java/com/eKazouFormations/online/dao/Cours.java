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
public class Cours implements Serializable {

	private static final long serialVersionUID = 2130829244144705121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double duration;
	private String professor;
	private String imagePath;
	private Date initClasse;
	@OneToMany(mappedBy = "cours")
	private Collection<Seance> seances;
	@ManyToOne
	private Categorie categorie;
}
