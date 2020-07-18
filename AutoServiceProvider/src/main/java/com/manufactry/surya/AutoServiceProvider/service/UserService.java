package com.manufactry.surya.AutoServiceProvider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manufactry.surya.AutoServiceProvider.dao.UserRepository;
import com.manufactry.surya.AutoServiceProvider.dto.NewUsertDto;

@Service
@Transactional
public class UserService {

	@Autowired
	public UserRepository userRepo;

	public List<NewUsertDto> findAll() {
		return userRepo.findAll();
	}

	public NewUsertDto findById(Long rollnumber) {
		return userRepo.findById(rollnumber).get();
	}

	public void delete(NewUsertDto newUsertDto) {
		userRepo.delete(newUsertDto);
	}

	public void deleteById(Long rollnumber) {

		userRepo.deleteById(rollnumber);
	}

	public void save(NewUsertDto newUsertDto) {
		userRepo.save(newUsertDto);
	}

	public void saveNewContact(NewUsertDto newUsertDto) {
		System.out.println("ID:::" + newUsertDto.getId());
		System.out.println("id:::" + newUsertDto.getName());
		System.out.println("email:::" + newUsertDto.getEmail());
		System.out.println("password:::" + newUsertDto.getPassword());
		System.out.println("phone:::" + newUsertDto.getPhone());

		//userRepo.save(newUsertDto);
	}

}
