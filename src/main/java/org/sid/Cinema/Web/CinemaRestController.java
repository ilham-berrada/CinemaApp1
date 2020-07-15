package org.sid.Cinema.Web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.sid.Cinema.dao.FilmP;
import org.sid.Cinema.dao.TicketP;
import org.sid.Cinema.entities.Film;
import org.sid.Cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
	@Autowired private FilmP filmP;
	@Autowired private TicketP ticketP;
	@GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name="id")Long id) throws IOException
	{
		Film f=filmP.findById(id).get();
		String photoname=f.getPhoto();	
		File file =new File(System.getProperty("user.home")+"/imageCinema/"+photoname);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm)
	{	
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket=ticketP.findById(idTicket).get();
			ticket.setNomclient(ticketForm.getNomClient());
			ticket.setReservee(true);
			ticket.setCodepayement(ticketForm.getCodePayement());
			ticketP.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}
	
}

@Data
class TicketForm{
	private String nomClient;
	private int codePayement;
	private List<Long> tickets=new ArrayList<>();
}
