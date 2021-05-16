package com.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.dao.TagDao;
import com.library.model.Tag;

@SpringBootTest
public class TagServiceTest {

	@InjectMocks
	private TagService tagService;
	
	@Mock
	private TagDao tagDao; 
	
	private List<Tag> tags;
	
	@BeforeEach
	public void before() {
		tags = new ArrayList<>();
		Tag tag1 = new Tag(1L, "Horror", new Date());
		tags.add(tag1);
		Tag tag2= new Tag(2l, "Thriller", new Date());
		tags.add(tag2);
		Tag tag3 = new Tag(3l, "Science", new Date());
		tags.add(tag3);
		Tag tag4 = new Tag(4l, "History", new Date());
		tags.add(tag4);
		when(tagDao.findAll()).thenReturn(tags);
		Map<String, Tag> tagMap = new HashMap<String, Tag>();
		tagMap.put("Horror", tag1);
		tagMap.put("Thriller", tag2);
		tagMap.put("Science", tag3);
		tagMap.put("History", tag4);
		when(tagDao.findAllMap()).thenReturn(tagMap);
		when(tagDao.saveAll(ArgumentMatchers.anyList())).thenReturn(tags);
		doNothing().when(tagDao).delete(ArgumentMatchers.any());
	}
	
	@Test
	public void getTags() {
		List<Tag> tags = tagService.getTags();
		assertThat(tags).isNotNull();
	}
	
	@Test
	public void getTagsMapTest() {
		Map<String, Tag> tagMap = tagService.getTagsMap();
		assertThat(tagMap).isNotEmpty();
	}
	
	@Test
	public void insertTagsTest() {
		List<Tag> tagList = tagService.insertTags(tags);
		assertThat(tagList).isNotEmpty();
	}
	
	@Test
	public void deleteTagTest() {
		String status = tagService.deleteTag("Horror");
		assertThat(status).isEqualTo("Success");
	}
	
	@Test
	public void deleteTagsTest() {
		List<String> tagNames = new ArrayList<>();
		tagNames.add("Horro");
		String status = tagService.deleteTags(tagNames);
		assertThat(status).isEqualTo("Success");
	}
	
	@Test
	public void getTagSetTest() {
		Set<Tag> tagSet = tagService.getTagSet("Horror,History".split(","));
		assertThat(tagSet.size()).isEqualTo(2);
	}
	
	
}
