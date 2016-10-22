package usjobs.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import usjobs.model.Application;

public interface ApplicationDao {
	
	List<Application> getApplications(int jobId);
	
	Application saveApplication(Application application);
}
