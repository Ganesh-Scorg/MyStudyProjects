package com.manufactry.surya.AutoServiceProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.manufactry.surya.AutoServiceProvider.dto.NewUsertDto;
import com.manufactry.surya.AutoServiceProvider.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")  
    @ResponseBody  
    public String index() {  
        return "You made it!";  
    }  
    // Login form  
    @RequestMapping("/login")  
    public String login() {  
        return "login";  
    }  
    }  
//    // Login form with error  
//    @RequestMapping("/login-error")  
//    public String loginError(Model model) {  
//        model.addAttribute("loginError", true);  
//        return "login.html";  
//    }  
    
    // Register form  
    @RequestMapping(value = "/register", method = RequestMethod.POST)  
    public String register() {  
        return "register";  
    } 
    
    // Login form  
    @RequestMapping("/login2")  
    public String register2() {  
        return "login2";  
    }
    
    @RequestMapping(value = "/user/saveContact", method = RequestMethod.POST)
	public void saveContact(@RequestBody NewUsertDto newUsertDto) {
		System.err.println("saveContact:::" + newUsertDto.toString());
		userService.saveNewContact(newUsertDto);
    }
	
    
    ///
    
}
