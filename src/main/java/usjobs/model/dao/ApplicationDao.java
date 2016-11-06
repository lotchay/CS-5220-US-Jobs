package usjobs.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import usjobs.model.Application;

public interface ApplicationDao {
	
	List<Application> getJobApplications(int jobId);
	
	List<Application> getUserApplications(int userId);
	
	Application saveApplication(Application application);
	
	Application getApplication(int id);
}
