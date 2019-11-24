package com.vk.backoffice.qr.model;

import java.util.Date;

public class QrMaster {

    private Long id;

    private String productId;

    private String batchId;

    private String numberOfQrGenerated;

    private String points;

    private String activationStatus;

    private String creationDate;

    private String createdBy;

    private String modifiedDate;

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

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
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
		return "QrMaster [id=" + id + ", productId=" + productId + ", batchId=" + batchId + ", numberOfQrGenerated="
				+ numberOfQrGenerated + ", points=" + points + ", activationStatus=" + activationStatus
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + "]";
	}

   
}
