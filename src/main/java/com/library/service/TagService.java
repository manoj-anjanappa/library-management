package com.library.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.TagDao;
import com.library.model.Tag;

@Service
@Transactional
public class TagService {

	@Autowired
	private TagDao tagDao;
	
	public List<Tag> getTags() {
		return tagDao.findAll();
	}
	
	public Map<String, Tag> getTagsMap() {
		return tagDao.findAllMap();
	}

	public List<Tag> insertTags(List<Tag> tags) {
		return tagDao.saveAll(tags);
	}

	public String deleteTag(String tagName) {
		tagDao.deleteByTagName(tagName);
		return "Success";
	}

	public String deleteTags(List<String> tagNames) {
		tagDao.deleteByTagNames(tagNames);
		return "Success";
	}
	
	public Set<Tag> getTagSet(String[] tags){
		Map<String, Tag> tagMap = getTagsMap();
		return Arrays.stream(tags).map(s-> getTagObject(s, tagMap)).collect(Collectors.toSet());
		
	}
	
	private Tag getTagObject(String tagName, Map<String, Tag> tagMap) {
		Tag tag = tagMap.get(tagName);
		if(tag == null) {
			tag = new Tag(null, tagName, null);
		}
		return tag;
	}
}
