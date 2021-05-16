package com.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.library.controller.exception.BadRequestException;
import com.library.model.Book;
import com.library.service.BookService;

@SpringBootTest
public class BookControllerTest {

	@InjectMocks
	BookController bookController;
	
	@Mock
	BookService bookService;
	
	@BeforeEach
	public void before() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		b1.setBookId(1L);
		List<Book> books = new ArrayList<>();
		books.add(b1);
		when(bookService.addBooks(ArgumentMatchers.anyList())).thenReturn(books);
		when(bookService.addBook(ArgumentMatchers.any())).thenReturn(b1);
		when(bookService.getBooks()).thenReturn(books);
		when(bookService.getBookByName(ArgumentMatchers.anyString())).thenReturn(b1);
		when(bookService.getBookByISBN(ArgumentMatchers.anyString())).thenReturn(b1);
		when(bookService.searchBookByKey(ArgumentMatchers.anyString())).thenReturn(books);
		when(bookService.searchById(ArgumentMatchers.anyLong())).thenReturn(b1);
		when(bookService.update(ArgumentMatchers.any())).thenReturn(b1);
		when(bookService.deleteBook(ArgumentMatchers.anyLong())).thenReturn("Success");
	}
	
	@Test
	public void uploadBooksTest() throws IOException {
		MockMultipartFile csvfile = new MockMultipartFile("data", "book.csv", "text/plain", "title,isbn,author,tags\\r\\nHarry Potter,451231156441,JK Rowling,\"Horror,History\"".getBytes());
		List<Book> books = bookController.uploadBooks(csvfile);
		assertThat(books).isNotEmpty();
	}
	
	@Test
	public void addBookTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		Book book = bookController.addBook(b1);
		assertThat(book).isNotNull();
	}
	
	@Test
	public void getBooksTest() {
		List<Book> books = bookController.getBooks();
		assertThat(books).isNotEmpty();
	}
	
	@Test
	public void searchByBookNameTest() {
		Book book = bookController.searchByBookName("Harry Potter");
		assertThat(book).isNotNull();
	}
	
	@Test
	public void searchByISBMTest() {
		Book book = bookController.searchByISBM("451231156441");
		assertThat(book).isNotNull();
	}
	
	@Test
	public void searchByKeyTest() {
		List<Book> books = bookController.searchByKey("Harry");
		assertThat(books).isNotEmpty();
	}
	
	@Test
	public void searchByIdTest() {
		Book book = bookController.searchById(1l);
		assertThat(book).isNotNull();
	}
	
	@Test
	public void updateById() throws BadRequestException {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		b1.setBookId(1L);
		Book book = bookController.updateById(b1);
		assertThat(book).isNotNull();
	}
	@Test
	public void updateByIdNull() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		Exception e = assertThrows(BadRequestException.class, ()->{ bookController.updateById(b1);});
		assertThat(e.getMessage()).isEqualTo("bookid is Required");
	}
	
	@Test
	public void deleteBookTest() {
		String status = bookController.deleteBook(1L);
		assertThat(status).isEqualTo("Success");
	}
}
