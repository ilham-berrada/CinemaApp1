package org.sid.Cinema.Web;

import java.util.List;

import javax.validation.Valid;

import org.sid.Cinema.dao.CinemaP;
import org.sid.Cinema.dao.VilleP;
import org.sid.Cinema.entities.Cinema;
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
public class CinemaController {
@Autowired private VilleP villeP;
@Autowired private CinemaP cinemaP;
	@GetMapping("/cinema")
	public String Cinema(Model model,@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name ="size",defaultValue = "5") int size,
			@RequestParam(name ="keyword",defaultValue = "") String mc){
		Page<Cinema> pageCinemas=cinemaP.findByNameContains(mc,PageRequest.of(page,size));
		List<Cinema> cinemas=pageCinemas.getContent();
		model.addAttribute("cinemas",cinemas);
		model.addAttribute("keyword",mc);
		model.addAttribute("pages",new int[pageCinemas.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		return"cinema";
	}
	@GetMapping("/deleteCinema")
	public String deleteCinema(Long id,int page,int size,String keyword)
	{ 
		cinemaP.deleteById(id);
		return "redirect:/cinema?page="+page+"&size="+size+"&keyword="+keyword;
	}
	@PostMapping("/addCinema")
	public String addCinema( @Valid Cinema cinema,BindingResult bindingResult,Model model)
	{		
		if(bindingResult.hasErrors())
			return "/CinemaForme";
		cinemaP.save(cinema);
		model.addAttribute("cinema",cinema);
		return "CinemaConfirmation";
	}
	@GetMapping("/CinemaForme")
	public String formCinema(Model model)
	{ 	
		List<Ville> villes=villeP.findAll();
		model.addAttribute("villes",villes);
		model.addAttribute("cinema",new Cinema());
		return "/CinemaForme";
		}
	@GetMapping("editCinema")
	public String editCinema(Model model,Long id)
	{ 
		List<Ville> villes=villeP.findAll();
		model.addAttribute("villes",villes);
		Cinema cinema=cinemaP.findById(id).get();
		model.addAttribute("cinema",cinema);
		String mode="edit";
		model.addAttribute("mode",mode);
		return "/CinemaForme";
		}
}
