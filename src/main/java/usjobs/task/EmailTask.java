package usjobs.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailTask {

    @Autowired
    private MailSender mailSender;
    
    // Sending an email every minute
    @Scheduled(cron = "0 * * * * *")
    public void sendMail() {
        
        SimpleMailMessage msg = new SimpleMailMessage();
        
        String from = "USJobs@usjobs.com";
        String subject = "New Job Listings";
        String content = "There are new job postings on US Jobs that you may be"
            + " interested in. Please go to US Jobs website to view these jobs."
            + " Thank you and have a wonderful day!";
        String to = "jobseeker@localhost.localdomain";
        
        msg.setFrom( from );
        msg.setTo( to );
        msg.setSubject( subject );
        msg.setText( content );
        
        mailSender.send( msg );
        
        // Print to console
        System.out.println( "An email has been sent by the system." );
    }
    
}
