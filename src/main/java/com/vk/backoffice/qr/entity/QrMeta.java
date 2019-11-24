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
    private String numberOfQrGenerated;
    
    @Column(name="points")
    private String points;
    
    @Column(name="activationStatus")
    private int activationStatus;
    
    @Column(name="creationDate")
    private String creationDate;
    
    @Column(name="createdBy")
    private String createdBy;
    
    @Column(name="modifiedDate")
    private String modifiedDate;
    
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

	public String getNumberOfQrGenerated() {
		return numberOfQrGenerated;
	}

	public void setNumberOfQrGenerated(String numberOfQrGenerated) {
		this.numberOfQrGenerated = numberOfQrGenerated;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public int getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
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
