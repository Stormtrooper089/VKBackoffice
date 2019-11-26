package com.vk.backoffice.qr.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "rvRedemption")
public class RVRedemptionRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="redeemDate")
    private String redeemDate;
    
    @Column(name="redeemTime")
    private String redeemTime;
    

    
    @Column(name="mobileNumber")
    private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="redeemValue")
    private String redeemValue;
   
    @Column(name="redeemUser")
    private String redeemUser;
    
    @Column(name="redeemLatitude")
    private String redeemLatitude;
    
    @Column(name="redeemLongitude")
    private String redeemLongitude;

    @Column(name="redeemStatus")
    private String redeemStatus;
    
    public String getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(String redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRedeemDate() {
		return redeemDate;
	}

	public void setRedeemDate(String redeemDate) {
		this.redeemDate = redeemDate;
	}

	public String getRedeemValue() {
		return redeemValue;
	}

	public void setRedeemValue(String redeemValue) {
		this.redeemValue = redeemValue;
	}

	public String getRedeemUser() {
		return redeemUser;
	}



	public void setRedeemUser(String redeemUser) {
		this.redeemUser = redeemUser;
	}

	public String getRedeemLatitude() {
		return redeemLatitude;
	}

	public void setRedeemLatitude(String redeemLatitude) {
		this.redeemLatitude = redeemLatitude;
	}

	public String getRedeemLongitude() {
		return redeemLongitude;
	}

	public void setRedeemLongitude(String redeemLongitude) {
		this.redeemLongitude = redeemLongitude;
	}

	@Override
	public String toString() {
		return "RVRedemptionRequest [id=" + id + ", redeemDate=" + redeemDate + ", redeemTime=" + redeemTime
				+ ", mobileNumber=" + mobileNumber + ", redeemValue=" + redeemValue + ", redeemUser=" + redeemUser
				+ ", redeemLatitude=" + redeemLatitude + ", redeemLongitude=" + redeemLongitude + ", redeemStatus="
				+ redeemStatus + "]";
	}

}
