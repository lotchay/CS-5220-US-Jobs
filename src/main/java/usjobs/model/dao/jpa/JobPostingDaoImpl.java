package usjobs.model.dao.jpa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.access.prepost.PreAuthorize;
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
        return em.createQuery( query, JobPosting.class ).getResultList();
    }

    @Override
    public List<JobPosting> getPublicJobPostings() {

        String query = "From JobPosting where enabled = true AND opened = true";
        return em.createQuery( query, JobPosting.class ).getResultList();
    }

    @Override
    public List<JobPosting> getClosedJobPostings() {

        String query = "From JobPosting where opened = false";
        return em.createQuery( query, JobPosting.class ).getResultList();
    }
    
    @Override
    public List<JobPosting> getNewJobPostings() {

        String query = "From JobPosting where new = true";
        return em.createQuery( query, JobPosting.class ).getResultList();
    }

    @Override
    public List<JobPosting> getEmployerJobPostings( int id ) {

        String query = "FROM JobPosting WHERE employer_id = :id";
        return em.createQuery( query, JobPosting.class )
            .setParameter( "id", id )
            .getResultList();
    }

    @Override
    public JobPosting getJobPosting( int id ) {

        return em.find( JobPosting.class, id );
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.username == #jobPosting.company.username")
    public void delete( JobPosting jobPosting ) {

        em.remove( jobPosting );
    }

    @Override
    public List<JobPosting> searchJobs( String searchTerm, String searchLoc ) {
    	
    	return em.createNamedQuery("job.search", JobPosting.class).
    			setParameter("text", searchTerm).
    			setParameter("location", searchLoc).getResultList();
        
    }

    @Override
    public List<JobPosting> searchJobsByKeyword(String keyword, int maxResults) {
    	TypedQuery<JobPosting> query = em.createNamedQuery("emailjob.search", JobPosting.class);
    	if(maxResults > 0){
    		query.setMaxResults(maxResults);
    	}
        return query.setParameter("text", keyword).getResultList();
    }

    @Override
    public List<JobPosting> searchJobSalary( String searchTerm,
        String searchLoc ) {

        String digits = "\\d+";
        if ( searchTerm.contains( "," ) || searchTerm.contains( "$" ) ) {
            searchTerm = searchTerm.replace( ",", "" );
            searchTerm = searchTerm.replace( "$", "" );
        }
        if ( !searchTerm.matches( digits ) ) { return null; }
        int salary = Integer.parseInt( searchTerm );
        int bottomRange = salary - 10000;
        int topRange = salary + 10000;
        String query = "FROM JobPosting j WHERE j.enabled = true and j.opened = true AND (j.salary) BETWEEN "
            + bottomRange + " AND " + topRange
            + "AND UPPER(j.location) like ?2";
        return em.createQuery( query, JobPosting.class )
            .setParameter( 2, "%" + searchLoc.toUpperCase() + "%" )
            .getResultList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.username == #jobPosting.company.username")
    public JobPosting save( JobPosting jobPosting ) {

        return em.merge( jobPosting );
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SEEKER')")
    public JobPosting jobFavoritedOrApplied( JobPosting jobPosting ) {

        return em.merge( jobPosting );
    }
    
}
