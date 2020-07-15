package org.sid.Cinema;

import org.sid.Cinema.Service.ICinemaService;
import org.sid.Cinema.entities.Film;
import org.sid.Cinema.entities.Salle;
import org.sid.Cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;



@SpringBootApplication
public class CinemaApplication implements CommandLineRunner{

	@Autowired
	private ICinemaService cinemainitservice;
	
	@Autowired
	private RepositoryRestConfiguration rescon;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		rescon.exposeIdsFor(Film.class,Salle.class,Ticket.class);
		cinemainitservice.initVilles();
		cinemainitservice.initCinemas();
		cinemainitservice.initSalles();
		cinemainitservice.initPlaces();
		cinemainitservice.initSeances();
		cinemainitservice.initCategorie();
		cinemainitservice.initFilms();
		cinemainitservice.initProjections();
		cinemainitservice.initTicket();
		
		
	}

}