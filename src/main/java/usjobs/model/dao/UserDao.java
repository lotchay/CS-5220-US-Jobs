package usjobs.model.dao;

import java.util.List;

import usjobs.model.User;

public interface UserDao {

	User getUser( Integer id );
	
	User getUser( String username );
	
	User getProfileUser( String username);
	
	List<User> getUsers();
	
	User saveUser( User user );
	
	User saveProfileUser( User user );
	
}
