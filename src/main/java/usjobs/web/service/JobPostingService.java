package usjobs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import usjobs.model.Application;
import usjobs.model.JobPosting;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.JobPostingDao;

@RestController
public class JobPostingService {
	
	@Autowired
	JobPostingDao jobPostingDao;
	
	@RequestMapping(value="/service/job/{id}", method=RequestMethod.GET)
	public JobPosting getJobPosting(@PathVariable int id) {
		return jobPostingDao.getJobPosting(id);
	}
}
