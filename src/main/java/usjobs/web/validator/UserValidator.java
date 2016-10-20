package usjobs.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import usjobs.model.dao.UserDao;
import usjobs.model.User;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserDao userDao;
    
    @Override
    public boolean supports( Class<?> clazz ) {
        
        return User.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object target, Errors errors ) {
        
        User user = (User) target;
        
        if ( !StringUtils.hasText( user.getUsername() ) ) {
            errors.rejectValue( "username", "error.field.empty" );
        }

        if ( !StringUtils.hasText( user.getPassword() ) ) {
            errors.rejectValue( "password", "error.field.empty" );
        }
        
        if ( !StringUtils.hasText( user.getFirstName() ) ) {
            errors.rejectValue( "firstName", "error.field.empty" );
        }
        
        if ( !StringUtils.hasText( user.getLastName() ) ) {
            errors.rejectValue( "lastName", "error.field.empty" );
        }
        
        if ( !StringUtils.hasText( user.getEmail() ) ) {
            errors.rejectValue( "password2", "error.field.empty" );
        }
        
        if ( ( user.getPassword() != null ) && 
            !( user.getPassword().equals(user.getPassword2() ) ) ) {
            errors.rejectValue( "email", "error.field.empty" );
        }
        
        String username = user.getUsername();
        
        if ( ( username != null ) && ( userDao.getUser( username ) != null ) ) {
            errors.rejectValue( "username", "error.username.taken" );
        }
    }

}
