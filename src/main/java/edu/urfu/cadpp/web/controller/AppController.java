package edu.urfu.cadpp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	private final Logger logger = LoggerFactory.getLogger(AppController.class);

	public AppController() {
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(ModelMap model) {

		logger.debug("index() is executed!");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName(); // get logged in username
		
		model.addAttribute("pageTitle", "Главная страница");
		model.addAttribute("login", login);
		
		return "index";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";// You can redirect wherever you want,
										// but generally it's a good practice to
										// show login screen again.
	}
}