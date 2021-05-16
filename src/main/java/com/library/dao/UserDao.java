package com.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
