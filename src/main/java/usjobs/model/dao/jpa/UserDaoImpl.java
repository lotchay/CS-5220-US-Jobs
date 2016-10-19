package usjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
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
	@PostAuthorize ("hasRole('ROLE_ADMIN') or principal.username == returnObject.username")
	public User getUser( String username ) {
		
		return entityManager.find( User.class, username );
	}

	@Override
	public List<User> getUsers() {
		
		return entityManager.createQuery( "from User order by id", User.class ).getResultList();
	}

	@Override
	@Transactional
	@PreAuthorize ("hasRole('ROLE_ADMIN') or principal.username == #user.username")
	public User saveUser( User user ) {
		
		return entityManager.merge( user );
	}	
	
}
