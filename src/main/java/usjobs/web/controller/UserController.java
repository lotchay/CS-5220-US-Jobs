package usjobs.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import usjobs.model.Employer;
import usjobs.model.JobPosting;
import usjobs.model.User;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@Controller
@SessionAttributes("user")
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JobPostingDao jobPostingDao;
    
    @RequestMapping("/user/list.html")
    public String list( ModelMap models ) {
        
        // Get all users from database and pass them to JSP
        List<User> users = userDao.getUsers();
        
        models.put( "users", users );
        
        return "user/list";
    }
    
    @RequestMapping("/user/view/{id}.html")
    public String view( @PathVariable Integer id, ModelMap models ) {
        
        // Get user from database and pass it to JSP
        models.put("user", userDao.getUser( id ) );
        return "user/view";
    }
    
    // Using Model Attribute to bind fields
    @RequestMapping(value = "/user/add.html", method = RequestMethod.GET)
    public String add( ModelMap models ) {
        
        models.put( "user", new User() );
        
        return "user/add";
    }
    
    @RequestMapping(value = "/user/add.html", method = RequestMethod.POST)
    public String add( @ModelAttribute User user ) {
                
        // Save the user to database
        user = userDao.saveUser( user );
        
        // Redirect to user list
        return "redirect:list.html";
    }
    
    @RequestMapping(value = "/user/edit.html", method = RequestMethod.GET)
    public String edit( @RequestParam Integer id, ModelMap models ) {
        
        models.put( "user", userDao.getUser( id ) );
        
        return "user/edit";
    }
    
    @RequestMapping(value = "/user/edit.html", method = RequestMethod.POST)
    public String edit( @ModelAttribute User user, SessionStatus status ) {
                
        // Save the user to database
        user = userDao.saveUser( user );
        
        // Remove all the session attributes when done
        status.setComplete();
        
        // Redirect to user list
        return "redirect:list.html";
    }
    
    /**
     * Return the correct profile page for the user depending on if they are
     * an employer, seeker, or admin.
     * @return
     */
    @RequestMapping(value = "/user/profile.html", method = RequestMethod.GET)
    public String getProfile(ModelMap models) {
    	UserDetails details = Security.getUserDetails();
    	User user = userDao.getUser(details.getUsername());
    	if (user.isAdmin()) {
    		return "profile/admin";
    	} else if (user.isEmployer()) {
    		Employer employer = (Employer) user;
    		employer.setJobsPosted(jobPostingDao.getJobPostings(user.getId()));
    		models.put("user", employer);
    		JobPosting newJob = new JobPosting();
    		newJob.setCompany(employer);
    		models.put("newJob", newJob);
    		return "profile/employer";
    	} else {
    		return "profile/job-seeker";
    	}
    }
}
