package usjobs.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usjobs.model.User;
import usjobs.model.dao.UserDao;

@RestController
public class RegisterService {
	private static final Logger logger = Logger.getLogger(JobPostingService.class);
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/service/register", method = RequestMethod.POST)
	public void checkAvailability(HttpServletResponse response, @RequestParam String username) throws IOException{
		response.setContentType("text/plain");
		response.getWriter().print(userDao.getUser(username));
	}
}
