package com.eKazouFormations.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.eKazouFormations.online.dao.ProjectionCours;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProjectionCoursRepository extends JpaRepository<ProjectionCours, Long> {

}
