package org.sid.Cinema.dao;

import org.sid.Cinema.entities.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
@CrossOrigin("*")
public interface ProjectionP extends JpaRepository<Projection,Long>{
	public Page<Projection> findByFilmTitreContains(String mc,Pageable pageable);
	
}
