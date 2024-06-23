package com.restapi.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.blogapp.entities.User;
import com.restapi.blogapp.exceptions.ResourceNotFoundException;
import com.restapi.blogapp.payloads.UserDTO;
import com.restapi.blogapp.repositories.UserRepository;
import com.restapi.blogapp.services.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToEntity(userDto);
		
		User savedUser = this.userRepo.save(user);
		
		return this.entityToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
	
		User user = this.userRepo
				.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));	
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		
		return this.entityToDto(updatedUser);  
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User user = this.userRepo
				.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		return this.entityToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDTO> usersList = users
				.stream()
				.map(user -> this.entityToDto(user))
				.collect(Collectors.toList());
		
		return usersList;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo
				.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		this.userRepo.delete(user);
 	}
	
	
//  convert userDTO to user Entity
	public User dtoToEntity(UserDTO userDto) {
		
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;	
	}
	
	//convert user Entity to userDTO
	public UserDTO entityToDto(User user) {
		
		UserDTO userDto = new UserDTO();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
		
	}
	
}
