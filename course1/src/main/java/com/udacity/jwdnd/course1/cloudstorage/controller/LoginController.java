package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

  public class LoginController {
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
/*@RequestMapping("/logouttt")	

 public String logout(String logoutMessage,Model model) {
	String lougoutSuccess = null;
	model.addAttribute("lougoutSuccess", true);
	System.out.println(model.getAttribute(lougoutSuccess));
	return "login";
}*/

@RequestMapping("/login?logout")
    public String logoutPage(Model model){
	String lougoutSuccess = null;
	model.addAttribute("lougoutSuccess", true);
	System.out.println(model.getAttribute(lougoutSuccess));
	return "login";
        
    }	
	
	
	
}
