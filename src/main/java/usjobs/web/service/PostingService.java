package usjobs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import usjobs.model.JobPosting;
import usjobs.model.dao.JobPostingDao;

@Controller
public class PostingService{

    @Autowired
    private JobPostingDao jobPostingDao;
    
    @RequestMapping(value = "/service/postings", method = RequestMethod.GET)
    @ResponseBody
    public List<JobPosting> list(){
        // Get all job postings from database and pass them back as list json objects
        return jobPostingDao.getJobPostings();
    }
    
    @RequestMapping(value = "/service/posting/{id}", produces="application/json", method = RequestMethod.GET)
    @ResponseBody
    public JobPosting getJobPosting(@PathVariable Integer id, ModelMap models){
    	// Get job posting based on id and pass it back as a json object
        return jobPostingDao.getJobPosting(id);
    }
    
    @RequestMapping(value = "/service/postings", method = RequestMethod.POST)
    @ResponseBody
    public JobPosting addJobPosting(@RequestBody JobPosting jobPosting){
    	// Get job posting from request body and save it to db
        return jobPostingDao.save(jobPosting);
    }

    @RequestMapping(value = "/service/posting/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateJobPosting(@PathVariable Integer id, @RequestBody JobPosting jobPosting){
    	jobPosting.setId(id);
    	jobPostingDao.save(jobPosting);
    }

    @RequestMapping(value = "/service/posting/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable Integer id){
    	jobPostingDao.delete(id);
    }
}