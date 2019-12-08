package com.vk.backoffice.qr.entity;

import java.util.Date;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "qrgenerated")
public class QRGenerated {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "qrCode")
    private String qrCode;

    @Column(name = "value")
    private String value;

    @Column(name = "dateCreated")
    private Date dateCreated;

    @Column(name = "isRedeemed")
    private String isRedeemed;

    @Column(name = "isActivated")
    private String isActivated;

    @Column(name = "batchId")
    private String batchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIsRedeemed() {
        return isRedeemed;
    }

    public void setIsRedeemed(String isRedeemed) {
        this.isRedeemed = isRedeemed;
    }

    public String getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "QRGenerated [id=" + id + ", qrCode=" + qrCode + ", value=" + value + ", dateCreated=" + dateCreated
                + ", isRedeemed=" + isRedeemed + ", isActivated=" + isActivated + ", batchId=" + batchId + "]";
    }

}

