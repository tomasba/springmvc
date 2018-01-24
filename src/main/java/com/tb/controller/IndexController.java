package com.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Enable rendering the index view by tomcat.  
 */
@Controller
public class IndexController {

	@RequestMapping(path="/")
	public String index() {
		// we're telling Spring MVC what view we want to render
		// the Spring MVC will by default look for templates in the resources templates named "index" (.html is implied)
		return "index";
	}
	
}
