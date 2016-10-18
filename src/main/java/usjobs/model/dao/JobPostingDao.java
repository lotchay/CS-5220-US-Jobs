package usjobs.model.dao;

import java.util.List;

import usjobs.model.JobPosting;

public interface JobPostingDao {
	
	List<JobPosting> getJobPostings();
	
	JobPosting getJobPosting(int id);
	
	void delete(JobPosting jobPosting);
	
	List<JobPosting> searchJobs(String searchTerm);
	
	JobPosting save(JobPosting jobPosting);
	
}
