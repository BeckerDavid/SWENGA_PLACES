package at.fh.swenga.places.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserRepository;
import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.UserCategoryModel;
import at.fh.swenga.places.model.UserModel;

@Controller
public class PlacesControler {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecommendationRepository recommendationRepository;

	@PostMapping("/login")
	@Transactional
	public String registerUser(Model model) {
	List<UserModel> users = null;
	
	users = userRepository.findAll();
	
		return "forward:index";
	}
	
	@RequestMapping(value = { "/", "browse" })
	@Transactional
	public String fillRecommendations (Model model) {
		model.addAttribute("recommendations", recommendationRepository.findAll());
		
		return "forward:browse";
	}
	
	

	
	@GetMapping("/recommendations")
	@Transactional
	public String getRecommendations(Model model) {
		List<CountryModel> countries = countryRepository.findAll();
		
		model.addAttribute("countries", countries);
		
		return "forward:recommendations";
	}

	
}
