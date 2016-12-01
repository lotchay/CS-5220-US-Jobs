package usjobs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import usjobs.model.Application;
import usjobs.model.JobPosting;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.JobPostingDao;

@Controller
public class ApplicationController {

    @Autowired
    ApplicationDao applicationDao;
    
    @Autowired
    JobPostingDao jobPostingDao;

    @RequestMapping(value = "/application/view",
        method = RequestMethod.GET)
    public String view( @RequestParam int id, ModelMap models ) {

        Application application = applicationDao.getApplication( id );
        models.put( "application", application );

        return "application/view";
    }

    @RequestMapping(value = "/application/jobApplications",
        method = RequestMethod.GET)
    public String getJobApplications( @RequestParam int jobId,
        ModelMap models ) {

        List<Application> applications = applicationDao
            .getJobApplications( jobId );
        JobPosting jobPosting = jobPostingDao.getJobPosting(jobId);
        models.put( "applications", applications );
        models.put( "jobTitle", jobPosting.getJobTitle());
        return "application/job-applications";
    }

}
