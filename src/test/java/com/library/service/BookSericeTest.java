package com.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.dao.BookDao;
import com.library.model.Book;

@SpringBootTest
public class BookSericeTest {

	@InjectMocks
	BookService bookService;
	
	@Mock
	BookDao bookDao;
	
	@Mock
	TagService tagService;
	
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
		when(bookDao.saveAll(ArgumentMatchers.anyIterable())).thenReturn(books);
		when(bookDao.save(ArgumentMatchers.any())).thenReturn(b1);
		when(bookDao.findAll()).thenReturn(books);
		when(bookDao.findBybookName(ArgumentMatchers.anyString())).thenReturn(b1);
		when(bookDao.findBybookISBN(ArgumentMatchers.anyString())).thenReturn(b1);
		when(bookDao.findByKey(ArgumentMatchers.anyString())).thenReturn(books);
		when(bookDao.getOne(ArgumentMatchers.anyLong())).thenReturn(b1);
		doNothing().when(bookDao).deleteById(ArgumentMatchers.anyLong());
	}
	
	@Test
	public void addBooksTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		List<Book> books = new ArrayList<>();
		books.add(b1);
		List<Book> addedbooks = bookService.addBooks(books);
		assertThat(addedbooks).isNotEmpty();
	}
	
	@Test
	public void addBookTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		Book book = bookService.addBook(b1);
		assertThat(book).isNotNull();
	}
	
	@Test
	public void getBooksTest() {
		List<Book> books = bookService.getBooks();
		assertThat(books).isNotEmpty();
	}
	
	@Test
	public void getBookByNameTest() {
		Book book = bookService.getBookByName("Harry Potter");
		assertThat(book).isNotNull();

	}
	
	@Test
	public void getBookByISBNTest() {
		Book book = bookService.getBookByISBN("451231156441");
		assertThat(book).isNotNull();
	}
	
	@Test
	public void searchBookByKeyTest() {
		List<Book> books = bookService.searchBookByKey("Harry");
		assertThat(books).isNotNull();
	}
	
	@Test
	public void searchByIdTest() {
		Book book = bookService.searchById(1L);
		assertThat(book).isNotNull();
	}
	
	@Test
	public void updateTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		b1.setBookId(1L);
		Book book = bookService.update(b1);
		assertThat(book).isNotNull();
	}
	
	@Test
	public void deleteBookTest() {
		String status = bookService.deleteBook(1L);
		assertThat(status).isEqualTo("Success");
	}
	
	@Test
	public void reduceCountTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		b1.setBookId(1L);
		when(bookDao.getOne(ArgumentMatchers.anyLong())).thenReturn(b1);
		bookService.reduceCount(1L);
		assertThat(b1.getCount()).isEqualTo(1L);
	}
	
	@Test
	public void increaseCountTest() {
		Book b1 = new Book();
		b1.setAuthor("JK Rowling");
		b1.setTags("Horror,History");
		b1.setCount(2L);
		b1.setIsbn("451231156441");
		b1.setTitle("Harry Potter");
		b1.setBookId(1L);
		when(bookDao.getOne(ArgumentMatchers.anyLong())).thenReturn(b1);
		bookService.increaseCount(1L);
		assertThat(b1.getCount()).isEqualTo(3L);
	}
}
