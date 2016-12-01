package usjobs.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import usjobs.model.User;
import usjobs.model.dao.UserDao;

@RestController
public class UserService {

    private static final Logger logger = Logger.getLogger( UserService.class );
    
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/service/users", method = RequestMethod.POST)
    public User addUser( @RequestBody User user ) {

        return userDao.saveUser( user );
    }

    @RequestMapping(value = "/service/user/{id}", method = RequestMethod.PUT)
    public void updateUser( @PathVariable Integer id, @RequestBody User user ) {

        user.setId( id );
        userDao.saveUser( user );
    }

    @RequestMapping(value = "/service/users", method = RequestMethod.GET)
    public String getUsername( String name ) {

        boolean available = true;

        List<User> users = userDao.getUsers();

        for ( User user : users ) {

            if ( user.getUsername().equalsIgnoreCase( name ) ) {
                available = false;
            }
        }

        if ( available ) {
            return "Username is available!";
        } else {
            return "Username is not available!";
        }
    }

}
