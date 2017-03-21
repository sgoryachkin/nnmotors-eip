package ru.nnmotors.eip.web.ui.pages.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("news")
public class NewsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String showUserRoleForm(Model model) {
		LOGGER.debug("show news list");
		// model.addAttribute("userRoleForm", userRoleForm);
		return "news/list";
	}

}
