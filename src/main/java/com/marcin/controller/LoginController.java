package com.marcin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marcin.model.User;
import com.marcin.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	// mapowanie stron
	
	//stron logowania
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	//strona rejestrowania uzytkownikow
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	// rejestrowanie uzytkownika
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());//sprawdzanie czy istnieje juz taki uzytkownik
		if (userExists != null) { 
			bindingResult
					.rejectValue("email", "error.user",
							"!Email already in use!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Registration completed!");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	// strona po zalogowaniu
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		// wiadomosci na stronie
		modelAndView.addObject("userName", "Name: " + user.getName() + "  ||  Last name: " + user.getLastName() + "  ||  Email: " + user.getEmail());
		modelAndView.addObject("adminMessage","Congrats! You have admin privileges.");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	// Maly easter-egg
	@RequestMapping(value="/admin/kanye", method = RequestMethod.GET)
	public ModelAndView kanye(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/kanye");
		return modelAndView;
	}

}
