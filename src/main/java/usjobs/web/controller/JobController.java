package usjobs.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import usjobs.model.JobPosting;
import usjobs.model.JobSeeker;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;

@Controller
public class JobController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	private JobPostingDao jobPostingDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/job-posting.html")
	public String jobPosting(@RequestParam int id, ModelMap models) {
		JobPosting job = jobPostingDao.getJobPosting(id);
		models.put("jobPosting", job);
		return "job-posting";
	}
    
    @RequestMapping(value = "/favoriteAdd.html", method = RequestMethod.POST)
    public String addFavorites(@RequestParam int jobid) {
    	JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
    	JobSeeker user = (JobSeeker) userDao.getUser(1);
    	jobPosting.addUsersFavorited(user);
    	jobPostingDao.save(jobPosting);
    	return "redirect:job-posting.html?id=" + jobid;
    }
    

    @RequestMapping(value = "/applyAdd.html", method = RequestMethod.POST)
    public String addApplied(@RequestParam int jobid) {
    	JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
    	JobSeeker user = (JobSeeker) userDao.getUser(1);
    	jobPosting.addUsersApplied(user);
    	jobPostingDao.save(jobPosting);
    	return "redirect:job-posting.html?id=" + jobid;
    }  
}
