package at.fh.swenga.places.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserCategoryDao;
import at.fh.swenga.places.dao.UserCategoryRepository;
import at.fh.swenga.places.dao.UserRepository;
import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.RecommendationModel;
import at.fh.swenga.places.model.UserCategoryModel;
import at.fh.swenga.places.model.UserModel;

@Controller
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class PlacesControler {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserCategoryDao userCatDao;
	
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
	
	 @GetMapping("/index")
	 @Transactional
	 public String getIndex(Model model) {
			
			
			return "index";
			
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
		
		List<CountryModel> countries = countryRepository.findAll();
		model.addAttribute("countries", countries);
			
		return "register";
		
	}
	
	@PostMapping("/register")
	@Transactional
	public String registerUser(@Valid UserModel user, BindingResult res, Model model, Authentication auth) {
		if (userRepository.findFirstByUsername(user.getUsername()) != null) {
			model.addAttribute("error", "Username is already in use, sorry!");
		} else {
			Set<UserCategoryModel> roles = new HashSet<UserCategoryModel>();
			UserCategoryModel catV = userCatDao.getRole("ROLE_VIEWER");
			UserCategoryModel catU = userCatDao.getRole("ROLE_USER");
			roles.add(catV);
			roles.add(catU);
			
			
			user.encryptPassword();
			user.setCategory(roles);
			user.setEnabled(true);
			userRepository.save(user);
			
			model.addAttribute("message", "Welcome" + user.getUsername());
		}
		
		return "index";
	}
	
	@GetMapping("/userProfile")
	@Transactional
	public String getProfile(Model model, Authentication authentication) {
		
		UserModel user = userRepository.findFirstByUsername(authentication.getName());


		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);			
		}
		
		return "userProfile";
		
	}
	
	 @ExceptionHandler(Exception.class)
	 public String handleAllException(Exception ex) {

	  return"error";

	 }
	

	
}
