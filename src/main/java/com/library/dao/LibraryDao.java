package com.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.model.Library;

@Repository
public interface LibraryDao extends JpaRepository<Library, Long> {
	
	@Query("from Library where user_id = :userId")
	public List<Library> getList(Long userId);

}
