package com.tb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DataTrack {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	// commented-out as doesn't work on embeddable objects. Must be used within Entity or @MappedSuperclass
	//@CreationTimestamp
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true)
	// commented-out as doesn't work on embeddable objects. Must be used within Entity or @MappedSuperclass
	//@UpdateTimestamp
	private Date updated;

// commented-out as doesn't work on embeddable objects. Must be used within Entity or @MappedSuperclass
//	@PrePersist
//	public void onCreate() {
//		setCreated(Calendar.getInstance().getTime());
//	}
//
//	@PreUpdate
//	public void onUpdate() {
//		setUpdated(Calendar.getInstance().getTime());
//	}		
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
