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
	public List<Application> getApplications(int jobid) {
		String query = "FROM Applications where jobid = :jobid";
		return em
		.createQuery(query, Application.class)
		.setParameter("jobid", jobid)
		.getResultList();
	}
	
	@Override
	@Transactional
	public Application saveApplication(Application application) {
		return em.merge(application);
	}
}
