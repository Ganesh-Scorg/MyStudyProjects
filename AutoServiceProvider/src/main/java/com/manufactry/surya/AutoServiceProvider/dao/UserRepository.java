package com.manufactry.surya.AutoServiceProvider.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manufactry.surya.AutoServiceProvider.dto.NewUsertDto;

public interface UserRepository extends JpaRepository<NewUsertDto, Long>{

}
