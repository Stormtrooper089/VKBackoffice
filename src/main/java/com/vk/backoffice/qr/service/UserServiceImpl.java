package com.vk.backoffice.qr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.backoffice.qr.entity.RVUser;
import com.vk.backoffice.qr.entity.RVUserKyc;
import com.vk.backoffice.qr.repository.KYCRepository;
import com.vk.backoffice.qr.repository.UserRepository;
import com.vk.backoffice.qr.util.RequestStatusResponse;
import com.vk.backoffice.qr.util.VankonConstant;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private KYCRepository kycRepository;
	
	public List<RVUser> getAllUsers() {
		
		List<RVUser> rvUserList = userRepository.findAll();
		return rvUserList;
	}

	public RequestStatusResponse updateUser(RVUser rvUser) {
		// TODO Auto-generated method stub
		
		RVUser userAfterSave = userRepository.save(rvUser);
		RequestStatusResponse requestStatusResponse = new RequestStatusResponse();
		requestStatusResponse.setResponseStatus(VankonConstant.FAILURE);
		requestStatusResponse.setResponseStatusDescription("Unable to update the user");
		
		if(userAfterSave != null) {
			requestStatusResponse.setResponseStatus(VankonConstant.SUCCESS);
			requestStatusResponse.setResponseStatusDescription("Successfully updated the user");
		}
		return requestStatusResponse;
	}

	public Optional<RVUserKyc> getUserKyc(String mobileNumber) {
		// TODO Auto-generated method stub
		Optional<RVUserKyc> rvUserKyc = kycRepository.findByMobileNumber(mobileNumber);
		if(rvUserKyc.isPresent()) {
			return rvUserKyc;
		}
		
		return Optional.empty();
	}

}
