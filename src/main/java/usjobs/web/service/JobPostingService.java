package usjobs.web.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import usjobs.model.Application;
import usjobs.model.JobPosting;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.JobPostingDao;

@RestController
public class JobPostingService {
	private static final Logger logger = Logger.getLogger(JobPostingService.class);
	
	@Autowired
	JobPostingDao jobPostingDao;

	@Autowired
	ApplicationDao applicationDao;

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
}
