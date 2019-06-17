package at.fh.swenga.places.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserCategoryDao;
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

	@Secured("ROLE_USER")
	@GetMapping("/achievements")
	@Transactional
	public String getAchievments(Model model) {

		return "achievements";

	}

	@Secured("ROLE_USER")
	@RequestMapping("/browse")
	@Transactional
	public String fillRecommendations(Model model) {

		List<RecommendationModel> allModels = recommendationRepository.findAll();
		RecommendationModel defaultModel = new RecommendationModel();

		if (allModels.size() > 0) {
			model.addAttribute("recommendations", allModels);
		} else {
			model.addAttribute("recommendations", defaultModel);
		}

		return "browse";
	}

	@Secured("ROLE_USER")
	@GetMapping("/contacts")
	@Transactional
	public String getContacts(Model model) {

		return "contacts";

	}

	@Secured("ROLE_USER")
	@GetMapping("/dashboard")
	@Transactional
	public String getDashboard(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		return "dashboard";

	}

	@Secured("ROLE_USER")
	@PostMapping("/editUser")
	@Transactional
	public String editUser(@Valid UserModel changedUserModel, BindingResult bindingResult, Model model) {

		List<CountryModel> countries = countryRepository.findAll();
		model.addAttribute("countries", countries);

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/userProfile";
		}

		UserModel user = userRepository.findById(changedUserModel.getId());

		// Change the attributes		
		user.setUsername(changedUserModel.getUsername());
		user.setFirstName(changedUserModel.getFirstName());
		user.setLastName(changedUserModel.getLastName());
		user.setMail(changedUserModel.getMail());
		user.setCountry(changedUserModel.getCountry());
		
		userRepository.save(user);

		// Save a message for the web page
		model.addAttribute("message", "Changed user " + changedUserModel.getId());

		return "userProfile";
	}

	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
	@GetMapping("/journey")
	@Transactional
	public String getJourney(Model model) {

		return "journey";

	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@Secured("ROLE_USER")
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

		return "dashboard";
	}

	@Secured("ROLE_USER")
	@GetMapping("/userProfile")
	@Transactional
	public String getProfile(Model model, Authentication authentication) {

		List<CountryModel> countries = countryRepository.findAll();
		model.addAttribute("countries", countries);
	
		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}

		return "userProfile";

	}

	@RequestMapping("/visitor")
	@Transactional
	public String getVisitor(Model model) {

		UserModel user = userRepository.getDefaultUser("default");

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}

		return "index";

	}
	
	
	/*@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/changeUserRole")
	public String changeUserRole(@RequestParam("username") String username, Model model,
			Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(username);

		if (user != null && user.isEnabled()) {

			if (user.getUsername().equalsIgnoreCase(authentication.getName())) {
				model.addAttribute("warningMessage", "You cannot change your own user role!");
				return showUserManagement(model, authentication);
			}

			model.addAttribute("user", user);
			return "changeUserRole";
		} else {
			model.addAttribute("errorMessage", "Error while reading User data! Either dissabled or does not exist");
			return showUserManagement(model, authentication);
		}
}*/



	@Secured({ "ROLE_USER" })
	@PostMapping(value = "/changePassword")
	public String changePassword(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, 
			Model model, Authentication authentication) {

		UserModel user = userRepository.getDefaultUser("default");

		if (user != null && user.isEnabled()) {
			if ((user.getUsername().equalsIgnoreCase(authentication.getName())
					|| authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {

				user.setPassword(password);
				user.encryptPassword();
				userRepository.save(user);

				model.addAttribute("message", "Password successfully changed for User: " + username);
				return"userProfile";
				
			} else {
				model.addAttribute("warningMessage", "Error while reading User data!");
				return"error";
			}
		} 
		
		return"userProfile";
	}

	

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}

}
