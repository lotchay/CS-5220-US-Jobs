package usjobs.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

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

import usjobs.model.Application;
import usjobs.model.Resume;
import usjobs.model.User;
import usjobs.model.dao.ApplicationDao;
import usjobs.model.dao.ResumeDao;
import usjobs.model.dao.UserDao;
import usjobs.util.Security;

@Controller
public class ResumeController {

    Logger logger = Logger.getLogger( this.getClass() );

    public static final File fileDir = new File( "/WEB-INF/files/user_" );

    @Autowired
    ServletContext context;

    @Autowired
    ResumeDao resumeDao;

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    UserDao userDao;

    private File getFileDirectory( Integer userId ) {

        String userPath = fileDir.getPath() + userId;
        String fullPath = context.getRealPath( userPath );

        logger.info( "User Path: " + userPath );
        logger.info( "Full path: " + fullPath );

        boolean pathCreated = new File( fullPath ).mkdirs();

        if ( pathCreated ) {
            logger.info( "path created" );
        } else {
            logger.info( "path not created" );
        }
        return new File( fullPath );
    }

    @RequestMapping(value = "/resume/upload.html", method = RequestMethod.POST)
    public String upload( @RequestParam MultipartFile resume )
        throws IllegalStateException, IOException {

        UserDetails details = Security.getUserDetails();

        User user = userDao.getProfileUser( details.getUsername() );

        Integer userId = user.getId();

        File file = new File( getFileDirectory( userId ),
            resume.getOriginalFilename() );

        resume.transferTo( file );

        if ( file.exists() ) {
            logger.info(
                "file successfully uploaded to path: " + file.getPath() );
            Resume newResume = new Resume();
            newResume.setFilePath( file.getPath() );
            newResume.setFileName( resume.getOriginalFilename() );
            newResume.setUser( user );
            newResume.setUploadDate( new Date() );
            resumeDao.saveResume( newResume );
        } else {
            logger.error( "failed to upload file: " + file.getPath() );
        }

        return "redirect:/user/profile.html";
    }

    @RequestMapping("/resume/download.html")
    public String download( HttpServletResponse response,
        @RequestParam int resumeId ) throws IOException {

        Resume resume = resumeDao.getResume( resumeId );
        response.setHeader( "Content-Disposition",
            "attachment; filename=" + resume.getFileName() );

        FileInputStream in = new FileInputStream(
            new File( resume.getFilePath() ) );

        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[2048];

        int bytesRead;

        while ( (bytesRead = in.read( buffer )) > 0 ) {

            out.write( buffer, 0, bytesRead );
        }

        out.close();
        in.close();

        return null;
    }

    @RequestMapping("/resume/delete.html")
    public String delete( HttpServletResponse response,
        @RequestParam Integer userId, @RequestParam int resumeId )
        throws IOException {

        logger.info( "resume id: " + resumeId );
        Resume resume = resumeDao.getResume( resumeId );
        File fileToDelete = new File( resume.getFilePath() );

        if ( fileToDelete.exists() ) {
            boolean deleted = fileToDelete.delete();
            if ( deleted ) {
                logger.info( "File: " + fileToDelete.getPath()
                    + " successfully deleted" );
                List<Application> applications = applicationDao
                    .getApplicationByResume( resume.getId() );
                for ( Application application : applications ) {
                    application.setResume( null ); // this application should no
                                                   // longer reference the
                                                   // resume being deleted
                    applicationDao.saveApplication( application );
                }
                resumeDao.deleteResume( resume ); // then you can safely delete
                                                  // the resume.
            } else {
                logger.error( "file not deleted successfully" );
            }
        } else {
            logger.info( "file to delete doesn't exist." );
        }

        return "redirect:/user/profile.html";
    }

}
