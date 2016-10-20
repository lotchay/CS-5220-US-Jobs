package usjobs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import usjobs.model.dao.JobPostingDao;

@Controller
public class HomeController {
	
	@Autowired
	private JobPostingDao jobPostingDao;
	
	@RequestMapping( value={"/index.html", "/home.html"}, 
			method = RequestMethod.GET)
	public String home(ModelMap models) {
		models.put("jobPostings", jobPostingDao.getJobPostings());
		return "home";
	}
	
	@RequestMapping( value={"/index.html", "/home.html"}, 
			method = RequestMethod.POST )
	public String home(@RequestParam String searchBar, ModelMap models) {
		models.put("searchResultJob", jobPostingDao.searchJobs(searchBar));
		models.put("searchBar", searchBar);
		return home(models);
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
