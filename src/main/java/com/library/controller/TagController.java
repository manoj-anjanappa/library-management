package com.library.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.controller.exception.BadRequestException;
import com.library.model.Tag;
import com.library.service.TagService;
import com.library.util.CsvReaderUtil;

@RestController
public class TagController {
	
	@Autowired private TagService tagService; 
	

	@PostMapping("/tags/upload")
	public List<Tag> uploadTags(@RequestParam("file") MultipartFile file) throws IOException {
		return tagService.insertTags(CsvReaderUtil.read(Tag.class, file.getInputStream()));
	}
	
	@PostMapping("/tags/add")
	public List<Tag> addTags(@Valid @RequestBody List<Tag> tags){
		return tagService.insertTags(tags);
	}
	
	@GetMapping("/tags")
	public List<Tag> getTags() {
		return tagService.getTags();
	}
	
	@DeleteMapping("/tag/{tagName}")
	public String deleteTag(@PathVariable("tagName") String tagName) throws BadRequestException {
		if(tagName == null || StringUtils.isEmpty(tagName)) {
			throw new BadRequestException("Tag Name Required");
		}
		return tagService.deleteTag(tagName);
	}
	
	@DeleteMapping("/tags")
	public String deleteTags(@RequestBody List<String> tagNames) throws BadRequestException {
		if(tagNames.size() == 0) {
			throw new BadRequestException("TagNames Required");
		}
		return tagService.deleteTags(tagNames);
	}

}
