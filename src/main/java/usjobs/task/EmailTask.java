package usjobs.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import usjobs.model.JobPosting;
import usjobs.model.JobSeeker;
import usjobs.model.User;
import usjobs.model.dao.JobPostingDao;
import usjobs.model.dao.UserDao;

@Component
public class EmailTask {

    private static final Logger logger = Logger.getLogger( EmailTask.class );

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobPostingDao jobPostingDao;

    /*
     * After application start-up, once 1 second has elapsed, this task checks
     * job postings that were posted within the last 24-hours and sees if it
     * matches with the keyword preferences of each user. for each user where
     * there's a match, send an email to that user with a list of all the job
     * postings that match their query.
     */
    @Scheduled(cron = "0 0 * * * *")
    public void notifyNewPostings() {

        List<JobPosting> jobPostingsSent = new ArrayList<JobPosting>();
        List<JobPosting> jobPostingsToBeSent = new ArrayList<JobPosting>();
        
        List<User> seekers = userDao.getJobSeekers();
        
        String keywordString;
        String[] keywords;
        
        JobSeeker seeker;
        
        for ( User user : seekers ) {
            
            seeker = (JobSeeker) user;
            
            if ( seeker.isNotified()
                && !StringUtils.isEmpty( seeker.getKeywords() ) ) {
                
                keywordString = seeker.getKeywords();
                
                logger.info( "Searching relevant postings with '"
                    + keywordString + "' keywords for " + seeker.getEmail() );
                
                keywords = keywordString.split( "," );
                
                for ( int j = 0; j < keywords.length; j++ ) {
                    // get 3 new relevant postings, 3 by default we can change
                    // this if we want.
                    List<JobPosting> jobPostingsSearchedByKeyword = jobPostingDao
                        .searchJobsByKeyword( keywords[j].trim(), 3 );
                    
                    for ( JobPosting jobPosting : jobPostingsSearchedByKeyword ) {
                        
                        if ( !jobPostingsToBeSent.contains( jobPosting ) ) {
                            
                            jobPostingsToBeSent.add( jobPosting );
                            
                            if ( !jobPostingsSent.contains( jobPosting ) ) {
                                
                                jobPostingsSent.add( jobPosting );
                                
                                logger
                                    .info( "Adding " + jobPosting.getJobTitle()
                                        + " to be set to old." );
                            }
                        }
                    }
                }

                if ( jobPostingsToBeSent.size() > 0 ) {

                    logger.info( "Sending " + jobPostingsToBeSent.size()
                        + " job postings to " + seeker.getEmail() + "." );
                    
                    sendMail( jobPostingsToBeSent, seeker.getEmail() );
                    
                    jobPostingsToBeSent.clear();
                }
            }
        }
        
        // Set new to false for jobs that have been processed.
        for ( JobPosting jobPosting : jobPostingsSent ) {
            
            jobPosting.setNew( false );
            
            logger.info(
                jobPosting.getJobTitle() + " is new? " + jobPosting.isNew() );
            
            jobPostingDao.saveEmail( jobPosting );
        }
    }

    public void sendMail( List<JobPosting> jobPostings, String email ) {

        SimpleMailMessage msg = new SimpleMailMessage();

        String from = "USJobs@usjobs.com";
        String subject = "New Job Listings";
        String content = "There are new job postings on US Jobs that you may be"
            + " interested in. Please go to US Jobs website to view these jobs."
            + " Thank you and have a wonderful day!\n";

        for ( JobPosting job : jobPostings ) {

            content += "Job Title: " + job.getJobTitle() + " @"
                + job.getLocation() + "\n";
            content += "Job Details: " + job.getJobDescription() + "\n";
        }

        String to = email;

        msg.setFrom( from );
        msg.setTo( to );
        msg.setSubject( subject );
        msg.setText( content );

        mailSender.send( msg );

        // Print to console
        logger.info( "An email has been sent by the system." );
    }

}
