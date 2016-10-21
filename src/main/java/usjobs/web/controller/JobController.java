package usjobs.web.controller;

import java.security.Principal;

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
	public String jobPosting(@RequestParam int jobid, ModelMap models, Principal principal) {
		// only if there's someone logged in do we attempt to get current user.
		// I need
		// the user so that we don't let users favorite or apply to a job if
		// theyve already done so.
		if (principal != null) {
			User currentUser = userDao.getUser(Security.getUserDetails().getUsername());
			models.put("currentUser", currentUser);
		}
		JobPosting job = jobPostingDao.getJobPosting(jobid);
		models.put("jobPosting", job);
		return "job/post";
	}

	@RequestMapping(value = "/job/favorite.html", method = RequestMethod.POST)
	public String addFavorites(@RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		User user = userDao.getProfileUser(Security.getUserDetails().getUsername());
		if (jobPosting.getUsersFavorited().contains(user)) {
			jobPosting.removeUsersFavorited(user);
		} else {
			jobPosting.addUsersFavorited(user);
		}
		jobPostingDao.jobFavoritedOrApplied(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}

	@RequestMapping(value = "/job/apply.html", method = RequestMethod.POST)
	public String addApplied(@RequestParam int jobId) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobId);
		User user = userDao.getProfileUser(Security.getUserDetails().getUsername());
		jobPosting.addUsersApplied(user);
		jobPostingDao.jobFavoritedOrApplied(jobPosting);
		return "redirect:view.html?jobid=" + jobId;
	}
	
	@RequestMapping(value="/job/apply.html", method = RequestMethod.GET)
	public String apply(@RequestParam int jobId, @RequestParam int seekerId, ModelMap models){
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobId);
		User user = userDao.getProfileUser(Security.getUserDetails().getUsername());
		models.put("applyJob", user);
		return "job/apply";
	}
}
