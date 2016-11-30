package usjobs.model.dao;

import java.util.List;

import usjobs.model.JobPosting;

public interface JobPostingDao {

    List<JobPosting> getJobPostings();

    List<JobPosting> getEmployerJobPostings( int id );

    List<JobPosting> getPublicJobPostings();

    List<JobPosting> getClosedJobPostings();
    
    List<JobPosting> getNewJobPostings();

    JobPosting getJobPosting( int id );

    void delete( JobPosting jobPosting );
    
    void delete( int id );

    List<JobPosting> searchJobs( String searchTerm, String searchLocation );
    
    List<JobPosting> searchJobsByKeyword( String keyword, int maxResults );

    List<JobPosting> searchJobSalary( String searchTerm,
        String searchLocation );

    JobPosting save( JobPosting jobPosting );

    JobPosting jobFavoritedOrApplied( JobPosting jobPosting );

}
