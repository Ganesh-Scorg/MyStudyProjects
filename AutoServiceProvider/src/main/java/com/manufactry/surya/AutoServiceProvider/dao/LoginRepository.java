package com.manufactry.surya.AutoServiceProvider.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manufactry.surya.AutoServiceProvider.dto.LoginUser;

public interface LoginRepository extends JpaRepository<LoginUser, String>{

}
