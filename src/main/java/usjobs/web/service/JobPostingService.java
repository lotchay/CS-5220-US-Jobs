package usjobs.web.service;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import usjobs.model.Application;
import usjobs.model.Employer;
import usjobs.model.JobPosting;
import usjobs.model.User;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@RestController
public class JobPostingService {
	private static final Logger logger = Logger.getLogger(JobPostingService.class);

	@Autowired
	JobPostingDao jobPostingDao;

	@Autowired
	ApplicationDao applicationDao;
	
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/service/job/{id}", method = RequestMethod.GET)
	public JobPosting getJobPosting(@PathVariable int id) {
		return jobPostingDao.getJobPosting(id);
	}

	@RequestMapping(value = "/service/job/{id}", method = RequestMethod.DELETE)
	public void deleteJob(@PathVariable int id) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(id);
		List<Application> applications = applicationDao.getJobApplications(id);
		for (Application app : applications) {
			app.setJobApplied(null); // since job is being deleted, app should
										// no longer reference that job
			applicationDao.saveApplication(app);
		}
		jobPostingDao.delete(jobPosting);
	}

	@RequestMapping(value = "/service/job/toggle/{id}", method = RequestMethod.PUT)
	public void toggleJob(@PathVariable int id) {
		JobPosting jobPosting = jobPostingDao.getJobPosting(id);
		boolean isOpened = jobPosting.isOpened();
		if (isOpened) {
			jobPosting.setDateClosed(new Date());
		} else {
			jobPosting.setDatePosted(new Date());
		}
		jobPosting.setOpened(!isOpened);
		jobPostingDao.save(jobPosting);
	}

	@RequestMapping(value = "/service/job/{id}", method = RequestMethod.PUT)
	public void editJob(@PathVariable int id, @RequestBody JobPosting jobPosting) {
//		logger.info(jobPosting.getJobTitle());
//		logger.info("Website: " + jobPosting.getWebsite());
		JobPosting originalJob = jobPostingDao.getJobPosting(id);
		originalJob.setJobTitle(jobPosting.getJobTitle());
		originalJob.setWebsite(jobPosting.getWebsite());
		originalJob.setLocation(jobPosting.getLocation());
		originalJob.setJobDescription(jobPosting.getJobDescription());
		originalJob.setSalary(jobPosting.getSalary());
		jobPostingDao.save(originalJob);
	}

	@RequestMapping(value = "/service/job", method = RequestMethod.POST)
	public JobPosting addJob(@RequestBody JobPosting jobPosting) {
		UserDetails details = Security.getUserDetails();
		User user = userDao.getProfileUser(details.getUsername());
		jobPosting.setCompany((Employer) user);
		jobPosting.setEnabled(true);
//		logger.info(jobPosting.getJobTitle());
//		logger.info(jobPosting.getCompany().getUsername());
		return jobPostingDao.save(jobPosting);
	}
}
