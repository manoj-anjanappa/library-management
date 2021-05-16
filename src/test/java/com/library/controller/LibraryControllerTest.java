package com.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.library.controller.exception.BadRequestException;
import com.library.model.Book;
import com.library.model.Library;
import com.library.service.LibraryService;

@SpringBootTest
public class LibraryControllerTest {

	@InjectMocks
	LibraryController libraryController;
	
	@Mock
	LibraryService libraryService;
	
	@BeforeEach
	public void before() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter 1");
		b1.setBookId(1L);
		Book b2 = new Book();
		b2.setAuthor("JK Rowling");
		b2.setTags("Horror,History");
		b2.setCount(2L);
		b2.setIsbn("451231156441");
		b2.setTitle("Harry Potter 2");
		b2.setBookId(1L);
		Library library1 = new Library();
		library1.setBookSerialId(1L);
		library1.setUserId(2L);
		library1.setFromDate(new Date());
		library1.setCreateTimestamp(new Date());
		library1.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		Library library2 = new Library();
		library2.setBookSerialId(2L);
		library2.setUserId(2L);
		library2.setFromDate(new Date());
		library2.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		library1.setCreateTimestamp(new Date());
		List<Library> libraries = new ArrayList<>();
		libraries.add(library2);
		libraries.add(library1);
		when(libraryService.addLedgerEntry(ArgumentMatchers.anyList())).thenReturn(libraries);
		when(libraryService.getList(ArgumentMatchers.anyLong())).thenReturn(libraries);
		library1.setLeadgerId(1L);
		when(libraryService.updateLedgerEntry(ArgumentMatchers.any())).thenReturn(library1);
	}
	
	@Test
	public void issueBookTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter 1");
		b1.setBookId(1L);
		Book b2 = new Book();
		b2.setAuthor("JK Rowling");
		b2.setTags("Horror,History");
		b2.setCount(2L);
		b2.setIsbn("451231156441");
		b2.setTitle("Harry Potter 2");
		b2.setBookId(1L);
		Library library1 = new Library();
		library1.setBookSerialId(1L);
		library1.setUserId(1L);
		library1.setFromDate(new Date());
		library1.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		Library library2 = new Library();
		library2.setBookSerialId(2L);
		library2.setUserId(2L);
		library2.setFromDate(new Date());
		library1.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		List<Library> libraries = new ArrayList<>();
		libraries.add(library2);
		libraries.add(library1);
		List<Library> librariesReturned = libraryController.issueBook(libraries);
		assertThat(librariesReturned).isNotEmpty();
	}
	
	@Test
	public void getListTest() {
		List<Library> libraries = libraryController.getLibrariesListByUserId(1L);
		assertThat(libraries).isNotEmpty();
	}
	
	@Test
	public void updateLedgerEntryTest() throws BadRequestException {
		Library library1 = new Library();
		library1.setLeadgerId(1L);
		library1.setBookSerialId(1L);
		library1.setBookSerialId(2L);
		library1.setUserId(2L);
		library1.setFromDate(new Date());
		library1.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		Library library = libraryController.updateLedgerEntry(library1);
		assertThat(library).isNotNull();
	}
	
	@Test
	public void updateLedgerEntryTestError() throws BadRequestException {
		Library library1 = new Library();
		library1.setBookSerialId(1L);
		library1.setBookSerialId(2L);
		library1.setUserId(2L);
		library1.setFromDate(new Date());
		library1.setToDate(new Date(new Date().getTime()+ 2*24*3600*1000));
		Exception e = assertThrows(BadRequestException.class, ()->{ libraryController.updateLedgerEntry(library1);});
		assertThat(e.getMessage()).isEqualTo("Ledgerid is Required");
	}
}
