package at.fh.swenga.places.controller;
 

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.places.dao.UserCategoryRepository;
import at.fh.swenga.places.dao.UserRepository;
 
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

