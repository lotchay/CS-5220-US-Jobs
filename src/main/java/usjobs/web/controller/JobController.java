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

@Controller
@SessionAttributes({"newJob", "editJob"})
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
	public String addFavorites(@RequestParam int userid, @RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		JobSeeker user = (JobSeeker) userDao.getUser(userid);
		jobPosting.addUsersFavorited(user);
		jobPostingDao.save(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}

	@RequestMapping(value = "/job/apply.html", method = RequestMethod.POST)
	public String addApplied(@RequestParam int userid, @RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		JobSeeker user = (JobSeeker) userDao.getUser(userid);
		jobPosting.addUsersApplied(user);
		jobPostingDao.save(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}
    
	@RequestMapping(value = "/user/addJob.html", method = RequestMethod.POST)
	public String addJob(@RequestParam int employerId, @ModelAttribute("newJob") JobPosting newJob, SessionStatus session) {
		jobPostingDao.save(newJob);
		session.setComplete();
		return "redirect:profile.html?id=" + employerId;
	}

	@RequestMapping(value = "/user/deleteJob.html", method = RequestMethod.POST)
	public String deleteJob(@RequestParam int employerId, @RequestParam int jobId) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobId);
		jobPostingDao.delete(jobPosting);
		return "redirect:profile.html?id=" + employerId;
	}

	@RequestMapping(value = "/user/editJob.html", method = RequestMethod.GET)
	public String editJobForm(@RequestParam int employerId, @RequestParam int jobId, ModelMap models) {
		models.put("editJob", jobPostingDao.getJobPosting(jobId));
		return "job-edit";
	}

	@RequestMapping(value = "/user/editJob.html", method = RequestMethod.POST)
	public String editJobForm(@RequestParam int employerId, @ModelAttribute("editJob") JobPosting editJob, SessionStatus sessionStatus) {
		jobPostingDao.save(editJob);
		sessionStatus.setComplete();
		return "redirect:profile.html?id=" + employerId;
	}
}
