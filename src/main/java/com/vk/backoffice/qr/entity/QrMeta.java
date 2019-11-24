package com.vk.backoffice.qr.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "qrMeta")
public class QrMeta {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="productId")
    private String productId;
    
    @Column(name="batchId")
    private String batchId;
    
    @Column(name="numberOfQrGenerated")
    private int numberOfQrGenerated;
    
    @Column(name="points")
    private int points;
    
    @Column(name="activationStatus")
    private int activationStatus;
    
    @Column(name="creationDate")
    private Date creationDate;
    
    @Column(name="createdBy")
    private String createdBy;
    
    @Column(name="modifiedDate")
    private Date modifiedDate;
    
    @Column(name="modifiedBy")
    private String modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public int getNumberOfQrGenerated() {
		return numberOfQrGenerated;
	}

	public void setNumberOfQrGenerated(int numberOfQrGenerated) {
		this.numberOfQrGenerated = numberOfQrGenerated;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "QrMeta [id=" + id + ", productId=" + productId + ", batchId=" + batchId + ", numberOfQrGenerated="
				+ numberOfQrGenerated + ", points=" + points + ", activationStatus=" + activationStatus
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + "]";
	}
    
    
}
