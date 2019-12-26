package com.vk.backoffice.qr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rv_user")
public class RVUser {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="mobileNumber",unique = true)
    private String mobileNumber;
    
    @Column(name="rewardPoints")
    private String rewardPoints;
   
    @Column(name="rewardPointsEarnedTillDate")
    private String rewardPointsEarnedTillDate;
    
    @Column(name="rewardPointsRedeemedTillDate")
    private String rewardPointsRedeemedTillDate;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="latitude")
    private String latitude;
    
    @Column(name="longitude")
    private String longitude;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="address")
    private String address;
    
    @Column(name="state")
    private String state;
    
    @Column(name="city")
    private String city;
    
    @Column(name="role")
    private String role;
    
    @Column(name="referralCode")
    private String referralCode;

    @Column(name="active")
    private boolean isActive;;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public RVUser() {
    }

    public RVUser(String rewardPoints,String rewardPointsEarnedTillDate,String rewardPointsRedeemedTillDate ){
        this.rewardPoints = rewardPoints;
        this.rewardPointsEarnedTillDate= rewardPointsEarnedTillDate;
        this.rewardPointsRedeemedTillDate = rewardPointsRedeemedTillDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRewardPointsEarnedTillDate() {
		return rewardPointsEarnedTillDate;
	}

	public void setRewardPointsEarnedTillDate(String rewardPointsEarnedTillDate) {
		this.rewardPointsEarnedTillDate = rewardPointsEarnedTillDate;
	}

	public String getRewardPointsRedeemedTillDate() {
		return rewardPointsRedeemedTillDate;
	}

	public void setRewardPointsRedeemedTillDate(String rewardPointsRedeemedTillDate) {
		this.rewardPointsRedeemedTillDate = rewardPointsRedeemedTillDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RVUser [id=" + id + ", mobileNumber=" + mobileNumber + ", rewardPoints=" + rewardPoints
				+ ", rewardPointsEarnedTillDate=" + rewardPointsEarnedTillDate + ", rewardPointsRedeemedTillDate="
				+ rewardPointsRedeemedTillDate + ", firstName=" + firstName + ", latitude=" + latitude + ", longitude="
				+ longitude + ", lastName=" + lastName + ", address=" + address + ", state=" + state + ", city=" + city
				+ ", role=" + role + ", referralCode=" + referralCode + ", isActive=" + isActive + "]";
	}

	

	

	
}
