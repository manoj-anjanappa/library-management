package com.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long>{
	
	@Query("from Book where title = :bookName")
	Book findBybookName(String bookName);
	
	@Query("from Book where title like %:key%")
	List<Book> findByKey(String key);

	@Query("from Book where isbn = :isbn")
	Book findBybookISBN(String isbn);

}
