package usjobs.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import usjobs.model.Employer;
import usjobs.model.JobPosting;
import usjobs.model.JobSeeker;
import usjobs.model.User;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@Controller
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobPostingDao jobPostingDao;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/job/view.html")
	public String jobPosting(@RequestParam int jobid, ModelMap models) {
		JobPosting job = jobPostingDao.getJobPosting(jobid);
		models.put("jobPosting", job);
		return "job-posting";
	}

	@RequestMapping(value = "/job/favorite.html", method = RequestMethod.POST)
	public String addFavorites(@RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		User user = userDao.getUser(Security.getUserDetails().getUsername());
		jobPosting.addUsersFavorited(user);
		jobPostingDao.jobFavoritedOrApplied(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}

	@RequestMapping(value = "/job/apply.html", method = RequestMethod.POST)
	public String addApplied(@RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		User user = userDao.getUser(Security.getUserDetails().getUsername());
		jobPosting.addUsersApplied(user);
		jobPostingDao.jobFavoritedOrApplied(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}
}
