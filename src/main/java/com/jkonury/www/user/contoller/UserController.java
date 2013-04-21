package com.jkonury.www.user.contoller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jkonury.www.common.controller.GenericController;
import com.jkonury.www.common.security.LoginInfo;
import com.jkonury.www.common.validator.LoginValidator;
import com.jkonury.www.common.validator.UserValidator;
import com.jkonury.www.user.model.User;
import com.jkonury.www.user.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController extends GenericController<User, UserService, UserValidator> {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private LoginValidator loginValidator;
	
	@Autowired
	private UserService userService;
	
	@Inject
	Provider<LoginInfo> loginInfoProvider;
	
	@Override
	public void add(ModelMap model) {
		model.addAttribute("user", new User());
	}

	@Override
	public String add(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status) {
		this.userValidator.validate(user, result);
		if (result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
                System.out.println(error.getCode() + " : " + error.getDefaultMessage());
            }
			return "user/add";
		} else {
			userService.add(user);
			status.setComplete();
			return "redirect:/";
		}

	}

	@Override
	public void update(/*@ModelAttribute("user") */ModelMap model) {
		System.out.println("model : " + model.get("user"));
	}

	@Override
	public String update(User user, BindingResult result, SessionStatus status) {

		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(ModelMap model) {
		model.addAttribute("user", new User());
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, ModelMap model, HttpSession session) {
		this.loginValidator.validate(user, result);
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.getCode() + " : " + error.getDefaultMessage());
			}
			return "user/login";
		} else {
			status.setComplete();
			
			LoginInfo loginInfo = loginInfoProvider.get();
			
			System.out.println(loginInfo.currentUser());
			
			session.setAttribute("loginInfo", loginInfo.currentUser());
			return "redirect:/";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		LoginInfo loginInfo = loginInfoProvider.get();
		loginInfo.remove();
		
		session.removeAttribute("loginInfo");
		
		return "redirect:/";
	}
	
	@RequestMapping("/form")
	public void form(ModelMap model, HttpSession session) {
		model.addAttribute("user", new User());
	}
	
	@RequestMapping("/base")
	public void base() {
		
	}
}
