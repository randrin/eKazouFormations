package com.eKazouFormations.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.eKazouFormations.online.dao.Place;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Long>{

}
