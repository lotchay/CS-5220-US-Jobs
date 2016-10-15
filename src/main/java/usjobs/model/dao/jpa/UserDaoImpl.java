package usjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import usjobs.model.User;
import usjobs.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao  {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getUser( Integer id ) {
				
		return entityManager.find( User.class, id );
	}

	@Override
	public User getUser( String username ) {
		
		return entityManager.find( User.class, username );
	}

	@Override
	public List<User> getUsers() {
		
		return entityManager.createQuery( "from User order by id", User.class ).getResultList();
	}

	@Override
	@Transactional
	public User saveUser( User user ) {
		
		return entityManager.merge( user );
	}	
	
}
