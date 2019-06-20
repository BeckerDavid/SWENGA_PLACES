package at.fh.swenga.places.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.places.dao.CountryRepository;
import at.fh.swenga.places.dao.JourneyRepository;
import at.fh.swenga.places.dao.PictureRepository;
import at.fh.swenga.places.dao.PlaceRepository;
import at.fh.swenga.places.dao.RecommendationRepository;
import at.fh.swenga.places.dao.UserCategoryDao;
import at.fh.swenga.places.dao.UserDao;
import at.fh.swenga.places.dao.UserRepository;
import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.JourneyModel;
import at.fh.swenga.places.model.PictureModel;
import at.fh.swenga.places.model.PlaceModel;
import at.fh.swenga.places.model.RecommendationModel;
import at.fh.swenga.places.model.UserCategoryModel;
import at.fh.swenga.places.model.UserModel;

@Controller
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class PlacesController {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserCategoryDao userCatDao;

	@Autowired
	RecommendationRepository recommendationRepository;

	@Autowired
	PlaceRepository placeRepo;

	@Autowired
	UserDao userDao;

	@Autowired
	JourneyRepository journeyRepo;
	
	@Autowired
	PictureRepository pictureRepository;


	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage templateMessage;

	
	@Secured("ROLE_USER")
	@GetMapping("/achievements")
	@Transactional
	public String getAchievments(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		return "achievements";

	}

	@Secured("ROLE_VIEWER")
	@RequestMapping("/browse")
	@Transactional
	public String fillRecommendations(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());
		List<CountryModel> countries = countryRepository.findAll();
		
		model.addAttribute("countries", countries);
		
		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		List<RecommendationModel> allModels = recommendationRepository.listNewest(0, "");
		RecommendationModel defaultModel = new RecommendationModel();

		if (allModels.size() > 0) {
			model.addAttribute("recommendations", allModels);
		} else {
			model.addAttribute("recommendations", defaultModel);
		}

		return "browse";
	}
	
	@GetMapping("uploadProfilePicture")
	public String uploadProfilePicture (Model model, @RequestParam("id") int id) {
		model.addAttribute("id", id);
		return "uploadPicture";
	}
	
	@PostMapping(value = "upload")
	public String uploadDocument(Model model, @RequestParam("id") int id,
			@RequestParam("myFile") MultipartFile file) {
 
		try {
 
			UserModel user = userRepository.findById(id);
 
			// Already a document available -> delete it
			if (user.getProfilePicture() != null) {
				pictureRepository.delete(user.getProfilePicture());
				// Don't forget to remove the relationship too
				user.setProfilePicture(null);
			}
 
			// Create a new document and set all available infos
 
			PictureModel picture = new PictureModel();
			picture.setContent(file.getBytes());
			picture.setContentType(file.getContentType());
			picture.setCreated(new Date());
			picture.setFilename(file.getOriginalFilename());
			picture.setName(file.getName());
			user.setProfilePicture(picture);
			userRepository.save(user);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}
 
		return "redirect:/userProfile";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @RequestParam int countryId, @RequestParam String searchType) {

		List<CountryModel> countries = countryRepository.findAll();
		List<RecommendationModel> recommendations = null;

		model.addAttribute("countries", countries);

		int count = 0;

		switch (searchType) {
		case "query1":
			recommendations = recommendationRepository.findAll();
			break;
		case "query2":
			recommendations = recommendationRepository.listNewest(countryId, searchString);
			break;
		case "query3":
			recommendations = recommendationRepository.listByPlaces(countryId, searchString);
			break;
		case "query4":
			recommendations = recommendationRepository.listBySeason(countryId, searchString);
			break;
		case "query5":
			recommendations = recommendationRepository.listByUsername(countryId, searchString);
			break;
		default:
			recommendations = recommendationRepository.listNewest(0, "");
		}

		model.addAttribute("recommendations", recommendations);

		if (recommendations != null) {
			model.addAttribute("count", recommendations.size());
		} else {
			model.addAttribute("count", count);
		}
		return "browse";
	}

	@Secured("ROLE_USER")
	@GetMapping("/contacts")
	@Transactional
	public String getContacts(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}
		return "contacts";

	}

	@Secured("ROLE_USER")
	@GetMapping("/dashboard")
	@Transactional
	public String getDashboard(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}
		return "dashboard";

	}

	@Secured("ROLE_USER")
	@PostMapping("/editUser")
	@Transactional
	public String editUser(@Valid UserModel changedUserModel, @RequestParam(value = "countryId") int cid,
			@RequestParam(value = "noShow") boolean no, BindingResult bindingResult, Model model,
			Authentication authentication) {

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

		UserModel user = userRepository.getOne(changedUserModel.getId());
		CountryModel country = countryRepository.getOne(cid);
		UserModel user1 = userRepository.findFirstByUsername(authentication.getName());

		if (user1 != null && user1.isEnabled()) {

			model.addAttribute("user", user1);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		if (!changedUserModel.getUsername().equals(user.getUsername())) {
			if (userRepository.findFirstByUsername(changedUserModel.getUsername()) != null) {
				model.addAttribute("error", "Username is already in use, sorry!");
				return "dashboard";
			}
		}

		// Change the attributes
		user.setUsername(changedUserModel.getUsername());
		user.setFirstName(changedUserModel.getFirstName());
		user.setLastName(changedUserModel.getLastName());
		user.setMail(changedUserModel.getMail());
		user.setCountry(country);
		user.setPrivate(no);

		userRepository.save(user);

		return "redirect:/logout";

	}

	@Secured("ROLE_USER")
	@GetMapping("/following")
	@Transactional
	public String getFollowing(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}
		return "following";

	}

	@GetMapping("/index")
	@Secured("ROLE_VIEWER")
	@Transactional
	public String getIndex(Model model) {

		return "index";
	}

	@Secured("ROLE_USER")
	@GetMapping("/journey")
	@Transactional
	public String getJourney(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		List<CountryModel> countries = countryRepository.findAll();
		model.addAttribute("countries", countries);

		return "journey";

	}

	@Secured("ROLE_USER")
	@PostMapping("/addJourney")
	@Transactional
	public String addJourney(JourneyModel journey, Model model, @RequestParam(value = "countryId") int cid,
			@RequestParam(value = "countryId2") int cid2, @RequestParam(value = "departureDateS") String depD,
			@RequestParam(value = "arrivalDateS") String arD, Authentication auth) throws ParseException {

		Set<CountryModel> countries = new HashSet<CountryModel>();
		CountryModel country = countryRepository.getOne(cid);
		countries.add(country);
		if (cid2 != 999) {
			CountryModel country2 = countryRepository.getOne(cid2);
			countries.add(country2);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateD = sdf.parse(depD);
		Calendar calD = Calendar.getInstance();
		calD.setTime(dateD);
		Date dateS = sdf.parse(arD);
		Calendar calS = Calendar.getInstance();
		calS.setTime(dateS);

		journey.setCountries(countries);
		journey.setDepartureDate(calS);
		journey.setArrivalDate(calD);

		UserModel user = userRepository.findByUsername(auth.getName());
		journey.setUsers(user);

		journeyRepo.save(journey);

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		return "myJourneys";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/logout")
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
	public String getRecommendations(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());
		List<CountryModel> countries = countryRepository.findAll();

		model.addAttribute("countries", countries);

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		return "recommendations";

	}

	@Secured("ROLE_USER")
	@PostMapping("/addRecommendation")
	@Transactional
	public String addRecommendation(Model model, @Valid RecommendationModel rec, Authentication auth,
			@RequestParam("placeName") String placeName, @RequestParam("countryId") int countryId,
			@RequestParam(value = "recommendationImage", required = false) MultipartFile imageFile) {

		UserModel user = userRepository.findByUsername(auth.getName());


		if (imageFile != null) {
			try {
				PictureModel picture = new PictureModel();
				picture.setContent(imageFile.getBytes());
				picture.setContentType(imageFile.getContentType());
				picture.setCreated(new Date());
				picture.setFilename(imageFile.getOriginalFilename());
				picture.setName(imageFile.getName());
				rec.setRecommendationPicture(picture);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Error:" + e.getMessage());
			}
		}


		PlaceModel place = placeRepo.findByName(placeName);
		Optional<CountryModel> countryOpt = countryRepository.findById(countryId);
		CountryModel country = countryOpt.get();
		if (place == null) {
			place = new PlaceModel(placeName, country);
			placeRepo.save(place);
		} else if (place.getCountry() != country) {
			place = new PlaceModel(placeName, country);
			placeRepo.save(place);
		}
		rec.setApproved(false);
		rec.setPlace(place);
		rec.setRating(0);
		rec.setUser(user);
		recommendationRepository.save(rec);

		return "redirect:/recommendations";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/myRecommendations")
	@Transactional
	public String getMyRecommendations(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());
		ArrayList<RecommendationModel> recommendations = recommendationRepository.findByUserId(user.getId());

		List<CountryModel> countries = countryRepository.findAll();

		model.addAttribute("countries", countries);

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}

			model.addAttribute("recommendations", recommendations);
		}

		return "myRecommendations";

	}

	// Spring 4: @RequestMapping(value = "/deleteMakeup", method = RequestMethod.GET)
	@GetMapping("/deleteRecommendation")
	public String delete(Model model, @RequestParam int id) {

		recommendationRepository.removeById(id);

		return"redirect:/myRecommendations";
	}
	
	@Secured("ROLE_VIEWER")
	@GetMapping("/register")
	@Transactional
	public String getRegister(Model model) {

		List<CountryModel> countries = countryRepository.findAll();
		model.addAttribute("countries", countries);

		return "register";

	}

	@Secured("ROLE_VIEWER")
	@PostMapping("/register")
	@Transactional
	public String registerUser(@Valid UserModel user, BindingResult res, Model model, Authentication auth,
			@RequestParam(value = "countryId") int cid) {
		if (userRepository.findFirstByUsername(user.getUsername()) != null) {
			model.addAttribute("error", "Username is already in use, sorry!");
		} else {
			Set<UserCategoryModel> roles = new HashSet<UserCategoryModel>();
			UserCategoryModel catV = userCatDao.getRole("ROLE_VIEWER");
			UserCategoryModel catU = userCatDao.getRole("ROLE_USER");
			roles.add(catV);
			roles.add(catU);

			CountryModel country = countryRepository.getOne(cid);
			user.setPrivate(false);
			user.encryptPassword();
			user.setCategory(roles);
			user.setEnabled(true);
			user.setCountry(country);
			user.setToken(UUID.randomUUID().toString());
			userDao.persist(user);

			model.addAttribute("message", "Welcome" + user.getUsername());
		}
		model.addAttribute("user", user);
		if (user.getProfilePicture() != null) {

			Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
			PictureModel pp = ppOpt.get();
			byte[] profilePicture = pp.getContent();

			StringBuilder sb = new StringBuilder();
			sb.append("data:image/jpeg;base64,");
			sb.append(Base64.encodeBase64String(profilePicture));
			String image = sb.toString();

			model.addAttribute("image", image);
		}
		return "redirect:/login";
	}
	

	@Secured("ROLE_USER")
	@GetMapping("/forgotPassword")
	@Transactional
	public String getForgotPasswordSite(Model model) {
		
		return ("forgotPassword");
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/forgotPassword")
	@Transactional
	public String getForgotPassword(@RequestParam("username") String username, Model model, Authentication authentication,
			@RequestParam("mail") String mail) {
		
		UserModel user = userRepository.findByUsername(username);

		if (user != null && user.isEnabled() && user.getMail() != null && user.getToken() != null) {
			sendPasswordResetMail(user);
			model.addAttribute("message", "Reset Passwort Email for User: " + user.getUsername() + " has been sent.");
			return "login";
		} else {
			model.addAttribute("errorMessage", "Error while reading user data!");
		return "login";
		}
}

	private void sendPasswordResetMail(UserModel user) {
		
		String content = "Copy and paste the following link in your browser to reset your password: ";
		String resetPasswordUrl = "http://localhost:8080/Places/resetPassword?token=" + user.getToken();
		
	 
		// Create a thread safe "copy" of the template message and customize it
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);

		// You can override default settings from dispatcher-servlet.xml:
		msg.setTo(user.getMail());
		msg.setSubject("Password Reset");
		msg.setText(String.format(msg.getText(), user.getFirstName() + ' ' + user.getLastName(), content,
				resetPasswordUrl));
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}	
	
	@GetMapping("/resetPassword")
	@Transactional
	public String resetPassword(@RequestParam("token") String token, Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByToken(token);
		if (user != null && user.isEnabled()) {
			model.addAttribute("message", "You can now reset the password.");
			model.addAttribute("token", token);
			return "resetPassword";
		} else
			model.addAttribute("errorMessage", "Error while reading user data!");
		return "login";
	}	
	

	@PostMapping("/resetPassword")	
	@Transactional
	public String resetPassword(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, 
			@RequestParam(value = "token", required=false) String token, Model model,
			Authentication authentication) {
		
		UserModel user = userRepository.findFirstByUsername(username);
		UserModel tokenUser = userRepository.findByToken(token);
		
		if (user != null && user.isEnabled() && user.equals(tokenUser)) {
			user.setPassword(password);
			user.encryptPassword();
			userRepository.save(user);
			model.addAttribute("message", "Password successfully reset");
			return "login";
		} else {
			model.addAttribute("errorMessage", "Error while reading user data!");
		}
		return "login";
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
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		return "userProfile";

	}

	@Secured("ROLE_VIEWER")
	@RequestMapping("/visitor")
	@Transactional
	public String getVisitor(Model model) {

		UserModel user = userRepository.getDefaultUser("default");

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}

		return "index";

	}

	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin_userlist")
	@Transactional
	public String getUserList(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			if ((authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {

			model.addAttribute("user", user);
			List<UserModel> users = userRepository.findAll();


			model.addAttribute("users", users);
			model.addAttribute("count", users.size());
			return "admin_userlist";
			}

			return "logout";
		}

		return "logout";

	}
	
	
	@RequestMapping("/fillUsers")
	@Secured("ROLE_ADMIN")
	@Transactional
	public String fillUsers(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		
		List<UserModel> users = userRepository.findAll();
		
		
		model.addAttribute("users", users);
		model.addAttribute("count", users.size());
		
		//CountryModel country1 = new CountryModel("asf","asfd");
		
		//UserModel user1 = new UserModel("test", "test", "test", "e", "asraerw",
		//		country1, false);
	
		return "/admin_userlist";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteData(Model model, Authentication authentication, @RequestParam int id) {
		userRepository.disableUser(id);

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		
		return "forward:fillUsers";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/enable")
	public String enableData(Model model, Authentication authentication, @RequestParam int id) {
		userRepository.enableUser(id);

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		
		return "forward:fillUsers";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/like")
	public String likeRec(Model model, Authentication authentication, @RequestParam int uId, @RequestParam int rId) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());
		//userRepository.addFavRec(uId,rId);
		
		//RecommendationModel rec = recommendationRepository.findById(rId);

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
		}
		
		return "forward:fillUsers";
	}
	
	/*
	 * @Secured({ "ROLE_ADMIN" })
	 * 
	 * @GetMapping(value = "/changeUserRole") public String
	 * changeUserRole(@RequestParam("username") String username, Model model,
	 * Authentication authentication) {
	 * 
	 * UserModel user = userRepository.findFirstByUsername(username);
	 * 
	 * if (user != null && user.isEnabled()) {
	 * 
	 * if (user.getUsername().equalsIgnoreCase(authentication.getName())) {
	 * model.addAttribute("warningMessage",
	 * "You cannot change your own user role!"); return showUserManagement(model,
	 * authentication); }
	 * 
	 * model.addAttribute("user", user); return "changeUserRole"; } else {
	 * model.addAttribute("errorMessage",
	 * "Error while reading User data! Either dissabled or does not exist"); return
	 * showUserManagement(model, authentication); } }
	 */

	@Secured({ "ROLE_USER" })
	@PostMapping(value = "/changePassword")
	public String changePassword(@RequestParam(value = "newPassword") String passwordNew,
			@RequestParam(value = "usernameHidden") String username, Model model, Authentication authentication) {

		UserModel user = userRepository.findByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {
			if ((user.getUsername().equalsIgnoreCase(authentication.getName())
					|| authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
				
				user.setPassword(passwordNew);
				user.encryptPassword();
				userRepository.save(user);
				System.out.println("PW correcot");
				model.addAttribute("message", "Password successfully changed for User: " + username);
				
				model.addAttribute("user", user);
                
			}
			return "redirect:logout";
		}

		return "error";
	}

	@Secured({ "ROLE_USER" })
	@GetMapping(value = "/journeys")
	@Transactional
	public String showJourneys(Model model, Authentication auth) {

		UserModel current = userRepository.findByUsername(auth.getName());
		ArrayList<JourneyModel> journeys = journeyRepo.findByUsersId(current.getId());

		if (current != null && current.isEnabled()) {

			model.addAttribute("user", current);
			if (current.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(current.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}
		model.addAttribute("journeys", journeys);

		return "myJourneys";
	}

//	@Secured("ROLE_USER")
//	@GetMapping("/getPicturePNG")
//	@Transactional
//	public String getPicturePNG(Model model) {
//		model.addAttribute(model)
//		if (profilePicture == null) {
//			return "bootstrap/img/default-avatar.png";
//		}
//		else {
//			return "data:image/png;base64," + profilePicture;
//		}
//	}

	@RequestMapping(value = "maps")
	public String showMap(Model model, Authentication authentication) {

		UserModel user = userRepository.findFirstByUsername(authentication.getName());

		if (user != null && user.isEnabled()) {

			model.addAttribute("user", user);
			if (user.getProfilePicture() != null) {

				Optional<PictureModel> ppOpt = pictureRepository.findById(user.getProfilePicture().getId());
				PictureModel pp = ppOpt.get();
				byte[] profilePicture = pp.getContent();

				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpeg;base64,");
				sb.append(Base64.encodeBase64String(profilePicture));
				String image = sb.toString();

				model.addAttribute("image", image);
			}
		}
		return "maps";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}

}
