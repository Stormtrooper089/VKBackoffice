package com.vk.backoffice.qr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LastQrNumber")
public class LastQrNumber {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="lastNumberGenerated")
    private String lastNumberGenerated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastNumberGenerated() {
		return lastNumberGenerated;
	}

	public void setLastNumberGenerated(String lastNumberGenerated) {
		this.lastNumberGenerated = lastNumberGenerated;
	}

	@Override
	public String toString() {
		return "LastQrNumber [id=" + id + ", lastNumberGenerated=" + lastNumberGenerated + "]";
	}
    
}


