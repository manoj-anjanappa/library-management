package com.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.dao.LibraryDao;
import com.library.model.Library;

@SpringBootTest
public class LibraryServiceTest {

	@InjectMocks
	LibraryService libraryService;
	
	@Mock
	LibraryDao libraryDao;
	
	@Mock
	BookService bookService;
	
	@BeforeEach
	public void before() {
		Library library = new Library();
		library.setLeadgerId(1L);
		library.setBookId(1L);
		library.setBookSerialId(1L);
		library.setCreateTimestamp(new Date());
		library.setFromDate(new Date());
		library.setToDate(new Date(new Date().getTime() + 1000*24*60*60));
		library.setReturnDate(null);
		library.setUserId(1L);
		List<Library> libraries = new ArrayList<>();
		libraries.add(library);
		when(libraryDao.saveAll(ArgumentMatchers.anyIterable())).thenReturn(libraries);
		doNothing().when(bookService).reduceCount(ArgumentMatchers.anyLong());
		library.setReturnDate(new Date(new Date().getTime() + 1000*24*60*60));
		when(libraryDao.save(ArgumentMatchers.any())).thenReturn(library);
		when(libraryService.getList(ArgumentMatchers.anyLong())).thenReturn(libraries);
	}
	
	@Test
	public void addLedgerEntryTest() {
		Library library = new Library();
		library.setBookId(1L);
		library.setBookSerialId(1L);
		library.setFromDate(new Date());
		library.setToDate(new Date(new Date().getTime() + 1000*24*60*60));
		library.setReturnDate(null);
		library.setUserId(1L);
		List<Library> libraries = new ArrayList<>();
		libraries.add(library);
		List<Library> library_entries = libraryService.addLedgerEntry(libraries);
		assertThat(library_entries).isNotEmpty();
	}
	
	@Test
	public void updateLedgerEntryTest() {
		Library library = new Library();
		library.setLeadgerId(1L);
		library.setBookId(1L);
		library.setBookSerialId(1L);
		library.setCreateTimestamp(new Date());
		library.setFromDate(new Date());
		library.setToDate(new Date(new Date().getTime() + 1000*24*60*60));
		library.setReturnDate(new Date(new Date().getTime() + 1000*24*60*60));
		library.setUserId(1L);
		Library library_entry = libraryService.updateLedgerEntry(library);
		assertThat(library_entry).isNotNull();
	}
	
	@Test
	public void getListTest() {
		List<Library> libraries = libraryService.getList(1L);
		assertThat(libraries).isNotEmpty();
	}
}
