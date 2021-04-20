package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
	
private final UserService userService;

public SignupController(UserService userService) {
	super();
	this.userService = userService;
}

@GetMapping()	
public String SignUp() {
	return "signup";
}

@PostMapping()
public String postSignup(@ModelAttribute User user,Model model) {
	String signupError =null;
	if(user !=null && user.getUsername()!=null) {
		if (!userService.isUsernameAvailable(user.getUsername())) {
			System.out.println("the value of name available"+userService.isUsernameAvailable(user.getUsername()));
			
            signupError = "The username already exists.";
        }else {
		int rowAdded=userService.createUser(user);
		if(rowAdded <0) {
			 signupError = "There was an error signing you up. Please try again.";
		}
        }
		}
	
	 if (signupError == null) {
         model.addAttribute("signupSuccess", true);
         
     } else {
         model.addAttribute("signupError", signupError);
     }
	
		
	
	
	return "signup";
	
}

}
