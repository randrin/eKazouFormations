package com.eKazouFormations.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.eKazouFormations.online.dao.Reservation;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
