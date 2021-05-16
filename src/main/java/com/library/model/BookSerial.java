package com.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "book_serial")
public class BookSerial {

	@Id
	@Column(name = "book_serial_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookSerailId;

	@Column(name = "book_serial_number")
	private Long bookSerailNumber;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@Column(name = "status")
	private int status;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;

	public Long getBookSerailId() {
		return bookSerailId;
	}

	public void setBookSerailId(Long bookSerailId) {
		this.bookSerailId = bookSerailId;
	}

	public Long getBookSerailNumber() {
		return bookSerailNumber;
	}

	public void setBookSerailNumber(Long bookSerailNumber) {
		this.bookSerailNumber = bookSerailNumber;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
