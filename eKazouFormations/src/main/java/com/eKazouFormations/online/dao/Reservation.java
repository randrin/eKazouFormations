package com.eKazouFormations.online.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {

	private static final long serialVersionUID = 4023036005102348924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 75)
	private String nameUser;
	private double prix;
	private int codePayment;
	private boolean isReserved;
	@ManyToOne
	private Place place;
	private Seance seance;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Projection projection;
}
