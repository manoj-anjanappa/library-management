package com.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tags")
public class Tag implements Serializable {
	
	private static final long serialVersionUID = -7521706013533410751L;

	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagId;
	
	@Column(name="tag_name", unique = true)
	@NotBlank(message = "tagName is required")
	private String tagName;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_timestamp")
	private Date createTimeStamp;

	public Tag() {
		
	}
	public Tag(Long tagId, String tagName, Date creatTimeStamp) {
		this.createTimeStamp = creatTimeStamp;
		this.tagId = tagId;
		this.tagName = tagName;
	}
	
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

}
