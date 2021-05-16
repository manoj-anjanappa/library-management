
package com.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.controller.exception.BadRequestException;
import com.library.model.User;
import com.library.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@BeforeEach
	public void before() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		user.setId(1L);
		when(userService.addUser(ArgumentMatchers.any())).thenReturn(user);
		when(userService.updateUser(ArgumentMatchers.any())).thenReturn(user);
		List<User> users = new ArrayList<User>();
		users.add(user);
		when(userService.getUsers()).thenReturn(users);
		when(userController.deleteUser(ArgumentMatchers.anyLong())).thenReturn("Success");
	}
	
	@Test
	public void addUserTest() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		User userReturned = userController.addUser(user);
		assertThat(userReturned.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateUserTest() throws BadRequestException {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		user.setId(1L);
		User userReturned = userController.updateUser(user);
		assertThat(userReturned.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateUserNullIdTest() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("wick");
		user.setEmail("jobwick@gmail.com");
		user.setPhoneNumber("+144502345455");
		Exception e = assertThrows(BadRequestException.class, ()->{ userController.updateUser(user);});
		assertThat(e.getMessage()).isEqualTo("user id is required");
	}
	
	@Test
	public void getUsersTest() {
		List<User> users = userController.getUsers();
		assertThat(users).isNotEmpty();
	}
	
	@Test
	public void deleteUserTest() {
		String status = userController.deleteUser(1l);
		assertThat(status).isEqualTo("Success");
	}
}
