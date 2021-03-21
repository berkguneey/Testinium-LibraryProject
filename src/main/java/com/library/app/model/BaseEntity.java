package com.library.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date insertDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updateDate;
	
	@PrePersist
	public void prePersist() {
		insertDate = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		updateDate = new Date();
	}

	
}
