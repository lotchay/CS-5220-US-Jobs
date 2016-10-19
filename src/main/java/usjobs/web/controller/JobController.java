package usjobs.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import usjobs.model.Employer;
import usjobs.model.JobPosting;
import usjobs.model.JobSeeker;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;

@Controller
@RequestMapping("/job")
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobPostingDao jobPostingDao;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/view.html")
	public String jobPosting(@RequestParam int jobid, ModelMap models) {
		JobPosting job = jobPostingDao.getJobPosting(jobid);
		models.put("jobPosting", job);
		return "job-posting";
	}

	@RequestMapping(value = "/favorite.html", method = RequestMethod.POST)
	public String addFavorites(@RequestParam int userid, @RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		JobSeeker user = (JobSeeker) userDao.getUser(userid);
		jobPosting.addUsersFavorited(user);
		jobPostingDao.save(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}

	@RequestMapping(value = "/apply.html", method = RequestMethod.POST)
	public String addApplied(@RequestParam int userid, @RequestParam int jobid) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobid);
		JobSeeker user = (JobSeeker) userDao.getUser(userid);
		jobPosting.addUsersApplied(user);
		jobPostingDao.save(jobPosting);
		return "redirect:view.html?jobid=" + jobid;
	}
}
