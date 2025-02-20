package com.quizz.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "auth", "", "/" })
public class UserController {

    @GetMapping({ "/", "/login" })
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
	if (error != null) {
	    modelMap.put("error", "Login Failed");
	}
	return "auth/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied(ModelMap model) {
	model.put("msg", "You don't have permission to access this resource");
	return "auth/accessDenied";
    }

    @GetMapping({ "/logout" })
    public String logout() {
	return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String welcome(ModelMap model, Authentication authentication) {
	String username = authentication.getName();
	System.out.println("Username: " + username);
	model.put("msg", "Welcome to the application!");
	return "auth/welcome";
    }

}
