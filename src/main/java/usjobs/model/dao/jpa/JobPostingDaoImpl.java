package usjobs.model.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usjobs.model.JobPosting;
import usjobs.model.dao.JobPostingDao;

@Repository
public class JobPostingDaoImpl implements JobPostingDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<JobPosting> getJobPostings() {
		String query = "FROM JobPosting ORDER BY id";
		return em.createQuery(query, JobPosting.class).getResultList();
	}
	
	@Override
	public List<JobPosting> getJobPostings(int id) {
		String query = "FROM JobPosting WHERE employer_id = :id";
		return em.createQuery(query, JobPosting.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	
	@Override
	public JobPosting getJobPosting(int id) {
		return em.find(JobPosting.class, id);
	}
	
	@Override
	@Transactional
	public void delete(JobPosting jobPosting) {
		em.remove(jobPosting);
	}
	
	@Override
	public List<JobPosting> searchJobs(String searchTerm) {
		String query = "FROM JobPosting j WHERE UPPER(j.job_title) LIKE ?1 "
				+ "OR UPPER(j.salary) like ?1 "
				+ "OR UPPER(u.location) like ?1";
		return em.createQuery(query, JobPosting.class)
				.setParameter(1, "%" + searchTerm.toUpperCase() + "%")
				.getResultList();
	}
	
	@Override
	@Transactional
	public JobPosting save(JobPosting jobPosting) {
		return em.merge(jobPosting);
	}
}
