package org.sid.Cinema.dao;

import java.util.List;

import org.sid.Cinema.entities.Categorie;

import org.sid.Cinema.entities.Film;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
@CrossOrigin("*")
public interface FilmP extends JpaRepository<Film ,Long>{

	public Page<Film> findByTitreContains(String mc,Pageable pageable);

	


}
