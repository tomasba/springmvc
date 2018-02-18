package com.tb.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity {

	@Embedded
	private DataTrack dataTrack = new DataTrack();
	
	@PrePersist
	public void onCreate() {
		dataTrack.setCreated(Calendar.getInstance().getTime());
	}

	@PreUpdate
	public void onUpdate() {
		dataTrack.setUpdated(Calendar.getInstance().getTime());
	}	
	
	public DataTrack getDataTrack() {
		return dataTrack;
	}

	public void setDataTrack(DataTrack dataTrack) {
		this.dataTrack = dataTrack;
	}	
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(updatable = false)
//	// hibernate implementation:	
//	// @CreationTimestamp
//	private Date created;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(updatable = true)
//	// hibernate implementation:
//	// @UpdateTimestamp
//	private Date updated;

//	@PrePersist
//	public void onCreate() {
//		created = Calendar.getInstance().getTime();
//	}
//
//	@PreUpdate
//	public void onUpdate() {
//		updated = Calendar.getInstance().getTime();
//	}

//	public Date getCreated() {
//		return created;
//	}
//
//	public void setCreated(Date created) {
//		this.created = created;
//	}
//
//	public Date getUpdated() {
//		return updated;
//	}
//
//	public void setUpdated(Date updated) {
//		this.updated = updated;
//	}

}
