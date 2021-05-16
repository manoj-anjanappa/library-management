package com.library.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.controller.exception.BadRequestException;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.CsvReaderUtil;

@RestController
public class BookController {

	@Autowired
	private BookService libraryService;
	
	@PostMapping("/books/upload")
	public List<Book> uploadBooks(@RequestParam("file") MultipartFile file) throws IOException{
		return libraryService.addBooks(CsvReaderUtil.read(Book.class, file.getInputStream()));
	}
	
	@PostMapping("/books/add")
	public Book addBook(@Valid @RequestBody Book book) {
		return libraryService.addBook(book);
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return libraryService.getBooks();
	}
	
	@GetMapping("/books/name")
	public Book searchByBookName(@RequestParam("bookName") String bookName) {
		return libraryService.getBookByName(bookName);
	}
	
	@GetMapping("/books/isbn")
	public Book searchByISBM(@RequestParam("isbn") String isbn) {
		return libraryService.getBookByISBN(isbn);
	}
	
	@GetMapping("/books/search")
	public List<Book> searchByKey(@RequestParam("key") String key){
		return libraryService.searchBookByKey(key);
	}
	
	@GetMapping("/books/id")
	public Book searchById(@RequestParam("bookId") Long id) {
		return libraryService.searchById(id);		
	}
	
	@PutMapping("/book/id")
	public Book updateById(@Valid @RequestBody Book book) throws BadRequestException {
		if(book.getBookId() == null || book.getBookId().equals(0L)) {
			throw new BadRequestException("bookid is Required");
		}
		book = libraryService.update(book);
		return book;
	}
	
	@DeleteMapping("/book/id/{bookId}")
	public String deleteBook(@RequestParam("bookId") Long bookId) {
		return libraryService.deleteBook(bookId);
	}
}
