package usjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import usjobs.model.User;
import usjobs.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao  {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostAuthorize ("hasRole('ROLE_ADMIN') or principal.username == returnObject.username")
	public User getUser( Integer id ) {
				
		return entityManager.find( User.class, id );
	}

	@Override
	public User getUser( String username ) {
		
	    String query = "from User where username= :username";
		User user;
		
		try {
			user = entityManager
					.createQuery(query, User.class)
					.setParameter("username", username)
					.getSingleResult();
			
		} catch(NoResultException e) {
			user = null; //did't find a user.
		}
		
		return user;
	}
	
	@Override
	@PostAuthorize ("hasRole('ROLE_ADMIN') or principal.username == returnObject.username")
	public User getProfileUser( String username ) {
		
	    String query = "from User where username= :username";
		User user;
		
		try {
			user = entityManager
					.createQuery(query, User.class)
					.setParameter("username", username)
					.getSingleResult();
			
		} catch(NoResultException e) {
			user = null; //did't find a user.
		}
		
		return user;
	}

	@Override
	public List<User> getUsers() {
		
		return entityManager.createQuery( "from User order by id", User.class )
		    .getResultList();
	}
	
	@Override
	public List<User> searchUsers( String searchTerm, String searchLoc ) {
	    
		String query = "FROM User u WHERE UPPER(u.username) LIKE ?1 AND u.enabled=true "
				+ "AND (UPPER(u.address.state) like ?2 OR UPPER(u.address.city) like ?2 OR u.address.zip like ?2)";
		
		return entityManager.createQuery(query, User.class)
				.setParameter(1, "%" + searchTerm.toUpperCase() + "%")
				.setParameter(2, "%" + searchLoc.toUpperCase() + "%")
				.getResultList();
	}
	
	@Override
	public List<User> searchSeekers( String searchTerm, String searchLoc ) {
	    
		String query = "From User u WHERE UPPER(u.currentJobTitle) LIKE ?1 OR u.experience LIKE ?1 AND u.enabled = true"
				+ "AND (UPPER(u.address.state) like ?2 OR UPPER(u.address.city) like ?2 OR u.address.zip like ?2)";
		
		return entityManager.createQuery(query, User.class)
				.setParameter(1, "%" + searchTerm.toUpperCase() + "%")
				.setParameter(2, "%" + searchLoc.toUpperCase() + "%")
				.getResultList();
	}

	@Override
	@Transactional
	public User saveUser( User user ) {
		
		return entityManager.merge( user );
	}		

	@Override
	@Transactional
	@PreAuthorize ("hasRole('ROLE_ADMIN') or principal.username == #user.username")
	public User saveProfileUser( User user ) {
		
		return entityManager.merge( user );
	}
	
}
