package at.fh.swenga.places.controller;
 

import org.springframework.stereotype.Controller;
 
@Controller
public class SecurityController {

	/*
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	UserCategoryRepository userCategoryRepository,
	
 
	@RequestMapping("/fillUsers")
	@Transactional
	public String fillData(Model model) {
 
		UserRepository adminRole = userCategoryRepository.getRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRepository("ROLE_ADMIN");
 
		UserRepository UserRepository = userCategoryRepository.getRole("ROLE_USER");
		if (UserRepository == null)
			UserRepository = new UserRepository("ROLE_USER");
 
		User admin = new User("admin", "password", true);
		admin.encryptPassword();
		admin.addUserRepository(UserRepository);
		admin.addUserRepository(adminRole);
		userRepository.persist(admin);
 
		User user = new User("user", "password", true);
		user.encryptPassword();
		user.addUserRepository(UserRepository);
		userRepository.persist(user);
 
		return "forward:login";
	}
 
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";
  */
}

