package edu.urfu.cadpp.web.controller.json;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.urfu.cadpp.web.entity.User;
import edu.urfu.cadpp.web.service.UserService;
import static edu.urfu.cadpp.web.controller.UsersController.USERS_PER_PAGE;

@RestController
@RequestMapping("/json/users")
public class UsersRestController {

	private final Logger logger = LoggerFactory.getLogger(UsersRestController.class);

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public List<UsersListFormData> defaultMethod(@RequestParam(required = false, defaultValue = "1") Integer page) {
		logger.info("/json/users default get method request");
		
		Integer page_i = page;
		if (page_i == null||page_i < 1)
			page_i = new Integer(1);
		List<User> users = userService.getUsersByPage(page_i, USERS_PER_PAGE);
		List<UsersListFormData> retData = new ArrayList<UsersListFormData>();
		for (User user : users) {
			UsersListFormData data = UsersListFormData.InstanceFromUser(user, userService);
			retData.add(data);
		}
		return retData;
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public User user(@PathVariable(value = "login") String login) {
		logger.debug("request for user data by login: " + login);
		User user = userService.getUserByLogin(login);
		if (user == null)
			logger.debug("    user wasn't found!");
		return user;
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.PUT)
	public Boolean putUser(@PathVariable(value = "login") String login, @RequestBody EditUserFormData data) {
		logger.debug("request chenging data for a user by login: " + login);
		User user = userService.getUserByLogin(login);
		if (user == null) {
			logger.debug("user wasn't found!");
			return false;
		} else {
			user.setName(data.getName());
			user.setSurname(data.getSurname());
			user.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
			userService.updateUser(user);
			logger.debug("userdata successfully changed");
			return true;
		}
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
	public Boolean deleteUser(@PathVariable(value = "login") String login) {
		logger.debug("request to delete a user by login: " + login);
		User user = userService.getUserByLogin(login);
		if (user != null) {
			userService.deleteUserWithRulesByLogin(login);
			logger.debug("user successfully removed");
			return true;
		} else {
			logger.debug("user wasn't found!");
			return false;
		}

	}

	@RequestMapping(value = "/{login}", method = RequestMethod.POST)
	public Boolean createUser(@PathVariable(value = "login") String login, @RequestBody CreateUserFormData data) {
		logger.debug("request to create a user: " + login);
		User user = userService.getUserByLogin(login);
		if (user != null) {
			logger.debug("user is already existed");
			return false;
		}
		user = new User();
		user.setLogin(login);
		user.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
		user.setName(data.getName());
		user.setSurname(data.getSurname());
		user = userService.addUserWithUserRoles(user, data.getRoles());

		logger.debug("user created");
		return true;
	}
}
