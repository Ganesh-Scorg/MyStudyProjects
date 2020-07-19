package com.manufactry.surya.AutoServiceProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manufactry.surya.AutoServiceProvider.dto.LoginUser;
import com.manufactry.surya.AutoServiceProvider.dto.NewUsertDto;
import com.manufactry.surya.AutoServiceProvider.service.LoginService;
import com.manufactry.surya.AutoServiceProvider.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// Register form
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register() {
		return "register";
	}

	// Login form
	@RequestMapping("/")
	public String register2() {
		return "login2";
	}

	@RequestMapping(value = "user/saveContact", method = RequestMethod.POST)
	public String saveContact(@RequestBody NewUsertDto newUsertDto) {
		System.err.println("saveContact:::" + newUsertDto.toString());
		userService.saveNewContact(newUsertDto);

		return "redirect:/login2/";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String userLogin(@RequestBody LoginUser loginuser) {

		System.err.println("LoginUser:::" + loginuser.toString());
		System.err.println("User id :::" + loginuser.getName());
		System.err.println("Password :::" + loginuser.getPassword());

		LoginUser user = loginService.validateUser(loginuser);

		if (null != user) {
			return "homepage";
		}
		return "Username or Password is wrong!!";
	}

}
