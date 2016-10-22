package usjobs.web.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.Hibernate;
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

import usjobs.model.Application;
import usjobs.model.Employer;
import usjobs.model.JobPosting;
import usjobs.model.JobSeeker;
import usjobs.model.User;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@Controller
@SessionAttributes({ "application" })
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobPostingDao jobPostingDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ApplicationDao applicationDao;

	@RequestMapping("/job/list.html")
	public String list(ModelMap models) {

		// Get all users from database and pass them to JSP
		List<JobPosting> jobPostings = jobPostingDao.getJobPostings();

		models.put("jobPostings", jobPostings);

		return "job/list";
	}

	@RequestMapping("/job/view/{id}.html")
	public String view(@PathVariable Integer id, ModelMap models) {

		// Get user from database and pass it to JSP
		models.put("jobPosting", jobPostingDao.getJobPosting(id));

		return "job/view";
	}

	@RequestMapping(value = "/job/disable.html", method = RequestMethod.GET)
	public String disable(@RequestParam Integer id, ModelMap models) {

		// Disable the user
		jobPostingDao.getJobPosting(id).setEnabled(false);

		// Save the user to database
		jobPostingDao.save(jobPostingDao.getJobPosting(id));

		// Get all users from database and pass them to JSP
		List<JobPosting> jobPostings = jobPostingDao.getJobPostings();

		models.put("jobPostings", jobPostings);

		return "job/list";
	}

	@RequestMapping(value = "/job/enable.html", method = RequestMethod.GET)
	public String enable(@RequestParam Integer id, ModelMap models) {

		// Disable the user
		jobPostingDao.getJobPosting(id).setEnabled(true);

		// Save the user to database
		jobPostingDao.save(jobPostingDao.getJobPosting(id));

		// Get all users from database and pass them to JSP
		List<JobPosting> jobPostings = jobPostingDao.getJobPostings();

		models.put("jobPostings", jobPostings);

		return "job/list";
	}

	@RequestMapping(value = "/job/post.html")
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
	public String addFavorites(@RequestParam int jobid, @ModelAttribute Application application) {
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

	@RequestMapping(value = "/job/apply.html", method = RequestMethod.GET)
	public String apply(@RequestParam int jobId, @RequestParam int seekerId, ModelMap models) {
		Application application = new Application();
		application.setJobApplied(jobPostingDao.getJobPosting(jobId));
		User user = userDao.getProfileUser(Security.getUserDetails().getUsername());
		if (user.isSeeker()) {
			application.setSeeker((JobSeeker) user);
		}
		models.put("application", application);
		return "job/apply";
	}

	@RequestMapping(value = "/job/apply.html", method = RequestMethod.POST)
	public String addApplied(@ModelAttribute("application") Application application, SessionStatus session) {
		Application app = applicationDao.saveApplication(application);
		JobPosting jobPosting = app.getJobApplied();
		jobPosting.addUsersApplied(userDao.getProfileUser(Security.getUserDetails().getUsername()));
		jobPostingDao.jobFavoritedOrApplied(jobPosting);
		return "redirect:post.html?jobid=" + application.getJobApplied().getId();
	}
}
