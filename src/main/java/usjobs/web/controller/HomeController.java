package usjobs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import usjobs.model.dao.JobPostingDao;

@Controller
public class HomeController {
	
	@Autowired
	private JobPostingDao jobPostingDao;
	
	@RequestMapping( {"/index.html","/home.html"} )
	public String home(ModelMap models) {
		models.put("jobPostings", jobPostingDao.getJobPostings());
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
