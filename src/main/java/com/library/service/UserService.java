package com.library.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.UserDao;
import com.library.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public User addUser(@Valid User user) {
		return userDao.save(user);
	}

	public User updateUser(@Valid User user) {
		return userDao.save(user);
	}

	public List<User> getUsers() {
		return userDao.findAll();
	}

	public String deleteUser(Long userId) {
		userDao.deleteById(userId);
		return "Success";
	}
}
