package at.fh.swenga.places.controller;
 

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class SecurityController {
	
	@GetMapping("/login")
	@Transactional
	public String loginUser(Model model) {

	
		return "forward:/";
	}
 
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}
	
}

