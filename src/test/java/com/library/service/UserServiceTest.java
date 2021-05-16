package com.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.dao.UserDao;
import com.library.model.User;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	UserService userService;
	
	@Mock
	UserDao userDao;
	
	@BeforeEach
	public void before() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		user.setId(1L);
		when(userDao.save(ArgumentMatchers.any())).thenReturn(user);
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userDao.findAll()).thenReturn(users);
		doNothing().when(userDao).deleteById(ArgumentMatchers.any());
	}
	
	@Test
	public void addUserTest() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		
		User userReturned = userService.addUser(user);
		assertThat(userReturned).isNotNull();
	}
	
	@Test
	public void updateUserTest() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		user.setId(1L);
		User userReturned = userService.updateUser(user);
		assertThat(userReturned).isNotNull();
	}
	
	@Test
	public void getUsers() {
		List<User> users = userService.getUsers();
		assertThat(users).isNotEmpty();
	}
	
	@Test
	public void deleteUserByIdTest() {
		String status = userService.deleteUser(1L);
		assertThat(status).isEqualTo("Success");
	}
}
