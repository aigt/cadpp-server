package edu.urfu.cadpp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.urfu.cadpp.web.entity.User;
import edu.urfu.cadpp.web.service.UserService;

@Controller
@RequestMapping("/cadt/types")
public class CadTemplateTypesController {

	public final static int TYPES_PER_PAGE = 20;

	private final Logger logger = LoggerFactory.getLogger(CadTemplateTypesController.class);

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView usersList(@RequestParam(required = false, name = "new") String new_param,
			@RequestParam(required = false) Integer page) {
		logger.debug("GET request on /types");

		Map<String, Object> model = new HashMap<String, Object>();
		String view;

		if (new_param != null) {
			logger.debug("request for creqting new cad template type");

			model.put("pageTitle", "Создание нового типа");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user_login = auth.getName(); // get logged in username
			model.put("login", user_login);
			view = "cadt/types/new";
		} else {
			logger.debug("    request for showing cad template types list");

			model.put("pageTitle", "Список шаблонов");

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user_login = auth.getName(); // get logged in username
			model.put("login", user_login);

			if (page == null) {
				page = new Integer(1);
				logger.debug("    page parameter was null or lower than 1 and setuped as 1");
			} else {
				logger.debug("    request for page number " + page.toString());
			}
			model.put("currentPage", page);
			Long pageCount = userService.getUserPageCount(TYPES_PER_PAGE);
			logger.debug("total pages count is " + pageCount);
			model.put("pageCount", pageCount);

			view = "cadt/types/list";
		}

		return new ModelAndView(view, model);
	}

	@RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable(value = "login") String login, @RequestParam(required = false) String edit,
			@RequestParam(required = false) String delete) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();

			User user = userService.getUserByLogin(login);
			if (user == null)
				model.put("user", "user wasnt loaded");
			else
				model.put("user", user);

			String view;
			if (edit != null) {
				model.put("pageTitle", "Редактировать профиль");
				model.put("roles", userService.getAllRoles());
				view = "users/edit";
			} else if (delete != null) {
				model.put("pageTitle", "Удалить профиль?");
				view = "users/delete";
			} else {
				model.put("pageTitle", "Профиль");
				view = "users/show";
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user_login = auth.getName(); // get logged in username
			model.put("login", user_login);

			return new ModelAndView(view, model);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("users/show", "login", "not accessed");
		}
	}
}
