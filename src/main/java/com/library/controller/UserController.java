package com.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.controller.exception.BadRequestException;
import com.library.model.User;
import com.library.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user/add")
	public User addUser(@Valid @RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/user/update")
	public User updateUser(@Valid @RequestBody User user) throws BadRequestException {
		if(user.getId() == null || Long.valueOf(0L).equals(user.getId())) {
			throw new BadRequestException("user id is required");
		}
		user = userService.updateUser(user);
		return user;
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@DeleteMapping("/user/{userId}")
	public String deleteUser(@RequestParam("userId") Long userId) {
		return userService.deleteUser(userId);
	}
}
