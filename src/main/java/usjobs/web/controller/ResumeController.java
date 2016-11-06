package usjobs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import usjobs.model.User;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@Controller
public class ResumeController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public static final File fileDir = new File("/WEB-INF/files/user_");
	
    @Autowired 
    ServletContext context;
    
    @Autowired
    UserDao userDao;
    
    private File getFileDirectory( Integer userId ) {

        String userPath = fileDir.getPath() + userId;
        logger.info("Path: " + userPath);
        
        boolean pathCreated = new File( userPath ).mkdirs();
        
        if (pathCreated) {
        	logger.info("path created");
        } else {
        	logger.info("path not created");
        }
        return new File( userPath );
    }
    
    @RequestMapping(value = "/resume/upload.html", method = RequestMethod.POST)
    public String upload( @RequestParam MultipartFile resume ) 
        throws IllegalStateException, IOException {
        
        UserDetails details = Security.getUserDetails();
        
        User user = userDao.getProfileUser( details.getUsername() );
        
        Integer userId = user.getId();
       
        resume.transferTo( new File( getFileDirectory( userId ), 
            resume.getOriginalFilename() ) );
        
        return "redirect:/user/profile.html";
    }
    
    @RequestMapping("/resume/download.html")
    public String download( HttpServletResponse response, 
        @RequestParam Integer userId, @RequestParam String filename ) 
            throws IOException {
        
        response.setHeader( "Content-Disposition", "attachment; filename=" + filename );
        
        FileInputStream in = new FileInputStream( new File( getFileDirectory( userId ), filename ) );
        
        OutputStream out = response.getOutputStream();
        
        byte[] buffer = new byte[2048];
        
        int bytesRead;
        
        while ( (bytesRead = in.read( buffer )) > 0 ) {
            
            out.write( buffer, 0, bytesRead );
        }
        
        in.close();
        
        return null;
    }  
    
}
