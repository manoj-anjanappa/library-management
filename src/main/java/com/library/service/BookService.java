package com.library.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.BookDao;
import com.library.model.Book;
import com.library.model.BookSerial;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private TagService tagService;
	
	
	public List<Book> addBooks(List<Book> books){
		books.stream().forEach(b->b.setBook_tags(tagService.getTagSet(b.getTags().split(","))));
		books.stream().forEach(b->b.setBookSerial(getBookSerials(b)));
		return bookDao.saveAll(books);
	}
	
	@Transactional
	private Set<BookSerial> getBookSerials(Book b) {
		Set<BookSerial> book_serial= null;
		if(b.getCount() > 0) {
			book_serial = new HashSet<>();
			for(int i=1; i <= b.getCount(); i++) {
				BookSerial bookSerial = new BookSerial();
				bookSerial.setBookSerailNumber(Long.valueOf(i));
				bookSerial.setBook(b);
				book_serial.add(bookSerial);
			}
		}
		return book_serial;
	}
	
	public Book addBook(Book book){
		return bookDao.save(book);
	}

	public List<Book> getBooks() {
		return bookDao.findAll();
	}

	public Book getBookByName(String bookName) {
		return bookDao.findBybookName(bookName);
	}
	
	public Book getBookByISBN(String isbn) {
		return bookDao.findBybookISBN(isbn);
	}

	public List<Book> searchBookByKey(String key) {
		return bookDao.findByKey(key);
	}

	public Book searchById(Long id) {
		return bookDao.findById(id).orElse(null);
	}

	public Book update(Book book) {
		return bookDao.save(book);
	}

	public String deleteBook(Long bookId) {
		bookDao.deleteById(bookId);
		return "Success";
	}

	public void reduceCount(Long bookId) {
		Book book= bookDao.getOne(bookId);
		book.setCount(book.getCount() - 1);
	}

	public void increaseCount(Long bookId) {
		Book book= bookDao.getOne(bookId);
		book.setCount(book.getCount() + 1);
	}
}
