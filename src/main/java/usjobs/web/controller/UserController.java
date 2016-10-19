package usjobs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@SessionAttributes({"user", "editJob"})
public class UserController {

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
    @RequestMapping(value = "/profile.html")
    public String getProfile(@RequestParam int id, ModelMap models) {
    	User user = userDao.getUser(id);
    	if (user.isAdmin()) {
    		return "profile/admin";
    	} else if (user.isEmployer()) {
    		models.put("jobPostings", jobPostingDao.getJobPostings(id));
    		models.put("newJob", new JobPosting());
    		return "profile/employer";
    	} else {
    		return "profile/job-seeker";
    	}
    }
    
	@RequestMapping(value = "/addJob.html", method = RequestMethod.POST)
	public String addJob(@RequestParam int employerId, @ModelAttribute JobPosting newJob) {
		Employer employer = (Employer) userDao.getUser(employerId);
		newJob.setCompany(employer);
		jobPostingDao.save(newJob);
		return "redirect:profile.html?id=" + employerId;
	}

	@RequestMapping(value = "/deleteJob.html", method = RequestMethod.POST)
	public String deleteJob(@RequestParam int employerId, @RequestParam int jobId) {
		/*
		 * need to set a restriction where only the employer that owns the job
		 * or an admin can delete this job
		 */
		JobPosting jobPosting = jobPostingDao.getJobPosting(jobId);
		jobPostingDao.delete(jobPosting);
		return "redirect:profile.html?id=" + employerId;
	}

	@RequestMapping(value = "/editJob.html", method = RequestMethod.GET)
	public String editJobForm(@RequestParam int employerId, @RequestParam int jobId, ModelMap models) {
		/**
		 * We want to make sure that the employer with the correct id can edit
		 * this and also that an admin can do it but no one else. still need to
		 * implement.
		 */
		models.put("editJob", jobPostingDao.getJobPosting(jobId));
		return "job-edit";
	}

	@RequestMapping(value = "/editJob.html", method = RequestMethod.POST)
	public String editJobForm(@RequestParam int employerId, @ModelAttribute JobPosting editJob, SessionStatus sessionStatus) {
		jobPostingDao.save(editJob);
		sessionStatus.setComplete();
		return "redirect:profile.html?id=" + employerId;
	}
}
