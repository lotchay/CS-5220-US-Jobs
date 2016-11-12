package usjobs.model.dao;

import java.util.List;

import usjobs.model.Application;

public interface ApplicationDao {

    List<Application> getJobApplications( int jobId );

    List<Application> getUserApplications( int userId );

    Application saveApplication( Application application );

    List<Application> getApplicationByResume( int resumeId );

    Application getApplication( int id );
}
