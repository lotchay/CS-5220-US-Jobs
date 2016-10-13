package usjobs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping( {"/index.html","/home.html"} )
	public String home() {
		
		return "home";
	}
	
	@RequestMapping(value="/login.html")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/register.html")
	public String register() {
		return "register";
	}
}
