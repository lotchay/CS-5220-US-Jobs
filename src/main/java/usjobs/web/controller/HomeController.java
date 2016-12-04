package usjobs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.ResumeDao;
import usjobs.model.dao.UserDao;

@Controller
public class HomeController {

    @Autowired
    private JobPostingDao jobPostingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResumeDao resumeDao;

    @RequestMapping(value = {"/","/index","/home"},
        method = RequestMethod.GET)
    public String home( ModelMap models ) {

        models.put( "jobPostings", jobPostingDao.getPublicJobPostings() );
        return "home";
    }

    @RequestMapping(value = {"/","/index","/home"},
        method = RequestMethod.POST)
    public String home( @RequestParam String searchBar,
        @RequestParam String searchLoc, @RequestParam String searchType,
        ModelMap models ) {

        if ( searchType.equals( "Job Postings" ) ) {
            models.put( "searchResultJob",
                jobPostingDao.searchJobs( searchBar, searchLoc ) );
        } else if ( searchType.equals( "Salary" ) ) {
            models.put( "searchResultJob",
                jobPostingDao.searchJobSalary( searchBar, searchLoc ) );
        } else if ( searchType.equals( "User" ) ) {
            models.put( "searchResultUser",
                userDao.searchUsers( searchBar, searchLoc ) );
        } else if ( searchType.equals( "Job Seeker" ) ) {
            models.put( "searchResultResumes",
                resumeDao.searchResumes( searchBar, searchLoc ) );
        }
        models.put( "searchBar", searchBar );
        return home( models );
    }

    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/loginError")
    public String loginError() {

        return "login-error";
    }

}
