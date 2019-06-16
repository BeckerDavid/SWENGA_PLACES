package at.fh.swenga.places.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserRepository;
import at.fh.swenga.places.model.RecommendationModel;

@Controller
public class PlacesControler {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecommendationRepository recommendationRepository;
	
	@GetMapping("/achievements")
	@Transactional
	public String getAchievments(Model model) {
		
			
		return "achievements";
		
	}
	
	@RequestMapping("/browse")
	@Transactional
	public String fillRecommendations (Model model) {
		
		List<RecommendationModel> allModels = recommendationRepository.findAll();
		RecommendationModel defaultModel = new RecommendationModel();

		if (allModels.size()>0) {
			model.addAttribute("recommendations", allModels);
		}else {
			model.addAttribute("recommendations", defaultModel);
		}
		
		return "browse";
	}
	
	@GetMapping("/contacts")
	@Transactional
	public String getContacts(Model model) {
		
			
		return "contacts";
		
	}
	
	@GetMapping("/dashboard")
	@Transactional
	public String getDashboard(Model model) {
		
			
		return "dashboard";
		
	}
	
	@GetMapping("/following")
	@Transactional
	public String getFollowing(Model model) {
		
			
		return "following";
		
	}
	
	@GetMapping("/journey")
	@Transactional
	public String getJourney(Model model) {
		
			
		return "journey";
		
	}
	
	
	@GetMapping("/recommendations")
	@Transactional
	public String getRecommendations(Model model) {
		
			
		return "recommendations";
		
	}

	@GetMapping("/register")
	@Transactional
	public String getRegister(Model model) {
		
			
		return "register";
		
	}
	
	@GetMapping("/userProfile")
	@Transactional
	public String getProfile(Model model) {
		
		
		
		return "userProfile";
		
	}
	
	 @ExceptionHandler(Exception.class)
	 public String handleAllException(Exception ex) {

	  return"error";

	 }
	

	
}
