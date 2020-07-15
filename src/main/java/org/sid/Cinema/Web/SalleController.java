package org.sid.Cinema.Web;

import java.util.List;

import javax.validation.Valid;

import org.sid.Cinema.dao.CinemaP;
import org.sid.Cinema.dao.SalleP;
import org.sid.Cinema.entities.Cinema;
import org.sid.Cinema.entities.Salle;
import org.sid.Cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalleController {

	@Autowired private SalleP salleP;
	@Autowired private CinemaP cinemaP;
		@GetMapping("/salle")
		public String salle(Model model,@RequestParam(name="page",defaultValue = "0") int page,
				@RequestParam(name ="size",defaultValue = "5") int size,
				@RequestParam(name ="keyword",defaultValue = "") String mc){
			Page<Salle> pageSalles=salleP.findByNameContains(mc,PageRequest.of(page,size));
			List<Salle> salles=pageSalles.getContent();
			model.addAttribute("salles",salles);
			model.addAttribute("keyword",mc);
			model.addAttribute("pages",new int[pageSalles.getTotalPages()]);
			model.addAttribute("currentPage",page);
			model.addAttribute("size",size);	
			List<Cinema> cinemas=cinemaP.findAll();
			model.addAttribute("cinemas",cinemas);
			model.addAttribute("salle",new Salle());
			return"salle";
		}
		@GetMapping("/deleteSalle")
		public String deleteSalle(Long id,int page,int size,String keyword)
		{ 
			salleP.deleteById(id);
			return "redirect:/salle?page="+page+"&size="+size+"&keyword="+keyword;
		}
		@PostMapping("/addSalle")
		public String addSalle( @Valid Salle salle,BindingResult bindingResult,Model model)
		{		
			if(bindingResult.hasErrors())
				return "/SalleForme";
			salleP.save(salle);
			model.addAttribute("salle",salle);
			return "SalleConfirmation";
		}
		@GetMapping("/SalleForme")
		public String formSalle(Model model)
		{ 	
			List<Cinema> cinemas=cinemaP.findAll();
			model.addAttribute("cinemas",cinemas);
			model.addAttribute("salle",new Salle());
			return "/SalleForme";
			}
		@GetMapping("editSalle")
		public String editCinema(Model model,Long id)
		{ 
			List<Cinema> cinemas=cinemaP.findAll();
			model.addAttribute("cinemas",cinemas);
			Salle salle=salleP.findById(id).get();
			model.addAttribute("salle",salle);
			String mode="edit";
			model.addAttribute("mode",mode);
			return "/SalleForme";
			}
}
