package com.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.library.controller.exception.BadRequestException;
import com.library.model.Tag;
import com.library.service.TagService;

@SpringBootTest
public class TagControllerTest {

	@InjectMocks 
	private TagController tagController;
	
	@Mock
	private TagService tagService;
	
	private List<Tag> tags;
	
	
	@BeforeEach
	public void before() {
		tags = new ArrayList<>();
		tags.add(new Tag(1L, "Horror", new Date()));
		tags.add(new Tag(2l, "Thriller", new Date()));
		tags.add(new Tag(3l, "Science", new Date()));
		tags.add(new Tag(4l, "History", new Date()));
		when(tagService.getTags()).thenReturn(tags);
		when(tagService.insertTags(ArgumentMatchers.anyList())).thenReturn(tags);
		when(tagService.deleteTags(ArgumentMatchers.anyList())).thenReturn("Success");
		when(tagService.deleteTag(ArgumentMatchers.anyString())).thenReturn("Success");
	}
	
	@Test
	public void getTagsTest() {
		List<Tag> tags = tagController.getTags();
		assertThat(tags).isNotEmpty();
	}
	
	@Test
	public void uploadTagTest() throws IOException {
		MockMultipartFile csvfile = new MockMultipartFile("data", "tags.csv", "text/plain", "tagName\r\nHorrible\r\nworst\r\nblacklist".getBytes());
		List<Tag> tags = tagController.uploadTags(csvfile);
		assertThat(tags).isNotEmpty();
	}
	
	@Test
	public void insertTagsTest() {
		List<Tag> tag = tagController.addTags(tags);
		assertThat(tag).isNotEmpty();
	}
	
	@Test
	public void deleteTags() throws BadRequestException {
		List<String> tagNames = new ArrayList<>();
		tagNames.add("Horror");
		String status = tagController.deleteTags(tagNames);
		assertThat(status).isNotBlank();
		assertThat(status).contains("Success");
	}
	
	@Test
	public void deleteNullTags() {
		List<String> tagNames = new ArrayList<>();
		Exception e = assertThrows(BadRequestException.class, ()->{ tagController.deleteTags(tagNames);});
		assertThat(e.getMessage()).isEqualTo("TagNames Required");
	}
	
	@Test
	public void deleteTag() throws BadRequestException {
		String status = tagController.deleteTag("Horror");
		assertThat(status).isNotBlank();
		assertThat(status).contains("Success");
	}
	
	@Test
	public void deleteNullTag() {
		Exception e = assertThrows(BadRequestException.class, ()->{ tagController.deleteTag(null);});
		assertThat(e.getMessage()).isEqualTo("Tag Name Required");
	}
	
}
