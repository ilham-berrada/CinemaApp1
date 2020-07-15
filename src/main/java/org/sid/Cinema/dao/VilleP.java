package org.sid.Cinema.dao;

import java.util.List;

import org.sid.Cinema.entities.Cinema;
import org.sid.Cinema.entities.Ville;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@CrossOrigin("*")
@RepositoryRestResource
public interface VilleP extends JpaRepository<Ville, Long>{
	public Page<Ville> findByNameContains(String mc,Pageable pageable);
	//public Ville findById(int id);
}
