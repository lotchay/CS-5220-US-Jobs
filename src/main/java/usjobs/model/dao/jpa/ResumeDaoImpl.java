package usjobs.model.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usjobs.model.Resume;
import usjobs.model.User;
import usjobs.model.dao.ResumeDao;

@Repository
public class ResumeDaoImpl implements ResumeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Resume> getResumes(User user) {
		String query = "FROM Resumes where user = :user";
		return em
		.createQuery(query, Resume.class)
		.setParameter("user", user)
		.getResultList();
	}
	
	@Override
	@Transactional
	@PreAuthorize ("hasRole('ROLE_ADMIN') or principal.username == #resume.user.username")
	public Resume saveResume(Resume resume) {
		return em.merge(resume);
	}
	
	@Override
	@Transactional
	@PreAuthorize ("hasRole('ROLE_ADMIN') or principal.username == #resume.user.username")
	public void deleteResume(Resume resume) {
		em.remove(resume);
	}
}
