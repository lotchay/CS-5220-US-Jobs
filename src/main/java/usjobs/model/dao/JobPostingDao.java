package usjobs.model.dao;

import java.util.List;

import usjobs.model.JobPosting;

public interface JobPostingDao {
	
	List<JobPosting> getJobPostings();
	
	List<JobPosting> getJobPostings(int id);
	
	List<JobPosting> getOpenedJobPostings();
	
	List<JobPosting> getClosedJobPostings();
	
	JobPosting getJobPosting(int id);
	
	void delete(JobPosting jobPosting);
	
	List<JobPosting> searchJobs(String searchTerm, String searchLocation);
	
	List<JobPosting> searchJobSalary(String searchTerm, String searchLocation);
	
	JobPosting save(JobPosting jobPosting);
	
	JobPosting jobFavoritedOrApplied(JobPosting jobPosting);

}
