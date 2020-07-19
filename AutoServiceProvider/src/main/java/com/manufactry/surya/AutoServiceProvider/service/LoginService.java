package com.manufactry.surya.AutoServiceProvider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manufactry.surya.AutoServiceProvider.dao.LoginRepository;
import com.manufactry.surya.AutoServiceProvider.dto.LoginUser;

@Service
@Transactional
public class LoginService {

	@Autowired
	public LoginRepository loginRepo;

	public LoginUser validateUser(LoginUser user) {
		return loginRepo.findById(user.getName()).get();
	}
	
}
