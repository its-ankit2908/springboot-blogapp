package com.restapi.blogapp.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.blogapp.payloads.UserDTO;
import com.restapi.blogapp.services.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer uid){
		UserDTO userDto = this.userService.getUserById(uid);
		
		return ResponseEntity.ok(userDto);
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
		
		//check user exist or not
		// need to add
		
		UserDTO createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDTO>(createdUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto ,@PathVariable("userId") Integer uid) {
		
		UserDTO updatedUserDto = this.userService.updateUser(userDto, uid);	
		return ResponseEntity.ok(updatedUserDto);  	
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid ){
		this.userService.deleteUser(uid);
		
//		return ResponseEntity.ok().body("User Deleted successfully");
		return new ResponseEntity(Map.of("message","User deleted uccessfully"),HttpStatus.OK);
		
	}
}
