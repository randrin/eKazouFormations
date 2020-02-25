package com.eKazouFormations.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.eKazouFormations.online.dao.Ville;

@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long> {

}
