package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.LibraryDao;
import com.library.model.Library;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryDao libraryDao;
	
	@Autowired
	private BookService bookService;

	public List<Library> addLedgerEntry(List<Library> library) {
		library.stream().forEach(l -> bookService.reduceCount(l.getBookSerialId()));
		return libraryDao.saveAll(library);
	}

	public Library updateLedgerEntry(Library library) {
		if(library.getReturnDate() != null) {
			bookService.increaseCount(library.getBookId());
		}
		return libraryDao.save(library);
	}

	public List<Library> getList(Long userId) {
		return libraryDao.getList(userId);
	}

}
