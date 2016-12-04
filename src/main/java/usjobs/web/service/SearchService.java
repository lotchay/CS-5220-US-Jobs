package usjobs.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usjobs.model.JobPosting;
import usjobs.model.dao.JobPostingDao;

@RestController
public class SearchService {
	private static final Logger logger = Logger.getLogger(JobPostingService.class);
	
	@Autowired
	JobPostingDao jobPostingDao;
	
	/**
	 * New version
	 */
	@RequestMapping(value = "/service/search/jobs", method = RequestMethod.GET)
	public List<String> suggestJobs(@RequestParam String term) {
		return jobPostingDao.searchJobNoFTS(term);
	}
	
	//old version
//	@RequestMapping(value = "/service/search/jobs", method = RequestMethod.POST)
//	public void searchJobTitles(HttpServletResponse response, @RequestParam String searchKey, @RequestParam String searchLoc)
//			throws IOException{
//		response.setContentType("application/json");
//		List<JobPosting> jobsList = jobPostingDao.searchJobsForAutocomplete(searchKey, searchLoc);
//		String jobTitleList = "[";
//		for(JobPosting job:jobsList){
//			jobTitleList += "\"" + job.getJobTitle() + "\",";
//		}
//		jobTitleList = jobTitleList.substring(0, jobTitleList.length()-1);
//		jobTitleList += "]";
//		response.getWriter().write(jobTitleList);
//	}

}
