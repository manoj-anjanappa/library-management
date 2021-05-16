package com.library.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.model.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, Long>{

	List<Tag> findAll();
	void deleteByTagName(String tagName);
	
	default Map<String, Tag> findAllMap() {
        return findAll().stream().collect(Collectors.toMap(Tag::getTagName, v -> v));
    }
	
	@Modifying
	@Query("delete from Tag where tagName in (:tagNames)")
	void deleteByTagNames(List<String> tagNames);
	
	@Query("from Tag where tagName = :tagName")
	Tag findByTagName(String tagName);
}
