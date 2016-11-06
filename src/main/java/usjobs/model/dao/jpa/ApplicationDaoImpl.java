package usjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import usjobs.model.Application;
import usjobs.model.dao.ApplicationDao;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Application> getJobApplications(int jobId) {
		String query = "FROM Application where job_id = :jobId";
		return em
		.createQuery(query, Application.class)
		.setParameter("jobId", jobId)
		.getResultList();
	}
	
	@Override
	public List<Application> getUserApplications(int userId) {
		String query = "FROM Application where seeker_id = :seekerId";
		return em
				.createQuery(query, Application.class)
				.setParameter("seekerId", userId)
				.getResultList();
	}
	
	@Override
	@Transactional
	public Application saveApplication(Application application) {
		return em.merge(application);
	}
}
