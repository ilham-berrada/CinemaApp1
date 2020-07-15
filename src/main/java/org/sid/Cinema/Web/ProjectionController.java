
package org.sid.Cinema.Web;

import java.util.List;

import javax.validation.Valid;

import org.sid.Cinema.dao.FilmP;
import org.sid.Cinema.dao.ProjectionP;
import org.sid.Cinema.dao.SalleP;
import org.sid.Cinema.dao.SeanceP;
import org.sid.Cinema.entities.Cinema;
import org.sid.Cinema.entities.Film;
import org.sid.Cinema.entities.Projection;
import org.sid.Cinema.entities.Salle;
import org.sid.Cinema.entities.Seance;
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
public class ProjectionController {

@Autowired private ProjectionP projectionP;
@Autowired private SalleP salleP;
@Autowired private FilmP filmP;
@Autowired private SeanceP seanceP;

@GetMapping("/projection")
	public String projection(Model model,@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name ="size",defaultValue = "5") int size,
			@RequestParam(name ="keyword",defaultValue = "") String mc){
		Page<Projection> pageProjections=projectionP.findByFilmTitreContains(mc,PageRequest.of(page,size));
		List<Projection> projections=pageProjections.getContent();
		model.addAttribute("projections",projections);
		model.addAttribute("keyword",mc);
		model.addAttribute("pages",new int[pageProjections.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		return"projection";
	}
	@GetMapping("/deleteProjection")
	public String deleteCinema(Long id,int page,int size,String keyword)
	{ 
		projectionP.deleteById(id);
		return "redirect:/projection?page="+page+"&size="+size+"&keyword="+keyword;
	}
	@PostMapping("/addProjection")
	public String addCinema( @Valid Projection projection,BindingResult bindingResult,Model model)
	{		
		if(bindingResult.hasErrors())
			return "/ProjectionForme";
		projectionP.save(projection);
		model.addAttribute("projection",projection);
		return "ProjectionConfirmation";
	}
	@GetMapping("/ProjectionForme")
	public String ProjectionForme(Model model)
	{ 	
		List<Seance> seances=seanceP.findAll();
		model.addAttribute("seances",seances);
		List<Film> films=filmP.findAll();
		model.addAttribute("films",films);
		List<Salle> salles=salleP.findAll();
		model.addAttribute("salles",salles);
		model.addAttribute("projection",new Projection());
		return "/ProjectionForme";
		}
	@GetMapping("editProjection")
	public String editProjection(Model model,Long id)
	{ 
		List<Seance> seances=seanceP.findAll();
		model.addAttribute("seances",seances);
		List<Film> films=filmP.findAll();
		model.addAttribute("films",films);
		List<Salle> salles=salleP.findAll();
		model.addAttribute("salles",salles);
		Projection projection=projectionP.findById(id).get();
		model.addAttribute("projection",projection);
		String mode="edit";
		model.addAttribute("mode",mode);
		return "/ProjectionForme";
		}
}
