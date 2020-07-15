package org.sid.Cinema.dao;

import org.sid.Cinema.entities.Place;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
@CrossOrigin("*")
public interface PlaceP extends JpaRepository< Place,Long>{

}

