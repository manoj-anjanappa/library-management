package com.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "book_ledger")
public class Library {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ledger_id")
	private Long leadgerId;
	
	@Column(name = "book_serial_id")
	private Long bookSerialId;
	
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "user_id")
	private Long userId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "from_date")
	private Date fromDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "to_date")
	private Date toDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "return_date")
	private Date returnDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@OneToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@OneToOne
	@JoinColumn(name = "book_serial_id", insertable = false, updatable = false)
	private BookSerial bookSerial;
	
	public Long getLeadgerId() {
		return leadgerId;
	}

	public void setLeadgerId(Long leadgerId) {
		this.leadgerId = leadgerId;
	}


	public Long getBookSerialId() {
		return bookSerialId;
	}

	public void setBookSerialId(Long bookSerialId) {
		this.bookSerialId = bookSerialId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookSerial getBookSerial() {
		return bookSerial;
	}

	public void setBookSerial(BookSerial bookserial) {
		this.bookSerial = bookserial;
	}

}
